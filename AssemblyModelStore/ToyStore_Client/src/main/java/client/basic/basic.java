package client.basic;

import java.sql.*;
import java.util.*;

public interface basic {
	boolean add(PreparedStatement pre);
	boolean edit(PreparedStatement pre);
	boolean del(PreparedStatement pre);
	
	ArrayList<ResultSet> gets(String multiSelect);
	ArrayList<ResultSet> gets(String sql,int value);
	ResultSet get(String sql,int value);
	ResultSet get(String sql,String name, String pass);
	ResultSet get(String sql,String name);
	<T> ResultSet get(String sql, List<T> values);
	<T> ArrayList<ResultSet> gets(String sql, List<T> values);
	
	void releaseConnection();
}
