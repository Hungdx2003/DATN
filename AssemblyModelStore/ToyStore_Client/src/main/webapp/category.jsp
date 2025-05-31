<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                        <span>${catName}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Section Begin -->
    <section class="shop spad"> 
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <jsp:include page="sidebar.jsp"/>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
				    <div class="row d-flex justify-content-center">
				        <c:forEach var="product" items="${products}">
				            <div class="col-lg-3 col-md-4 border" style="border-radius: 10px; margin: 8px;">
				                 <div class="product__item ${not empty product.discountName and product.active
								    and now.time >= product.startDate.time 
								    and now.time <= product.endDate.time ? 'sale' : ''}">
				                    <!-- Lấy ảnh sản phẩm bằng product_id -->
									<div class="product__item__pic mt-2">
										<img src="/product_images/${product.imageUrl}" 
				                             style="max-width: 100%; height: auto; object-fit: contain; object-position: center;" />
				                        <c:if test="${product.productStatus == 'new'}">
				                            <div class="label new">New</div>
				                        </c:if>
				                        <c:if test="${not empty product.discountName and product.active}">
										    <div class="label">
										        <c:choose>
										            <c:when test="${product.discountValueType == '%'}">
										                - ${product.discountValue}${product.discountValueType}
										            </c:when>
										            <c:otherwise>
										                Giảm giá
										            </c:otherwise>
										        </c:choose>
										    </div>
										</c:if>
				                        <c:if test="${product.productQuantity == 0}">
				                            <div class="label stockout stockblue">Hết hàng</div>
				                        </c:if>
				                        <ul class="product__hover">
				                            <li><a href="${baseUrl}/product_images/${product.imageUrl}" class="image-popup"><span class="arrow_expand"></span></a></li>
				                            <li>
				                            	<form action="quick-add-to-cart" method="post">
				                            		<input type="hidden" name="product_id" value="${product.productId}">
				                            		<button type="submit"><span class="icon_bag_alt"></span></button>
				                            	</form>
			                            	</li>
				                        </ul>
				                    </div>
				                    <div class="product__item__text">
				                        <h6><a href="page?view=product&product_id=${product.productId}">${product.productName}</a></h6>
				                        <c:choose> 
										    <c:when test="${product.productSalePrice != 0 and product.active
										                  and now.time >= product.startDate.time 
										                  and now.time <= product.endDate.time}">
										        <div class="product__price">
										            <span class="formatted-value shop_sale_price">${product.productSalePrice}</span> 
										            <span class="formatted-value normal_price">${product.productPrice}</span>
										        </div>
										    </c:when>
										    <c:otherwise>
										        <div class="product__price formatted-value">${product.productPrice}</div>
										    </c:otherwise>
										</c:choose>
				                    </div>
				                </div>
				            </div>
				        </c:forEach>
				        <div class="col-lg-12 text-center">
						    <div class="pagination__option">
								<c:if test="${currentPage > 1}">
						            <a href="?view=category&cat_id=${catId}&page=${currentPage - 1}"><i class="fa fa-angle-left"></i></a>
						        </c:if>
						        <c:forEach var="page" items="${paginationData}">
						            <c:choose>
						                <c:when test="${page == 0}">
						                    <span>...</span>
						                </c:when>
						
						                <c:when test="${page == currentPage}">
						                    <a class="active" href="?view=category&cat_id=${catId}&page=${page}">${page}</a>
						                </c:when>
						
						                <c:otherwise>
						                    <a href="?view=category&cat_id=${catId}&page=${page}">${page}</a>
						                </c:otherwise>
						            </c:choose>
						        </c:forEach>
						
						        <c:if test="${currentPage < totalPages}">
						            <a href="?view=category&cat_id=${catId}&page=${currentPage + 1}">
						                <i class="fa fa-angle-right"></i>
						            </a>
						        </c:if>
						
						    </div>
						</div>
				    </div>
				</div>
            </div>
        </div>
    </section>
    <!-- Shop Section End -->
    <script src="js/functions.js"></script>
    <script>
    document.querySelectorAll('.formatted-value').forEach(function(td) {
        let value = td.textContent.trim();
        td.textContent = formatTd(value);
    });
    </script>