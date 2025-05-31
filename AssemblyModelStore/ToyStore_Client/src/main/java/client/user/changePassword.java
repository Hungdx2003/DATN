package client.user;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.UserObject;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/change-password")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        HttpSession session=request.getSession();
        UserObject user=(UserObject)session.getAttribute("logUser");
        if (user!=null) {
			String new_pass=request.getParameter("new_pass");
			String old_pass=request.getParameter("old_pass");
			
			if (new_pass==null||new_pass.trim().isEmpty()||old_pass==null||old_pass.trim().isEmpty()) {
				session.setAttribute("notification_message", "Mật khẩu mới hoặc cũ chưa được điền");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=password");
	            return;
			}
			
			if (!md5(old_pass).equals(user.getUser_pass())) {
				session.setAttribute("notification_message", "Mật khẩu cũ không đúng");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=password");
	            return;
			}
			
			UserObject uo=new UserObject();
			uo.setUser_pass(new_pass);
			uo.setUser_id(user.getUser_id());
			
			userModel um=new userModel();
			boolean change=um.changePassword(uo);
			if (change) {
				session.setAttribute("notification_message", "Đổi mật khẩu thành công");
	            session.setAttribute("notification_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=password");
			} else {
				session.setAttribute("notification_message", "Đổi mật khẩu không thành công");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=password");
			}
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới sử dụng được chức năng này");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
	}
	
	public String md5(String input) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Cập nhật chuỗi đầu vào
            byte[] messageDigest = md.digest(input.getBytes("UTF-8"));
            
            // Chuyển byte thành hex giống MySQL
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
