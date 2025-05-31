package ads.discount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import ads.objects.DiscountObject;
import ads.objects.UserObject;

/**
 * Servlet implementation class getDiscount
 */
@WebServlet("/api/getDiscount")
public class getDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		UserObject uo= (UserObject) session.getAttribute("logUser");
		
		if (uo!=null) {
			int discountId = Integer.parseInt(request.getParameter("discount_id"));
	        discountModel d=new discountModel();
	        DiscountObject di = d.getDiscountObject(discountId);
	        
	        JSONObject discountDetails = new JSONObject();
	        discountDetails.put("name", di.getDiscount_name());
	        discountDetails.put("type", di.getDiscount_type());
	        discountDetails.put("value", di.getDiscount_value());
	        discountDetails.put("valueType", di.getDiscount_value_type());
	        discountDetails.put("startDate", di.getStart_date());
	        discountDetails.put("endDate", di.getEnd_date());
	        discountDetails.put("maxUsers", di.getMax_users());
	        discountDetails.put("maxUsage", di.getMax_usage());
	        discountDetails.put("isActive", di.isActive());
	        discountDetails.put("discountId", discountId);

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json; charset=UTF-8");
	        response.getWriter().write(discountDetails.toString());
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
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
