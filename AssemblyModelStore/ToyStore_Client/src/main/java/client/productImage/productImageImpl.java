package client.productImage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.ProductImage;

public class productImageImpl extends basicImpl implements productImage {
	
	public productImageImpl() {
		super("ProductImage");
	}

	@Override
	public boolean addProductImage(ProductImage item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO productimages(");
		sql.append("product_id, image_url) ");
		sql.append("VALUES(?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getProduct_id());
            pre.setString(2, item.getImage_url());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editProductImage(ProductImage item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE productimages SET ");
		sql.append("product_id=?, image_url=? ");
		sql.append("WHERE image_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setInt(1, item.getProduct_id());
            pre.setString(2, item.getImage_url());
            pre.setInt(3, item.getImage_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delProductImage(ProductImage item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM productimages WHERE image_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getImage_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getProductImage(ProductImage similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM productimages ");
		sql.append("");
		sql.append("ORDER BY image_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getProductImage(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM productimages WHERE product_id=?";
		return this.get(sql, id);
	}

}
