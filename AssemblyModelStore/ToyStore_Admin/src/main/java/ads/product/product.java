package ads.product;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.ProductObject;

public interface product {
	boolean addProduct(ProductObject item);
	boolean editProduct(ProductObject item);
	boolean editProduct(ArrayList<ProductObject> items);
	boolean delProduct(ProductObject item);
	
	ArrayList<ResultSet> getProduct(ProductObject similar, int at, byte total);
	ArrayList<ResultSet> getBestSaleByDay(ProductObject similar);
	ArrayList<ResultSet> getBestSaleByMonth(ProductObject similar);
	ArrayList<ResultSet> getBestSaleByYear(ProductObject similar);
	ArrayList<ResultSet> getLowQuantity(ProductObject similar);
	ArrayList<ResultSet> getProductDiscount(ProductObject similar);
	ArrayList<ResultSet> getProductDiscountById(int id);
	ArrayList<ResultSet> getSlowSellingProduct(ProductObject similar);
	ArrayList<ResultSet> getBrand(ProductObject similar);
	
	ResultSet getProduct(int id);
	ResultSet getProductByCreator(int id);
}
