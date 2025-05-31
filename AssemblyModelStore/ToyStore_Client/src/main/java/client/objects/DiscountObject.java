package client.objects;

import lombok.*;

@Data
public class DiscountObject {
	private int discount_id;
	private String discount_name;
	private String discount_type;
	private int discount_value;
	private String start_date;
	private String end_date;
	private Integer max_users;
	private Integer max_usage;
	private int usage_count;
	private boolean active;
	private String discount_value_type;
}
