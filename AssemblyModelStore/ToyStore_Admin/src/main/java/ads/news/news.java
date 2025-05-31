package ads.news;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.NewsObject;

public interface news {
	boolean addNews(NewsObject item);
	boolean editNews(NewsObject item);
	boolean delNews(NewsObject item);
	
	ArrayList<ResultSet> getNews(NewsObject similar);
	ResultSet getNews(int id);
}
