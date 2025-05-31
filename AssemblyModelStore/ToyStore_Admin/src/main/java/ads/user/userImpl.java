package ads.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.UserObject;

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
		sql.append("user_birthday, user_mobilephone, user_email, ");
		sql.append("user_address, user_roles, user_deleted, user_gender)");
		sql.append("VALUES(?,md5(?),?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_email());
			pre.setString(7, item.getUser_address());
			pre.setInt(8, item.getUser_roles());
			pre.setBoolean(9, item.isUser_deleted());
			pre.setString(10, item.getUser_gender());
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
		sql.append("user_pass=md5(?), user_fullname=?, ");
		sql.append("user_birthday=?, user_mobilephone=?, user_email=?, ");
		sql.append("user_address=?, user_roles=?, user_deleted=?, user_gender=? ");
		sql.append("WHERE user_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getUser_pass());
			pre.setString(2, item.getUser_fullname());
			pre.setString(3, item.getUser_birthday());
			pre.setString(4, item.getUser_mobilephone());
			pre.setString(5, item.getUser_email());
			pre.setString(6, item.getUser_address());
			pre.setInt(7, item.getUser_roles());
			pre.setBoolean(8, item.isUser_deleted());
			pre.setString(9, item.getUser_gender());
			pre.setInt(10, item.getUser_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	private boolean isEmpty(UserObject item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_id FROM discountusage WHERE user_id=").append(item.getUser_id()).append(";");
		sql.append("SELECT user_id FROM news WHERE news_created_by=").append(item.getUser_id()).append(";");
		sql.append("SELECT user_id FROM orders WHERE user_id=").append(item.getUser_id()).append(";");
		sql.append("SELECT user_id FROM cart WHERE user_id='").append(item.getUser_name()).append("';");
		sql.append("SELECT user_id FROM product_review WHERE user_id='").append(item.getUser_name()).append("';");
		sql.append("SELECT user_id FROM product WHERE product_created_by=").append(item.getUser_id()).append(";");
		sql.append("SELECT user_id FROM productcategories WHERE user_id=").append(item.getUser_id()).append(";");
		
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
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		if (!this.isEmpty(item)) {
			return false;
		}
		
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
	public ArrayList<ResultSet> getUser(UserObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM user ");
		sql.append("");
		sql.append("ORDER BY user_id DESC ");
		
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM user WHERE user_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM user WHERE (user_name=?) AND (user_pass=md5(?))";
		return this.get(sql, username, userpass);
	}

}
