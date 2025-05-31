package client.discount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.DiscountObject;

public class discountImpl extends basicImpl implements discount {

	public discountImpl() {
		super("Discount");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addDiscount(DiscountObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO discounts(");
		sql.append("discount_name, discount_type, discount_value, start_date, ");
		sql.append("end_date, max_users, max_usage, usage_count, is_active, discount_value_type)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getDiscount_name());
            pre.setString(2, item.getDiscount_type());
            pre.setInt(3, item.getDiscount_value());
            pre.setString(4, item.getStart_date());
            pre.setString(5, item.getEnd_date());
            if ("Khuyến mãi".equalsIgnoreCase(item.getDiscount_type())) {
            	pre.setNull(6, Types.INTEGER);
            	pre.setNull(7, Types.INTEGER);
            } else {
            	pre.setObject(6, item.getMax_users(), Types.INTEGER);
            	pre.setObject(7, item.getMax_usage(), Types.INTEGER);
            }
            pre.setInt(8, item.getUsage_count());
            pre.setBoolean(9, item.isActive());
            pre.setString(10, item.getDiscount_value_type());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editDiscount(DiscountObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE discounts SET ");
		sql.append("discount_name=?, discount_type=?, discount_value=?, start_date=?, ");
		sql.append("end_date=?, max_users=?, max_usage=?, usage_count=?, is_active=?, discount_value_type=? ");
		sql.append("WHERE discount_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getDiscount_name());
            pre.setString(2, item.getDiscount_type());
            pre.setInt(3, item.getDiscount_value());
            pre.setString(4, item.getStart_date());
            pre.setString(5, item.getEnd_date());
            if ("Khuyến mãi".equalsIgnoreCase(item.getDiscount_type())) {
            	pre.setNull(6, Types.INTEGER);
            	pre.setNull(7, Types.INTEGER);
            } else {
            	pre.setObject(6, item.getMax_users(), Types.INTEGER);
            	pre.setObject(7, item.getMax_usage(), Types.INTEGER);
            }
            pre.setInt(8, item.getUsage_count());
            pre.setBoolean(9, item.isActive());
            pre.setString(10, item.getDiscount_value_type());
            pre.setInt(11, item.getDiscount_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delDiscount(DiscountObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM discounts WHERE discount_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getDiscount_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getDiscount(DiscountObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM discounts ");
		sql.append("");
		sql.append("ORDER BY discount_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getDiscount(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM discounts WHERE discount_id=?";
		return this.get(sql, id);
	}
	
	@Override
	public ResultSet getDiscount(String name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM discounts WHERE discount_name= ? AND is_active=1 AND discount_type= 'Mã giảm giá';";
		return this.get(sql, name);
	}

	@Override
	public ResultSet countTimesUseDiscount(int userId, int discountId) {
		String sql="SELECT COUNT(*) AS use_count FROM discountusage WHERE discount_id = ? AND user_id ="+userId;	
		return this.get(sql,discountId);
	}

	@Override
	public ResultSet getUseDiscountUser(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(DISTINCT user_id) AS user_count FROM discountusage WHERE discount_id = ?;";
		return this.get(sql, id);
	}
	
	public static void main(String[] args) {
		discount d=new discountImpl();
		ResultSet rs=d.getDiscount("HISTORE");
		String row = "";
		try {
			if (rs.next()) {
				row = "ID: " + rs.getInt("discount_id");
				row += "\tname: " + rs.getString("discount_name");

				System.out.println(row);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ResultSet> getValidDiscount(DiscountObject item) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM discounts WHERE discount_type='Khuyến mãi' AND is_active = 1 AND end_date >= CURRENT_DATE";
		return this.gets(sql);
	}
}
