package client.discountUsage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import client.basic.basicImpl;
import client.objects.DiscountUsage;

public class discountUsageImpl extends basicImpl implements discountUsage {

	public discountUsageImpl() {
		super("DiscountUsage");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addDiscountUsage(DiscountUsage item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO discountusage(");
		sql.append("user_id,discount_id, order_id) ");
		sql.append("VALUES(?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getUser_id());
            pre.setInt(2, item.getDiscount_id());
            pre.setInt(3, item.getOrder_id());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editDiscountUsage(DiscountUsage item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE discountusage SET ");
		sql.append("user_id=?,discount_id=?, order_id=? ");
		sql.append("WHERE usage_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setInt(1, item.getUser_id());
            pre.setInt(2, item.getDiscount_id());
            pre.setInt(3, item.getOrder_id());
            pre.setInt(4, item.getUsage_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delDiscountUsage(DiscountUsage item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM discountusage WHERE usage_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getUsage_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
