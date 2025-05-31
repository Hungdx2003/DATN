package ads.news;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ads.objects.NewsObject;

/**
 * Servlet implementation class getNews
 */
@WebServlet("/api/getNews")
public class getNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int newsId = Integer.parseInt(request.getParameter("news_id"));
		newsModel d=new newsModel();
        NewsObject no = d.getNews(newsId);
        
        JSONObject newsDetails = new JSONObject();
        newsDetails.put("id", no.getNews_id());
        newsDetails.put("title", no.getNews_title());
        newsDetails.put("content", no.getNews_content());
        newsDetails.put("author", no.getNews_author());
        newsDetails.put("image", no.getNews_cover_image());
        newsDetails.put("createdDate", no.getNews_create_date());
        newsDetails.put("createdBy", no.getNews_created_by());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(newsDetails.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
