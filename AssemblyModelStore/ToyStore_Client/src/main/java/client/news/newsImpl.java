package client.news;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.NewsObject;

public class newsImpl extends basicImpl implements news {

	public newsImpl() {
		super("News");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addNews(NewsObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO news(");
		sql.append("news_title, news_content, news_author, news_cover_image, news_create_date, news_created_by) ");
		sql.append("VALUES(?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getNews_title());
            pre.setString(2, item.getNews_content());
            pre.setString(3, item.getNews_author());
            pre.setString(4, item.getNews_cover_image());
            pre.setString(5, item.getNews_create_date());
        	pre.setInt(6, item.getNews_created_by());
            	
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editNews(NewsObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE news SET ");
		sql.append("news_title=?, news_content=?, news_author=?, news_cover_image=? ");
		sql.append("WHERE news_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getNews_title());
            pre.setString(2, item.getNews_content());
            pre.setString(3, item.getNews_author());
            pre.setString(4, item.getNews_cover_image());
        	pre.setInt(5, item.getNews_id());
            	
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delNews(NewsObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM news WHERE news_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getNews_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ArrayList<ResultSet> getNews(NewsObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM news ");
		sql.append("");
		sql.append("ORDER BY news_id DESC; ");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getNews(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM news WHERE news_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getNews(NewsObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM news ");
		sql.append("");
		sql.append("ORDER BY news_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getTotalNews(NewsObject similar) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(news_id) AS total FROM news";
		return this.get(sql, 0);
	}

	@Override
	public ArrayList<ResultSet> getHotNews(NewsObject similar) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM news ORDER BY news_create_date DESC LIMIT 4";
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getAdjacentNews(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT news_id AS previous_id FROM news WHERE news_id <").append(id).append(" ORDER BY news_id DESC LIMIT 1;\n");
		sql.append("SELECT news_id AS next_id FROM news WHERE news_id >").append(id).append(" ORDER BY news_id ASC LIMIT 1;");
		
		return this.gets(sql.toString());
	}

}
