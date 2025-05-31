package client.cartItem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.CartItem;
import client.objects.UserObject;

/**
 * Servlet implementation class updateCart
 */
@WebServlet("/update-cart")
public class updateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCart() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		UserObject uo=(UserObject) session.getAttribute("logUser");
		if (uo!=null) {
			String[] cartItemIds = request.getParameterValues("cartItemId");
		    String[] quantities = request.getParameterValues("quantity");
		    String[] subtotals = request.getParameterValues("subtotal");
		    String action = request.getParameter("action");
		    
		    cartItemModel cim = new cartItemModel();
		    int userId= (int) session.getAttribute("userId");

		    boolean hasError = false;

		    if (cartItemIds != null && quantities != null && subtotals != null &&
		        cartItemIds.length == quantities.length && cartItemIds.length == subtotals.length) {
		        
		        for (int i = 0; i < cartItemIds.length; i++) {
		            try {
		                int cartItemId = Integer.parseInt(cartItemIds[i]);
		                int quantity = Integer.parseInt(quantities[i]);
		                int subtotal = Integer.parseInt(subtotals[i]);
		                
		                CartItem ci = new CartItem();
		                ci.setCi_id(cartItemId);
		                ci.setQuantity(quantity);
		                ci.setCart_subtotal(subtotal);
		                
		                boolean editResult = cim.editCartItem(ci);
		                if (!editResult) {
		                    hasError = true;
		                    break;
		                }
		            } catch (NumberFormatException e) {
		                hasError = true;
		                e.printStackTrace();
		                break;
		            }
		        }

		        if (!hasError) {
		            int newTotal = cim.getTotalCartItem(userId);
		            session.setAttribute("totalCartItem", newTotal);
		            if ("update".equals(action)) {
		            	response.sendRedirect("page?view=cart");
		            } else if ("checkout".equals(action)) {
		                response.sendRedirect("page?view=checkout");
		            }
		        } else {
		            response.sendRedirect("page?view=cart");
		        }
		    } else {
		        response.sendRedirect("page?view=cart");
		    }
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới cập nhật được giỏ hàng");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=shop");
		}
	}

}
