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
 * Servlet implementation class addUser
 */
@WebServlet("/api/user/add")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
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
			user u=new userImpl();
			String username=request.getParameter("username");
			String pass=request.getParameter("password");
			String email=request.getParameter("email");
			String fname=request.getParameter("fullname");
			String birthday=request.getParameter("birthday");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("mobilephone");
			String address=request.getParameter("address");
			String role=request.getParameter("role");
			
			UserObject uo=new UserObject();
			uo.setUser_name(username);
			uo.setUser_pass(pass); 
			uo.setUser_email(email);
			uo.setUser_birthday(birthday);
			uo.setUser_mobilephone(phone);
			uo.setUser_address(address);
			uo.setUser_fullname(fname);
			uo.setUser_gender(gender);
			uo.setUser_roles(Integer.parseInt(role));
			
			boolean addResult=u.addUser(uo);
	        if (addResult) {
	        	session.setAttribute("flash_message", "Thêm người dùng thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=user");
	        } else {
	        	session.setAttribute("flash_message", "Thêm người dùng không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=user");
	        }
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=user");
		}
	}

}
