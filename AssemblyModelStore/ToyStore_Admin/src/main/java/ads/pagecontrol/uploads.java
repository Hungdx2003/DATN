package ads.pagecontrol;

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
import javax.servlet.http.Part;

/**
 * Servlet implementation class uploads
 */
@WebServlet("/upload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 5 * 1024 * 1024,   // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class uploads extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String IMAGE_UPLOAD_PATH = "C:/Users/admin/Documents/DATN/images";  // Đảm bảo đường dẫn đúng

    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploads() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        Part imagePart = request.getPart("post_image");
        String fileName = "";

        // Kiểm tra xem có file ảnh được gửi lên không
        if (imagePart != null && imagePart.getSize() > 0) {
            // Lấy tên file từ phần ảnh đã chọn
            fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        } else {
            // Nếu không có file ảnh, gán tên file trống
            fileName = "";
        }

        // Tạo thư mục lưu ảnh nếu chưa tồn tại
        File uploadDir = new File(IMAGE_UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Nếu có file ảnh, tiến hành lưu
        if (!fileName.isEmpty()) {
            File imageFile = new File(uploadDir, fileName);
            // Lưu file vào thư mục
            Files.copy(imagePart.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        // Trả về URL của ảnh đã lưu
        String imageUrl = "/images/" + fileName;  // Đảm bảo đây là URL hợp lệ để hiển thị ảnh
        response.getWriter().write("{\"url\":\"" + imageUrl + "\"}");
    }
}
