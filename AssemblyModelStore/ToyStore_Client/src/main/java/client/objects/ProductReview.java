package client.objects;
import lombok.Data;

@Data
public class ProductReview {
	private int review_id;
	private int product_id;
	private int user_id;
	private String comment;
	private String creat_at;
}
