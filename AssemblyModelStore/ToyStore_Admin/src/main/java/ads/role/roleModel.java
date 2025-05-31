package ads.role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.RoleObject;
import ads.viewModel.RoleDetailViewModel;

public class roleModel {
	private role r;

	public roleModel() {
		this.r = new roleImpl();
	}

	public boolean addRole(RoleObject item) {
		return this.r.addRole(item);
	}

	public boolean editRole(RoleObject item) {
		return this.r.editRole(item);
	}

	public boolean delRole(RoleObject item) {
		return this.r.delRole(item);
	}

	public RoleObject getRoleObject(int id) {
		RoleObject item = null;

		ResultSet rs = this.r.getRole(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new RoleObject();
					item.setRole_id(rs.getInt("role_id"));
					item.setRole_name(rs.getString("role_name"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ArrayList<RoleObject> getRoleObjects() {
	    ArrayList<RoleObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.r.getRoles(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	RoleObject ro = new RoleObject();
	            ro.setRole_id(rs.getInt("role_id"));
	            ro.setRole_name(rs.getString("role_name"));
	            list.add(ro);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<RoleDetailViewModel> getRoleDetail(int id) {
	    ArrayList<RoleDetailViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.r.getRoleDetail(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	RoleDetailViewModel ro = new RoleDetailViewModel();
	            ro.setRole_id(rs.getInt("role_id"));
	            ro.setRole_name(rs.getString("role_name"));
	            ro.setPermission_id(rs.getInt("permission_id"));
	            ro.setObject_name(rs.getString("object_name"));
	            ro.setObject_display_name(rs.getString("object_display_name"));
	            ro.setCan_add(rs.getBoolean("can_add"));
	            ro.setCan_edit(rs.getBoolean("can_edit"));
	            ro.setCan_view(rs.getBoolean("can_view"));
	            ro.setCan_delete(rs.getBoolean("can_delete"));
	            list.add(ro);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public RoleObject getRole(String name) {
		RoleObject item = null;

		ResultSet rs = this.r.getRole(name);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new RoleObject();
					item.setRole_id(rs.getInt("role_id"));
					item.setRole_name(rs.getString("role_name"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
}
