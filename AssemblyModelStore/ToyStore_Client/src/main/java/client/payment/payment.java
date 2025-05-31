package client.payment;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.PaymentObject;

public interface payment {
	boolean addPayment(PaymentObject item);
	boolean editPayment(PaymentObject item);
	
	ArrayList<ResultSet> getPayment(int id, int at, byte total);
	ResultSet getPayment(int id);
}
