package ads.orderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.OrderDetail;

public class orderDetailImpl extends basicImpl implements orderDetail {

	public orderDetailImpl() {
		super("Order Detail");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addOrderDetail(OrderDetail item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO orderdetails(");
		sql.append("order_id, product_id, product_price, quantity, od_subtotal)");
		sql.append("VALUES(?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getOrder_id());
            pre.setInt(2, item.getProduct_id());
            pre.setInt(3, item.getProduct_price());
            pre.setInt(4, item.getQuantity());
            pre.setInt(5, item.getOd_subtotal());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editOrderDetail(OrderDetail item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE orderdetails SET ");
		sql.append("order_id=?, product_id=?, product_price=?, quantity=?, od_subtotal=? ");
		sql.append("WHERE od_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setInt(1, item.getOrder_id());
            pre.setInt(2, item.getProduct_id());
            pre.setInt(3, item.getProduct_price());
            pre.setInt(4, item.getQuantity());
            pre.setInt(5, item.getOd_subtotal());
            pre.setInt(6, item.getOd_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public ArrayList<ResultSet> getOrderDetails(int id) {
		// TODO Auto-generated method stub
		
		String sql="SELECT * FROM orderdetails WHERE order_id="+id;
		
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getOrderDetail(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM orderdetails WHERE od_id=?";
		return this.get(sql, id);
	}

}
