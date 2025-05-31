package client.viewModel;
import lombok.Data;

@Data
public class orderView {
	private int orderId;
    private int userId;
    private String orderDate;
    private String receiverName;
    private String receiverMobilephone;
    private String deliveryAddress;
    private String orderStatus;
    private int totalAmount;
    private String email;
    private int discountMoney;
	private int totalOrderValue;
    
    private String paymentMethod;
    private String paymentStatus;
    
    private String address;
    private String ward;
    private String district;
    private String province;

}
