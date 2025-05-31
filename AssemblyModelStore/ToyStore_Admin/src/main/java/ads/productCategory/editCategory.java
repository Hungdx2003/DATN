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
 * Servlet implementation class editCategory
 */
@WebServlet("/api/category/edit")
public class editCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCategory() {
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
	    if (permissionsCategory!=null&&permissionsCategory.isCan_edit()) {
	    	categoryModel u=new categoryModel();
			String cate_id=request.getParameter("category_id");
			String cate_name=request.getParameter("edit_category_name");
			String parent_id=request.getParameter("edit_category_parent");
			
			ProductCategory pc=new ProductCategory();
			pc.setCategory_name(cate_name);
			pc.setParent_category_id(Integer.parseInt(parent_id)); 
			pc.setCategory_id(Integer.parseInt(cate_id));
			pc.setCategory_modified_by(userId);

			
			boolean editResult=u.editProductCategory(pc);
	        if (editResult) {
	        	session.setAttribute("flash_message", "Sửa thông tin danh mục thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=category");
	        } else {
	        	session.setAttribute("flash_message", "Sửa thông tin danh mục không thành công");
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
