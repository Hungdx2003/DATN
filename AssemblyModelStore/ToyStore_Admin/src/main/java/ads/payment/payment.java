package ads.payment;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.PaymentObject;

public interface payment {
	boolean addPayment(PaymentObject item);
	boolean editPayment(PaymentObject item);
	
	ArrayList<ResultSet> getPayment(int id, int at, byte total);
	ArrayList<ResultSet> getRevenueData(PaymentObject object);
	
	ResultSet getPayment(int id);
}
