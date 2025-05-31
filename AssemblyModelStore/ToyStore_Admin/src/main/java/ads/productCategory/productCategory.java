package ads.productCategory;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.ProductCategory;

public interface productCategory {
	boolean addProductCategory(ProductCategory item);
	boolean editProductCategory(ProductCategory item);
	boolean delProductCategory(ProductCategory item);
	
	ArrayList<ResultSet> getProductCategory(ProductCategory similar, int at, byte total);
	ResultSet getProductCategory(int id);
}
