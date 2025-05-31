package ads.role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.RoleObject;

public class roleImpl extends basicImpl implements role {

	public roleImpl() {
		super("Role");
		// TODO Auto-generated constructor stub
	}
	
	private boolean isExisting(RoleObject item) {
		boolean flag = false;

		String sql = "SELECT role_id FROM tbluser WHERE role_name='" + item.getRole_name() + "' ";
		ResultSet rs = this.get(sql, 0);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	@Override
	public boolean addRole(RoleObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
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
	
	private boolean isEmpty(RoleObject item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT role_id FROM permissions WHERE role_id=").append(item.getRole_id()).append(";");
		sql.append("SELECT role_id  FROM user WHERE user_roles=").append(item.getRole_id()).append(";");

		ArrayList<ResultSet> res = this.gets(sql.toString());
		for (ResultSet rs : res) {
			try {
				if (rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	@Override
	public boolean delRole(RoleObject item) {
		// TODO Auto-generated method stub
		if (!this.isEmpty(item)) {
			return false;
		}
		
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
	public ArrayList<ResultSet> getRoles(RoleObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM roles ");
		sql.append("");
		sql.append("ORDER BY role_id DESC ");
		
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet getRole(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM roles WHERE role_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getRoleDetail(int id) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT p.*,r.role_name FROM roles r ");
		sql.append("JOIN permissions p ON r.role_id = p.role_id ");
		sql.append("WHERE r.role_id = ?;");
		return this.gets(sql.toString(),id);
	}

	@Override
	public ResultSet getRole(String name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM roles WHERE role_name='"+name+"'";
		return this.get(sql, 0);
	}
	
}
