package client.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.OrderObject;
import client.viewModel.orderView;

public class orderModel {
	private order o;

	public orderModel() {
		this.o = new orderImpl();
	}

	public boolean addOrder(OrderObject item) {
		return this.o.addOrder(item);
	}

	public boolean editOrder(OrderObject item) {
		return this.o.editOrder(item);
	}
	public boolean delOrder(OrderObject item) {
		return this.o.delOrder(item);
	}

	public int getLatestOrder(int id) {
		ResultSet res = this.o.getLatestOrder(id);
		int oId = 0;
		if (res != null) {
			try {
				if (res.next()) {
					oId = res.getInt("order_id");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return oId;
	}

	public ArrayList<OrderObject> getOrder(OrderObject similar, int at, byte total) {
	    ArrayList<OrderObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.o.getOrder(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	OrderObject item = new OrderObject();
	        	item.setOrder_id(rs.getInt("order_id"));
				item.setUser_id(rs.getInt("user_id"));
				item.setOrder_date(rs.getString("order_date"));
				item.setReceiver_name(rs.getString("receiver_name"));
				item.setReceiver_mobilephone(rs.getString("receiver_mobilephone"));
				item.setDelivery_address(rs.getString("delivery_address"));
				item.setOrder_status(rs.getString("order_status"));
				item.setTotal_amount(rs.getInt("total_amount"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<OrderObject> getOrders(int id) {
	    ArrayList<OrderObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.o.getOrders(id);
	    ResultSet rs = res.get(0);
	    try {
	        while (rs.next()) {
	        	OrderObject item = new OrderObject();
	        	item.setOrder_id(rs.getInt("order_id"));
				item.setUser_id(rs.getInt("user_id"));
				item.setOrder_date(rs.getString("order_date"));
				item.setReceiver_name(rs.getString("receiver_name"));
				item.setReceiver_mobilephone(rs.getString("receiver_mobilephone"));
				item.setDelivery_address(rs.getString("delivery_address"));
				item.setOrder_status(rs.getString("order_status"));
				item.setTotal_amount(rs.getInt("total_amount"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public orderView getOrder(int id) {
		  ResultSet rs=this.o.getOrder(id);
		  orderView item=null;
		  if(rs!=null) {
			  try {
			        if (rs.next()) {
			        	item = new orderView();
			        	item.setOrderId(rs.getInt("order_id"));
						item.setUserId(rs.getInt("user_id"));
						item.setOrderDate(rs.getString("order_date"));
						item.setReceiverName(rs.getString("receiver_name"));
						item.setReceiverMobilephone(rs.getString("receiver_mobilephone"));
						item.setDeliveryAddress(rs.getString("delivery_address"));
						item.setOrderStatus(rs.getString("order_status"));
						item.setTotalAmount(rs.getInt("total_amount"));
						item.setEmail(rs.getString("email"));
						item.setPaymentMethod(rs.getString("payment_method"));
						item.setPaymentStatus(rs.getString("payment_status"));
						item.setTotalOrderValue(rs.getInt("total_order_value"));
						item.setDiscountMoney(rs.getInt("discount_money"));
			        }
			        rs.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		  }
		  return item;
	}
}
