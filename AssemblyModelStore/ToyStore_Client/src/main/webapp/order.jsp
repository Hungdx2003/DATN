<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <span>Đơn hàng</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Shop Cart Section Begin -->
<section class="shop-cart spad">
    <div class="container">
        <div class="row">
        	<div class="col-lg-5">
	            <div class="card shadow-sm">
	            	<div class="card-body">
		              <h5 class="card-title" style="font-weight: 600;">Thông tin đơn hàng</h5>
		              <hr>
				        <div class="row p-1">
			                <strong class="col">Người nhận: </strong>
			                <span class="col">${order.receiverName}</span>
			            </div>
			            <div class="row p-1">
			                <strong class="col">Số điện thoại: </strong>
			                <span class="col">${order.receiverMobilephone}</span>
			            </div>
			            <div class="row p-1">
			                <strong class="col">Email: </strong>
			                <span class="col">${order.email}</span>
			            </div>
			            <div class="row p-1">
			                <strong class="col">Địa chỉ nhận: </strong>
			                <span class="col">${order.deliveryAddress}</span>
			            </div>
			            <c:set var="statusClass" value="danger" />
						<c:choose>
						    <c:when test="${order.orderStatus == 'Đang xử lý'}">
						        <c:set var="statusClass" value="info" />
						    </c:when>
						    <c:when test="${order.orderStatus == 'Đang giao'}">
						        <c:set var="statusClass" value="warning" />
						    </c:when>
						    <c:when test="${order.orderStatus == 'Hoàn thành'}">
						        <c:set var="statusClass" value="success" />
						    </c:when>
						</c:choose>
			            <div class="row p-1">
			                <strong class="col">Tình trạng đơn hàng: </strong>
			                <span class="col"><span class=" badge bg-${statusClass}" style="font-size: 14px;">${order.orderStatus}</span></span>
			            </div>
				        <hr>
				        <div class="row p-1">
			                <strong class="col">Phương thức thanh toán: </strong>
			                <span class="col">${order.paymentMethod}</span>
			            </div>
			            <c:set var="payClass" value="danger" />
						<c:choose>
						    <c:when test="${order.paymentStatus == 'Đã thanh toán'}">
						        <c:set var="payClass" value="success" />
						    </c:when>
						</c:choose>
			            <div class="row p-1">
			                <strong class="col">Trạng thái: </strong>
			                <span class="col text-${payClass}" style="font-weight: 600;">${order.paymentStatus}</span>
			            </div>
			            <c:if test="${order.orderStatus=='Đang xử lý'}">
			            	<hr>
					        <div class="actions d-flex justify-content-center">
					        	<form action="canceled-order" method="post">
					        		<input type="hidden" value="${order.orderId}" name="order_id">
					        		<button type="submit" class="register" ><i class="bi bi-chevron-left"></i> Hủy đơn hàng</button>
					        	</form>
					        </div>
			            </c:if>
       			   </div>
	            </div>
	        </div>
            <div class="col-lg-7">
	           	<div>
		            <div class="card mb-4 shadow-sm px-4 pt-4">
		                <div class="order-summary">
		                	<c:forEach var="od" items="${orderDetails}">
		                		<div class="od-product">
							      <img src="/product_images/${od.imageUrl}" alt="${od.productName}">
							      <div class="details">
							        <p class="name">${od.productName}</p>
							        <p class="meta">Số lượng: ${od.quantity} &nbsp;&nbsp; Giá: <span class="formatted-value">${od.productPrice}</span></p>
							      </div>
							      <p class="price formatted-value text-nowrap">${od.subtotal}</p>
							    </div>
		                	</c:forEach>
						
						    <div class="summary">
						      <div class="pay"><span >Tổng tiền:</span><span class="formatted-value">${order.totalOrderValue}</span></div>
						      <div class="pay"><span>Giảm giá:</span><span class="formatted-value">${order.discountMoney}</span></div>
						      <div class="pay total"><span>Thanh toán:</span><span class="formatted-value">${order.totalAmount}</span></div>
						    </div>
						  </div>
		                </div>
		          	</div>
	            </div>
       	</div>
    </div>
</section>
<script src="js/functions.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
    let value = td.textContent.trim();
    td.textContent = formatTd(value);
});
</script>

