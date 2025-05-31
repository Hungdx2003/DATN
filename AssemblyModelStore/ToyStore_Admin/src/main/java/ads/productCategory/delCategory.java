package ads.productCategory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.PermissionObject;
import ads.objects.ProductCategory;

/**
 * Servlet implementation class delCategory
 */
@WebServlet("/api/category/del")
public class delCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delCategory() {
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
		
		PermissionObject permissionsCategory = (PermissionObject) session.getAttribute("categoryPer");
	    if (permissionsCategory!=null&&permissionsCategory.isCan_delete()) {
	    	categoryModel u=new categoryModel();
			String cate_id=request.getParameter("category_id_del");
			
			ProductCategory pc=new ProductCategory();
			pc.setCategory_id(Integer.parseInt(cate_id));

			
			boolean delResult=u.delProductCategory(pc);
	        if (delResult) {
	        	session.setAttribute("flash_message", "Xóa danh mục thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=category");
	        } else {
	        	session.setAttribute("flash_message", "Xóa danh mục không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=category");
	        }
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=category");
		}
	}

}
