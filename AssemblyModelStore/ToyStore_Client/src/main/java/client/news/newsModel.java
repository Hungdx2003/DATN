package client.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.NewsObject;

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
	
	public ArrayList<NewsObject> getNews(NewsObject similar, int at, byte total) {
	    ArrayList<NewsObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.n.getNews(null, at, total);
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
	
	public int getTotalNews() {
		ResultSet res = this.n.getTotalNews(null);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public ArrayList<NewsObject> getHotNews() {
	    ArrayList<NewsObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.n.getHotNews(null);
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
	        	item.setDescription(rs.getString("description"));
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<Integer> getAdjacentNews(int id) {
	    ArrayList<Integer> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.n.getAdjacentNews(id);

	    Integer previousId = null;
	    Integer nextId = null;

	    if (res != null) {
	        ResultSet rsPrev = null;
	        ResultSet rsNext = null;

	        if (res.size() > 0) rsPrev = res.get(0);
	        if (res.size() > 1) rsNext = res.get(1);

	        try {
	            if (rsPrev != null && rsPrev.next()) {
	                previousId = rsPrev.getInt("previous_id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rsPrev != null) rsPrev.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        try {
	            if (rsNext != null && rsNext.next()) {
	                nextId = rsNext.getInt("next_id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rsNext != null) rsNext.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    list.add(previousId);
	    list.add(nextId);

	    return list;
	}
}
