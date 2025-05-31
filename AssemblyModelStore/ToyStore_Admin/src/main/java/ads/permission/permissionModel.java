package ads.permission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.PermissionObject;

public class permissionModel {
	private permission p;

	public permissionModel() {
		this.p = new permissionImpl();
	}

	public boolean addPermission(PermissionObject item) {
		return this.p.addPermission(item);
	}

	public boolean editPermission(PermissionObject item) {
		return this.p.editPermission(item);
	}

	public boolean delPermission(PermissionObject item) {
		return this.p.delPermission(item);
	}

	public PermissionObject getPermission(int id, String objname) {
		PermissionObject item = null;

		ResultSet rs = this.p.getPermission(id,objname);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new PermissionObject();
					item.setRole_id(rs.getInt("role_id"));
					item.setObject_name(rs.getString("object_name"));
					item.setCan_add(rs.getBoolean("can_add"));
					item.setCan_view(rs.getBoolean("can_view"));
		            item.setCan_edit(rs.getBoolean("can_edit"));
		            item.setCan_delete(rs.getBoolean("can_delete"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ArrayList<PermissionObject> getPermission(PermissionObject similar, int at, byte total) {
	    ArrayList<PermissionObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getPermission(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	PermissionObject po = new PermissionObject();
	        	po.setRole_id(rs.getInt("role_id"));
	            po.setObject_name(rs.getString("object_name"));
	            po.setCan_add(rs.getBoolean("can_add"));
	            po.setCan_view(rs.getBoolean("can_view"));
	            po.setCan_edit(rs.getBoolean("can_edit"));
	            po.setCan_delete(rs.getBoolean("can_delete"));
	            list.add(po);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<PermissionObject> getObjectName() {
	    ArrayList<PermissionObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getObjectName(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	PermissionObject po=new PermissionObject();
	        	po.setObject_name(rs.getString("object_name"));
	        	po.setObject_display_name(rs.getString("object_display_name"));
	            list.add(po);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}

