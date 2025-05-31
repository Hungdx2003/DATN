package client.productReview;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.ProductReview;

public interface productReview {
	boolean addProductReview(ProductReview item);
	boolean editProductReview(ProductReview item);
	boolean delProductReview(ProductReview item);
	
	ArrayList<ResultSet> getProductReviews(int id, int at, byte total);
	ResultSet getTotalProductReview(int id);
}
