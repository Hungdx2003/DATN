package ads.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.PaymentObject;

public class paymentModel {
	private payment p;

	public paymentModel() {
		this.p = new paymentImpl();
	}

	public boolean addPayment(PaymentObject item) {
		return this.p.addPayment(item);
	}

	public boolean editPayment(PaymentObject item) {
		return this.p.editPayment(item);
	}

	public ArrayList<PaymentObject> getPayment(int id, int at, byte total) {
	    ArrayList<PaymentObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getPayment(id, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	PaymentObject item = new PaymentObject();
	        	item.setOrder_id(rs.getInt("order_id"));
				item.setPayment_id(rs.getInt("payment_id"));
				item.setPayment_method(rs.getString("payment_method"));
				item.setPayment_status(rs.getString("payment_status"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public ArrayList<Integer> getRevenueData() {
	    ArrayList<Integer> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getRevenueData(null);

	    for (int i = 0; i < 3; i++) {
	        if (res.size() > i) {
	            ResultSet rs = res.get(i);
	            if (rs != null) {
	                try {
	                    if (rs.next()) {
	                        switch (i) {
	                            case 0:
	                                list.add(rs.getInt("daily_revenue"));
	                                break;
	                            case 1:
	                                list.add(rs.getInt("monthly_revenue"));
	                                break;
	                            case 2:
	                                list.add(rs.getInt("yearly_revenue"));
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
	
	public PaymentObject getPayment(int id) {
		PaymentObject item=null;
		ResultSet rs = this.p.getPayment(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new PaymentObject();
					item.setOrder_id(rs.getInt("order_id"));
					item.setPayment_id(rs.getInt("payment_id"));
					item.setPayment_method(rs.getString("payment_method"));
					item.setPayment_status(rs.getString("payment_status"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return item;
	}
}
