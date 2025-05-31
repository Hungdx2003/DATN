package ads.news;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.NewsObject;

public class newsImpl extends basicImpl implements news {

	public newsImpl() {
		super("News");
		// TODO Auto-generated constructor stub
	}
	
	private boolean isExisting(NewsObject item) {
		boolean flag = false;

		String sql = "SELECT news_id FROM news WHERE news_title='" + item.getNews_title() + "' ";
		ResultSet rs = this.get(sql, 0);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	@Override
	public boolean addNews(NewsObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO news(");
		sql.append("news_title, news_content, news_author, news_cover_image, news_created_by, description) ");
		sql.append("VALUES(?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getNews_title());
            pre.setString(2, item.getNews_content());
            pre.setString(3, item.getNews_author());
            pre.setString(4, item.getNews_cover_image());
        	pre.setInt(5, item.getNews_created_by());
    		pre.setString(6, item.getDescription());
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
		sql.append("news_title=?, news_content=?, news_author=?, news_cover_image=?, description=?");
		sql.append("WHERE news_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setString(1, item.getNews_title());
            pre.setString(2, item.getNews_content());
            pre.setString(3, item.getNews_author());
            pre.setString(4, item.getNews_cover_image());
            pre.setString(5, item.getDescription());
        	pre.setInt(6, item.getNews_id());
            	
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

}
