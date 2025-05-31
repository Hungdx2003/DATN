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

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, 
    maxFileSize = 1024 * 1024 * 10,       
    maxRequestSize = 1024 * 1024 * 50 
)
@WebServlet("/api/product/edit")
public class editProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/product_images";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProduct() {
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
		PermissionObject permissionsProduct = (PermissionObject) session.getAttribute("productPer");
	    int userId=(int) session.getAttribute("userId");
	    if (permissionsProduct!=null && permissionsProduct.isCan_add()) {
	    	productModel pm=new productModel();
			String name=request.getParameter("edit_name");
			String quantity=request.getParameter("edit_quantity");
			String original_price=request.getParameter("edit_original_price");
			String price=request.getParameter("edit_price");
			String detail=request.getParameter("edit_detail_data");
			String product_category=request.getParameter("edit_product_category");
			String status=request.getParameter("edit_status");
			String id=request.getParameter("product_id");
			String brand =request.getParameter("edit_brand");
			
			ProductObject po=new ProductObject();
			po.setProduct_name(name);
			po.setProduct_quantity(Integer.parseInt(quantity));
			po.setProduct_original_price(Integer.parseInt(original_price));
			po.setProduct_price(Integer.parseInt(price));
			po.setProduct_detail(detail);
			po.setProduct_pc_id(Integer.parseInt(product_category));
			po.setProduct_status(status);
			po.setProduct_modified_by(userId);
			po.setProduct_id(Integer.parseInt(id));
			po.setProduct_brand(brand);
			
			boolean editResult=pm.editProduct(po);
			
			Part image = request.getPart("edit_image");
			String fileName = null;
			productImageModel im = new productImageModel();
			ProductImage oldImage = im.getProductImage(Integer.parseInt(id));

			if (image != null && image.getSize() > 0) {
			    fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
			    File uploadDir = new File(IMAGE_UPLOAD_PATH);
			    if (!uploadDir.exists()) uploadDir.mkdirs();

			    if (oldImage != null && oldImage.getImage_url() != null) {
			        File oldImageFile = new File(uploadDir, oldImage.getImage_url());
			        if (oldImageFile.exists()) {
			            oldImageFile.delete();
			        }
			    }

			    File imageFile = new File(uploadDir, fileName);
			    Files.copy(image.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} else {
			    if (oldImage != null && oldImage.getImage_url() != null) {
			        fileName = oldImage.getImage_url();
			    } else {
			        fileName = "";
			    }
			}

			if (editResult) {
			    ProductImage i = new ProductImage();
			    i.setImage_url(fileName);
			    i.setProduct_id(Integer.parseInt(id));

			    boolean imageResult = false;
			    if (oldImage != null) {
			        i.setImage_id(oldImage.getImage_id());
			        imageResult = im.editProductImage(i);
			    } else {
			        if (fileName != null && !fileName.isEmpty()) {
			            imageResult = im.addProductImage(i);
			        } else {
			            imageResult = true;
			        }
			    }

			    if (imageResult) {
			        session.setAttribute("flash_message", "Sửa thông tin sản phẩm thành công");
			        session.setAttribute("flash_type", "success");
			        response.sendRedirect(request.getContextPath() + "/page?view=product");
			        return;
			    }
			}

			session.setAttribute("flash_message", "Sửa thông tin sản phẩm không thành công");
			session.setAttribute("flash_type", "danger");
			response.sendRedirect(request.getContextPath() + "/page?view=product");
	    }else {
	    	session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=product");
		}

	}

}
