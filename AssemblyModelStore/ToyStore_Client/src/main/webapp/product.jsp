<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" />
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <a href="page?view=category&cat_id=${product.categoryId}">${product.categoryName} </a>
                    <span>${product.productName}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->
<!-- Product Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="product__details__pic">
                    <img data-hash="product-1" class="product__big__img border rounded-lg" src="/product_images/${product.imageUrl}" alt="">
                </div>
            </div>
            <div class="col-lg-6">
                <div class="product__details__text">
                    <h3>${product.productName}</h3>
                    <div>Thương hiệu: <strong>${product.productBrand}</strong></div>
                    <c:choose>
					    <c:when test="${product.productSalePrice != 0 and product.active
					                  and now.time >= product.startDate.time 
					                  and now.time <= product.endDate.time}">
					        <div class="product__details__price d-flex align-items-center">
					            <p class="formatted-value sale_price">${product.productSalePrice}</p>
					            <span class="formatted-value normal_price">${product.productPrice}</span>
					        </div>
					    </c:when>
					    <c:otherwise>
					        <div class="product__details__price formatted-value">${product.productPrice}</div>
					    </c:otherwise>
					</c:choose>
                    <div class="product__details__button">
                        <form id="addToCartForm" action="add-to-cart" method="post" onsubmit="return validateQuantity(${product.productQuantity})">
						    <div class="quantity">
						        <span>Số lượng:</span>
						        <div class="pro-qty">
						        	<input type="hidden" name="product_id" value="${product.productId}">
						            <span class="dec qtybtn">-</span>
								    <input type="text" id="quantityInput" name="quantity" value="1">
								    <span class="inc qtybtn">+</span>
						        </div>
						        <div id="warningMessage" style="color: red; font-size: 0.9em; margin-top: 4px;"></div>
						    </div>
						    <c:choose>
							    <c:when test="${product.productStatus == 'Hết hàng'}">
							        <button type="button" class="cart-btn" style="border:none; background-color: #e0e0e0; color: #888888;" disabled>
							            <span class="icon_blocked"></span> Hết hàng
							        </button>
							    </c:when>
							    <c:otherwise>
							        <button type="submit" class="cart-btn" style="border:none; background-color: #ca1515; color: #fff;">
							            <span class="icon_bag_alt"></span> Thêm vào giỏ hàng
							        </button>
							    </c:otherwise>
							</c:choose>
						</form>
                    </div>
                    <div class="product__details__widget">
                        <ul>
                            <li>
                                <span>Trạng thái:</span>
                                <c:if test="${product.productStatus == 'Còn hàng'}">
	                                <div class="stock__checkbox">
	                                    <label class="text-success">
	                                        ${product.productStatus}
	                                    </label>
	                                </div>
                                </c:if>
                                <c:if test="${product.productStatus == 'Hết hàng'}">
	                                <div class="stock__checkbox">
	                                    <label for="stockin" class="text-danger fw-bold">
	                                        ${product.productStatus}
	                                    </label>
	                                </div>
                                </c:if>
                            </li>
                            <li>
                                <span>Khuyến mãi:</span>
                                <c:choose>
								    <c:when test="${product.discountName != null and product.active
								                  and now.time >= product.startDate.time 
								                  and now.time <= product.endDate.time}">
								        <p>Sản phẩm này giảm ${product.discountValue}${product.discountValueType}</p>
								    </c:when>
								    <c:otherwise>
								        <p>Sản phẩm này chưa có chương trình khuyến mãi</p>
								    </c:otherwise>
								</c:choose>
                            </li>
                            <li style="font-weight: 600; font-size: 14px;">Tiện ích khi mua hàng: </li>
                        </ul>
                    </div>
                    <div class="container py-3">
					    <div class="row text-start justify-content-center">
					        <div class="col-6 d-flex align-items-center mb-2">
					            <i class="fa-solid fa-boxes-packing me-2" style="font-size: 16px;"></i>
					            <span>Dịch vụ đóng gói riêng</span>
					        </div>
					        <div class="col-6 d-flex align-items-center mb-2">
					            <i class="fa-solid fa-check-circle me-2" style="font-size: 16px;"></i>
					            <span>Hàng chính hãng</span>
					        </div>
					        <div class="col-6 d-flex align-items-center mb-2">
					            <i class="fa-solid fa-gift me-2" style="font-size: 16px;"></i>
					            <span>Quà tặng bí mật</span>
					        </div>
					        <div class="col-6 d-flex align-items-center mb-2">
					            <i class="fa-regular fa-rectangle-list me-2" style="font-size: 16px;"></i>
					            <span>Mã giảm giá đặc quyền</span>
					        </div>
					    </div>
					</div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="product__details__tab">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item">
							<a class="nav-link active" data-bs-toggle="tab" href="#tabs-1" role="tab">Thông tin sản phẩm</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="tab" href="#tabs-2" role="tab">Đánh giá ( ${totalItems} )</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tabs-1" role="tabpanel">
                           <div class="product-description-container">
							  <div id="description" class="collapsed">
							    <div class="description-content ql-editor" id="detail">
							      
							    </div>
							    <div class="fade-out"></div>
							  </div>
							  <button id="toggle-button" style="display: none;">
							  	<span class="button-text">Xem thêm</span> <i class="fa-solid fa-chevron-down"></i>
							  </button>
							</div>
                        </div>
                        <div class="tab-pane" id="tabs-2" role="tabpanel">
                            <div class="review-container">
							    <p class="leave-btn">Viết đánh giá</p>
							    <div style="margin-top:50px; ">	
							    	<form action="addReview" method="post">
							    		<input type="hidden" name="product_id" value="${product.productId}">
								      	<textarea id="review" name="review" placeholder="Nội dung"></textarea><br>
										<div style="text-align: right;">
										  <button type="submit" class="submit-btn">Gửi</button>
										</div>
							    	</form>
							    </div>
						  	</div>
						  	<h6>Đánh giá ( ${totalItems} )</h6>
                            <div class="blog__details__comment">
	                            <c:forEach var="r" items="${review}">
	                            	<div class="blog__comment__item">
		                                <div class="blog__comment__item__pic" style="background-color: ${r.color}; color: #fff;">
		                                    ${r.firstCharName}
		                                </div>
		                                <div class="blog__comment__item__text">
		                                    <h5>${r.userFullName}</h5>
		                                    <p>${r.comment}</p>
		                                    <ul>
		                                        <li><i class="fa-regular fa-clock"></i> <span class="review-date">${r.createAt}</span></li>
		                                    </ul>
		                                </div>
		                            </div>
	                            </c:forEach>
	                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        	<div class="col-lg-12 text-center">
                <div class="related__title">
                    <h5>Sản phẩm liên quan</h5>
                </div>
            </div>
        	<div class="container col-lg-12">
			  <div class="related-carousel-wrapper position-relative">
			    <!-- Carousel -->
			    <div class="owl-carousel owl-theme related-carousel">
			      <c:forEach var="product" items="${relatedProduct}">
			      	<div class="item">
				        <div class="card shadow-sm rounded-4 border-0 h-100">
				        	<div class="badge-container">
				        		<c:if test="${product.productStatus == 'Mới'}">
		                            <span class="badge-new">Mới</span>
		                        </c:if>
		                        <c:if test="${not empty product.discountName and product.active
	                       				 	and now.time >= product.startDate.time 
	                        				and now.time <= product.endDate.time}">
							        <c:choose>
							            <c:when test="${product.discountValueType == '%'}">
							                <span class="badge-sale">-${product.discountValue}${product.discountValueType}</span>
							            </c:when>
							            <c:otherwise>
							                 <span class="badge-sale">Giảm giá</span>
							            </c:otherwise>
							        </c:choose>
								</c:if>
				        	</div>
				          <img src="/product_images/${product.imageUrl}" class="card-img-top rounded-top-4" alt="Product 1">
				          <div class="card-body">
				            <a class="card-title mb-2" href="page?view=product&product_id=${product.productId}">${product.productName}</a>
				            <c:choose>
							    <c:when test="${product.productSalePrice != 0 and product.active
							                  and now.time >= product.startDate.time 
							                  and now.time <= product.endDate.time}">
							        <div class="d-flex align-items-baseline">
							            <span class="fw-bold text-danger fs-6 formatted-value">${product.productSalePrice}</span>
							    		<span class="text-muted text-decoration-line-through ms-2 fs-6 formatted-value">${product.productPrice}</span>
							        </div>
							    </c:when>
							    <c:otherwise>
							        <p class="text-dark fw-bold fs-6 formatted-value">${product.productPrice}</p>
							    </c:otherwise>
							</c:choose>
				          </div>
				        </div>
				      </div>
			      </c:forEach>
			    </div>
			
			    <!-- Nút điều hướng -->
			    <button class="custom-prev position-absolute top-50 start-0 translate-middle-y btn btn-light rounded-circle shadow">
			      <i class="bi bi-chevron-left"></i>
			    </button>
			    <button class="custom-next position-absolute top-50 end-0 translate-middle-y btn btn-light rounded-circle shadow">
			      <i class="bi bi-chevron-right"></i>
			    </button>
			  </div>
			</div>
        </div>
    </div>
</section>
<script src="js/functions.js"></script>
<script src="quill/quill.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
    let value = td.textContent.trim();
    td.textContent = formatTd(value);
});

document.querySelectorAll(".review-date").forEach(function (cell) {
  cell.textContent = formatDateOnly(cell.textContent.trim());
});

const detail = document.getElementById("detail");
detail.innerHTML = `${product.productDetail}`;

document.addEventListener("DOMContentLoaded", function () {
	const description = document.getElementById("description");
	const content = description.querySelector(".description-content");
	const fadeOut = description.querySelector(".fade-out");
	const toggleButton = document.getElementById("toggle-button");
	const buttonText = toggleButton.querySelector(".button-text");
	const buttonIcon = toggleButton.querySelector("i");
	
	const isOverflowing = content.scrollHeight > description.clientHeight;
	
	if (isOverflowing) {
	  toggleButton.style.display = "block";
	  fadeOut.classList.remove("hidden");
	} else {
	  toggleButton.style.display = "none";
	  fadeOut.classList.add("hidden");
	}
	
	toggleButton.addEventListener("click", function () {
	  description.classList.toggle("expanded");
	  const isExpanded = description.classList.contains("expanded");
	
	  buttonText.textContent = isExpanded ? "Thu gọn" : "Xem thêm";
	  buttonIcon.className = isExpanded ? "fas fa-chevron-up" : "fas fa-chevron-down";
	
	  // Ẩn hoặc hiện fade-out khi mở rộng / thu gọn
	  if (isExpanded) {
	    fadeOut.classList.add("hidden");
	  } else {
	    fadeOut.classList.remove("hidden");
	  }
	});
	
    const quantityInput = document.getElementById("quantityInput");
    const warningMessage = document.getElementById("warningMessage");
    const maxStock = ${product.productQuantity};

    const decBtn = document.querySelector(".dec");
    const incBtn = document.querySelector(".inc");
    let typingTimer;
    
    function showWarning(message) {
        warningMessage.innerHTML = "<span style='margin-right: 5px;'>❗</span>" + message;
    }

    function clearWarning() {
        warningMessage.textContent = "";
    }

    function checkQuantityRealtime() {
        const value = parseInt(quantityInput.value);
        if (isNaN(value) || value < 1) {
            showWarning("Vui lòng nhập số hợp lệ (>= 1).");
        } else if (value > maxStock) {
            showWarning("Chỉ còn " + maxStock + " sản phẩm trong kho.");
        } else {
            clearWarning();
        }
    }

    function enforceQuantityLimits() {
        let value = parseInt(quantityInput.value);
        if (isNaN(value) || value < 1) {
            quantityInput.value = 1;
        } else if (value > maxStock) {
            quantityInput.value = maxStock;
        }
    }

    quantityInput.addEventListener("input", function () {
        checkQuantityRealtime();
        typingTimer = setTimeout(function () {
            enforceQuantityLimits(); 
        }, 1000);
    });

    quantityInput.addEventListener("blur", function () {
        checkQuantityRealtime();
        enforceQuantityLimits();
    });

    if (incBtn) {
        incBtn.addEventListener("click", function () {
            let value = parseInt(quantityInput.value) || 1;
            if (value < maxStock) {
                quantityInput.value = value + 1;
            }
            checkQuantityRealtime();
        });
    }

    if (decBtn) {
        decBtn.addEventListener("click", function () {
            let value = parseInt(quantityInput.value) || 1;
            if (value > 1) {
                quantityInput.value = value - 1;
            }
            checkQuantityRealtime();
        });
    }
    
    function initCarouselWhenReady() {
        if (window.jQuery && typeof $.fn.owlCarousel === 'function') {
          $('.related-carousel').owlCarousel({
        	  loop: false,
              margin: 30,
              nav: false,
              dots: false,
              responsive:{
                0:{ items:1 },
                576:{ items:2 },
                768:{ items:3 },
                992:{ items:4 }
              }
          });

          $('.related-carousel-wrapper .custom-next').click(function () {
            $('.related-carousel').trigger('next.owl.carousel');
          });

          $('.related-carousel-wrapper .custom-prev').click(function () {
            $('.related-carousel').trigger('prev.owl.carousel');
          });
        } else {
          setTimeout(initCarouselWhenReady, 100);
        }
      }
    initCarouselWhenReady();
});
</script>
<c:if test="${sessionScope.openReviewTab != null}">
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var tabTrigger = document.querySelector('a[href="#tabs-2"]');
        if (tabTrigger) {
            new bootstrap.Tab(tabTrigger).show();

            // Cuộn tới tab đánh giá
            var reviewTab = document.querySelector("#tabs-2");
            if (reviewTab) {
                reviewTab.scrollIntoView({ behavior: "smooth", block: "start" });
            }
        }
    });
</script>
<%
    session.removeAttribute("openReviewTab");
%>
</c:if>
