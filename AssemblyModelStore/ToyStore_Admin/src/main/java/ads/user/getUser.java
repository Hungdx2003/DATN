package ads.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.objects.UserObject;

import org.json.JSONObject;

/**
 * Servlet implementation class getUser
 */
@WebServlet("/api/getUser")
public class getUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int userId = Integer.parseInt(request.getParameter("user_id"));
        userModel u = new userModel();
        UserObject uo = u.getUserObject(userId);
        

        JSONObject userDetails = new JSONObject();
        userDetails.put("username", uo.getUser_name());
        userDetails.put("password", uo.getUser_pass());
        userDetails.put("fullname", uo.getUser_fullname());
        userDetails.put("email", uo.getUser_email());
        userDetails.put("gender", uo.getUser_gender());
        userDetails.put("role", uo.getUser_roles());
        userDetails.put("birthday", uo.getUser_birthday());
        userDetails.put("phone", uo.getUser_mobilephone());
        userDetails.put("create_date", uo.getUser_created_date());
        userDetails.put("last_modified", uo.getUser_last_modified());
        userDetails.put("address", uo.getUser_address());
        userDetails.put("userId", userId);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(userDetails.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
