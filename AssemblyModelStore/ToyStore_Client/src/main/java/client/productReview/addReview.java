package client.productReview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.ProductReview;
import client.objects.UserObject;

/**
 * Servlet implementation class addReview
 */
@WebServlet("/addReview")
public class addReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addReview() {
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
		 
		 String product_id=request.getParameter("product_id");
		 String comment=request.getParameter("review");
		 UserObject user=(UserObject)session.getAttribute("logUser");
		 if (user!=null) {
			if (comment.equalsIgnoreCase("")) {
				response.sendRedirect(request.getContextPath() + "/page?view=product&product_id="+product_id);
				return;
			}
			int userId=(int) session.getAttribute("userId");
			ProductReview pr=new ProductReview();
			pr.setUser_id(userId);
			pr.setProduct_id(Integer.parseInt(product_id));
			pr.setComment(comment);
			
			productReviewModel prm=new productReviewModel();
			boolean addResult=prm.addProductReview(pr);
			if (addResult) {
				request.getSession().setAttribute("openReviewTab", true);
				response.sendRedirect(request.getContextPath() + "/page?view=product&product_id="+product_id);
			}else {
				session.setAttribute("notification_message", "Bạn chưa mua sản phẩm này nên không đánh giá được");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=product&product_id="+product_id);
			}
			
		} else {
			session.setAttribute("notification_message", "Bạn phải đăng nhập mới đánh giá sản phẩm được");
            session.setAttribute("notification_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=product&product_id="+product_id);
		}
	}

}
