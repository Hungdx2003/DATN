package client.productCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import client.objects.ProductCategory;

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
				 rs.close();
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
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<ProductCategory> getCategory(int id) {
	    ArrayList<ProductCategory> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.pc.getCategory(id);
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
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<ProductCategory> getAllSubCategories(int parentId) {
	    ArrayList<ProductCategory> result = new ArrayList<>();
	    Queue<Integer> queue = new LinkedList<>();
	    Set<Integer> visited = new HashSet<>();

	    queue.add(parentId);
	    visited.add(parentId);

	    while (!queue.isEmpty()) {
	        int currentId = queue.poll();
	        ArrayList<ProductCategory> children = getCategory(currentId);

	        for (ProductCategory child : children) {
	            if (!visited.contains(child.getCategory_id())) {
	                result.add(child);
	                queue.add(child.getCategory_id());
	                visited.add(child.getCategory_id());
	            }
	        }
	    }

	    return result;
	}

}
