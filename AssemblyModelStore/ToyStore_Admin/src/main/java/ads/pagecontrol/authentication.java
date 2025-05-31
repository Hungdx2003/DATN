package ads.pagecontrol;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.objects.PermissionObject;
import ads.objects.UserObject;
import ads.permission.permissionModel;

/**
 * Servlet Filter implementation class authentication
 */
@WebFilter(urlPatterns = {"/page", "/api/*"})
public class authentication extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public authentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();
        String path = uri.substring(contextPath.length());

        String loginPath = contextPath + "/login.jsp";

        // Kiểm tra đăng nhập
        boolean isLoggedIn = (session != null && session.getAttribute("logUser") != null);

        // Ngăn cache
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        if (!isLoggedIn) {
            httpResponse.sendRedirect(loginPath);
            return;
        }

        // Nếu là đường dẫn /page, kiểm tra quyền can_view
        if (path.startsWith("/page")) {
            String objectName = httpRequest.getParameter("view");
            UserObject user=(UserObject)session.getAttribute("logUser");
            int roleId=user.getUser_roles();
            
            if ("home".equalsIgnoreCase(objectName)) {
                chain.doFilter(request, response);
                return;
            }
            
            if ("role".equalsIgnoreCase(objectName)) {
                if (roleId != 1) {
                    session.setAttribute("flash_message", "Chỉ quản trị viên mới được phép truy cập vào phần quyền.");
                    session.setAttribute("flash_type", "warning");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/page?view=home");
                    return;
                }
            } else {
                // Các đối tượng khác: kiểm tra quyền như bình thường
                permissionModel p = new permissionModel();
                PermissionObject permissions = p.getPermission(roleId, objectName);

                if (objectName == null || permissions == null || !permissions.isCan_view()) {
                    session.setAttribute("flash_message", "Bạn không được phép truy cập vào trang bạn vừa yêu cầu");
                    session.setAttribute("flash_type", "warning");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/page?view=home");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
