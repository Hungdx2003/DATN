package client.objects;

import lombok.*;

@Data
public class CartItem {
	private int ci_id;
	private int cart_id;
	private int product_id;
	private int quantity;
	private int cart_subtotal;
	private ProductObject product;
}
