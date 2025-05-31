package ads.viewModel;
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
    
    private String discountName;
    private String discountType;
    private int discountValue;
    private String discountValueType;
    private String startDate;
    private String endDate;
    
    private int pd_id;
}
