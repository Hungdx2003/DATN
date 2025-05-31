package client.role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.RoleObject;

public class roleImpl extends basicImpl implements role {

	public roleImpl() {
		super("Role");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addRole(RoleObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO roles(");
		sql.append("role_name) ");
		sql.append("VALUES(?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getRole_name());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editRole(RoleObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE roles SET ");
		sql.append("role_name=?");
		sql.append("WHERE role_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getRole_name());
			pre.setInt(2, item.getRole_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delRole(RoleObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM roles WHERE role_id=?";
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
	public ArrayList<ResultSet> getRole(RoleObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM roles ");
		sql.append("");
		sql.append("ORDER BY role_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet getRole(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM roles WHERE role_id=?";
		return this.get(sql, id);
	}

}
