package client.orderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.OrderDetail;
import client.viewModel.orderDetailView;

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
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<orderDetailView> getOrderDetails(int id) {
	    ArrayList<orderDetailView> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.od.getOrderDetails(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	orderDetailView item = new orderDetailView();
				item.setOrderId(rs.getInt("order_id"));
				item.setOdId(rs.getInt("od_id"));
				item.setProductId(rs.getInt("product_id"));
				item.setProductPrice(rs.getInt("order_price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setSubtotal(rs.getInt("od_subtotal"));
				item.setProductName(rs.getString("product_name"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
