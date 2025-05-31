package ads.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.OrderObject;
import ads.objects.PermissionObject;

/**
 * Servlet implementation class editOrder
 */
@WebServlet("/api/order/edit")
public class editOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editOrder() {
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
		PermissionObject permissionsOrder = (PermissionObject) session.getAttribute("orderPer");
		
		if (permissionsOrder!=null&&permissionsOrder.isCan_edit()) {
			orderModel o=new orderModel();
			String order_id=request.getParameter("order_id");
			String status=request.getParameter("status");
			
			OrderObject od=new OrderObject();
			od.setOrder_id(Integer.parseInt(order_id));
			od.setOrder_status(status);
			
			boolean editResult=o.editOrder(od);
	        if (editResult) {
	        	session.setAttribute("flash_message", "Sửa đơn hàng thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=order");
	        } else {
	        	session.setAttribute("flash_message", "Sửa đơn hàng không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=order");
	        }
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=order");
		}
	}

}
