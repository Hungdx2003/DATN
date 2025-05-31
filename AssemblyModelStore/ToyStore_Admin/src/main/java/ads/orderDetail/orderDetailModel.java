package ads.orderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.OrderDetail;

public class orderDetailModel {
	private orderDetail od;

	public orderDetailModel() {
		this.od = new orderDetailImpl();
	}

	public boolean addOrderDetail(OrderDetail item) {
		return this.od.addOrderDetail(item);
	}

	public boolean editOrderDetail(OrderDetail item) {
		return this.od.editOrderDetail(item);
	}

	public OrderDetail getOrderOrderDetail(int id) {
		OrderDetail item = null;

		ResultSet rs = this.od.getOrderDetail(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new OrderDetail();
					item.setOrder_id(rs.getInt("order_id"));
					item.setOd_id(rs.getInt("od_id"));
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setQuantity(rs.getInt("quantity"));
					item.setOd_subtotal(rs.getInt("od_subtotal"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<OrderDetail> getOrderDetails(int id) {
	    ArrayList<OrderDetail> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.od.getOrderDetails(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	OrderDetail item = new OrderDetail();
	        	item = new OrderDetail();
				item.setOrder_id(rs.getInt("order_id"));
				item.setOd_id(rs.getInt("od_id"));
				item.setProduct_id(rs.getInt("product_id"));
				item.setProduct_price(rs.getInt("product_price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setOd_subtotal(rs.getInt("od_subtotal"));
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
