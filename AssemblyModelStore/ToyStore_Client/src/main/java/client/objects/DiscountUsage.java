package client.objects;

import lombok.*;

@Data
public class DiscountUsage {
	private int usage_id;
	private int user_id;
	private int discount_id;
	private int order_id;
	private String used_at;
}
