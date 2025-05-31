package client.discount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.DiscountObject;

public class discountModel {
	private discount d;

	public discountModel() {
		this.d = new discountImpl();
	}

	public boolean addDiscount(DiscountObject item) {
		return this.d.addDiscount(item);
	}

	public boolean editDiscount(DiscountObject item) {
		return this.d.editDiscount(item);
	}

	public boolean delDiscount(DiscountObject item) {
		return this.d.delDiscount(item);
	}

	public DiscountObject getDiscountObject(int id) {
		DiscountObject item = null;

		ResultSet rs = this.d.getDiscount(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new DiscountObject();
					item.setDiscount_id(rs.getInt("discount_id"));
					item.setDiscount_name(rs.getString("discount_name"));
					item.setDiscount_type(rs.getString("discount_type"));
					item.setDiscount_value(rs.getInt("discount_value"));
					item.setStart_date(rs.getString("start_date"));
					item.setEnd_date(rs.getString("end_date"));
					item.setMax_users(rs.getInt("max_users"));
					item.setMax_usage(rs.getInt("max_usage"));
					item.setUsage_count(rs.getInt("usage_count"));
					item.setActive(rs.getBoolean("is_active"));
					item.setDiscount_value_type(rs.getString("discount_value_type"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public DiscountObject getDiscount(String name) {
		DiscountObject item = null;

		ResultSet rs = this.d.getDiscount(name);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new DiscountObject();
					item.setDiscount_id(rs.getInt("discount_id"));
					item.setDiscount_name(rs.getString("discount_name"));
					item.setDiscount_type(rs.getString("discount_type"));
					item.setDiscount_value(rs.getInt("discount_value"));
					item.setStart_date(rs.getString("start_date"));
					item.setEnd_date(rs.getString("end_date"));
					item.setMax_users(rs.getInt("max_users"));
					item.setMax_usage(rs.getInt("max_usage"));
					item.setUsage_count(rs.getInt("usage_count"));
					item.setActive(rs.getBoolean("is_active"));
					item.setDiscount_value_type(rs.getString("discount_value_type"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<DiscountObject> getDiscountObject(DiscountObject similar, int at, byte total) {
	    ArrayList<DiscountObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.d.getDiscount(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	DiscountObject item = new DiscountObject();
	        	item.setDiscount_id(rs.getInt("discount_id"));
				item.setDiscount_name(rs.getString("discount_name"));
				item.setDiscount_type(rs.getString("discount_type"));
				item.setDiscount_value(rs.getInt("discount_value"));
				item.setStart_date(rs.getString("start_date"));
				item.setEnd_date(rs.getString("end_date"));
				item.setMax_users(rs.getInt("max_users"));
				item.setMax_usage(rs.getInt("max_usage"));
				item.setUsage_count(rs.getInt("usage_count"));
				item.setActive(rs.getBoolean("is_active"));
				item.setDiscount_value_type(rs.getString("discount_value_type"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public int getUseDiscountUser(int id) {
		ResultSet res = this.d.getUseDiscountUser(id);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("user_count");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public int countTimesUseDiscount(int userId, int discountId) {
		ResultSet res = this.d.countTimesUseDiscount(userId, discountId);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("use_count");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public ArrayList<DiscountObject> getValidDiscount() {
	    ArrayList<DiscountObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.d.getValidDiscount(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	DiscountObject item = new DiscountObject();
	        	item.setDiscount_id(rs.getInt("discount_id"));
				item.setDiscount_name(rs.getString("discount_name"));
				item.setDiscount_type(rs.getString("discount_type"));
				item.setDiscount_value(rs.getInt("discount_value"));
				item.setStart_date(rs.getString("start_date"));
				item.setEnd_date(rs.getString("end_date"));
				item.setMax_users(rs.getInt("max_users"));
				item.setMax_usage(rs.getInt("max_usage"));
				item.setUsage_count(rs.getInt("usage_count"));
				item.setActive(rs.getBoolean("is_active"));
				item.setDiscount_value_type(rs.getString("discount_value_type"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
