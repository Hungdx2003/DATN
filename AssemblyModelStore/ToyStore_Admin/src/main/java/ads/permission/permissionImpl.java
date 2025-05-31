package ads.permission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.PermissionObject;

public class permissionImpl extends basicImpl implements permission {
	public permissionImpl() {
		super("Permission");
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean addPermission(PermissionObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO permissions(");
		sql.append("role_id, object_name, can_view, can_add, can_edit, can_delete, object_display_name) ");
		sql.append("VALUES(?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getRole_id());
            pre.setString(2, item.getObject_name());
            pre.setBoolean(3, item.isCan_view());
            pre.setBoolean(4, item.isCan_add());
            pre.setBoolean(5, item.isCan_edit());
            pre.setBoolean(6, item.isCan_delete());
            pre.setString(7, item.getObject_display_name());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editPermission(PermissionObject item) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE permissions SET ");
		sql.append("can_view=?, can_add=?, can_edit=?, can_delete=? ");
		sql.append("WHERE permission_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
             pre.setBoolean(1, item.isCan_view());
             pre.setBoolean(2, item.isCan_add());
             pre.setBoolean(3, item.isCan_edit());
             pre.setBoolean(4, item.isCan_delete());
			pre.setInt(5, item.getPermission_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delPermission(PermissionObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM permissions WHERE role_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getRole_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getPermission(PermissionObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM permissions ");
		sql.append("ORDER BY permission_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getPermission(int id, String objname) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM permissions WHERE role_id=? AND object_name = '"+objname+"'";
		return this.get(sql,id);
	}
	@Override
	public ArrayList<ResultSet> getObjectName(PermissionObject similar) {
		// TODO Auto-generated method stub
		String sql="SELECT DISTINCT object_name, object_display_name FROM permissions;";
		return this.gets(sql);
	}

}
