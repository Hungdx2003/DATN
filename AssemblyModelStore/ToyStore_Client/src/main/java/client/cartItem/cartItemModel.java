package client.cartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.objects.CartItem;
import client.viewModel.cartItemViewModel;

public class cartItemModel {
	private cartItem ci;

	public cartItemModel() {
		this.ci = new cartItemImpl();
	}

	public boolean addCartItem(CartItem item) {
		return this.ci.addCartItem(item);
	}

	public boolean editCartItem(CartItem item) {
		return this.ci.editCartItem(item);
	}

	public boolean delCartItem(CartItem item) {
		return this.ci.delCartItem(item);
	}
	
	public boolean delCartItems(CartItem item) {
		return this.ci.delCartItems(item);
	}

	public CartItem getCartItem(int id, int productId) {
		CartItem item = null;

		ResultSet rs = this.ci.getCartItem(id,productId);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new CartItem();
					item.setCi_id(rs.getInt("ci_id"));
					item.setCart_id(rs.getInt("cart_id"));
					item.setProduct_id(rs.getInt("product_id"));
					item.setQuantity(rs.getInt("quantity"));
					item.setCart_subtotal(rs.getInt("cart_subtotal"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ArrayList<CartItem> getCartItem(CartItem similar, int at, byte total) {
	    ArrayList<CartItem> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.ci.getCartItem(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	CartItem item = new CartItem();
	        	item.setCi_id(rs.getInt("ci_id"));
				item.setCart_id(rs.getInt("cart_id"));
				item.setProduct_id(rs.getInt("product_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setCart_subtotal(rs.getInt("cart_subtotal"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<cartItemViewModel> getCartItems(int id) {
	    ArrayList<cartItemViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.ci.getCartItems(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	cartItemViewModel item = new cartItemViewModel();
	        	item.setCartItemId(rs.getInt("cartItemId"));
	            item.setProductId(rs.getInt("product_id"));
	            item.setProductName(rs.getString("product_name"));
	            item.setProductPrice(rs.getInt("product_price"));
	            item.setProductSalePrice(rs.getInt("product_sale_price"));
	            item.setProductQuantity(rs.getInt("product_quantity"));
	            item.setQuantity(rs.getInt("quantity"));
	            item.setImageUrl(rs.getString("image_url"));
	            item.setSubtotal(rs.getInt("cart_subtotal"));
	            
	            item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate ("start_date"));
				item.setEndDate(rs.getDate ("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public int getTotalCartItem(int id) {
		ResultSet res=this.ci.getTotalCartItem(id);
		
		int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total_quantity");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
}
