package client.viewModel;
import lombok.Data;

@Data
public class ProductReviewViewModel {
	private int reviewId;
	private int productId;
	private int userId;
	private String userFullName;
	private char firstCharName;
	private String comment;
	private String createAt;
	private String color;
}
