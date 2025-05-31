package ads.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.UserObject;

public class userModel {
	private user u;

	public userModel() {
		this.u = new userImpl();
	}

	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean editUser(UserObject item) {
		return this.u.editUser(item);
	}
	
	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}

	public UserObject getUserObject(int id) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_roles(rs.getInt("user_roles"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_gender(rs.getString("user_gender"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public UserObject getUserObject(String username, String userpass) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(username, userpass);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_roles(rs.getInt("user_roles"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_gender(rs.getString("user_gender"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ArrayList<UserObject> getUserObjects() {
	    ArrayList<UserObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.u.getUser(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	            UserObject item = new UserObject();
	            item.setUser_id(rs.getInt("user_id"));
	            item.setUser_fullname(rs.getString("user_fullname"));
				item.setUser_email(rs.getString("user_email"));
				item.setUser_mobilephone(rs.getString("user_mobilephone"));
				item.setUser_roles(rs.getInt("user_roles"));
				item.setUser_address(rs.getString("user_address"));
				item.setUser_pass(rs.getString("user_pass"));
				item.setUser_name(rs.getString("user_name"));
				item.setUser_gender(rs.getString("user_gender"));
				item.setUser_birthday(rs.getString("user_birthday"));
				item.setUser_created_date(rs.getString("user_created_date"));
				item.setUser_last_modified(rs.getString("user_last_modified"));
				item.setUser_deleted(rs.getBoolean("user_deleted"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
