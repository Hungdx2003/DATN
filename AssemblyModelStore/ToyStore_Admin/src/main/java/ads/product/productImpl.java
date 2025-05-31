package ads.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.ProductObject;

public class productImpl extends basicImpl implements product {
	
	public productImpl() {
		super("Product");
	}
	
	private boolean isExisting(ProductObject item) {
		boolean flag = false;

		String sql = "SELECT product_id FROM product WHERE product_name='" + item.getProduct_name() + "' ";
		ResultSet rs = this.get(sql, 0);
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
	public boolean addProduct(ProductObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO product(");
		sql.append("product_name, product_price, product_quantity, product_created_by, ");
		sql.append("product_pc_id,  product_detail, product_status, product_original_price, product_modified_by, product_brand )");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");
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
            pre.setString(10, item.getProduct_brand());
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
		sql.append("product_name=?, product_price=?, product_quantity=?, ");
		sql.append("product_modified_by=?, product_pc_id=?, ");
		sql.append(" product_detail=?, product_status=?, product_original_price=?, product_brand=? ");
		sql.append("WHERE product_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getProduct_name());
            pre.setInt(2, item.getProduct_price());
            pre.setInt(3, item.getProduct_quantity());
            pre.setInt(4, item.getProduct_modified_by());
            pre.setInt(5, item.getProduct_pc_id());
            pre.setString(6, item.getProduct_detail());
            pre.setString(7, item.getProduct_status());
            pre.setInt(8, item.getProduct_original_price());
            pre.setString(9, item.getProduct_brand());
			pre.setInt(10, item.getProduct_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	private boolean isEmpty(ProductObject item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product_id FROM product_discounts WHERE product_id=").append(item.getProduct_id()).append(";");
		sql.append("SELECT product_id FROM productimages WHERE product_id=").append(item.getProduct_id()).append(";");
		sql.append("SELECT product_id FROM orderdetails WHERE product_id=").append(item.getProduct_id()).append(";");
		sql.append("SELECT product_id FROM cartitem WHERE product_id='").append(item.getProduct_id()).append(";");
		sql.append("SELECT product_id FROM product_review WHERE product_id='").append(item.getProduct_id()).append(";");

		ArrayList<ResultSet> res = this.gets(sql.toString());
		for (ResultSet rs : res) {
			try {
				if (rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	@Override
	public boolean delProduct(ProductObject item) {
		if (!this.isEmpty(item)) {
			return false;
		}
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
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM product ");
		sql.append("");
		sql.append("ORDER BY product_id DESC ");
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
	public ArrayList<ResultSet> getBestSaleByDay(ProductObject similar) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_sale_price, pi.image_url, p.product_price, ");
		sql.append("SUM(od.quantity) AS total_quantity_sold ");
		sql.append("FROM orders o ");
		sql.append("JOIN orderdetails od ON o.order_id = od.order_id ");
		sql.append("JOIN product p ON od.product_id = p.product_id ");
		sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url FROM productimages GROUP BY product_id) pi ");
		sql.append("ON p.product_id = pi.product_id ");
		sql.append("WHERE o.order_status != 'Đã hủy' AND DATE(o.order_date) = CURDATE() ");
		sql.append("GROUP BY p.product_id, p.product_name, p.product_sale_price, pi.image_url ");
		sql.append("ORDER BY total_quantity_sold DESC LIMIT 5;");
	    
	    return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getBestSaleByMonth(ProductObject similar) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_sale_price, pi.image_url, p.product_price, ");
		sql.append("SUM(od.quantity) AS total_quantity_sold ");
		sql.append("FROM orders o ");
		sql.append("JOIN orderdetails od ON o.order_id = od.order_id ");
		sql.append("JOIN product p ON od.product_id = p.product_id ");
		sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url FROM productimages GROUP BY product_id) pi ");
		sql.append("ON p.product_id = pi.product_id ");
		sql.append("WHERE o.order_status != 'Đã hủy' AND MONTH(o.order_date) = MONTH(CURDATE()) ");
		sql.append("AND YEAR(o.order_date) = YEAR(CURDATE()) ");
		sql.append("GROUP BY p.product_id, p.product_name, p.product_sale_price, pi.image_url ");
		sql.append("ORDER BY total_quantity_sold DESC LIMIT 5;");
        
        return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getBestSaleByYear(ProductObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_sale_price, pi.image_url, p.product_price, ");
		sql.append("SUM(od.quantity) AS total_quantity_sold ");
		sql.append("FROM orders o ");
		sql.append("JOIN orderdetails od ON o.order_id = od.order_id ");
		sql.append("JOIN product p ON od.product_id = p.product_id ");
		sql.append("LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url FROM productimages GROUP BY product_id) pi ");
        sql.append("ON p.product_id = pi.product_id ");
        sql.append("WHERE o.order_status != 'Đã hủy' AND YEAR(o.order_date) = YEAR(CURDATE()) ");
        sql.append("GROUP BY p.product_id, p.product_name, p.product_sale_price, pi.image_url ");
        sql.append("ORDER BY total_quantity_sold DESC LIMIT 5;");
	    
	    return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getLowQuantity(ProductObject similar) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, p.product_sale_price, ");
		sql.append("p.product_quantity, pi.image_url ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN productimages pi ON p.product_id = pi.product_id ");
		sql.append("WHERE p.product_quantity < 5;");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getProductDiscount(ProductObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, p.product_sale_price, ");
		sql.append("p.product_status, pi.image_url ");
		sql.append("FROM product p ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("LEFT JOIN productimages pi ON p.product_id = pi.product_id ");
		sql.append("WHERE (pd.discount_id IS NULL OR d.end_date < CURRENT_DATE ");
		sql.append("OR d.is_active = 0) AND p.product_status != 'Hết hàng';");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getProductDiscountById(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.product_id, p.product_name, p.product_price, p.product_sale_price, ");
		sql.append("p.product_status, pi.image_url, pd.pd_id ");
		sql.append("FROM product p ");
		sql.append("JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("LEFT JOIN productimages pi ON p.product_id = pi.product_id ");
		sql.append("WHERE d.discount_id = ? ");
		
		return this.gets(sql.toString(),id);
	}

	@Override
	public boolean editProduct(ArrayList<ProductObject> items) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE product SET  product_sale_price=? ");
		sql.append("WHERE product_id=?");

        try {
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            for (ProductObject item : items) {
                pre.setInt(1, item.getProduct_sale_price());
                pre.setInt(2, item.getProduct_id());
                pre.addBatch();;
            }
            return this.editBatch(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getSlowSellingProduct(ProductObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT p.*, pi.image_url FROM product p ");
		sql.append("LEFT JOIN productimages pi ON p.product_id = pi.product_id ");
		sql.append("ORDER BY p.product_sold ASC LIMIT 5;");
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getBrand(ProductObject similar) {
		// TODO Auto-generated method stub
		String sql="SELECT DISTINCT product_brand FROM product";
		return this.gets(sql);
	}
}
