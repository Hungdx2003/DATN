package client.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.RoleObject;
import client.objects.UserObject;
import client.role.roleModel;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
        
		String email = request.getParameter("loginEmail");
	    String password = request.getParameter("loginPassword");
	    
	    userModel u=new userModel();
	    UserObject logUser=u.getUserObject(email, password);
	    HttpSession session = request.getSession();
	    if(logUser!=null) {
	    	int roleId=logUser.getUser_roles();
		    
	    	session.setAttribute("logUser", logUser);
		    session.setAttribute("userId", logUser.getUser_id());
		    
			roleModel r=new roleModel();
			RoleObject rl=r.getRoleObject(roleId);
			if (rl.getRole_name().equalsIgnoreCase("Khách hàng")) {
				session.setAttribute("notification_message", "Đăng nhập thành công");
	            session.setAttribute("notification_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=home");
			}else {
				session.setAttribute("notification_message", "Đăng nhập không thành công");
		        session.setAttribute("notification_type", "danger");
		        response.sendRedirect(request.getContextPath() + "/page?view=login");
			}
	    }else {
	    	session.setAttribute("notification_message", "Email hoặc mật khẩu không đúng");
	        session.setAttribute("notification_type", "warning");
	        response.sendRedirect(request.getContextPath() + "/page?view=login");
	    }
	}

}
