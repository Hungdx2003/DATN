package client.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.cart.cartModel;
import client.cartItem.cartItemModel;
import client.discountUsage.discountUsageModel;
import client.objects.CartItem;
import client.objects.DiscountUsage;
import client.objects.OrderDetail;
import client.objects.OrderObject;
import client.objects.PaymentObject;
import client.objects.ProductObject;
import client.objects.UserObject;
import client.orderDetail.orderDetailModel;
import client.payment.paymentModel;
import client.product.productModel;
import client.viewModel.cartItemViewModel;

/**
 * Servlet implementation class vnpayReturn
 */
@WebServlet("/vnpay-return")
public class vnpayReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vnpayReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserObject uo=(UserObject) session.getAttribute("logUser");
		
		if (uo!=null) {
			String responseCode = request.getParameter("vnp_ResponseCode");

	        if (!"00".equals(responseCode)) {
	            session.setAttribute("notification_message", "Thanh toán thất bại hoặc bị hủy.");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	            return;
	        }

	        int userId = (int) session.getAttribute("userId");
	        String fullname = (String) session.getAttribute("order_info_fullname");
	        String email = (String) session.getAttribute("order_info_email");
	        String phone = (String) session.getAttribute("order_info_phone");
	        String fulladdress = (String) session.getAttribute("order_info_fulladdress");
	        int finalAmount = (int) session.getAttribute("order_info_total");
	        int totalAmount = (int) session.getAttribute("order_info_subtotal");

	        @SuppressWarnings("unchecked")
	        ArrayList<cartItemViewModel> cart = (ArrayList<cartItemViewModel>) session.getAttribute("cart");

	        Integer discountMoneySession = (Integer) session.getAttribute("discount_money");
	        int discount_money = discountMoneySession != null ? discountMoneySession : 0;

	        OrderObject o = new OrderObject();
	        o.setUser_id(userId);
	        o.setReceiver_name(fullname);
	        o.setReceiver_mobilephone(phone);
	        o.setEmail(email);
	        o.setDelivery_address(fulladdress);
	        o.setTotal_amount(finalAmount);
	        o.setOrder_status("Đang xử lý");
	        o.setDiscount_money(discount_money);
	        o.setTotal_order_value(totalAmount);

	        orderModel om = new orderModel();
	        boolean addOrderSuccess = om.addOrder(o);

	        if (addOrderSuccess) {
	            int orderId = om.getLatestOrder(userId);

	            cartModel cm = new cartModel();
	            int cartId = cm.getCart(userId).getCart_id();

	            orderDetailModel odm = new orderDetailModel();
	            productModel pm = new productModel();

	            for (cartItemViewModel item : cart) {
	                OrderDetail od = new OrderDetail();
	                od.setOrder_id(orderId);
	                od.setProduct_price(item.getProductSalePrice() != 0 ? item.getProductSalePrice() : item.getProductPrice());
	                od.setProduct_id(item.getProductId());
	                od.setQuantity(item.getQuantity());
	                od.setOd_subtotal(item.getSubtotal());

	                odm.addOrderDetail(od);

	                ProductObject p = pm.getProduct(item.getProductId());
	                ProductObject po = new ProductObject();
	                po.setProduct_id(item.getProductId());
	                po.setProduct_quantity(p.getProduct_quantity() - item.getQuantity());
	                po.setProduct_sold(p.getProduct_sold() + item.getQuantity());
	                po.setProduct_status(p.getProduct_quantity() - item.getQuantity() > 0 ? "Còn hàng" : "Hết hàng");

	                pm.editProduct(po);
	            }

	            // Thêm thanh toán VNPay
	            paymentModel paym = new paymentModel();
	            PaymentObject payment = new PaymentObject();
	            payment.setOrder_id(orderId);
	            payment.setPayment_method("VNPay");
	            payment.setPayment_status("Đã thanh toán");
	            paym.addPayment(payment);

	            // Ghi nhận mã giảm giá
	            Object discountIdObj = session.getAttribute("discount_id");
	            if (discountIdObj != null) {
	                int discountId = Integer.parseInt(discountIdObj.toString());
	                discountUsageModel dum = new discountUsageModel();
	                DiscountUsage du = new DiscountUsage();
	                du.setDiscount_id(discountId);
	                du.setOrder_id(orderId);
	                du.setUser_id(userId);
	                dum.addDiscountUsage(du);
	            }

	            // Xóa giỏ hàng
	            cartItemModel cim = new cartItemModel();
	            CartItem ci=new CartItem();
	            ci.setCart_id(cartId);
	            
	            cim.delCartItems(ci);
	            session.setAttribute("totalCartItem", 0);
	            session.removeAttribute("cart");
	            session.removeAttribute("order_view");
	            session.removeAttribute("discount_money");
	            session.removeAttribute("discount_id");
	            session.removeAttribute("order_info_fullname");
	            session.removeAttribute("order_info_email");
	            session.removeAttribute("order_info_phone");
	            session.removeAttribute("order_info_fulladdress");
	            session.removeAttribute("order_info_total");

	            session.setAttribute("notification_message", "Thanh toán và đặt hàng thành công");
	            session.setAttribute("notification_type", "success");
	        } else {
	            session.setAttribute("notification_message", "Đặt hàng thất bại sau khi thanh toán");
	            session.setAttribute("notification_type", "danger");
	        }

	        response.sendRedirect(request.getContextPath() + "/page?view=cart");
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới thêm được giỏ hàng");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
