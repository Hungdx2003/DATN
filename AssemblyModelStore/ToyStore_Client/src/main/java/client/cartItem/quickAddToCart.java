package client.cartItem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.cart.cartModel;
import client.objects.CartItem;
import client.objects.CartObject;
import client.objects.UserObject;

/**
 * Servlet implementation class quickAddToCart
 */
@WebServlet("/quick-add-to-cart")
public class quickAddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quickAddToCart() {
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
		HttpSession session = request.getSession();
		
		String productIdstr=request.getParameter("product_id");
		if (productIdstr == null) {
			session.setAttribute("notification_message", "Thêm sản phẩm vào giỏ không thành công");
            session.setAttribute("notification_type", "danger");
            String referer = request.getHeader("referer");
            if (referer != null) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/page?view=shop");
            }
            return;
		}
		
		int quantity=1;
		int productId=Integer.parseInt(productIdstr);
		UserObject uo=(UserObject) session.getAttribute("logUser");
		if (uo!=null) {
			int userId= (int) session.getAttribute("userId");
			cartModel cm=new cartModel();
			CartObject co=cm.getCart(userId);
			
			if(co==null) {
				System.out.println(userId);
				CartObject c=new CartObject();
				c.setUser_id(userId);
				boolean addResult=cm.addCart(c);
				 if (!addResult) {
	                response.getWriter().write("Không thể tạo giỏ hàng.");
	                return;
	            }
				co=cm.getCart(userId);
			}
			int cartId=co.getCart_id();
			cartItemModel cim=new cartItemModel();
			CartItem ci=new CartItem();
			
			CartItem existCartItem=cim.getCartItem(cartId,productId);
			boolean actionResult;
			
			if(existCartItem!=null) {
				int newQuantity = existCartItem.getQuantity() + quantity;
				ci.setCart_id(cartId);
				ci.setProduct_id(productId);
				ci.setQuantity(newQuantity);
				actionResult = cim.editCartItem(ci);
			}else {
				ci.setCart_id(cartId);
				ci.setProduct_id(productId);
				ci.setQuantity(quantity);
				actionResult = cim.addCartItem(ci);
			}
			
			if (actionResult) {
				int newTotal = cim.getTotalCartItem(userId);
	            session.setAttribute("totalCartItem", newTotal);
	        	session.setAttribute("notification_message", "Thêm vào giỏ hàng thành công");
	            session.setAttribute("notification_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	        } else {
	        	session.setAttribute("notification_message", "Thêm vào giỏ hàng không thành công");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	        }
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới thêm được giỏ hàng");
            session.setAttribute("notification_type", "warning");
            String referer = request.getHeader("referer");
            if (referer != null) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/page?view=shop");
            }
		}
	}
}
