package client.cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.basic.basicImpl;
import client.objects.CartObject;

public class cartImpl extends basicImpl implements cart {

	public cartImpl() {
		super("Cart");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCart(CartObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO cart(");
		sql.append("user_id) ");
		sql.append("VALUES(?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getUser_id());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editCart(CartObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE cart SET ");
		sql.append("user_id=?");
		sql.append("WHERE cart_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getUser_id());
			pre.setInt(2, item.getCart_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delCart(CartObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM cart WHERE cart_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getCart_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public ResultSet getCart(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM cart WHERE user_id=?";
		return this.get(sql, id);
	}

}
