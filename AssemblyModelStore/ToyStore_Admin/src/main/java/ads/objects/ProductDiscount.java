package ads.objects;
import lombok.Data;

@Data
public class ProductDiscount {
	private int pd_id; 
	private int product_id;
	private int discount_id;
}
