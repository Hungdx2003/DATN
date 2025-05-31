package client.news;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.NewsObject;

public interface news {
	boolean addNews(NewsObject item);
	boolean editNews(NewsObject item);
	boolean delNews(NewsObject item);
	
	ArrayList<ResultSet> getNews(NewsObject similar);
	ArrayList<ResultSet> getNews(NewsObject similar, int at, byte total);
	ArrayList<ResultSet> getHotNews(NewsObject similar);
	ArrayList<ResultSet> getAdjacentNews(int id);
	
	ResultSet getNews(int id);
	ResultSet getTotalNews(NewsObject similar);
}
