package ads.role;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ads.viewModel.RoleDetailViewModel;

/**
 * Servlet implementation class getRoleDetail
 */
@WebServlet("/api/getRoleDetail")
public class getRoleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRoleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("role_id");
		if (id==null) {
			response.setContentType("application/json; charset=UTF-8");
		    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    response.getWriter().write("{\"error\": \"Thiếu role_id\"}");
	        return;
		}
		int roleId=Integer.parseInt(id);
        roleModel rl = new roleModel();
        ArrayList<RoleDetailViewModel> roles = rl.getRoleDetail(roleId);
        
        JSONObject responseJson = new JSONObject();
        if (!roles.isEmpty()) {
            RoleDetailViewModel first = roles.get(0);
            responseJson.put("id", first.getRole_id());
            responseJson.put("role_name", first.getRole_name());

            JSONArray permissions = new JSONArray();
            for (RoleDetailViewModel rs : roles) {
                JSONObject perm = new JSONObject();
                perm.put("permission_id", rs.getPermission_id());
                perm.put("object_name", rs.getObject_name());
                perm.put("display_name", rs.getObject_display_name());
                perm.put("view", rs.isCan_view());
                perm.put("add", rs.isCan_add());
                perm.put("edit", rs.isCan_edit());
                perm.put("del", rs.isCan_delete());
                permissions.put(perm);
            }
            responseJson.put("permissions", permissions);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(responseJson.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
