package ads.role;

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

/**
 * Servlet implementation class delRole
 */
@WebServlet("/api/role/del")
public class delRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delRole() {
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
	    HttpSession session =request.getSession();
	    UserObject user=(UserObject)session.getAttribute("logUser");
	    
	    if (user.getUser_roles()==1) {
	 	    String id=request.getParameter("role_id_del");
	 	    
	 	    if(id==null || id.trim().isEmpty() || id.equals("0")) {
	 	    	session.setAttribute("flash_message", "ID nhập vào không hợp lệ.");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=role");
	            return;
	 	    }
	 	    int roleId=Integer.parseInt(id);
	 	    PermissionObject po=new PermissionObject();
	    	po.setRole_id(roleId);

	 	    
	 	    roleModel rm=new roleModel();
	 	    permissionModel pm=new permissionModel();
	 	    boolean delPer=pm.delPermission(po);
	 	    if (delPer) {
	 	    	RoleObject role=new RoleObject();
		 	    role.setRole_id(roleId);
	 	    	
		 	   boolean delRole=rm.delRole(role);
				if (delRole) {
					session.setAttribute("flash_message", "Xóa vai trò thành công");
		            session.setAttribute("flash_type", "success");
					response.sendRedirect(request.getContextPath() + "/page?view=role");
				} else {
					session.setAttribute("flash_message", "Xóa vai trò không thành công");
		            session.setAttribute("flash_type", "danger");
					response.sendRedirect(request.getContextPath() + "/page?view=role");
				}
			} else {
				session.setAttribute("flash_message", "Xóa quyền không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=role");
			}
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=home");
		}
	}

}
