<%@page import="ads.objects.UserObject"%>
<%@page import="ads.objects.PermissionObject"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ads.objects.PermissionObject" %>
<%@ page import="ads.objects.UserObject" %>
<%@ page import="ads.objects.RoleObject" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  	<%
	  String pageTitle = (String) request.getAttribute("pageTitle");
	  if (pageTitle == null) {
	      pageTitle = "HiStore";
	  }
	%>
  <title><%= pageTitle %></title>
  <meta content="" name="description">
  <meta content="" name="keywords">


  <link href="assets/img/logo-icon.png" rel="icon">
  <link href="assets/img/logo-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
    <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index.html" class="logo d-flex align-items-center">
        <img src="assets/img/logo-icon.png" alt="">
        <span class="d-none d-lg-block" style="font-size: 35px;">Hi<span class="text-primary" style="font-size: 35px;">Store</span></span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->	

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item d-block d-lg-none">
          <a class="nav-link nav-icon search-bar-toggle " href="#">
            <i class="bi bi-search"></i>
          </a>
        </li><!-- End Search Icon-->

        <li class="nav-item dropdown pe-3">
        <%
			UserObject uo= (UserObject) session.getAttribute("logUser");
			RoleObject ro= (RoleObject) session.getAttribute("role");
		%>
          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="assets/img/avatar.jpg" alt="Profile" class="rounded-circle">
            <span class="d-none d-md-block dropdown-toggle ps-2"><%= uo.getUser_name()%></span>
          </a><!-- End Profile Iamge Icon -->
          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><%= uo.getUser_fullname()%></h6>
              <span><%= ro.getRole_name()%></span>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                <i class="bi bi-gear"></i>
                <span>Thông tin</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="<%= request.getContextPath() %>/logout">
                <i class="bi bi-box-arrow-right"></i>
                <span>Đăng xuất</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header>
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">
		<%
			PermissionObject permissionsProduct = (PermissionObject) session.getAttribute("productPer");
			PermissionObject permissionsUser = (PermissionObject) session.getAttribute("userPer");
			PermissionObject permissionsCategory = (PermissionObject) session.getAttribute("categoryPer");
			PermissionObject permissionsDiscount = (PermissionObject) session.getAttribute("discountPer");
			PermissionObject permissionsOrder = (PermissionObject) session.getAttribute("orderPer");
			PermissionObject permissionsNews = (PermissionObject) session.getAttribute("newsPer");
			PermissionObject permissionsInterface = (PermissionObject) session.getAttribute("interfacePer");
		    boolean isProductDropdownOpen = "Danh mục sản phẩm".equals(pageTitle) || "Danh sách sản phẩm".equals(pageTitle);
		    boolean isUserDropdownOpen = "Người dùng".equals(pageTitle) || "Phân quyền".equals(pageTitle);
		%>
		
      <li class="nav-item">
        <a class="nav-link <%= pageTitle.equals("Tổng quan") ? "" : "collapsed" %>" href="page?view=home">
          <i class="bi bi-grid"></i>
          <span>Tổng quan</span>  
        </a>
      </li>
		<!-- Dropdown Người dùng -->
		<li class="nav-item">
		  <a class="nav-link <%= isUserDropdownOpen ? "" : "collapsed" %>" 
		     data-bs-target="#user-nav" data-bs-toggle="collapse" href="#">
		    <i class="bi bi-people-fill"></i><span>Người dùng</span><i class="bi bi-chevron-down ms-auto"></i>
		  </a>
		  <ul id="user-nav" class="nav-content collapse <%= isUserDropdownOpen ? "show" : "" %>">
		    <% if (permissionsUser != null && permissionsUser.isCan_view()) { %>
		    <li>
		      <a href="page?view=user" class="<%= "Người dùng".equals(pageTitle) ? "active" : "" %>">
		        <i class="bi bi-people-fill"></i><span>Danh sách người dùng</span>
		      </a>
		    </li>
		    <% } %>
		    <% if (ro.getRole_name().equals("Quản trị viên")) { %>
		    <li>
		      <a href="page?view=role" class="<%= "Phân quyền".equals(pageTitle) ? "active" : "" %>">
		        <i class="bi bi-person-fill-lock"></i><span>Phân quyền</span>
		      </a>
		    </li>
		    <% } %>
		  </ul>
		</li>
		
		<!-- Dropdown Sản phẩm -->
		<li class="nav-item">
		  <a class="nav-link <%= isProductDropdownOpen ? "" : "collapsed" %>" 
		     data-bs-target="#product-nav" data-bs-toggle="collapse" href="#">
		    <i class="bi bi-shop-window"></i><span>Sản phẩm</span><i class="bi bi-chevron-down ms-auto"></i>
		  </a>
		  <ul id="product-nav" class="nav-content collapse <%= isProductDropdownOpen ? "show" : "" %>">
		    <% if (permissionsProduct != null && permissionsProduct.isCan_view()) { %>
		    <li>
		      <a href="page?view=category" class="<%= "Danh mục sản phẩm".equals(pageTitle) ? "active" : "" %>">
		        <i class="bi bi-collection"></i><span>Danh mục sản phẩm</span>
		      </a>
		    </li>
		    <% } %>
		    <% if (permissionsCategory != null && permissionsCategory.isCan_view()) { %>
		    <li>
		      <a href="page?view=product" class="<%= "Danh sách sản phẩm".equals(pageTitle) ? "active" : "" %>">
		        <i class="bi bi-box-seam"></i><span>Danh sách sản phẩm</span>
		      </a>
		    </li>
		    <% } %>
		  </ul>
		</li>

       <%if (permissionsDiscount != null && permissionsDiscount.isCan_view()) { %>
      <li class="nav-item">
        <a class="nav-link <%= pageTitle.equals("Giảm giá") ? "" : "collapsed" %>" href="page?view=discount">
          <i class="ri-coupon-line"></i>
          <span>Giảm giá</span>  
        </a>
      </li><%} %>
      <%if (permissionsNews != null && permissionsNews.isCan_view()) { %>
      <li class="nav-item">
        <a class="nav-link <%= pageTitle.equals("Tin tức") ? "" : "collapsed" %>" href="page?view=news">
          <i class="bi bi-newspaper"></i>
          <span>Tin tức</span>  
        </a>
      </li><%} %>
       <%if (permissionsOrder != null && permissionsOrder.isCan_view()) { %>
      <li class="nav-item">
        <a class="nav-link <%= pageTitle.equals("Đơn hàng") ? "" : "collapsed" %>" href="page?view=order">
          <i class="bx bx-receipt"></i>
          <span>Đơn hàng</span>  
        </a>
      </li><%} %>
      <%if (permissionsInterface != null && permissionsInterface.isCan_view()) { %>
      <li class="nav-item">
        <a class="nav-link <%= pageTitle.equals("Giao diện") ? "" : "collapsed" %>" href="page?view=interface">
          <i class="bi bi-aspect-ratio"></i>
          <span>Giao diện</span>  
        </a>
      </li><%} %>
    </ul>

  </aside>
    <main id="main" class="main">
        <% 
            String contentPage = (String) request.getAttribute("contentPage");
            if (contentPage != null) {
        %>
                <jsp:include page="<%= contentPage %>" />
        <%
            } else {
        %>
                <p>Không tìm thấy nội dung.</p>
        <%
            }
        %>
    </main>

    <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>HiStore</span></strong>.
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
 
  <!-- Vendor JS Files -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/quill-image-uploader@1.2.3/dist/quill.imageUploader.min.js"></script>
  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  <jsp:include page="notification-message.jsp" />
  <jsp:include page="flash-message.jsp" />
</body>
</html>
