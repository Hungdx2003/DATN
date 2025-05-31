<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
	  String pageTitle = (String) request.getAttribute("pageTitle");
	  if (pageTitle == null) {
	      pageTitle = "HiStore";
	  }
	%>
    <title><%= pageTitle %></title>
 	<link rel="icon" type="image/png" href="img/logo-icon.png">
 	<link rel="apple-touch-icon" type="image/png" href="img/logo-icon.png">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.min1.css" type="text/css">
    <link rel="stylesheet" href="css/all.min.css" type="text/css">	
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link href="quill/quill.snow.css" rel="stylesheet">
  	<link href="quill/quill.bubble.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <div class="offcanvas__logo">
            <a href="./index.html"><img src="img/logo-web.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
    <div class="container-fluid">
        <div class="row align-items-center">
            <!-- Logo -->
            <div class="col-xl-2 col-lg-2">
                <div class="header__logo">
                    <a href="page?view=home"><img src="img/logo-web.png" alt="" style="max-width: 100%; max-height: 100px;"></a>
                </div>
            </div>

            <!-- Menu -->
            <div class="col-xl-6 col-lg-6">
                <nav class="header__menu">
					<ul id="dynamic-menu">
					    <li class="${param.view == 'home' ? 'active' : ''}">
					        <a href="page?view=home">Trang chủ</a>
					    </li>
					    <li class="${param.view == 'shop' ? 'active' : ''}">
					        <a href="page?view=shop">Shop</a>
					    </li>
					
					    <c:forEach var="parent" items="${categories}">
					        <c:if test="${parent.parent_category_id == null || parent.parent_category_id == 0}">
					            <%-- Kiểm tra nếu là cha, hoặc tổ tiên của cat_id đang chọn --%>
					            <c:set var="isActiveParent" value="false" />
					
					            <c:if test="${param.cat_id == parent.category_id}">
					                <c:set var="isActiveParent" value="true" />
					            </c:if>
					
					            <c:forEach var="child" items="${categories}">
					                <c:if test="${child.parent_category_id == parent.category_id}">
					                    <c:if test="${param.cat_id == child.category_id}">
					                        <c:set var="isActiveParent" value="true" />
					                    </c:if>
					                    <c:forEach var="grandchild" items="${categories}">
					                        <c:if test="${grandchild.parent_category_id == child.category_id && param.cat_id == grandchild.category_id}">
					                            <c:set var="isActiveParent" value="true" />
					                        </c:if>
					                    </c:forEach>
					                </c:if>
					            </c:forEach>
					
					            <li class="${isActiveParent ? 'active' : ''}">
					                <a href="page?view=category&cat_id=${parent.category_id}">${parent.category_name}</a>
					
					                <%-- Kiểm tra có con không --%>
					                <c:set var="hasChild" value="false" />
					                <c:forEach var="child" items="${categories}">
					                    <c:if test="${child.parent_category_id == parent.category_id}">
					                        <c:set var="hasChild" value="true" />
					                    </c:if>
					                </c:forEach>
					
					                <c:if test="${hasChild}">
					                    <ul class="dropdown">
					                        <c:forEach var="child" items="${categories}">
					                            <c:if test="${child.parent_category_id == parent.category_id}">
					                                <c:set var="isActiveChild" value="${param.cat_id == child.category_id}" />
					
					                                <li class="${isActiveChild ? 'active' : ''}">
					                                    <a href="page?view=category&cat_id=${child.category_id}">${child.category_name}</a>
					
					                                    <%-- Kiểm tra có cháu không --%>
					                                    <c:set var="hasGrandchild" value="false" />
					                                    <c:forEach var="grandchild" items="${categories}">
					                                        <c:if test="${grandchild.parent_category_id == child.category_id}">
					                                            <c:set var="hasGrandchild" value="true" />
					                                        </c:if>
					                                    </c:forEach>
					
					                                    <c:if test="${hasGrandchild}">
					                                        <ul class="dropdown">
					                                            <c:forEach var="grandchild" items="${categories}">
					                                                <c:if test="${grandchild.parent_category_id == child.category_id}">
					                                                    <li class="${param.cat_id == grandchild.category_id ? 'active' : ''}">
					                                                        <a href="page?view=category&cat_id=${grandchild.category_id}">${grandchild.category_name}</a>
					                                                    </li>
					                                                </c:if>
					                                            </c:forEach>
					                                        </ul>
					                                    </c:if>
					                                </li>
					                            </c:if>
					                        </c:forEach>
					                    </ul>
					                </c:if>
					            </li>
					        </c:if>
					    </c:forEach>
					
					    <li class="${param.view == 'news' ? 'active' : ''}">
					        <a href="page?view=news">Tin tức</a>
					    </li>
					</ul>

                </nav>
            </div>

		<div class="col-xl-4 col-lg-4 d-flex align-items-center justify-content-end gap-3">
		
		    <!-- Thanh tìm kiếm -->
		    <div class="search-wrapper flex-shrink-1">
		        <form action="page" method="get" class="search-form">
		        	<input type="hidden" name="view" value="search">
		            <input type="text" name="query" placeholder="Tìm kiếm sản phẩm" class="search-input">
		            <button type="submit" class="search-btn">
		                <i class="fa fa-search"></i>
		            </button>
		        </form>
		    </div>
		
		    <!-- Login, Register, Icon -->
		    <div class="header__right d-flex">
				  <div class="header__right__auth">
				    <c:if test="${empty user}">
					  <a href="page?view=login" class="account-link">
					    <div class="acc-btn">
					      <i class="bi bi-person-workspace"></i>
					      <div class="account-text">
					        <span class="account-title">Tài khoản</span>
					        <span class="login-text">Đăng nhập</span>
					      </div>
					    </div>
					  </a>
					</c:if>
					
					<!-- Nếu đã đăng nhập -->
					<c:if test="${not empty user}">
					  <div class="account-wrapper">
					    <button class="account-btn" id="accountBtn">
					      <i class="bi bi-person-workspace"></i> Tài khoản
					    </button>
					    <div class="dropdown_user" id="accountDropdown">
					      <div class="dropdown-header">
					        <p class="welcome">Chào mừng đến với HiStore</p>
					        <p class="subtext">Quản lý tài khoản & đơn hàng</p>
					      </div>
					      <ul class="menu-list">
					        <li><a href="page?view=profile&side=info"><i class="bi bi-person-fill"></i> Hồ sơ của tôi</a></li>
					        <li><a href="page?view=profile&side=orders"><i class="bi bi-box-seam"></i> Đơn hàng của tôi</a></li>
					      </ul>
					      <div class="dropdown-footer">
					        <a href="logout" class="sign-in">Đăng Xuất</a>
					      </div>
					    </div>
					  </div>
					 <script>
						  document.addEventListener("DOMContentLoaded", () => {
						    const btn = document.getElementById("accountBtn");
						    const dropdown = document.getElementById("accountDropdown");
						
						    btn.addEventListener("click", (e) => {
						      e.stopPropagation();
						      dropdown.classList.toggle("show");
						    });
						
						    document.addEventListener("click", (e) => {
						      if (!dropdown.contains(e.target) && e.target !== btn) {
						        dropdown.classList.remove("show");
						      }
						    });
						
						    const menuLinks = dropdown.querySelectorAll("a, button");
						    menuLinks.forEach(item => {
						      item.addEventListener("click", () => {
						        dropdown.classList.remove("show");
						      });
						    });
						  });
						</script>
					</c:if>
				  </div>
				</div>

		        <ul class="header__right__widget d-flex align-items-center mb-0">
		            <li>
		                <a href="page?view=cart"><span class="icon_bag_alt"></span><div class="tip">${totalCartItem}</div></a>
		            </li>
		        </ul>
		    </div>
		
		</div>

        </div>

        <div class="canvas__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>

    <!-- Header Section End -->
    
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

<!-- Footer Section Begin -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="footer__about">
                    <div class="footer__logo">
                        <a href="page?view=home"><img src="img/logo-web.png" alt="" style="max-width:200px;"></a>
                    </div>
                    <div class="footer__payment">
                        <a href="#"><img src="img/payment/payment-6.png" alt="" style="width: 50px;height: 50px;"></a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-2">
                <div class="footer__widget">
                    <h6>Hỗ trợ khách hàng</h6>
                    <ul>
                        <li><a href="page?view=cart">Giỏ hàng</a></li>
                        <li><a href="page?view=shop">Danh mục sản phẩm</a></li>
                        <li><a href="#">Tìm kiếm</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3 col-md-2">
                <div class="footer__widget">
                    <h6>Tài khoản</h6>
                    <ul>
                        <li><a href="page?view=profile&side=info">Thông tin tài khoản</a></li>
                        <li><a href="page?view=profile&side=orders">Kiểm tra đơn hàng</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3 col-md-2">
                <div class="footer__widget">
                    <h6>Liên hệ</h6>
                    <p>Nếu bạn cần hỗ trợ hoặc có bất kỳ thắc mắc gì, hãy liên hệ ngay với HISTORE nhé!</p>
                    <ul style="font-size: 15px; color:#666666;">
                        <li class="row">
                        	<div class="col-auto"><i class="fa-solid fa-location-dot" ></i></div>
                        	<div class="col">Phường Phú Diễn, Quận Bắc Từ Liêm, Hà Nội</div>
                       	</li>
                        <li class="row">
                        	<div class="col-auto"><i class="fa-solid fa-phone"></i></div>
                        	<div class="col">0349 749410</div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                <div class="footer__copyright__text">
                    <p>Copyright &copy; <script>document.write(new Date().getFullYear());</script> All rights reserved</p>
                </div>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->  

<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.countdown.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.nicescroll.min.js"></script>
<script src="js/main.js"></script>
<jsp:include page="notification-message.jsp" />
</body>

</html>