package ads.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ads.viewModel.productViewModel;

/**
 * Servlet implementation class getProductDiscountById
 */
@WebServlet("/api/getProductDiscountById")
public class getProductDiscountById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProductDiscountById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int discountId = Integer.parseInt(request.getParameter("discount_id"));
        productModel p = new productModel();
        ArrayList<productViewModel> list = p.getProductDiscountById(discountId);

        JSONArray jsonArray = new JSONArray();
        
        for (productViewModel pv : list) {
	    	JSONObject jsonProduct = new JSONObject();
	    	jsonProduct.put("pd_id", pv.getPd_id());
	    	jsonProduct.put("product_id", pv.getProductId());
	    	jsonProduct.put("product_name", pv.getProductName());
	    	jsonProduct.put("product_price", pv.getProductPrice());
	    	jsonProduct.put("product_sale_price", pv.getProductSalePrice());
	    	jsonProduct.put("product_status", pv.getProductStatus());
	    	jsonProduct.put("image_url", pv.getImageUrl());
	    	jsonArray.put(jsonProduct);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
