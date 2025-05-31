package ads.discount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.DiscountObject;

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
		sql.append("end_date, max_users, max_usage, is_active, discount_value_type)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?)");
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
            pre.setBoolean(8, item.isActive());
            pre.setString(9, item.getDiscount_value_type());

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
		sql.append("end_date=?, max_users=?, max_usage=?, is_active=?, discount_value_type=? ");
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
            pre.setBoolean(8, item.isActive());
            pre.setString(9, item.getDiscount_value_type());
            pre.setInt(10, item.getDiscount_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	private boolean isEmpty(DiscountObject item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT discount_id FROM product_discounts WHERE discount_id=").append(item.getDiscount_id()).append(";");
		sql.append("SELECT discount_id FROM discountusage WHERE discount_id=").append(item.getDiscount_id()).append(";");

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
	public boolean delDiscount(DiscountObject item) {
		if (!this.isEmpty(item)) {
			return false;
		}
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
	public boolean updateDiscountStatus(DiscountObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE discounts SET is_active = 0 ");
		sql.append("WHERE (end_date < NOW()");
		sql.append(" OR usage_count >= max_users) AND is_active = 1");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

}
