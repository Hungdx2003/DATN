package client.product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import client.objects.ProductObject;

public interface product {
	boolean addProduct(ProductObject item);
	boolean editProduct(ProductObject item);
	boolean delProduct(ProductObject item);
	
	ArrayList<ResultSet> getProduct(ProductObject similar, int at, byte total);
	ArrayList<ResultSet> getProductByCategory(List<Integer> categoryIds, int at, byte total);
	ArrayList<ResultSet> getProductByWord(String word, int at, byte total);
	ArrayList<ResultSet> getNewProduct(ProductObject similar);
	ArrayList<ResultSet> getBestSeller(ProductObject similar);
	ArrayList<ResultSet> getSaleProduct(ProductObject similar, int at, byte total);
	ArrayList<ResultSet> GetProductByBrand(List<String> brands,int at, byte total);
	ArrayList<ResultSet> GetBrand(ProductObject item);
	ArrayList<ResultSet> GetRelatedProduct(int currentProductId);
	
	
	ResultSet getProduct(int id);
	ResultSet getProductDetail(int id);
	ResultSet getProductByCreator(int id);
	ResultSet GetTotalProducts(int page);
	ResultSet GetTotalProductByCategory(int page,List<Integer> categoryIds);
	ResultSet GetTotalProductByWord(int page, String word);
	ResultSet GetTotalProductByBrand(List<String> brands);
}
