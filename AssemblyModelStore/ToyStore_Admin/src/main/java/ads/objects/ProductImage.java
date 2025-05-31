package ads.objects;

import lombok.*;

@Data
public class ProductImage {
	private int image_id; 
	private int product_id; 
	private String image_url;
}
