package client.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.UserObject;

public class userImpl extends basicImpl implements user {
	public userImpl() {
		super("User");
	}
	
	private boolean isExisting(UserObject item) {
		boolean flag = false;

		String sql = "SELECT user_id FROM user WHERE user_name='" + item.getUser_name() + "' ";
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
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO user(");
		sql.append("user_name, user_pass, user_fullname, ");
		sql.append("user_mobilephone, user_email, user_roles)");
		sql.append("VALUES(?,md5(?),?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_mobilephone());
			pre.setString(5, item.getUser_email());
			pre.setInt(6, item.getUser_roles());
			return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editUser(UserObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE user SET ");
		sql.append("user_fullname=?, ");
		sql.append("user_birthday=?, user_mobilephone=?, user_email=?, ");
		sql.append("user_address=?, user_gender=? ");
		sql.append("WHERE user_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getUser_fullname());
			pre.setString(2, item.getUser_birthday());
			pre.setString(3, item.getUser_mobilephone());
			pre.setString(4, item.getUser_email());
			pre.setString(5, item.getUser_address());
			pre.setString(6, item.getUser_gender());
			pre.setInt(7, item.getUser_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	@Override
	public boolean changePassword(UserObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE user SET ");
		sql.append("user_pass= md5(?) ");
		sql.append("WHERE user_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getUser_pass());
			pre.setInt(2, item.getUser_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM user WHERE user_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getUser_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getUser(UserObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM user ");
		sql.append("");
		sql.append("ORDER BY user_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM user WHERE user_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String email, String userpass) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM user WHERE (user_email=?) AND (user_pass=md5(?))";
		return this.get(sql, email, userpass);
	}

	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String sql = "SELECT user_id FROM user WHERE user_name='" + username + "' ";
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
	public boolean isEmailExists(String email) {
		boolean flag = false;

		String sql = "SELECT user_id FROM user WHERE user_email='" + email + "' ";
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

}
