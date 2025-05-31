package ads.productCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.ProductCategory;

public class productCategoryImpl extends basicImpl implements productCategory {

	public productCategoryImpl() {
		super("ProductCategory");
		// TODO Auto-generated constructor stub
	}
	
	private boolean isExisting(ProductCategory item) {
		boolean flag = false;

		String sql = "SELECT category_id FROM productcategories WHERE category_name='" + item.getCategory_name() + "' ";
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
	public boolean addProductCategory(ProductCategory item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO productcategories(");
		sql.append("category_name, category_created_by, category_modified_by) ");
		sql.append("VALUES(?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getCategory_name());
            pre.setInt(2, item.getCategory_created_by());
            pre.setInt(3, item.getCategory_modified_by());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editProductCategory(ProductCategory item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE productcategories SET ");
		sql.append("category_name=?, category_modified_by=?, parent_category_id=? ");
		sql.append("WHERE category_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getCategory_name());
            pre.setInt(2, item.getCategory_modified_by());
            pre.setInt(3, item.getParent_category_id());
            pre.setInt(4, item.getCategory_id());
            
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;	
	}
	
	private boolean isEmpty(ProductCategory item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT category_id FROM product WHERE product_pc_id=").append(item.getCategory_id()).append(";");

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
	public boolean delProductCategory(ProductCategory item) {
		// TODO Auto-generated method stub
		if (!this.isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE FROM productcategories WHERE category_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getCategory_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getProductCategory(ProductCategory similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM productcategories ");
		sql.append("");
		sql.append("ORDER BY category_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getProductCategory(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM productcategories WHERE category_id=?";
		return this.get(sql, id);
	}

}
