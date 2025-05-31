package client.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class addOrder
 */
@WebServlet("/add-order")
public class addOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session=request.getSession();
		UserObject uo=(UserObject) session.getAttribute("logUser");
		
		if (uo!=null) {
			int userId= (int) session.getAttribute("userId");
			
			//Lấy thông tin từ form 
			String fullname=request.getParameter("fullname");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String province=request.getParameter("province");
			String district=request.getParameter("district");
			String ward=request.getParameter("ward");
			String fulladdress=address+", "+ward+", "+district+", "+province;
			String paymethod=request.getParameter("option");
			
			//Lấy danh sách sản phẩm trong giỏ hàng
			@SuppressWarnings("unchecked")
			ArrayList<cartItemViewModel> cart=(ArrayList<cartItemViewModel>) session.getAttribute("cart");
			
			if (cart == null || cart.isEmpty()) {
				session.setAttribute("notification_message", "Không có sản phẩm nào trong giỏ hàng");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	            return;
	        }
			int totalAmount=0;
			for (cartItemViewModel item : cart) {
	            totalAmount += item.getProductPrice() * item.getQuantity();
	        }
			Integer discountMoneySession=(Integer)session.getAttribute("discount_money");
			int discount_money;
			if (discountMoneySession!=null) {
				discount_money=discountMoneySession;
			} else {
				discount_money=0;
			}
			int finalAmount = totalAmount - discount_money;
			
			if (paymethod.equals("VNPay")) {
	            session.setAttribute("order_info_fullname", fullname);
	            session.setAttribute("order_info_email", email);
	            session.setAttribute("order_info_phone", phone);
	            session.setAttribute("order_info_fulladdress", fulladdress);
	            session.setAttribute("order_info_total", finalAmount);
	            session.setAttribute("order_info_subtotal", totalAmount);
	            response.sendRedirect(request.getContextPath() + "/vnpay-payment");
	            return;
	        }
			
			OrderObject o=new OrderObject();
			o.setUser_id(userId);
			o.setReceiver_name(fullname);
			o.setReceiver_mobilephone(phone);
			o.setEmail(email);
			o.setDelivery_address(fulladdress);
			o.setTotal_amount(finalAmount);
			o.setOrder_status("Đang xử lý");
			o.setDiscount_money(discount_money);
			o.setTotal_order_value(totalAmount);
			
			productModel pm=new productModel();
			Date now = new Date();
			//Tạo đơn hàng
			orderModel om=new orderModel();
			boolean addResult=om.addOrder(o);
			if(addResult) {
				int orderId=om.getLatestOrder(userId);
				orderDetailModel odm=new orderDetailModel();
				//Tạo chi tiết đơn hàng
				for (cartItemViewModel item : cart) {
					OrderDetail od=new OrderDetail();
					od.setOrder_id(orderId);
					if(item.getProductSalePrice()!=0 && item.isActive() 
							&& now.compareTo(item.getStartDate()) >= 0 && now.compareTo(item.getEndDate()) <= 0) {
						od.setProduct_price(item.getProductSalePrice());
					}else {
						od.setProduct_price(item.getProductPrice());
					}
					od.setProduct_id(item.getProductId());
					od.setQuantity(item.getQuantity());
					od.setOd_subtotal(item.getSubtotal());
					
					// Cập nhật số lượng sản phẩm
					ProductObject p=pm.getProduct(item.getProductId());
					int newQuantity=p.getProduct_quantity()-item.getQuantity();
					int newSold=p.getProduct_sold()+item.getQuantity();
					ProductObject po=new ProductObject();
					po.setProduct_id(item.getProductId());
					po.setProduct_quantity(newQuantity);
					po.setProduct_sold(newSold);
					if(newQuantity>0) {
						po.setProduct_status("Còn hàng");
					}else{
						po.setProduct_status("Hết hàng");
					}
					
					boolean editAction=pm.editProduct(po);
					if(!editAction) {
						response.getWriter().write("Sửa thông tin sản phẩm không thành công!");
						OrderObject oo=new OrderObject();
						oo.setOrder_id(orderId);
						boolean delResult=om.delOrder(oo);
						if(!delResult) {
							response.getWriter().write("Xóa đơn hàng không thành công.");
						}
						return;
					}
					//Tạo sản phẩm đơn hàng
					boolean addDetail=odm.addOrderDetail(od);
					if(!addDetail) {
						response.getWriter().write("Không tạo chi tiết đơn hàng thành công.");
						OrderObject oo=new OrderObject();
						oo.setOrder_id(orderId);
						boolean delResult=om.delOrder(oo);
						if(!delResult) {
							response.getWriter().write("Xóa đơn hàng không thành công.");
						}
						return;
					}
				}
				//Tạo thanh toán mới
				paymentModel paym=new paymentModel();
				PaymentObject po=new PaymentObject();
				po.setOrder_id(orderId);
				po.setPayment_method("COD");
	            po.setPayment_status("Chưa thanh toán");
				
				boolean addAction=paym.addPayment(po);
				if(addAction) {
					Object discountIdObj = session.getAttribute("discount_id");
				    if (discountIdObj != null) {
				        try {
				        	//Lưu thông tin đơn hàng đã sử dụng mã giảm giá
				            int discountId = Integer.parseInt(discountIdObj.toString());
				            DiscountUsage du=new DiscountUsage();
				            du.setDiscount_id(discountId);
				            du.setOrder_id(orderId);
				            du.setUser_id(userId);
				            		
				            discountUsageModel dum = new discountUsageModel();
				            boolean usageAdded = dum.addDiscountUsage(du);
				            if (!usageAdded) {
				                response.getWriter().write("Không thể ghi nhận discount usage.");
				                return;
				            }
				        } catch (NumberFormatException e) {
				            response.getWriter().write("discount_id không hợp lệ.");
				            return;
				        }
				    }
					
					cartItemModel cim=new cartItemModel();
					cartModel cm=new cartModel();
					
					int cartId=cm.getCart(userId).getCart_id();
					CartItem ci=new CartItem();
					ci.setCart_id(cartId);
					
					// Xóa sản phẩm trong giỏ hàng sau khi thanh toán thành công
					boolean delResult=cim.delCartItems(ci);
					if (!delResult) {
						response.getWriter().write("Xóa giỏ hàng chưa thành công");
						return;
					}
		            session.setAttribute("totalCartItem", 0);
		            session.removeAttribute("cart");
		            session.removeAttribute("order_view");
		            session.removeAttribute("discount_money");
		            session.removeAttribute("discount_id");
		            
					session.setAttribute("notification_message", "Đặt hàng thành công");
		            session.setAttribute("notification_type", "success");
		            response.sendRedirect(request.getContextPath() + "/page?view=cart");
				}else {
					session.setAttribute("notification_message", "Đặt hàng không thành công");
		            session.setAttribute("notification_type", "danger");
		            response.sendRedirect(request.getContextPath() + "/page?view=cart");
				}
			}else {
				session.setAttribute("notification_message", "Đặt hàng không thành công");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
			}
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới sử dụng được chức năng này");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
	}

}
