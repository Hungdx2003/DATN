package ads.image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.ImageObject;

public class imageModel {
	private image i;

	public imageModel() {
		this.i = new imageImpl();
	}

	public boolean addImage(ImageObject item) {
		return this.i.addImage(item);
	}

	public boolean editImage(ImageObject item) {
		return this.i.editImage(item);
	}

	public boolean delImage(ImageObject item) {
		return this.i.delImage(item);
	}

	public ImageObject getImage(int id) {
		ImageObject item = null;

		ResultSet rs = this.i.getImage(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ImageObject();
					item.setImage_id(rs.getInt("image_id"));
					item.setImage_url(rs.getString("image_url"));
					item.setType(rs.getString("type"));
					item.setUpload_date(rs.getString("upload_date"));
					item.setActive(rs.getBoolean("is_active"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<ImageObject> getImages(String type) {
	    ArrayList<ImageObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.i.getImages(null,type);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	ImageObject item = new ImageObject();
	        	item.setImage_id(rs.getInt("image_id"));
				item.setImage_url(rs.getString("image_url"));
				item.setType(rs.getString("type"));
				item.setUpload_date(rs.getString("upload_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
