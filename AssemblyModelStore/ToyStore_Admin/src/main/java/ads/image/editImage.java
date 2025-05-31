package ads.image;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.ImageObject;
import ads.objects.PermissionObject;

/**
 * Servlet implementation class editImage
 */
@WebServlet("/api/interface/editImage")
public class editImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editImage() {
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
        HttpSession session = request.getSession();

        
        PermissionObject permissionsImage = (PermissionObject) session.getAttribute("interfacePer");
        if (permissionsImage != null && permissionsImage.isCan_edit()) {
        	String id=request.getParameter("image_id");
        	String activeParam=request.getParameter("active");
        	boolean active= ("1".equals(activeParam))?true:false;
        	if (id==null || id.trim().isEmpty() || id.equals("0")) {
        		session.setAttribute("flash_message", "Thêm ảnh không thành công");
                session.setAttribute("flash_type", "danger");
                response.sendRedirect(request.getContextPath() + "/page?view=interface");
			}
        	
        	ImageObject io=new ImageObject();
        	io.setActive(active);
        	io.setImage_id(Integer.parseInt(id));
        	
        	imageModel im=new imageModel();
        	boolean editResult = im.editImage(io);

            if (editResult) {
                session.setAttribute("flash_message", "Sửa trạng thái ảnh thành công");
                session.setAttribute("flash_type", "success");
            } else {
                session.setAttribute("flash_message", "Lỗi khi sửa trạng thái ảnh");
                session.setAttribute("flash_type", "danger");
            }
            response.sendRedirect(request.getContextPath() + "/page?view=interface");
        }else {
        	session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=interface");
		}
	}

}
