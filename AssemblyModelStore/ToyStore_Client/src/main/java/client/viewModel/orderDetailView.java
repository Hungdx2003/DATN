package client.viewModel;
import lombok.Data;

@Data
public class orderDetailView {
	private int odId;                
    private int orderId;             
    private int productId;           
    private String productName;      
    private int productPrice;     
    private int quantity;            
    private int subtotal;         
    private String imageUrl; 
}
