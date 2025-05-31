package client.productCategory;

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

import client.objects.ProductCategory;

/**
 * Servlet implementation class getCategories
 */
@WebServlet("/getCategories")
public class getCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCategories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        categoryModel rl = new categoryModel();
        ArrayList<ProductCategory> pcs = rl.getProductCategory(null, 0, (byte)50);
        JSONArray jsonArray = new JSONArray();
        
        for (ProductCategory pc : pcs) {
	    	JSONObject jsonCate = new JSONObject();
	    	jsonCate.put("id", pc.getCategory_id());
	    	jsonCate.put("name", pc.getCategory_name());
	    	jsonCate.put("parentCategoryId", pc.getParent_category_id());
	        jsonArray.put(jsonCate);
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
