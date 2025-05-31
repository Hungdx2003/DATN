package client.orderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.OrderDetail;

public interface orderDetail {
	boolean addOrderDetail(OrderDetail item);
	boolean editOrderDetail(OrderDetail item);
	
	ArrayList<ResultSet> getOrderDetails(int id);
	ResultSet getOrderDetail(int id);
}
