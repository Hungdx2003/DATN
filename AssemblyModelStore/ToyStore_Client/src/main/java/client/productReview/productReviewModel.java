package client.productReview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.ProductReview;
import client.viewModel.ProductReviewViewModel;

public class productReviewModel {
	private productReview pr;

	public productReviewModel() {
		this.pr = new productReviewImpl();
	}

	public boolean addProductReview(ProductReview item) {
		return this.pr.addProductReview(item);
	}

	public boolean editProductReview(ProductReview item) {
		return this.pr.editProductReview(item);
	}

	public boolean delProductReview(ProductReview item) {
		return this.pr.delProductReview(item);
	}

	public int getTotalProductReview(int id) {
		ResultSet res = this.pr.getTotalProductReview(id);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}

	public ArrayList<ProductReviewViewModel> getProductReview(int id, int at, byte total) {
	    ArrayList<ProductReviewViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.pr.getProductReviews(id, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	ProductReviewViewModel item = new ProductReviewViewModel();
	        	item.setReviewId(rs.getInt("review_id"));
				item.setProductId(rs.getInt("product_id"));
				item.setUserId(rs.getInt("user_id"));
				item.setComment(rs.getString("comment"));
				item.setCreateAt(rs.getString("create_at"));
				item.setUserFullName(rs.getString("user_fullname"));
				String fullname=rs.getString("user_fullname");
				String[] parts = fullname.trim().split("\\s+");
                String lastName = parts[parts.length - 1];
                char FisrtCharName = lastName.charAt(0);
                item.setFirstCharName(FisrtCharName);
                item.setColor(getColorFromString(fullname));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public static String getColorFromString(String input) {
	    int hash = input.hashCode();
	    int r = (hash & 0xFF0000) >> 16;
	    int g = (hash & 0x00FF00) >> 8;
	    int b = (hash & 0x0000FF);
	    return String.format("#%02X%02X%02X", r, g, b);
	}

}
