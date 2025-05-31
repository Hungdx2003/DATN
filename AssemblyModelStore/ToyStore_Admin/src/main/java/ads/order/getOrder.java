package ads.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ads.objects.OrderObject;

/**
 * Servlet implementation class getOrder
 */
@WebServlet("/api/getOrder")
public class getOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int orderId = Integer.parseInt(request.getParameter("order_id"));
        orderModel o = new orderModel();
        OrderObject uo = o.getOrder(orderId);
        

        JSONObject orderDetails = new JSONObject();
        orderDetails.put("userId", uo.getUser_id());
        orderDetails.put("receiverName", uo.getReceiver_name());
        orderDetails.put("receiverPhone", uo.getReceiver_mobilephone());
        orderDetails.put("deliveryAddress", uo.getDelivery_address());
        orderDetails.put("status", uo.getOrder_status());
        orderDetails.put("totalAmount", uo.getTotal_amount());
        orderDetails.put("orderId", orderId);
        orderDetails.put("totalOrderValue", uo.getTotal_order_value());
        orderDetails.put("discountMoney", uo.getDiscount_money());
        orderDetails.put("email", uo.getEmail());
        orderDetails.put("orderDate", uo.getOrder_date());
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(orderDetails.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
