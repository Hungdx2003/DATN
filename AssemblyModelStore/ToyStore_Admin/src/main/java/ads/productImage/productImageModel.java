package ads.productImage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.ProductImage;

public class productImageModel {
	private productImage p;

	public productImageModel() {
		this.p = new productImageImpl();
	}

	public boolean addProductImage(ProductImage item) {
		return this.p.addProductImage(item);
	}

	public boolean editProductImage(ProductImage item) {
		return this.p.editProductImage(item);
	}

	public boolean delProductImage(ProductImage item) {
		return this.p.delProductImage(item);
	}

	public ProductImage getProductImage(int id) {
		ProductImage item = null;

		ResultSet rs = this.p.getProductImage(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductImage();
					item.setImage_id(rs.getInt("image_id"));
					item.setProduct_id(rs.getInt("product_id"));
					item.setImage_url(rs.getString("image_url"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<ProductImage> getProductImage(ProductImage similar, int at, byte total) {
	    ArrayList<ProductImage> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getProductImage(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	ProductImage item = new ProductImage();
	        	item.setImage_id(rs.getInt("image_id"));
				item.setProduct_id(rs.getInt("product_id"));
				item.setImage_url(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
