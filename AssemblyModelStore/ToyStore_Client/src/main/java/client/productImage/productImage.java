package client.productImage;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.ProductImage;

public interface productImage {
	boolean addProductImage(ProductImage item);
	boolean editProductImage(ProductImage item);
	boolean delProductImage(ProductImage item);
	
	ArrayList<ResultSet> getProductImage(ProductImage similar, int at, byte total);
	ResultSet getProductImage(int id);
}
