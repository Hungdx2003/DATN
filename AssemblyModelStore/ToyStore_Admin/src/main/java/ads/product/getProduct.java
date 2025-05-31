package ads.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ads.objects.ProductImage;
import ads.objects.ProductObject;
import ads.productImage.productImageModel;

/**
 * Servlet implementation class getProduct
 */
@WebServlet("/api/getProduct")
public class getProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int productId = Integer.parseInt(request.getParameter("product_id"));
        productModel p = new productModel();
        ProductObject po = p.getProduct(productId);
        
        productImageModel i=new productImageModel();
        ProductImage pi=i.getProductImage(productId);

        JSONObject productDetails = new JSONObject();
        productDetails.put("product_created_date", po.getProduct_created_date());
        productDetails.put("product_modified_date", po.getProduct_modified_date());
        productDetails.put("product_created_by", po.getProduct_created_by());
        productDetails.put("product_modified_by", po.getProduct_modified_by());
        productDetails.put("name", po.getProduct_name());
        productDetails.put("quantity", po.getProduct_quantity());
        productDetails.put("original_price", po.getProduct_original_price());
        productDetails.put("price", po.getProduct_price());
        productDetails.put("product_sold", po.getProduct_sold());
        productDetails.put("status", po.getProduct_status());
        productDetails.put("category_id", po.getProduct_pc_id());
        productDetails.put("detail", po.getProduct_detail());
        String imageUrl = (pi != null && pi.getImage_url() != null) ? pi.getImage_url() : "";
        productDetails.put("image_url", imageUrl);
        productDetails.put("productId", productId);
        productDetails.put("productBrand", po.getProduct_brand());
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(productDetails.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
