package ads.product;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.PermissionObject;
import ads.objects.ProductImage;
import ads.objects.ProductObject;
import ads.productImage.productImageModel;

/**
 * Servlet implementation class delProduct
 */
@WebServlet("/api/product/del")
public class delProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/product_images";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delProduct() {
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
		
		PermissionObject permissionsProduct = (PermissionObject) session.getAttribute("productPer");
	    if (permissionsProduct!=null && permissionsProduct.isCan_delete()) {
	    	productImageModel im=new productImageModel();
			String product_id=request.getParameter("product_id_del");
			
			ProductImage pi=im.getProductImage(Integer.parseInt(product_id));
			
			if (pi != null && pi.getImage_url() != null && !pi.getImage_url().isEmpty()) {
	            File imageFile = new File(IMAGE_UPLOAD_PATH + File.separator + pi.getImage_url());
	            if (imageFile.exists()) {
	                imageFile.delete();
	            }
	            im.delProductImage(pi);
	        }
			productModel pm = new productModel();
			ProductObject po = new ProductObject();
			po.setProduct_id(Integer.parseInt(product_id));

			boolean delResult = pm.delProduct(po);
	        if (delResult) {
	    		session.setAttribute("flash_message", "Xóa sản phẩm thành công");
	            session.setAttribute("flash_type", "success");
	            response.sendRedirect(request.getContextPath() + "/page?view=product");
	        } else {
	        	session.setAttribute("flash_message", "Xóa sản phẩm không thành công");
	            session.setAttribute("flash_type", "danger");
	            response.sendRedirect(request.getContextPath() + "/page?view=product");
	        }
	    }else {
	    	session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=product");
		}
	}

}
