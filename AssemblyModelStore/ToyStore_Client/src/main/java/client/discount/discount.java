package client.discount;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.DiscountObject;

public interface discount {
	boolean addDiscount(DiscountObject item);
	boolean editDiscount(DiscountObject item);
	boolean delDiscount(DiscountObject item);
	
	ArrayList<ResultSet> getDiscount(DiscountObject similar, int at, byte total);
	ArrayList<ResultSet> getValidDiscount(DiscountObject item);
	
	ResultSet getUseDiscountUser(int id);	
	ResultSet getDiscount(int id);
	ResultSet countTimesUseDiscount(int userId, int discountId);
	ResultSet getDiscount(String name);
}
