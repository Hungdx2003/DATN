package client.objects;

import lombok.*;

@Data
public class OrderDetail {
	private int od_id;
	private int order_id;
	private int product_id;
	private int quantity;
	private int od_subtotal;
	private int product_price;
}
