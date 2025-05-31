package client.cartItem;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.CartItem;

public interface cartItem {
	boolean addCartItem(CartItem item);
	boolean editCartItem(CartItem item);
	boolean delCartItem(CartItem item);
	boolean delCartItems(CartItem item);
	
	ArrayList<ResultSet> getCartItem(CartItem similar, int at, byte total);
	ArrayList<ResultSet> getCartItems(int id);
	
	ResultSet getCartItem(int id, int productId);
	ResultSet getTotalCartItem(int id);
}
