package client.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.PaymentObject;

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
}
