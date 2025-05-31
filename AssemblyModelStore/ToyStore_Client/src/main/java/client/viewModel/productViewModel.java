package client.viewModel;
import java.sql.Date;

import lombok.Data;

@Data
public class productViewModel {
    private int productId;
    private String productName;
    private String productBrand;
    private int productPrice;
    private int productSalePrice;
    private String productDetail;
    private int productSold;
    private int productQuantity;
    private String productStatus;
    private String imageUrl;
    private String categoryName;
    private int categoryId;
    
    private String discountName;
    private String discountType;
    private int discountValue;
    private String discountValueType;
    private Date  startDate;
    private Date  endDate;
    private boolean active;
}
