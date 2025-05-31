package ads.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.OrderObject;

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

	public OrderObject getOrder(int id) {
		OrderObject item = null;

		ResultSet rs = this.o.getOrder(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new OrderObject();
					item.setOrder_id(rs.getInt("order_id"));
					item.setUser_id(rs.getInt("user_id"));
					item.setOrder_date(rs.getString("order_date"));
					item.setReceiver_name(rs.getString("receiver_name"));
					item.setReceiver_mobilephone(rs.getString("receiver_mobilephone"));
					item.setDelivery_address(rs.getString("delivery_address"));
					item.setOrder_status(rs.getString("order_status"));
					item.setTotal_amount(rs.getInt("total_amount"));
					item.setEmail(rs.getString("email"));
					item.setTotal_order_value(rs.getInt("total_order_value"));
					item.setDiscount_money(rs.getInt("discount_money"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
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
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<Integer> countOrder() {
	    ArrayList<Integer> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.o.countOrder(null);

	    for (int i = 0; i < 3; i++) {
	        if (res.size() > i) {
	            ResultSet rs = res.get(i);
	            if (rs != null) {
	                try {
	                    if (rs.next()) {
	                        switch (i) {
	                            case 0:
	                                list.add(rs.getInt("daily_order_count"));
	                                break;
	                            case 1:
	                                list.add(rs.getInt("monthly_order_count"));
	                                break;
	                            case 2:
	                                list.add(rs.getInt("yearly_order_count"));
	                                break;
	                        }
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } finally {
	                    try {
	                        rs.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }
	    return list;
	}
	
	public ArrayList<Integer> countQuantity() {
	    ArrayList<Integer> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.o.countQuantity(null);

	    for (int i = 0; i < 3; i++) {
	        if (res.size() > i) {
	            ResultSet rs = res.get(i);
	            if (rs != null) {
	                try {
	                    if (rs.next()) {
	                        switch (i) {
	                            case 0:
	                                list.add(rs.getInt("daily_quantity_sold"));
	                                break;
	                            case 1:
	                                list.add(rs.getInt("monthly_quantity_sold"));
	                                break;
	                            case 2:
	                                list.add(rs.getInt("yearly_quantity_sold"));
	                                break;
	                        }
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } finally {
	                    try {
	                        rs.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }
	    return list;
	}
}
