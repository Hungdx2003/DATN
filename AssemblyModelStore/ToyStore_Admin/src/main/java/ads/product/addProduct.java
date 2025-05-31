package ads.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ads.objects.PermissionObject;
import ads.objects.ProductImage;
import ads.objects.ProductObject;
import ads.productImage.productImageModel;

/**
 * Servlet implementation class addProduct
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, 
    maxFileSize = 1024 * 1024 * 10,       
    maxRequestSize = 1024 * 1024 * 50 
)
@WebServlet("/api/product/add")
public class addProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/product_images";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProduct() {
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
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session=request.getSession();
	    
	    PermissionObject permissionsProduct = (PermissionObject) session.getAttribute("productPer");
	    int userId=(int) session.getAttribute("userId");
	    if (permissionsProduct!=null && permissionsProduct.isCan_add()) {
	    	productModel pm = new productModel();
		    String name = request.getParameter("name");
		    String quantity = request.getParameter("quantity");
		    String original_price = request.getParameter("original_price");
		    String price = request.getParameter("price");
		    String detail = request.getParameter("detail_data");
		    String product_category = request.getParameter("product_category");
		    String status = request.getParameter("status");
		    String brand =request.getParameter("brand");

		    ProductObject po = new ProductObject();
		    po.setProduct_name(name);
		    po.setProduct_quantity(Integer.parseInt(quantity));
		    po.setProduct_original_price(Integer.parseInt(original_price));
		    po.setProduct_price(Integer.parseInt(price));
		    po.setProduct_detail(detail);
		    po.setProduct_pc_id(Integer.parseInt(product_category));
		    po.setProduct_status(status);
		    po.setProduct_created_by(userId);
		    po.setProduct_modified_by(userId);
		    po.setProduct_brand(brand);

		    boolean addResult = pm.addProduct(po);

		    if (addResult) {
		        ProductObject p = pm.getProductByCreator(1);

		        Part image = request.getPart("image");
		        String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();

		        if (fileName != null && !fileName.isEmpty()) {
		            File uploadDir = new File(IMAGE_UPLOAD_PATH);
		            if (!uploadDir.exists()) uploadDir.mkdirs();

		            File imageFile = new File(uploadDir, fileName);
		            Files.copy(image.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		            ProductImage i = new ProductImage();
		            i.setImage_url(fileName);
		            i.setProduct_id(p.getProduct_id());

		            productImageModel im = new productImageModel();
		            boolean addResult1 = im.addProductImage(i);

		            if (addResult1) {
		                session.setAttribute("flash_message", "Thêm sản phẩm kèm ảnh thành công");
		                session.setAttribute("flash_type", "success");
		            } else {
		                session.setAttribute("flash_message", "Thêm sản phẩm thành công, nhưng lỗi khi lưu ảnh");
		                session.setAttribute("flash_type", "warning");
		            }
		        } else {
		            session.setAttribute("flash_message", "Thêm sản phẩm thành công (không có ảnh)");
		            session.setAttribute("flash_type", "success");
		        }

		        response.sendRedirect(request.getContextPath() + "/page?view=product");
		    } else {
		        session.setAttribute("flash_message", "Thêm sản phẩm không thành công");
		        session.setAttribute("flash_type", "danger");
		        response.sendRedirect(request.getContextPath() + "/page?view=product");
		    }
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=product");
		}
	}


}
