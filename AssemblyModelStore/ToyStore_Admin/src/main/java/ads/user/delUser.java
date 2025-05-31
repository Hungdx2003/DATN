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
 * Servlet implementation class delUser
 */
@WebServlet("/api/user/del")
public class delUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delUser() {
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
		HttpSession session = request.getSession();
		PermissionObject permissionsUser = (PermissionObject) session.getAttribute("userPer");
		
		if (permissionsUser!=null && permissionsUser.isCan_delete()) {
			String id=request.getParameter("user_id_del");
			if(id!=null && !id.equalsIgnoreCase("")) {
				UserObject uo=new UserObject();
				uo.setUser_id(Integer.parseInt(id));
				userModel u=new userModel();
				boolean delUser=u.delUser(uo);
				response.setContentType("text/html; charset=UTF-8");
				if (delUser) {
					session.setAttribute("flash_message", "Xóa người dùng thành công");
		            session.setAttribute("flash_type", "success");
		            response.sendRedirect(request.getContextPath() + "/page?view=user");
				} else {
					session.setAttribute("flash_message", "Xóa người dùng thành công");
		            session.setAttribute("flash_type", "danger");
		            response.sendRedirect(request.getContextPath() + "/page?view=user");
				} 
			}
		}else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=user");
		}
		
	}

}
