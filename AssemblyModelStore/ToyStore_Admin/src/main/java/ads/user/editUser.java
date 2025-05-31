package ads.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.PermissionObject;
import ads.objects.UserObject;

/**
 * Servlet implementation class editUser
 */
@WebServlet("/api/user/edit")
public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editUser() {
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
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PermissionObject permissionsUser = (PermissionObject) session.getAttribute("userPer");
		
		if (permissionsUser!=null && permissionsUser.isCan_add()) {
			userModel u=new userModel();
			String pass=request.getParameter("editPassword");
			String email=request.getParameter("editEmail");
			String fname=request.getParameter("editFullname");
			String birthday=request.getParameter("editBirthday");
			String gender=request.getParameter("editGender");
			String phone=request.getParameter("editPhone");
			String address=request.getParameter("editAddress");
			String role=request.getParameter("editRole");
			String id=request.getParameter("user_id");
			
			UserObject uo=new UserObject();
			uo.setUser_pass(pass); 
			uo.setUser_email(email);
			uo.setUser_birthday(birthday);
			uo.setUser_mobilephone(phone);
			uo.setUser_address(address);
			uo.setUser_fullname(fname);
			uo.setUser_gender(gender);
			uo.setUser_roles(Integer.parseInt(role));
			uo.setUser_id(Integer.parseInt(id));
			
			boolean editResult=u.editUser(uo);
	        if (editResult) {
	        	session.setAttribute("flash_message", "Sửa thông tin người dùng thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=user");
	        } else {
	        	session.setAttribute("flash_message", "Sửa thông tin người dùng không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=user");
	        }
		}else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=user");
		}
	}

}
