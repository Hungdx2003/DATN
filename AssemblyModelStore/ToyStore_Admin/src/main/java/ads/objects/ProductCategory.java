package ads.objects;

import lombok.*;

@Data
public class ProductCategory {
	private int category_id;
	private String category_name;
	private int category_created_by;
	private String category_created_date;
	private String category_modified_date;
	private int category_modified_by;
	private int parent_category_id;
}
