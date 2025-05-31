package client.discount;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.DiscountObject;
import client.objects.UserObject;
import client.viewModel.cartItemViewModel;
import client.viewModel.orderView;

/**
 * Servlet implementation class applyCode
 */
@WebServlet("/apply-code")
public class applyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applyCode() {
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
			String code=request.getParameter("discount_code");
			
			orderView ov=new orderView();
			ov.setReceiverName(fullname);
			ov.setEmail(email);
			ov.setAddress(address);
			ov.setDistrict(district);
			ov.setProvince(province);
			ov.setWard(ward);
			ov.setPaymentMethod(paymethod);
			ov.setDeliveryAddress(fulladdress);
			ov.setReceiverMobilephone(phone);
			
			session.setAttribute("order_view", ov);
			
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
			
			session.setAttribute("allowCheckout", true);
			
			discountModel dm=new discountModel();
			DiscountObject dis=dm.getDiscount(code);
			if (dis!=null) {
				int use_count=dm.countTimesUseDiscount(userId, dis.getDiscount_id());
				int user_count=dm.getUseDiscountUser(dis.getDiscount_id());
				if (user_count<dis.getMax_users()) {
					if (use_count<dis.getMax_usage()) {
						int discount_money=0;
						if("%".equals(dis.getDiscount_value_type())) {
							discount_money = (int)(totalAmount * ((double) dis.getDiscount_value() / 100));
						}else if ("VND".equals(dis.getDiscount_value_type())) {
							discount_money=dis.getDiscount_value();
						}
						
						session.setAttribute("discount_money", discount_money);
						session.setAttribute("discount_id", dis.getDiscount_id());
						response.sendRedirect(request.getContextPath() + "/page?view=checkout");
					} else {
						session.setAttribute("notification_message", "Bạn đã sử dụng hết số lần của mã này");
			            session.setAttribute("notification_type", "warning");
			            response.sendRedirect(request.getContextPath() + "/page?view=checkout");
					}
				} else {
					session.setAttribute("notification_message", "Mã đã được sử dụng hết");
		            session.setAttribute("notification_type", "warning");
		            response.sendRedirect(request.getContextPath() + "/page?view=checkout");
				}
			} else {
				session.setAttribute("notification_message", "Mã nhập sai hoặc đã quá hạn");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=checkout");
			}
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới thực hiện được chức năng này");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
		
	}

}
