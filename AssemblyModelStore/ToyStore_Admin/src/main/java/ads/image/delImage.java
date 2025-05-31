package ads.image;

import java.io.File;
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
 * Servlet implementation class delImage
 */
@WebServlet("/api/interface/delImage")
public class delImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/images";    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delImage() {
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
        if (permissionsImage != null && permissionsImage.isCan_delete()) {
        	String id=request.getParameter("del_image_id");
        	if (id==null || id.trim().isEmpty() || id.equals("0")) {
        		session.setAttribute("flash_message", "Thiếu tham số image_id");
                session.setAttribute("flash_type", "danger");
                response.sendRedirect(request.getContextPath() + "/page?view=interface");
                return;
			}
        	
        	imageModel im = new imageModel();
            ImageObject imageObj = im.getImage(Integer.parseInt(id));

            if (imageObj != null) {
                String fileName = imageObj.getImage_url();
                File imageFile = new File(IMAGE_UPLOAD_PATH, fileName);
                ImageObject io=new ImageObject();
                io.setImage_id(Integer.parseInt(id));
                
                boolean deleteResult = im.delImage(io);

                if (deleteResult) {
                    if (imageFile.exists()) {
                        imageFile.delete();
                    }

                    session.setAttribute("flash_message", "Xóa ảnh thành công");
                    session.setAttribute("flash_type", "success");
                } else {
                    session.setAttribute("flash_message", "Lỗi khi xóa ảnh trong database");
                    session.setAttribute("flash_type", "danger");
                }
            }else {
                session.setAttribute("flash_message", "Không tìm thấy ảnh để xóa");
                session.setAttribute("flash_type", "warning");
            }
            response.sendRedirect(request.getContextPath() + "/page?view=interface");
        }else {
        	session.setAttribute("flash_message", "Bạn không được phép sử dụng chức năng này");
            session.setAttribute("flash_type", "warning");
            response.sendRedirect(request.getContextPath() + "/page?view=interface");
		}
	}

}
