package ads.productCategory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ads.objects.ProductCategory;

/**
 * Servlet implementation class getCategory
 */
@WebServlet("/api/getCategory")
public class getCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int catId = Integer.parseInt(request.getParameter("category_id"));
        categoryModel c = new categoryModel();
        ProductCategory pc = c.getProductCategory(catId);
        

        JSONObject catDetails = new JSONObject();
        catDetails.put("category_name", pc.getCategory_name());
        catDetails.put("category_created_by", pc.getCategory_created_by());
        catDetails.put("category_created_date", pc.getCategory_created_date());
        catDetails.put("category_modified_by", pc.getCategory_modified_by());
        catDetails.put("category_modified_date", pc.getCategory_modified_date());
        catDetails.put("parent_category_id", pc.getParent_category_id());
        catDetails.put("category_id", catId);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(catDetails.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
