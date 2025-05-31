package ads.objects;

import lombok.*;

@Data
public class ProductObject {
	private int product_id;
	private String product_name;
	private String product_brand;
	private int product_price;
	private int product_quantity;
	private int product_created_by;
	private String product_created_date;
	private String product_modified_date;
	private int product_modified_by;
	private int product_pc_id;
	private String product_detail;
	private int product_sold;
	private int product_original_price;
	private int product_sale_price;
	private String product_status;
	private ProductImage product_image;
}
