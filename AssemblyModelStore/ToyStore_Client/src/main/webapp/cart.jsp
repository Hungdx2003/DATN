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
                    <span>Giỏ hàng</span>
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
            <c:choose>
			    <c:when test="${not empty cartitems}">
			       <div class="col-lg-8">
			            <form action="update-cart" method="post" id="cartForm">
			            	<div>
					            <div class="card mb-4 shadow-sm px-4 pt-4">
					                <div class="shop__cart__table">
						                    <table>
						                        <thead>
						                            <tr>
						                                <th>Sản phẩm</th>
						                                <th>Giá</th>
						                                <th>Số lượng</th>
						                                <th>Thành tiền</th>
						                                <th></th>
						                            </tr>
						                        </thead>
						                        <tbody>
						                        	<c:forEach var="c" items="${cartitems}">
							                            <tr>
							                                <td class="cart__product__item">
							                                    <img src="/product_images/${c.imageUrl}" alt="" style="width: 100px; height: 100px; object-fit: cover; border-radius: 5px;">
							                                    <div class="cart__product__item__title">
							                                        <h6>${c.productName}</h6>
							                                    </div>
							                                </td>
							                                <c:set var="price" value=""/>
							                                <c:choose> 
															    <c:when test="${c.productSalePrice != 0 and c.active
															                  and now.time >= c.startDate.time 
															                  and now.time <= c.endDate.time}">
															        <c:set var="price" value="${c.productSalePrice}"/>
															    </c:when>
															    <c:otherwise>
															        <c:set var="price" value="${c.productPrice}"/>
															    </c:otherwise>
															</c:choose>
							                                <td class="cart__price price formatted-value" data-price="${price}">${price}</td>
							                                <td class="cart__quantity">
															    <div class="quantity-box border rounded-pill" data-maxstock="${c.productQuantity}">
															        <span class="dec quantity-input">-</span>
															        <input type="hidden" name="cartItemId" value="${c.cartItemId}" />
															        <input type="hidden" class="subtotal" name="subtotal"/>
															        <input type="text" class="quantity-input" value="${c.quantity}" name="quantity">
															        <span class="inc quantity-input">+</span>
															    </div>
															    <div class="warning-message text-danger small mt-1"></div>
															</td>
							                                <td class="cart__total total formatted-value">${c.quantity * price}</td>
							                                <td class="cart__close">
						                                		<button type="button" class="btn-delete" data-id="${c.cartItemId}"><span class="icon_close"></span></button>
							                                </td>
							                            </tr>
						                            </c:forEach>
						                        </tbody>
						                    </table>
						                </div>
					                </div>
					                <div class="d-flex align-items-center justify-content-end mt-3">
							            <button type="submit" class="btn btn-outline-primary rounded-pill mr-2" id="updateCartBtn" name="action" value="update"><i class="bi bi-arrow-clockwise"></i> Cập nhật</button>
							            <button type="button" class="btn btn-outline-danger rounded-pill btn-deletes" data-id="${cart_id}"><i class="bi bi-trash3"></i> Xóa hết</button>
						          	</div>
					          	</div>
				          	</form>
			            </div>
		            <div class="col-lg-4">
			            <div class="card shadow-sm">
			            	<div class="card-body">
				              <h5 class="card-title" style="font-weight: 600;">Thông tin đơn hàng</h5>
				              <hr>
				                <div class="cart__total__procced">
					              	<div class="d-flex justify-content-between align-items-center mb-3">
						                <h6 class="mb-0">Tổng tiền:</h6>
						                <h5 class="mb-0 text-nowrap" id="total"></h5>
					              	</div>
					              	<hr>
				                    <button type="submit" class="primary-btn" form="cartForm" name="action" value="checkout">Đặt hàng →</button>
				                    <a href="page?view=shop" class="btn btn-outline-danger rounded-pill w-100 mt-3 py-2">← Tiếp tục mua sắm</a>
				                </div>
			                </div>
			            </div>
			        </div>
			        
<script>
document.addEventListener("DOMContentLoaded", function () {
    function formatPrice(value) {
        return new Intl.NumberFormat("vi-VN", { style: "currency", currency: "VND" }).format(value);
    }
    function updateTotal() {
        let subtotal = 0;   
        document.querySelectorAll(".cart__total").forEach(function (td) {
            subtotal += parseFloat(td.dataset.total);
        });

        document.getElementById("total").textContent = formatPrice(subtotal);
    }

    document.querySelectorAll(".quantity-box").forEach(function (box, index) {
        const input = box.querySelector("input.quantity-input");
        const incBtn = box.querySelector(".inc");
        const decBtn = box.querySelector(".dec");

        const maxStock = parseInt(box.getAttribute("data-maxstock")) || 1;
        const warning = box.nextElementSibling;
        const priceEl = box.closest("tr").querySelector(".cart__price");
        const totalEl = box.closest("tr").querySelector(".cart__total");
        const sub_total=box.querySelector("input.subtotal");

        const price = parseFloat(priceEl.dataset.price);

        function showWarning(message) {
            warning.innerHTML = `<span style='margin-right: 5px;'>❗</span> ${message}`;
        }

        function clearWarning() {
            warning.textContent = "";
        }

        function updateTotalPrice() {
            const quantity = parseInt(input.value) || 1;
            const total = quantity * price;
            totalEl.dataset.total = total;
            totalEl.textContent = formatPrice(total);
            sub_total.value=total;
            updateTotal();
        }

        function checkQuantityRealtime() {
            let value = parseInt(input.value);
            if (isNaN(value) || value < 1) {
                showWarning("Vui lòng nhập số hợp lệ (>= 1).");
                return false;
            } else if (value > maxStock) {
                showWarning(`Chỉ còn ${maxStock} sản phẩm trong kho.`);
                return false;
            } else {
                clearWarning();
                return true;
            }
        }

        function enforceQuantityLimits() {
            let value = parseInt(input.value);
            if (isNaN(value) || value < 1) {
                input.value = 1;
            } else if (value > maxStock) {
                input.value = maxStock;
            }
            updateTotalPrice();
        }

        input.addEventListener("input", function () {
            clearTimeout(input.typingTimer);
            input.typingTimer = setTimeout(function () {
                enforceQuantityLimits();
                checkQuantityRealtime();
            }, 500);
        });

        input.addEventListener("blur", function () {
            enforceQuantityLimits();
        });

        incBtn.addEventListener("click", function () {
            let value = parseInt(input.value) || 1;
            if (value < maxStock) {
                input.value = value + 1;
                updateTotalPrice();
            }
            checkQuantityRealtime();
        });

        decBtn.addEventListener("click", function () {
            let value = parseInt(input.value) || 1;
            if (value > 1) {
                input.value = value - 1;
                updateTotalPrice();
            }
            checkQuantityRealtime();
        });

        updateTotalPrice();
    });

    updateTotal();
});
</script>
			    </c:when>
			    <c:otherwise>
			        <!-- Giao diện khi giỏ hàng rỗng -->
			        <div class="container mt-5">
			            <div class="text-center">
			                <img src="img/cart.png" alt="Giỏ hàng trống" style="width: 300px; max-width: 100%;">
			                <h4 class="mt-4">Không có sản phẩm nào trong giỏ hàng</h4>
			                <a href="page?view=shop" class="btn btn-outline-primary mt-3 rounded-pill px-4 py-2">← Tiếp tục mua sắm</a>
			            </div>
			        </div>
			    </c:otherwise>
			</c:choose>
            
       	</div>
    </div>
</section>
<script src="js/functions.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
    let value = td.textContent.trim();
    td.textContent = formatTd(value);
});
document.querySelectorAll(".btn-delete").forEach(function(button) {
    button.addEventListener("click", function() {
       const cartItemId = this.getAttribute("data-id");

       const form = document.createElement("form");
       form.method = "POST";
       form.action = "del-cartitem";

       const input = document.createElement("input");
       input.type = "hidden";
       input.name = "ci_id";
       input.value = cartItemId;

       form.appendChild(input);
       document.body.appendChild(form);
       form.submit();
    });
});

document.querySelectorAll(".btn-deletes").forEach(function(button) {
    button.addEventListener("click", function() {
       const cartId = this.getAttribute("data-id");

       const form = document.createElement("form");
       form.method = "POST";
       form.action = "del-cartitems";

       const input = document.createElement("input");
       input.type = "hidden";
       input.name = "cart_id";
       input.value = cartId;

       form.appendChild(input);
       document.body.appendChild(form);
       form.submit();
    });
});
</script>

