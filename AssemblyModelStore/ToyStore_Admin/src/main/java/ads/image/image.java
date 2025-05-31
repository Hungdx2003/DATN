package ads.image;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.ImageObject;

public interface image {
	boolean addImage(ImageObject item);
	boolean editImage(ImageObject item);
	boolean delImage(ImageObject item);
	
	ArrayList<ResultSet> getImages(ImageObject similar, String type);
	ResultSet getImage(int id);
}
