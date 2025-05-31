package ads.productDiscount;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.discount.discountModel;
import ads.objects.DiscountObject;
import ads.objects.ProductDiscount;
import ads.objects.ProductObject;
import ads.objects.RoleObject;
import ads.product.productModel;
/**
 * Servlet implementation class applyDiscount
 */
@WebServlet("/api/applyDiscount")
public class applyDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applyDiscount() {
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
		RoleObject ro= (RoleObject) session.getAttribute("role");
		if (ro!=null && "Quản trị viên".equals(ro.getRole_name())) {
			String[] productIds = request.getParameterValues("productIds");
			String discountId=request.getParameter("discountId");
			String[] prices = request.getParameterValues("product_price");
			
			if (productIds!=null && discountId!=null && prices!=null && productIds.length==prices.length) {
				 ArrayList<ProductDiscount> items = new ArrayList<>();
				 ArrayList<ProductObject> updatedProducts = new ArrayList<>();
				 
				 discountModel dm=new discountModel();
				 DiscountObject discount=dm.getDiscountObject(Integer.parseInt(discountId));
				 
				 for (int i = 0; i < prices.length; i++) {
					ProductDiscount pd=new ProductDiscount();
					pd.setDiscount_id(Integer.parseInt(discountId));
					pd.setProduct_id(Integer.parseInt(productIds[i]));
					items.add(pd);
					
					int price=Integer.parseInt(prices[i]);
					int sale_price= calculateDiscountedPrice(price, discount);
					
					ProductObject po=new ProductObject();
					po.setProduct_sale_price(sale_price);
					po.setProduct_id(Integer.parseInt(productIds[i]));
					updatedProducts.add(po);
				}
				 
				 productDiscountModel pdm=new productDiscountModel();
				 boolean addResult=pdm.addProductDiscount(items);
				 
				 productModel pm=new productModel();
				 boolean editResult=pm.editProduct(updatedProducts);
				 
				 if(addResult && editResult) {
					 session.setAttribute("flash_message", "Áp dụng khuyến mãi thành công");
					 session.setAttribute("flash_type", "success");
					 response.sendRedirect(request.getContextPath() + "/page?view=discount");
				 }else {
					 session.setAttribute("flash_message", "Áp dụng khuyến mãi không thành công");
					 session.setAttribute("flash_type", "danger");
					 response.sendRedirect(request.getContextPath() + "/page?view=discount");
				}
			} else {
				session.setAttribute("flash_message", "Bạn chưa chọn sản phẩm để áp dụng");
				session.setAttribute("flash_type", "warning");
				response.sendRedirect(request.getContextPath() + "/page?view=discount");
			}
		} else {
			session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=discount");
		}
	}
	
	private int calculateDiscountedPrice(int price, DiscountObject discount) {
        int discountAmount = 0;

        if (discount.getDiscount_value_type().equals("%")) {
            discountAmount = (int)(price * ((double) discount.getDiscount_value() / 100));
        } else if (discount.getDiscount_value_type().equals("VND")) {
            discountAmount = discount.getDiscount_value();
        }

        return price - discountAmount;
    }

}
