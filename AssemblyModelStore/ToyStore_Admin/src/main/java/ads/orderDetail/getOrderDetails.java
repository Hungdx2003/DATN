package ads.orderDetail;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ads.objects.OrderDetail;

/**
 * Servlet implementation class getOrderDetails
 */
@WebServlet("/api/getOrderDetails")
public class getOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrderDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String odId=request.getParameter("order_id");
		
		orderDetailModel odm=new orderDetailModel();
		ArrayList<OrderDetail> ods=odm.getOrderDetails(Integer.parseInt(odId));
		JSONArray jsonArray = new JSONArray();
        for (OrderDetail od : ods) {
        	JSONObject jsonOrderDetail = new JSONObject();
        	jsonOrderDetail.put("odId", od.getOd_id());
        	jsonOrderDetail.put("productId", od.getProduct_id());
        	jsonOrderDetail.put("productPrice", od.getProduct_price());
        	jsonOrderDetail.put("quantity", od.getQuantity());
        	jsonOrderDetail.put("orderId", od.getOrder_id());
        	jsonOrderDetail.put("subtotal", od.getOd_subtotal());
            jsonArray.put(jsonOrderDetail);
        }

		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonArray.toString());
        response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
