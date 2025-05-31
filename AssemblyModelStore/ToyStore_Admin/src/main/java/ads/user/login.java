package ads.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.PermissionObject;
import ads.objects.RoleObject;
import ads.objects.UserObject;
import ads.permission.permissionModel;
import ads.role.roleModel;

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
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    userModel u=new userModel();
	    UserObject logUser=u.getUserObject(username, password);
	    HttpSession session = request.getSession();
	    if(logUser!=null) {
	    	int roleId=logUser.getUser_roles();
		    permissionModel p=new permissionModel();
		    PermissionObject permissionsProduct = p.getPermission(roleId, "product");
		    PermissionObject permissionsUser = p.getPermission(roleId, "user");
		    PermissionObject permissionsDiscount = p.getPermission(roleId, "discount");
		    PermissionObject permissionsOrder = p.getPermission(roleId, "order");
		    PermissionObject permissionsCategory = p.getPermission(roleId, "category");
		    PermissionObject permissionsNews = p.getPermission(roleId, "news");
		    PermissionObject permissionsInterface = p.getPermission(roleId, "interface");
		    
		    session.setAttribute("userId", logUser.getUser_id());
	    	session.setAttribute("logUser", logUser);
		    session.setAttribute("productPer", permissionsProduct);
		    session.setAttribute("userPer", permissionsUser);
		    session.setAttribute("discountPer", permissionsDiscount);
		    session.setAttribute("orderPer", permissionsOrder);
		    session.setAttribute("categoryPer", permissionsCategory);
		    session.setAttribute("newsPer", permissionsNews);
		    session.setAttribute("interfacePer", permissionsInterface);
		    
			roleModel r=new roleModel();
			RoleObject rl=r.getRoleObject(roleId);
			session.setAttribute("role", rl);
			if (rl.getRole_name().equalsIgnoreCase("Quản trị viên")||rl.getRole_name().equalsIgnoreCase("Nhân viên")) {
				session.setAttribute("notification_message", "Đăng nhập thành công");
	            session.setAttribute("notification_type", "success");
	            response.sendRedirect(request.getContextPath() + "/layout.jsp");
			}else {
				session.setAttribute("notification_message", "Đăng nhập không thành công");
		        session.setAttribute("notification_type", "danger");
		        response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
	    }else {
	    	session.setAttribute("notification_message", "Đăng nhập không thành công");
	        session.setAttribute("notification_type", "warning");
	        response.sendRedirect(request.getContextPath() + "/login.jsp");
	    }
	}

}
