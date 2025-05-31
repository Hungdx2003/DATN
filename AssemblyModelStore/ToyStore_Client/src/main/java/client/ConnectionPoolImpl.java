package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import client.ConnectionPoolImpl;

public class ConnectionPoolImpl implements ConnectionPool {
	private String driver;
	private String username;
	private String password;
	private String url;
	
	private Stack<Connection> pool;
	
	//Singleton design pattern
	private static ConnectionPool cp=null;
	
	public ConnectionPoolImpl() {
		// Xac dinh trinh dieu khien
		this.driver = "com.mysql.cj.jdbc.Driver";
		// Xac dinh duong dan 
		this.url="jdbc:mysql://localhost:3306/mecha_toy_store?allowMultiQueries=true";
		this.username="hungdx";
		this.password="abc123!@#";
		
		//Nap trinh dieu khien
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Khoi tao bo nho luu tro doi tuong ket noi
		this.pool=new Stack<>();
	}
	
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		if(this.pool.isEmpty()) {
			System.out.println(objectName+" đã tạo ra một kết nối mới");
			return DriverManager.getConnection(this.url,this.username,this.password);
		}else {
			//Lay ket noi da duoc lu tru
			System.out.println(objectName+" đã xóa kết nối");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(objectName+" đã trả về kết nối");
		this.pool.push(con);
	}
	
	public static ConnectionPool getInstance() {
		if (cp==null) {
			
			synchronized (ConnectionPool.class) {
				if (cp==null) {
					cp=new ConnectionPoolImpl();
				}
			}
			
		}
		return cp;
	}

}
