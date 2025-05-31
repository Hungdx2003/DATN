package client.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.UserObject;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
        userModel um=new userModel();
        
        String name=request.getParameter("name");
        String surname=request.getParameter("surname");
        String fullname=surname+" "+name;

        String email=request.getParameter("registerEmail");
        String phone=request.getParameter("phone");
        String pass=request.getParameter("registerPassword");
        
        String username="";
        String fullName = fullname.trim().toLowerCase();
        String[] parts = fullName.split("\\s+");
        
        if (parts.length == 1) {
        	username= parts[0];
        }

        String lastName = parts[parts.length - 1];

        StringBuilder initials = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            initials.append(parts[i].charAt(0));
        }
        username=lastName+ initials.toString();
        
        String originalUsername = username;
        int suffix = 1;

        while (um.isUsernameExists(username)) {
            username = originalUsername + suffix;
            suffix++;
        }
        
        if (um.isEmailExists(email)) {
        	session.setAttribute("notification_message", "Email đã được sử dụng");
	        session.setAttribute("notification_type", "warning");
	        response.sendRedirect(request.getContextPath() + "/page?view=login");
	        return;
		}
        
        UserObject uo=new UserObject();
        uo.setUser_fullname(fullname);
        uo.setUser_name(username);
        uo.setUser_email(email);
        uo.setUser_mobilephone(phone);
        uo.setUser_pass(pass);
        uo.setUser_roles(3);
        
        boolean addResult=um.addUser(uo);
        if (addResult) {
        	session.setAttribute("notification_message", "Đăng ký thành công");
            session.setAttribute("notification_type", "success");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		} else {
			session.setAttribute("notification_message", "Đăng ký không thành công");
            session.setAttribute("notification_type", "danger");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
	}

}
