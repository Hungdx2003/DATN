package client.productReview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.ProductReview;

public class productReviewImpl extends basicImpl implements productReview {

	public productReviewImpl() {
		super("Product Review");
		// TODO Auto-generated constructor stub
	}
	
	public boolean isBuy(ProductReview item) {
		boolean flag = false;
		
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT 1 FROM orders o ");
		sql.append("JOIN orderdetails od ON o.order_id = od.order_id ");
		sql.append("WHERE o.user_id = ").append(item.getUser_id());
		sql.append(" AND od.product_id = ").append(item.getProduct_id());
		sql.append(" AND o.order_status = 'Hoàn thành'");
		ResultSet rs = this.get(sql.toString(), 0);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	@Override
	public boolean addProductReview(ProductReview item) {
		if (!this.isBuy(item)) {
			return false;
		}
		
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO product_review(");
		sql.append("product_id, user_id, comment) ");
		sql.append("VALUES(?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getProduct_id());
            pre.setInt(2, item.getUser_id());
            pre.setString(3, item.getComment());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	@Override
	public boolean editProductReview(ProductReview item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE product_review SET ");
		sql.append("comment=? ");
		sql.append("WHERE image_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getComment());
        	pre.setInt(2, item.getProduct_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delProductReview(ProductReview item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM product_review WHERE review_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getReview_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getProductReviews(int id, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT pr.*, u.user_fullname ");
		sql.append("FROM product_review pr ");
		sql.append("JOIN user u ON pr.user_id = u.user_id ");
		sql.append("WHERE pr.product_id = ? ");
		sql.append("ORDER BY pr.create_at DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString(),id);
	}

	@Override
	public ResultSet getTotalProductReview(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(review_id) AS total FROM product_review WHERE product_id=?;";
		return this.get(sql, id);
	}

}
