package client.image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.ImageObject;

public class imageImpl extends basicImpl implements image {
	
	public imageImpl() {
		super("Image");
	}
	@Override
	public boolean addImage(ImageObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO images(");
		sql.append("image_url, type, is_active) ");
		sql.append("VALUES(?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getImage_url());
            pre.setString(2, item.getType());
            pre.setBoolean(3, item.isActive());
            
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editImage(ImageObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE images SET ");
		sql.append("is_active=? ");
		sql.append("WHERE image_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setBoolean(1, item.isActive());
            pre.setInt(2, item.getImage_id());
            
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delImage(ImageObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM images WHERE image_id=?";
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
	public ArrayList<ResultSet> getImages(ImageObject similar, String type) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM images ");
		sql.append("WHERE type='").append(type).append("' ");
		sql.append("AND is_active = 1;");
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getImage(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM images WHERE image_id=?";
		return this.get(sql, id);
	}

}
