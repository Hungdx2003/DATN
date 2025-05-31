package ads.objects;

import lombok.*;

@Data
public class PaymentObject {
	private int payment_id;
	private int order_id;
	private String payment_method;
	private String payment_status;
}
