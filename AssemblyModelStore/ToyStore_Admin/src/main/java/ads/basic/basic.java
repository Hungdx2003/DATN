package ads.basic;

import java.sql.*;
import java.util.*;

public interface basic {
	boolean add(PreparedStatement pre);
	boolean edit(PreparedStatement pre);
	boolean del(PreparedStatement pre);
	
	ArrayList<ResultSet> gets(String multiSelect);
	ResultSet get(String sql,int value);
	ResultSet get(String sql,String name, String pass);
	ArrayList<ResultSet> gets(String sql, int value);
	
	boolean addBatch(PreparedStatement pre);
	boolean editBatch(PreparedStatement pre);
	boolean delBatch(PreparedStatement pre);
	
	void releaseConnection();
}
