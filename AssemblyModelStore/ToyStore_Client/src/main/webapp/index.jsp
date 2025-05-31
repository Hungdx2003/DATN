<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Banner Section Begin -->
<section class="banner">
   <div class="anime-carousel-container">
	    <div class="anime-carousel owl-carousel owl-theme">
	    	<c:forEach var="b" items="${banner}">
		    	<div class="item">
		            <img src="/images/${b.image_url}" alt="${b.image_url}">
		        </div>
	    	</c:forEach>
	    </div>
	</div>
</section>
<!-- Banner Section End -->

<!-- Product Section Begin -->
<section class="product">
    <div class="container">
        <div class="row mb-3">
            <div class="col-12 d-flex justify-content-between align-items-center">
                <div class="section-title">
                    <h4>Sản phẩm mới</h4>
                </div>
                <div class="product-nav" id="new-nav">
                    <button class="slide-btn prev"><i class="fa-solid fa-chevron-left"></i></button>
                    <button class="slide-btn next"><i class="fa-solid fa-chevron-right"></i></button>
                </div>
            </div>
        </div>
        
        <div class="product-slider owl-carousel">
        	<c:forEach var="np" items="${newProduct}">
            <div class="product__item">
                <div class="product__item__pic">
                	<img src="/product_images/${np.imageUrl}" alt="${np.productName}" style="max-width: 100%; height: auto;">
                	<c:if test="${np.productStatus =='Mới'}">
                   	 	<div class="label new">Mới</div>
                    </c:if>
                </div>
                <div class="product__item__text">
                    <h6><a href="page?view=product&product_id=${np.productId}">${np.productName}</a></h6>
                    <div class="product__price formatted-value">${np.productPrice}</div>
                </div>
            </div>
			</c:forEach>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Trend Section Begin -->
<section class="trend">
    <div class="container">
        <div class="row"> 
            <div class="col-lg-12">
                <div class="flash-sale-container ">
                    <div class="flash-sale-header">
                        <div class="flash-sale-header-left">
                            <h2>SẢN PHẨM GIẢM GIÁ</h2>
                        </div>
                        <div class="product-nav" id="sale-nav">
                            <button class="slide-btn prev"><i class="fa-solid fa-chevron-left"></i></button>
                            <button class="slide-btn next"><i class="fa-solid fa-chevron-right"></i></button>
                        </div>
                    </div>

                    <div class="owl-carousel flash-sale-carousel">
	                    <c:forEach var="sp" items="${listSale}">
	                    	<div class="flash-sale-item">
                                <div class="row g-2 align-items-center">
                                    <div class="col-4">
                                        <img src="/product_images/${sp.imageUrl}" alt="${sp.productName}">
                                    </div>
                                    <div class="col-8 flash-sale-content">
                                        <a href="page?view=product&product_id=${sp.productId}">${sp.productName}</a>
                                        <div class="d-flex align-items-center">
										  <p class="price me-2 formatted-value">${sp.productSalePrice}</p>
										  <span class="text-decoration-line-through text-muted formatted-value">${sp.productPrice}</span>
										</div>
                                        <p class="status">Còn lại: ${sp.productQuantity}</p>
                                    </div>
                                </div>
	                        </div>
	                    </c:forEach>
                    </div> <!-- end .flash-sale-carousel -->
                </div> <!-- end .flash-sale-container -->
            </div> <!-- end .col-lg-8 -->
        </div> <!-- end .row -->
    </div> <!-- end .container -->
</section>
<!-- Trend Section End -->

	<section class="trend">
    <div class="container">
        <div class="row"> 
            <div class="col-lg-4">
                <div class="best-sellers">
				  <h4>SẢN PHẨM BÁN CHẠY</h4>
					<c:forEach var="np" items="${bestSeller}">
						  <div class="product-item">
						    <img src="/product_images/${np.imageUrl}" alt="${np.productName}">
						    <div class="product-info">
						      <h5>${np.productName}</h5>
						      <div class="text-muted formatted-value">${np.productPrice}</div>
						      <a href="page?view=product&product_id=${np.productId}">Xem chi tiết</a>
						    </div>
						  </div>	
					  </c:forEach>		
				</div>
            </div>
            <div class="col-lg-8">
            	<h3 class="fw-bold mb-4">BLOG TIN TỨC</h3>
			    <div class="row">
			        <!-- Main post (phần tử đầu tiên) -->
			        <div class="col-lg-6 mb-3">
			            <c:forEach var="post" items="${posts}" varStatus="status">
			                <c:if test="${status.first}">
			                    <div class="main-post card h-100">
			                        <a href="page?view=news_detail&news_id=${post.news_id}">
			                        	<img src="/images/${post.news_cover_image}" class="card-img-top" alt="Main Post">
			                        </a>
			                        <div class="card-body">
			                        	<a href="page?view=news_detail&news_id=${post.news_id}" class="text-decoration-none text-dark">
			                        		<h5 class="card-title main-title">${post.news_title}</h5>
			                        	</a>
			                            <small class="text-muted formatted-date">${post.news_create_date}</small>
			                            <div class="small-post-desc mb-0">${post.description}</div>
			                        </div>
			                    </div>
			                </c:if>
			            </c:forEach>
			        </div>
			
			        <!-- Small posts (phần tử còn lại) -->
			        <div class="col-lg-6">
			            <c:forEach var="post" items="${posts}" varStatus="status">
			                <c:if test="${!status.first}">
			                    <div class="small-post d-flex mb-3">
			                        <a href="page?view=news_detail&news_id=${post.news_id}" style="flex: 0 0 40%;" class="mr-2">	
			                        	<img src="/images/${post.news_cover_image}">
			                        </a>
			                        <div style="flex: 1;" >
			                            <a href="page?view=news_detail&news_id=${post.news_id}" class="text-decoration-none text-dark">
			                        		<h6 class="card-title small-post-title">${post.news_title}</h6>
			                        	</a>
			                            <small class="text-muted formatted-date">${post.news_create_date}</small>
			                            <div class="small-post-desc mb-0">${post.description}</div>
			                        </div>
			                    </div>
			                </c:if>
			            </c:forEach>
			        </div>
			    </div>
            </div>
        </div> <!-- end .row -->
    </div> <!-- end .container -->
</section>
	<!-- Discount Section Begin -->
	<section class="discount">
	    <div class="container">
	        <div class="row d-flex align-items-stretch">
	            <!-- Hình ảnh bên trái -->
	            <div class="col-lg-6 p-0 d-flex align-items-center">
	                <div class="discount__pic">
	                    <c:forEach var="p" items="${promo}">
	            			<img src="/images/${p.image_url}" alt="${p.image_url}">
	            		</c:forEach>
	                </div>
	            </div>
	
	            <!-- Khối text bên phải (giữ nguyên khung) -->
	            <div class="col-lg-6 p-0">
	                <div class="discount__text">
	                    <div id="discountInnerCarousel" class="carousel slide" data-bs-ride="carousel">
	                        <div class="carousel-inner">
	                            <c:forEach var="discount" items="${discounts}" varStatus="loop">
	                                <div class="carousel-item ${loop.index == 0 ? 'active' : ''}" style="padding: 75px 90px 30px;">
	                                    <div class="discount__text__title">
	                                        <span>Khuyến mãi</span>
	                                        <h2>${discount.discount_name}</h2>
	                                        <h5><span>Giảm giá</span> ${discount.discount_value}${discount.discount_value_type}</h5>
	                                    </div>
	                                    <div class="discount__countdown" id="countdown-${discount.discount_id}">
	                                        <div class="countdown__item"><span class="days">--</span><p>Ngày</p></div>
	                                        <div class="countdown__item"><span class="hours">--</span><p>Giờ</p></div>
	                                        <div class="countdown__item"><span class="minutes">--</span><p>Phút</p></div>
	                                        <div class="countdown__item"><span class="seconds">--</span><p>Giây</p></div>
	                                    </div>
	                                </div>
	
	                                <script>
	                                    (function () {
	                                        const endDate = new Date("${fn:replace(discount.end_date, ' ', 'T')}");
	                                        const container = document.getElementById("countdown-${discount.discount_id}");
	
	                                        function updateCountdown() {
	                                            const now = new Date().getTime();
	                                            const distance = endDate - now;
	                                            if (distance < 0) {
	                                                container.closest(".carousel-item").style.display = "none";
	                                                return;
	                                            }
	                                            const days = Math.floor(distance / (1000 * 60 * 60 * 24));
	                                            const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	                                            const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	                                            const seconds = Math.floor((distance % (1000 * 60)) / 1000);
	                                            container.querySelector(".days").innerText = days;
	                                            container.querySelector(".hours").innerText = hours;
	                                            container.querySelector(".minutes").innerText = minutes;
	                                            container.querySelector(".seconds").innerText = seconds;
	                                        }
	
	                                        updateCountdown();
	                                        setInterval(updateCountdown, 1000);
	                                    })();
	                                </script>
	                            </c:forEach>
	                        </div>
	
	                        <!-- Nút điều hướng nếu cần -->
	                        <button class="carousel-control-prev" type="button" data-bs-target="#discountInnerCarousel" data-bs-slide="prev">
							  <span class="fa fa-chevron-left fs-2 text-dark" aria-hidden="true"></span>
							  <span class="visually-hidden">Previous</span>
							</button>
							
							<button class="carousel-control-next" type="button" data-bs-target="#discountInnerCarousel" data-bs-slide="next">
							  <span class="fa fa-chevron-right fs-2 text-dark" aria-hidden="true"></span>
							  <span class="visually-hidden">Next</span>
							</button>
	                    </div>
	
	                    <a href="page?view=shop">Mua ngay</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</section>

	<!-- Discount Section End -->
	
	<!-- Services Section Begin -->
	<section class="services bg-dark text-white mt-5">
    <div class="container py-2">
        <div class="row text-center">
            <div class="col-md-3">
                <i class="fa-solid fa-boxes-packing fa-2x mb-2"></i>
                <h6>Dịch vụ đóng gói riêng</h6>
            </div>
            <div class="col-md-3">
                <i class="fa-solid fa-check-circle fa-2x mb-2"></i>
                <h6>Hàng chính hãng</h6>
            </div>
            <div class="col-md-3">
                <i class="fa-solid fa-gift fa-2x mb-2"></i>
                <h6>Quà tặng bí mật</h6>
            </div>
            <div class="col-md-3">
                <i class="fa-regular fa-rectangle-list fa-2x mb-2"></i>
                <h6>Mã giảm giá đặc quyền</h6>
            </div>
        </div>
    </div>
</section>
	<!-- Services Section End -->
<script src="js/functions.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
    let value = td.textContent.trim();
    td.textContent = formatTd(value);
});
document.querySelectorAll(".formatted-date").forEach(function (cell) {
  cell.textContent = formatDateOnly(cell.textContent.trim());
});
</script>
<script>
function initCarousel() {
    if (typeof jQuery === 'undefined' || typeof jQuery.fn.owlCarousel === 'undefined') {
        return setTimeout(initCarousel, 50);
    }

    $(function() {
    	 $('.anime-carousel').owlCarousel({
             loop: true,
             margin: 10,
             center: true,
             nav: true,
             dots: false,
             autoHeight: true ,
             navText: ['<span>&#10094;</span>', '<span>&#10095;</span>'],
             items: 1,
             stagePadding: 150,
             responsive: {
                 0: {
                     items: 1,
                     stagePadding: 50
                 },
                 600: {
                     items: 1,
                     stagePadding: 100
                 },
                 1000: {
                     items: 1,
                     stagePadding: 150
                 }
             }
         });

        var newProductCarousel = $(".product-slider").owlCarousel({
            items: 4,
            loop: true,
            margin: 30,
            dots: false,
            nav: false,
            autoplay: true,
            autoplayTimeout: 3000,
            autoplayHoverPause: true,
            responsive: {
                0: { items: 1 },
                576: { items: 2 },
                768: { items: 3},
                992: { items: 4 }
            }
        });

        // Gán sự kiện cho nút điều hướng sản phẩm mới
        $("#new-nav .prev").click(function() {
            newProductCarousel.trigger('prev.owl.carousel');
        });
        $("#new-nav .next").click(function() {
            newProductCarousel.trigger('next.owl.carousel');
        });

        // Khởi tạo flash sale slider
        var flashSaleCarousel = $(".flash-sale-carousel").owlCarousel({
            items: 3,
            loop: true,
            stagePadding: 30,
            margin: 10,
            dots: false,
            nav: false,
            autoHeight: true ,
            responsive:{
                0:   { 
                	items: 1,
                	stagePadding: 30
               	},
                765: { 
                	items: 1,
                	stagePadding: 30
               	},
               	992: { 
                	items: 2,
                	stagePadding: 30 
               	},
                1200: { 
                	items: 3,
                	stagePadding: 30 
               	}
              }

        });

        $("#sale-nav .prev").click(function() {
            flashSaleCarousel.trigger('prev.owl.carousel');
        });
        $("#sale-nav .next").click(function() {
            flashSaleCarousel.trigger('next.owl.carousel');
        });

        $(".set-bg").each(function() {
            var bg = $(this).attr("data-setbg");
            if (bg) {
                $(this).css("background-image", "url(" + bg + ")");
            }
        });
    });
}

initCarousel();
</script>