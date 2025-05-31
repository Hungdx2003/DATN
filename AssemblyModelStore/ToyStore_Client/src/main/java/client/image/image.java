package client.image;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.ImageObject;

public interface image {
	boolean addImage(ImageObject item);
	boolean editImage(ImageObject item);
	boolean delImage(ImageObject item);
	
	ArrayList<ResultSet> getImages(ImageObject similar, String type);
	ResultSet getImage(int id);
}
