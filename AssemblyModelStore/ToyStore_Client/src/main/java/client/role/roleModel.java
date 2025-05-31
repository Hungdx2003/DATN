package client.role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.RoleObject;

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
	
	public ArrayList<RoleObject> getRoleObjects(RoleObject similar, int at, byte total) {
	    ArrayList<RoleObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.r.getRole(similar, at, total);
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
}
