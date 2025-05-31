package ads.order;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.OrderObject;

public interface order {
	boolean addOrder(OrderObject item);
	boolean editOrder(OrderObject item);
	
	ArrayList<ResultSet> getOrder(OrderObject similar, int at, byte total);
	ArrayList<ResultSet> countOrder(OrderObject similar);
	ArrayList<ResultSet> countQuantity(OrderObject similar);
	ArrayList<ResultSet> getRevenue(OrderObject similar);
	
	ResultSet getOrder(int id);
}
