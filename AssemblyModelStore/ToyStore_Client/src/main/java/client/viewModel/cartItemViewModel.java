package client.viewModel;
import java.sql.Date;

import lombok.Data;

@Data
public class cartItemViewModel {
	private int cartItemId;
    private int productId;
    private String productName;
    private int productPrice;
    private int productSalePrice;
    private int productQuantity;
    private int quantity;
    private String imageUrl;
    private int subtotal;
    
    private String discountName;
    private String discountType;
    private int discountValue;
    private String discountValueType;
    private Date  startDate;
    private Date  endDate;
    private boolean active;
}
