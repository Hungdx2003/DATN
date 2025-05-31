package ads.discount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.DiscountObject;
import ads.objects.PermissionObject;

/**
 * Servlet implementation class editDiscount
 */
@WebServlet("/api/discount/edit")
public class editDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editDiscount() {
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
		PermissionObject permissionsDiscount = (PermissionObject) session.getAttribute("discountPer");
		
		if (permissionsDiscount!=null && permissionsDiscount.isCan_edit()) {
			discountModel d=new discountModel();
			String name=request.getParameter("edit_discount_name");
			String discount_type=request.getParameter("edit_discount_type");
			String discount_value=request.getParameter("edit_discount_value");
			String discount_value_type=request.getParameter("edit_discount_value_type");
			String is_active=request.getParameter("edit_is_active");
			String start_date=request.getParameter("edit_start_date");
			String end_date=request.getParameter("edit_end_date");
			String id=request.getParameter("discount_id");
			
			boolean isActiveValue = is_active != null && is_active.equals("1");
					
			DiscountObject di=new DiscountObject();
			di.setDiscount_name(name);
			di.setDiscount_type(discount_type);
			di.setDiscount_value(Integer.parseInt(discount_value));
			di.setDiscount_value_type(discount_value_type);
			di.setActive(isActiveValue);
			di.setStart_date(start_date);
			di.setEnd_date(end_date);
			di.setDiscount_id(Integer.parseInt(id));
			if ("Khuyến mãi".equalsIgnoreCase(discount_type)) {
	            di.setMax_users(null);
	            di.setMax_usage(null);
	        } else {
	        	String max_users=request.getParameter("edit_max_users");
	    		String max_usage=request.getParameter("edit_max_usage");
	            di.setMax_users(max_users != null && !max_users.isEmpty() ? Integer.parseInt(max_users) : null);
	            di.setMax_usage(max_usage != null && !max_usage.isEmpty() ? Integer.parseInt(max_usage) : null);
	        }

			boolean editResult=d.editDiscount(di);
	        if (editResult) {
	        	session.setAttribute("flash_message", "Sửa thông tin giảm giá thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=discount");
	        } else {
	        	session.setAttribute("flash_message", "Sửa thông tin giảm giá không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=discount");
	        }
		} else {
			session.setAttribute("flash_message", "Bạn được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=discount");
		}
	}

}
