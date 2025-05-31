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
 * Servlet implementation class editRole
 */
@WebServlet("/api/role/edit")
public class editRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editRole() {
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
	    	String roleName=request.getParameter("edit_role_name");
	 	    String total=request.getParameter("edit_total_permissions");
	 	    String id=request.getParameter("role_id");
	 	    
	 	    if(roleName==null || total==null||id==null) {
	 	    	session.setAttribute("flash_message", "Trường nhập vào không hợp lệ");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=role");
	            return;
	 	    }
	 	    int roleId=Integer.parseInt(id);
	 	    int total_permissions=Integer.parseInt(total);
	 	    RoleObject role=new RoleObject();
	 	    role.setRole_name(roleName);
	 	    role.setRole_id(roleId);
	 	    
	 	    roleModel rm=new roleModel();
	 	    permissionModel pm=new permissionModel();
	 	    boolean editRole=rm.editRole(role);
	 	    if (editRole) {
				
				for (int i = 0; i < total_permissions; i++) {
					String objectName=request.getParameter("edit_object_name_"+i);
					String displayName=request.getParameter("edit_object_display_name_"+i);
					String permissionIdStr = request.getParameter("edit_permission_id_" + i);
					
					if (objectName == null || objectName.trim().isEmpty() || displayName == null || displayName.trim().isEmpty()) {
						session.setAttribute("flash_message", "Tên đối tượng và tên hiển thị không hợp lệ");
			            session.setAttribute("flash_type", "danger");
			            response.sendRedirect(request.getContextPath() + "/page?view=role");
			            return;
					}
					boolean view = request.getParameter("edit_view_"+i)!=null;
					boolean add = request.getParameter("edit_add_"+i)!=null;
					boolean edit = request.getParameter("edit_edit_"+i)!=null;
					boolean del = request.getParameter("edit_del_"+i)!=null;
					
					if (!view && !add && !edit && !del) {
					    if (permissionIdStr != null && !permissionIdStr.trim().isEmpty() && !permissionIdStr.equals("0")) {
					        int perId = Integer.parseInt(permissionIdStr);
					        PermissionObject per=new PermissionObject();
					        per.setPermission_id(perId);
					        boolean deleted = pm.delPermission(per);
					        if (!deleted) {
					            session.setAttribute("flash_message", "Xóa quyền không thành công");
					            session.setAttribute("flash_type", "danger");
					            response.sendRedirect(request.getContextPath() + "/page?view=role");
					            return;
					        }
					    }
					    continue;
					}
					
					PermissionObject po=new PermissionObject();
					po.setRole_id(roleId);
					po.setObject_name(objectName);
					po.setObject_display_name(displayName);
					po.setCan_add(add);
					po.setCan_edit(edit);
					po.setCan_delete(del);
					po.setCan_view(view);
					
					if (permissionIdStr == null || permissionIdStr.trim().isEmpty() || permissionIdStr.equals("0")) {
						
						boolean addPer=pm.addPermission(po);
						if (!addPer) {
							session.setAttribute("flash_message", "Thêm quyền hạn không thành công");
				            session.setAttribute("flash_type", "danger");
				            response.sendRedirect(request.getContextPath() + "/page?view=role");
				            return;
						}
					} else {
						int perId=Integer.parseInt(permissionIdStr);
						po.setPermission_id(perId);
						
						boolean editPer=pm.editPermission(po);
						if (!editPer) {
							session.setAttribute("flash_message", "Sửa thông tin quyền hạn không thành công");
				            session.setAttribute("flash_type", "danger");
				            response.sendRedirect(request.getContextPath() + "/page?view=role");
				            return;
						}
					}
				}
				session.setAttribute("flash_message", "Sửa thông tin vai trò thành công");
	            session.setAttribute("flash_type", "success");
				response.sendRedirect(request.getContextPath() + "/page?view=role");
			} else {
				session.setAttribute("flash_message", "Sửa thông tin vai trò không thành công");
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
