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
 * Servlet implementation class addRole
 */
@WebServlet("/api/role/add")
public class addRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addRole() {
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
	    	String roleName=request.getParameter("role_name");
	 	    String total=request.getParameter("total_permissions");
	 	    
	 	    if(roleName==null || total==null) {
	 	    	session.setAttribute("flash_message", "Thêm vai trò không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=role");
	            return;
	 	    }
	 	    
	 	    int total_permissions=Integer.parseInt(total);
	 	    RoleObject role=new RoleObject();
	 	    role.setRole_name(roleName);
	 	    
	 	    roleModel rm=new roleModel();
			permissionModel pm=new permissionModel();
	 	    boolean addRole=rm.addRole(role);
	 	    if (addRole) {
				RoleObject rl=rm.getRole(roleName);
				int roleId=rl.getRole_id();
				
				for (int i = 0; i < total_permissions; i++) {
					String objectName=request.getParameter("object_name_"+i);
					String displayName=request.getParameter("object_display_name_"+i);
					if (objectName==null||displayName==null) {
						session.setAttribute("flash_message", "Thêm quyền không thành công");
			            session.setAttribute("flash_type", "danger");
			            response.sendRedirect(request.getContextPath() + "/page?view=role");
			            return;
					}
					
					boolean view = request.getParameter("view_"+i)!=null;
					boolean add = request.getParameter("add_"+i)!=null;
					boolean edit = request.getParameter("edit_"+i)!=null;
					boolean del = request.getParameter("del_"+i)!=null;
					
					if (!view && !add && !edit && !del) continue;
					
					PermissionObject po=new PermissionObject();
					po.setRole_id(roleId);
					po.setObject_name(objectName);
					po.setObject_display_name(displayName);
					po.setCan_add(add);
					po.setCan_edit(edit);
					po.setCan_delete(del);
					po.setCan_view(view);
					
					boolean addPer=pm.addPermission(po);
					if (!addPer) {
						session.setAttribute("flash_message", "Thêm quyền hạn không thành công");
			            session.setAttribute("flash_type", "danger");
			            response.sendRedirect(request.getContextPath() + "/page?view=role");
			            return;
					}
				}
				session.setAttribute("flash_message", "Thêm vai trò thành công");
	            session.setAttribute("flash_type", "success");
				response.sendRedirect(request.getContextPath() + "/page?view=role");
			} else {
				session.setAttribute("flash_message", "Thêm vai trò không thành công");
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
