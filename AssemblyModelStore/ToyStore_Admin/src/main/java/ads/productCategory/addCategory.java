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
 * Servlet implementation class addCategory
 */
@WebServlet("/api/category/add")
public class addCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCategory() {
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
	    int userId=(int) session.getAttribute("userId");
	    
	    PermissionObject permissionsCategory = (PermissionObject) session.getAttribute("categoryPer");
	    if (permissionsCategory!=null&&permissionsCategory.isCan_add()) {
	    	categoryModel u=new categoryModel();
			String cate_name=request.getParameter("category_name");
					
			ProductCategory pc=new ProductCategory();
			pc.setCategory_name(cate_name);
			pc.setCategory_created_by(userId);
			pc.setCategory_modified_by(userId);

			boolean addResult=u.addProductCategory(pc);
	        if (addResult) {
	        	session.setAttribute("flash_message", "Thêm danh mục thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=category");
	        } else {
	        	session.setAttribute("flash_message", "Thêm danh mục không thành công");
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
