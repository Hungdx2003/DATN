package ads.news;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.NewsObject;
import ads.objects.PermissionObject;

/**
 * Servlet implementation class delNews
 */
@WebServlet("/api/news/del")
public class delNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/images";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delNews() {
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
	    
	    PermissionObject permissionsNews = (PermissionObject) session.getAttribute("newsPer");
	    if (permissionsNews!=null && permissionsNews.isCan_delete()) {
	    	newsModel d=new newsModel();
			String id=request.getParameter("news_id_del");
			String image=request.getParameter("news_image_del");
					
			NewsObject no=new NewsObject();
			no.setNews_id(Integer.parseInt(id));

			boolean delResult=d.delNews(no);
	        if (delResult) {
	        	if (image!=null && !image.isEmpty()) {
	        		File imageFile = new File(IMAGE_UPLOAD_PATH + File.separator + image);
	                if (imageFile.exists()) {
	                    imageFile.delete();
	                }
				}
	        	session.setAttribute("flash_message", "Xóa tin tức thành công");
	            session.setAttribute("flash_type", "success");
	        } else {
	        	session.setAttribute("flash_message", "Xóa tin tức không thành công");
	            session.setAttribute("flash_type", "danger");
	        }
	        response.sendRedirect(request.getContextPath() + "/page?view=news");
	    }else {
	    	session.setAttribute("flash_message", "Bạn được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=news");
		}
	}

}
