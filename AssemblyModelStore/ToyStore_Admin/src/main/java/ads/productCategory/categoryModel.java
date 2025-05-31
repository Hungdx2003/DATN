package ads.productCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.ProductCategory;

public class categoryModel {
	private productCategory pc;

	public categoryModel() {
		this.pc = new productCategoryImpl();
	}

	public boolean addProductCategory(ProductCategory item) {
		return this.pc.addProductCategory(item);
	}

	public boolean editProductCategory(ProductCategory item) {
		return this.pc.editProductCategory(item);
	}

	public boolean delProductCategory(ProductCategory item) {
		return this.pc.delProductCategory(item);
	}

	public ProductCategory getProductCategory(int id) {
		ProductCategory item = null;

		ResultSet rs = this.pc.getProductCategory(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductCategory();
					item.setCategory_id(rs.getInt("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_created_by(rs.getInt("category_created_by"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_modified_by(rs.getInt("category_modified_by"));
					item.setCategory_modified_date(rs.getString("category_modified_date"));
					item.setParent_category_id(rs.getInt("parent_category_id"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<ProductCategory> getProductCategory(ProductCategory similar, int at, byte total) {
	    ArrayList<ProductCategory> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.pc.getProductCategory(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	ProductCategory item = new ProductCategory();
	        	item.setCategory_id(rs.getInt("category_id"));
				item.setCategory_name(rs.getString("category_name"));
				item.setCategory_created_by(rs.getInt("category_created_by"));
				item.setCategory_created_date(rs.getString("category_created_date"));
				item.setCategory_modified_by(rs.getInt("category_modified_by"));
				item.setCategory_modified_date(rs.getString("category_modified_date"));
				item.setParent_category_id(rs.getInt("parent_category_id"));
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
