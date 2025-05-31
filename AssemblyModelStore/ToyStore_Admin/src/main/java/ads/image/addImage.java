package ads.image;

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

import ads.objects.ImageObject;
import ads.objects.PermissionObject;

/**
 * Servlet implementation class addImage
 */
@WebServlet("/api/interface/addImage")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 5 * 1024 * 1024,   // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class addImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/images";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addImage() {
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
        if (permissionsImage != null && permissionsImage.isCan_add()) { 
        	Part imagePart = request.getPart("image");
        	String type=request.getParameter("type");
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            if (fileName != null && !fileName.isEmpty()) {
                File uploadDir = new File(IMAGE_UPLOAD_PATH);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                File imageFile = new File(uploadDir, fileName);
                Files.copy(imagePart.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                ImageObject io = new ImageObject();
                io.setImage_url(fileName);
                io.setType(type);
                io.setActive(false);
                
                imageModel im = new imageModel();
                boolean addResult = im.addImage(io);

                if (addResult) {
                    session.setAttribute("flash_message", "Thêm ảnh thành công");
                    session.setAttribute("flash_type", "success");
                } else {
                    session.setAttribute("flash_message", "Lỗi khi lưu thông tin ảnh vào database");
                    session.setAttribute("flash_type", "danger");
                }
            } else {
                session.setAttribute("flash_message", "Không có file ảnh được chọn");
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
