package client.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.basic.basicImpl;
import client.objects.ProductObject;

public class productImpl extends basicImpl implements product {
	
	public productImpl() {
		super("Product");
	}

	@Override
	public boolean addProduct(ProductObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO product(");
		sql.append("product_name, product_price, product_quantity, product_created_by, ");
		sql.append("product_pc_id,  product_detail, product_status, product_original_price, product_modified_by )");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getProduct_name());
            pre.setInt(2, item.getProduct_price());
            pre.setInt(3, item.getProduct_quantity());
            pre.setInt(4, item.getProduct_created_by());
            pre.setInt(5, item.getProduct_pc_id());
            pre.setString(6, item.getProduct_detail());
            pre.setString(7, item.getProduct_status());
            pre.setInt(8, item.getProduct_original_price());
            pre.setInt(9, item.getProduct_modified_by());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editProduct(ProductObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE product SET ");
		sql.append("product_quantity=?, product_sold=?, product_status=? ");
		sql.append("WHERE product_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getProduct_quantity());
            pre.setInt(2, item.getProduct_sold());
            pre.setString(3, item.getProduct_status());
			pre.setInt(4, item.getProduct_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delProduct(ProductObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM product WHERE product_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getProduct_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getProduct(ProductObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
		sql.append("p.product_sale_price, p.product_quantity, ");
		sql.append("p.product_detail, p.product_sold, p.product_status, ");
		sql.append("(SELECT pi.image_url FROM productimages pi ");
		sql.append("WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
		sql.append("pc.category_name, ");
		sql.append("d.discount_name, d.discount_type, d.discount_value, ");
		sql.append("d.discount_value_type, d.start_date, d.end_date, d.is_active  ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productcategories pc ON p.product_pc_id = pc.category_id ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("ORDER BY p.product_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getProduct(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE product_id=?";
		return this.get(sql, id);
	}
	
	public ResultSet getProductByCreator(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE product_created_by=? ORDER BY product_id DESC LIMIT 1";
		return this.get(sql, id);
	}

	@Override
	public ResultSet GetTotalProducts(int page) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_id) AS total FROM product";
		return this.get(sql, page);
	}

	@Override
	public ArrayList<ResultSet> getProductByCategory(List<Integer> categoryIds, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
	    for (int i = 0; i < categoryIds.size(); i++) {
	    	str.append(categoryIds.get(i));
	        if (i < categoryIds.size() - 1) {
	        	str.append(",");
	        }
	    }
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
	    sql.append("p.product_sale_price, p.product_quantity, ");
	    sql.append("p.product_detail, p.product_sold, p.product_status, ");
	    sql.append("(SELECT pi.image_url FROM productimages pi ");
	    sql.append("WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
	    sql.append("pc.category_name, ");
	    sql.append("d.discount_id, d.discount_name, d.discount_type, d.discount_value, ");
	    sql.append("d.discount_value_type, d.start_date, d.end_date, d.is_active ");
	    sql.append("FROM product p ");
	    sql.append("LEFT JOIN productcategories pc ON p.product_pc_id = pc.category_id ");
	    sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
	    sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
	    sql.append("WHERE p.product_pc_id IN (").append(str.toString()).append(") ");
	    sql.append("ORDER BY p.product_id DESC ");
	    sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

	    return this.gets(sql.toString());
	}

	@Override
	public ResultSet GetTotalProductByCategory(int page, List<Integer> categoryIds) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
	    for (int i = 0; i < categoryIds.size(); i++) {
	    	str.append("?");
	        if (i < categoryIds.size() - 1) {
	        	str.append(",");
	        }
	    }
	    
	    String sql="SELECT COUNT(product_id) AS total FROM product WHERE product_pc_id IN ("+str.toString()+")";
	    return this.get(sql,categoryIds);
	}

	@Override
	public ArrayList<ResultSet> getProductByWord(String word, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
		sql.append("p.product_sale_price, p.product_quantity, ");
		sql.append("p.product_detail, p.product_sold, p.product_status, ");
		sql.append("(SELECT pi.image_url FROM productimages pi ");
		sql.append("WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
		sql.append("pc.category_name, ");
		sql.append("d.discount_id, d.discount_name, d.discount_type, d.discount_value, ");
		sql.append("d.discount_value_type, d.start_date, d.end_date, d.is_active ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productcategories pc ON p.product_pc_id = pc.category_id ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("WHERE p.product_name LIKE '%").append(word).append("%' ");
		sql.append("ORDER BY p.product_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

		return this.gets(sql.toString());
	}

	@Override
	public ResultSet GetTotalProductByWord(int page, String word) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_id) AS total FROM product WHERE product_name LIKE '%"+word+"%';";
		return this.get(sql,page);
	}

	@Override
	public ArrayList<ResultSet> getNewProduct(ProductObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
	    sql.append("p.product_sale_price, p.product_quantity, ");
	    sql.append("p.product_detail, p.product_sold, p.product_status, ");
	    sql.append("(SELECT pi.image_url FROM productimages pi ");
	    sql.append("WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
	    sql.append("pc.category_name ");
	    sql.append("FROM product p ");
	    sql.append("LEFT JOIN productcategories pc ON p.product_pc_id = pc.category_id ");
		sql.append("ORDER BY p.product_created_date DESC LIMIT 8;");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getBestSeller(ProductObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
	    sql.append("p.product_sale_price, p.product_quantity, ");
	    sql.append("p.product_detail, p.product_sold, p.product_status, ");
	    sql.append("(SELECT pi.image_url FROM productimages pi ");
	    sql.append("WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
	    sql.append("pc.category_name ");
	    sql.append("FROM product p ");
	    sql.append("LEFT JOIN productcategories pc ON p.product_pc_id = pc.category_id ");
		sql.append("ORDER BY p.product_sold DESC LIMIT 3;");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getSaleProduct(ProductObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, p.product_sale_price, ");
		sql.append("p.product_detail, p.product_sold, p.product_quantity, p.product_status, ");
		sql.append("c.category_name, d.discount_name, d.discount_type, d.discount_value, d.start_date, d.end_date, d.is_active, ");
		sql.append("d.discount_value_type, pi.image_url ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productcategories c ON p.product_pc_id = c.category_id ");
		sql.append("INNER JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("INNER JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("AND d.is_active = 1 AND d.start_date <= NOW() AND d.end_date >= NOW() ");
		sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url FROM productimages GROUP BY product_id) pi ");
		sql.append("ON p.product_id = pi.product_id");

	    return this.gets(sql.toString());
	}

	@Override
	public ResultSet getProductDetail(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
		sql.append("p.product_sale_price, p.product_detail, p.product_sold, p.product_brand, ");
		sql.append("p.product_quantity, p.product_status, c.category_name, c.category_id, ");
		sql.append("d.discount_name, d.discount_type, d.discount_value, d.start_date, d.end_date, d.is_active,  ");
		sql.append("d.discount_value_type, pi.image_url ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productcategories c ON p.product_pc_id = c.category_id ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("AND d.is_active = 1 AND d.start_date <= NOW() ");
		sql.append("AND d.end_date >= NOW() ");
		sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url ");
		sql.append("FROM productimages GROUP BY product_id) pi ");
		sql.append("ON p.product_id = pi.product_id ");
		sql.append("WHERE p.product_id = ?");
		return this.get(sql.toString(),id);
	}
	
	public ArrayList<ResultSet> GetBrand(ProductObject item) {
		// TODO Auto-generated method stub
		String sql="SELECT DISTINCT product_brand FROM product WHERE product_brand IS NOT NULL;";
	    return this.gets(sql);
	}
	
	public ArrayList<ResultSet> GetProductByBrand(List<String> brands, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
	    for (int i = 0; i < brands.size(); i++) {
	    	str.append("?");
	        if (i < brands.size() - 1) {
	        	str.append(",");
	        }
	    }
	    
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT p.product_id, p.product_name, p.product_price, ");
	    sql.append("p.product_sale_price, p.product_detail, p.product_sold, p.product_brand, ");
	    sql.append("p.product_quantity, p.product_status, c.category_name, c.category_id, ");
	    sql.append("d.discount_name, d.discount_type, d.discount_value, d.start_date, d.end_date, d.is_active, ");
	    sql.append("d.discount_value_type, pi.image_url ");
	    sql.append("FROM product p ");
	    sql.append("LEFT JOIN productcategories c ON p.product_pc_id = c.category_id ");
	    sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
	    sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
	    sql.append("AND d.is_active = 1 AND d.start_date <= NOW() ");
	    sql.append("AND d.end_date >= NOW() ");
	    sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url ");
	    sql.append("FROM productimages GROUP BY product_id) pi ");
	    sql.append("ON p.product_id = pi.product_id ");
	    sql.append("WHERE p.product_brand IN (").append(str.toString()).append(") ");
		sql.append("ORDER BY p.product_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
	    return this.gets(sql.toString(),brands);
	}
	
	@Override
	public ResultSet GetTotalProductByBrand(List<String> brands) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
	    for (int i = 0; i < brands.size(); i++) {
	    	str.append("?");
	        if (i < brands.size() - 1) {
	        	str.append(",");
	        }
	    }
	    
	    String sql="SELECT COUNT(product_id) AS total FROM product WHERE product_brand IN ("+str.toString()+")";
	    return this.get(sql,brands);
	}
	
	public ArrayList<ResultSet> GetRelatedProduct(int currentProductId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.*, pi.image_url, d.discount_id, d.discount_name, d.discount_type, ");
		sql.append("d.discount_value, d.discount_value_type, d.start_date, d.end_date, d.is_active ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productimages pi ON p.product_id = pi.product_id ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("WHERE p.product_status = 'Còn hàng' AND p.product_quantity > 0 ");
		sql.append("AND (");
		sql.append("(SELECT product_pc_id FROM product WHERE product_id = ").append(currentProductId).append(") = 4 AND p.product_pc_id != 4 ");
		sql.append("OR ");
		sql.append("(SELECT product_pc_id FROM product WHERE product_id = ").append(currentProductId).append(") != 4 AND p.product_pc_id = 4 ");
		sql.append(") ");
		sql.append("ORDER BY RAND() ").append("LIMIT 8");

	    return this.gets(sql.toString());
	}
}
