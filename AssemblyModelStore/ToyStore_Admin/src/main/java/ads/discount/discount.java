package ads.discount;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.DiscountObject;

public interface discount {
	boolean addDiscount(DiscountObject item);
	boolean editDiscount(DiscountObject item);
	boolean delDiscount(DiscountObject item);
	boolean updateDiscountStatus(DiscountObject item);
	
	ArrayList<ResultSet> getDiscount(DiscountObject similar, int at, byte total);
	ResultSet getDiscount(int id);
}
