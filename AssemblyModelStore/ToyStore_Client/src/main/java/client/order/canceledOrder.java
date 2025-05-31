package client.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.OrderObject;
import client.objects.ProductObject;
import client.objects.UserObject;
import client.orderDetail.orderDetailModel;
import client.product.productModel;
import client.viewModel.orderDetailView;

/**
 * Servlet implementation class canceledOrder
 */
@WebServlet("/canceled-order")
public class canceledOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public canceledOrder() {
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
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserObject uo=(UserObject) session.getAttribute("logUser");
		
		if (uo!=null) {
			String id=request.getParameter("order_id");
			
			if (id==null) {
				session.setAttribute("notification_message", "Hủy đơn hàng không thành công");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=orders");
	            return;
			}
			orderDetailModel odm=new orderDetailModel();
			productModel pm=new productModel();
			
			ArrayList<orderDetailView> odv=odm.getOrderDetails(Integer.parseInt(id));
			for (orderDetailView item : odv) {
				ProductObject p=pm.getProduct(item.getProductId());
				int newQuantity=p.getProduct_quantity()+item.getQuantity();
				int newSold=p.getProduct_sold()-item.getQuantity();
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
					return;
				}
			}
			
			OrderObject o=new OrderObject();
			o.setOrder_status("Đã hủy");
			o.setOrder_id(Integer.parseInt(id));
			
			orderModel om=new orderModel();
			boolean editAction=om.editOrder(o);
			if(editAction) {
				session.setAttribute("notification_message", "Hủy đơn hàng thành công");
	            session.setAttribute("notification_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=orders");
			}else {
				session.setAttribute("notification_message", "Hủy đơn hàng không thành công");
	            session.setAttribute("notification_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=profile&side=orders");
			}
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới thêm được giỏ hàng");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=login");
		}
	}

}
