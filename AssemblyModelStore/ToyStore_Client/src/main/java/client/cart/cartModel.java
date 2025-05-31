package client.cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import client.objects.CartObject;

public class cartModel {
	private cart c;

	public cartModel() {
		this.c = new cartImpl();
	}

	public boolean addCart(CartObject item) {
		return this.c.addCart(item);
	}

	public boolean editCart(CartObject item) {
		return this.c.editCart(item);
	}

	public boolean delCart(CartObject item) {
		return this.c.delCart(item);
	}

	public CartObject getCart(int id) {
		CartObject item = null;

		ResultSet rs = this.c.getCart(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new CartObject();
					item.setCart_id(rs.getInt("cart_id"));
					item.setUser_id(rs.getInt("user_id"));
					item.setCart_created_date(rs.getString("cart_created_date"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
}
