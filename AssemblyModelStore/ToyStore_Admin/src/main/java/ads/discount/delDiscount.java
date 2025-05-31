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
 * Servlet implementation class delDiscount
 */
@WebServlet("/api/discount/del")
public class delDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delDiscount() {
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
		
		if (permissionsDiscount!=null && permissionsDiscount.isCan_delete()) {
			discountModel d=new discountModel();
			String id=request.getParameter("discount_id_del");
					
			DiscountObject di=new DiscountObject();
			di.setDiscount_id(Integer.parseInt(id));

			boolean delResult=d.delDiscount(di);
	        if (delResult) {
	        	session.setAttribute("flash_message", "Xóa giảm giá thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=discount");
	        } else {
	        	session.setAttribute("flash_message", "Xóa giảm giá không thành công");
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
