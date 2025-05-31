package client.cart;

import java.sql.ResultSet;

import client.objects.CartObject;

public interface cart {
	boolean addCart(CartObject item);
	boolean editCart(CartObject item);
	boolean delCart(CartObject item);
	
	ResultSet getCart(int id);
}
