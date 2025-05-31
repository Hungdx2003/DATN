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
 * Servlet implementation class modifiedUser
 */
@WebServlet("/modify-user")
public class modifiedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifiedUser() {
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
		int userId= (int) session.getAttribute("userId");
		
		String fullname=request.getParameter("fullname");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String birthday=request.getParameter("birthday");
		
		String address=request.getParameter("address");
		String province=request.getParameter("province");
		String district=request.getParameter("district");
		String ward=request.getParameter("ward");
		String fulladdress=address+", "+ward+", "+district+", "+province;
		
		String gender=request.getParameter("gender");
		
		UserObject uo=new UserObject();
		uo.setUser_fullname(fullname);
		uo.setUser_email(email);
		uo.setUser_mobilephone(phone);
		uo.setUser_address(fulladdress);
		uo.setUser_gender(gender);
		uo.setUser_id(userId);
		uo.setUser_birthday(birthday);
		
		userModel um=new userModel();
		boolean editResult=um.editUser(uo);
		if (editResult) {
			UserObject u=um.getUserObject(userId);
			session.removeAttribute("logUser");
		    session.setAttribute("logUser", u);
		    
			session.setAttribute("notification_message", "Sửa thông tin thành công");
            session.setAttribute("notification_type", "success");
            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=info");
		} else {
			session.setAttribute("notification_message", "Sửa thông tin không thành công");
            session.setAttribute("notification_type", "danger");
            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=info");
		}
	}

}
