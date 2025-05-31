package ads.news;

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

import org.jsoup.Jsoup;

import ads.objects.NewsObject;
import ads.objects.PermissionObject;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, 
    maxFileSize = 1024 * 1024 * 10,       
    maxRequestSize = 1024 * 1024 * 50 
)
/**
 * Servlet implementation class addNews
 */
@WebServlet("/api/news/add")
public class addNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/images";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNews() {
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
	    int userId=(int) session.getAttribute("userId");
	    
	    PermissionObject permissionsNews = (PermissionObject) session.getAttribute("newsPer");
	    if (permissionsNews!=null && permissionsNews.isCan_add()) {
	    	newsModel n=new newsModel();
			String title=request.getParameter("title");
			String author=request.getParameter("author");
			String content=request.getParameter("content");
			Part image = request.getPart("image");
			
			String fileName = "";
			if (image != null && image.getSize() > 0) {
		    	fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
			} else {
				fileName = "";
			}
			
			String shortDetail = getShortContent(content, 150);
			
			NewsObject no=new NewsObject();
			no.setNews_title(title);
			no.setNews_author(author);
			no.setNews_content(content);
			no.setNews_created_by(userId);
			no.setDescription(shortDetail);
			
			if (fileName != null && !fileName.isEmpty()) {
	            File uploadDir = new File(IMAGE_UPLOAD_PATH);
	            if (!uploadDir.exists()) uploadDir.mkdirs();

	            File imageFile = new File(uploadDir, fileName);
	            Files.copy(image.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            no.setNews_cover_image(fileName);
	            
	        } else {
	        	no.setNews_cover_image("");
	        }
			
			boolean addResult=n.addNews(no);
	        if (addResult) {
	        	session.setAttribute("flash_message", "Thêm tin tức thành công");
	            session.setAttribute("flash_type", "success");
	        } else {
	        	session.setAttribute("flash_message", "Thêm tin tức không thành công");
	            session.setAttribute("flash_type", "danger");
	        }
	        response.sendRedirect(request.getContextPath() + "/page?view=news");
		} else {
			session.setAttribute("flash_message", "Bạn được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=news");
		}
	}
	
	public String getShortContent(String htmlContent, int maxLength) {
	    String plainText = Jsoup.parse(htmlContent).text();
	    if (plainText.length() > maxLength) {
	        return plainText.substring(0, maxLength);
	    }
	    return plainText;
	}
}
