package client.cartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.basic.basicImpl;
import client.objects.CartItem;

public class cartItemImpl extends basicImpl implements cartItem {

	public cartItemImpl() {
		super("Cart Item");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCartItem(CartItem item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO cartitem(");
		sql.append("cart_id, product_id, quantity, cart_subtotal) ");
		sql.append("VALUES(?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getCart_id());
            pre.setInt(2, item.getProduct_id());
            pre.setInt(3, item.getQuantity());
            pre.setInt(4, item.getCart_subtotal());
            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editCartItem(CartItem item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE cartitem SET ");
		sql.append("quantity=?, cart_subtotal=? ");
		sql.append("WHERE ci_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getQuantity());
            pre.setInt(2, item.getCart_subtotal());
            pre.setInt(3, item.getCi_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delCartItem(CartItem item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM cartitem WHERE ci_id=?";
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
        	pre.setInt(1, item.getCi_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	@Override
	public boolean delCartItems(CartItem item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM cartitem WHERE cart_id=?";
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
	public ArrayList<ResultSet> getCartItem(CartItem similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM cartitem ");
		sql.append("");
		sql.append("ORDER BY cart_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getCartItem(int id, int productId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM cartitem WHERE cart_id=? AND product_id="+productId;
		return this.get(sql, id);
	}
	
	public ArrayList<ResultSet> getCartItems(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ci.ci_id AS cartItemId, ci.product_id, p.product_name, ");
		sql.append("p.product_price, p.product_sale_price, p.product_quantity, ci.quantity, ci.cart_subtotal, ");
		sql.append("(SELECT pi.image_url FROM productimages pi WHERE pi.product_id = p.product_id LIMIT 1) AS image_url, ");
		sql.append("d.discount_id, d.discount_name, d.discount_type, d.discount_value, d.discount_value_type, ");
		sql.append("d.start_date, d.end_date, d.max_users, d.max_usage, d.usage_count, d.is_active ");
		sql.append("FROM cartitem ci ");
		sql.append("JOIN product p ON ci.product_id = p.product_id ");
		sql.append("JOIN cart c ON ci.cart_id = c.cart_id ");
		sql.append("LEFT JOIN product_discounts pd ON p.product_id = pd.product_id ");
		sql.append("LEFT JOIN discounts d ON pd.discount_id = d.discount_id ");
		sql.append("WHERE c.user_id = ?");

		return this.gets(sql.toString(),id);
	}

	@Override
	public ResultSet getTotalCartItem(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COALESCE(SUM(ci.quantity), 0) AS total_quantity ");
		sql.append("FROM cart c ");
		sql.append("LEFT JOIN cartitem ci ON c.cart_id = ci.cart_id ");
		sql.append("WHERE c.user_id = ?;");
		
		return this.get(sql.toString(), id);
	}
}
