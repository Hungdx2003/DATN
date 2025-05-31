package client.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.OrderObject;

public class orderImpl extends basicImpl implements order {

	public orderImpl() {
		super("Order");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addOrder(OrderObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO orders(");
		sql.append("user_id, receiver_name, receiver_mobilephone, ");
		sql.append("delivery_address, total_amount, email, order_status, discount_money, total_order_value)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getUser_id());
            pre.setString(2, item.getReceiver_name());
            pre.setString(3, item.getReceiver_mobilephone());
            pre.setString(4, item.getDelivery_address());
            pre.setInt(5, item.getTotal_amount());
            pre.setString(6, item.getEmail());
            pre.setString(7, item.getOrder_status());
            pre.setInt(8, item.getDiscount_money());
            pre.setInt(9, item.getTotal_order_value());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editOrder(OrderObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE orders SET ");
		sql.append("order_status=? ");
		sql.append("WHERE order_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getOrder_status());
            pre.setInt(2, item.getOrder_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	@Override
	public boolean delOrder(OrderObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM orders WHERE order_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getOrder_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	@Override
	public ArrayList<ResultSet> getOrder(OrderObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM orders ");
		sql.append("");
		sql.append("ORDER BY order_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getLatestOrder(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC LIMIT 1";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getOrders(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM orders WHERE user_id=?";
		
		return this.gets(sql, id);
	}
	
	@Override
	public ResultSet getOrder(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o.order_id, o.user_id, o.order_date, o.receiver_name, ");
		sql.append("o.receiver_mobilephone, o.delivery_address, o.order_status, ");
		sql.append("o.total_amount, o.discount_money, o.total_order_value , o.email, p.payment_method, p.payment_status ");
		sql.append("FROM orders o ");
		sql.append("LEFT JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE o.order_id = ?");
		
		return this.get(sql.toString(), id);
	}
	
	public static void main(String[] args) {
		order o=new orderImpl();
		
		ArrayList<ResultSet> res=o.getOrders(29);
		ResultSet rs = res.get(0);
		String row = "";
		try {
			while (rs.next()) {
				row = "ID: " + rs.getInt("order_id");
				row += "\tDATE: " + rs.getString("order_date");
				row += "\tFULLNAME: " + rs.getString("receiver_name");

				System.out.println(row);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
