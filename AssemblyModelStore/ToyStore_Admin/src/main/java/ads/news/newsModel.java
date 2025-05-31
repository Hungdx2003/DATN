package ads.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.NewsObject;

public class newsModel {
	private news n;

	public newsModel() {
		this.n = new newsImpl();
	}

	public boolean addNews(NewsObject item) {
		return this.n.addNews(item);
	}

	public boolean editNews(NewsObject item) {
		return this.n.editNews(item);
	}

	public boolean delNews(NewsObject item) {
		return this.n.delNews(item);
	}

	public NewsObject getNews(int id) {
		NewsObject item = null;

		ResultSet rs = this.n.getNews(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new NewsObject();
					item.setNews_id(rs.getInt("news_id"));
		        	item.setNews_title(rs.getString("news_title"));
		        	item.setNews_content(rs.getString("news_content"));
		        	item.setNews_author(rs.getString("news_author"));
		        	item.setNews_cover_image(rs.getString("news_cover_image"));
		        	item.setNews_create_date(rs.getString("news_create_date"));
		        	item.setNews_created_by(rs.getInt("news_created_by"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<NewsObject> getNews() {
	    ArrayList<NewsObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.n.getNews(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	NewsObject item = new NewsObject();
	        	item.setNews_id(rs.getInt("news_id"));
	        	item.setNews_title(rs.getString("news_title"));
	        	item.setNews_content(rs.getString("news_content"));
	        	item.setNews_author(rs.getString("news_author"));
	        	item.setNews_cover_image(rs.getString("news_cover_image"));
	        	item.setNews_create_date(rs.getString("news_create_date"));
	        	item.setNews_created_by(rs.getInt("news_created_by"));
	        	
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
