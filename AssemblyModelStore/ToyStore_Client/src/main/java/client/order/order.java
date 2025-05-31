package client.order;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.OrderObject;

public interface order {
	boolean addOrder(OrderObject item);
	boolean editOrder(OrderObject item);
	boolean delOrder(OrderObject item);
	
	ArrayList<ResultSet> getOrder(OrderObject similar, int at, byte total);
	ArrayList<ResultSet> getOrders(int id);
	
	ResultSet getLatestOrder(int id);
	ResultSet getOrder(int id);
}
