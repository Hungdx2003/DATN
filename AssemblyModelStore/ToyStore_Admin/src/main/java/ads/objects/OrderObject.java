package ads.objects;

import lombok.*;

@Data
public class OrderObject {
	private int order_id;
	private int user_id;
	private String order_date;
	private String receiver_name;
	private String receiver_mobilephone;
	private String delivery_address;
	private String order_status;
	private int total_amount;
	private int discount_money;
	private int total_order_value;
	private String email;
}
