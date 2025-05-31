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
 * Servlet implementation class delCartItem
 */
@WebServlet("/del-cartitem")
public class delCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delCartItem() {
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
			String id=request.getParameter("ci_id");
			if (id==null) {
				session.setAttribute("notification_message", "Xóa thất bại!");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	            return;
			}
			int ci_id=Integer.parseInt(id);
			int userId= (int) session.getAttribute("userId");

			CartItem ci=new CartItem();
			ci.setCi_id(ci_id);
			
			cartItemModel cim=new cartItemModel();
			boolean delAction=cim.delCartItem(ci);
			if (delAction) {
				int newTotal = cim.getTotalCartItem(userId);
	            session.setAttribute("totalCartItem", newTotal);
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	        } else {
	        	session.setAttribute("notification_message", "Xóa thất bại!");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=cart");
	        }
		} else {
			session.setAttribute("notification_message", "Phiên đăng nhập của bạn đã hết. Vui lòng đăng nhập lại!");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=cart");
		}
	}

}
