<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="checkout spad">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="#">Have a coupon?</a> Click
            here to enter your code.</h6>
        </div>
    </div>
    <form action="add-order" class="checkout__form" id="multiStepForm" method="post">
        <div class="row">
            <div class="col-lg-8 border rounded-lg pb-2">
                <div class="container py-2 mb-1">
                
				    <!-- Progress bar -->
				    <div class="progressbar">
				      <div class="progress-step active">
				        <div class="step-number">1</div>
				        <span class="step-label">Thông tin</span>
				      </div>
				      <div class="progress-line"></div>
				      
				      <div class="progress-step">
				        <div class="step-number">2</div>
				        <span class="step-label">Vận chuyển</span>
				      </div>
				      <div class="progress-line"></div>
				      
				      <div class="progress-step">
				        <div class="step-number">3</div>
				        <span class="step-label">Thanh toán</span>
				      </div>
				      <div class="progress-line"></div>
				      
				      <div class="progress-step">
				        <div class="step-number">4</div>
				        <span class="step-label">Xem lại</span>
				      </div>
				    </div>
				
				      <!-- Bước 1: Thông tin -->
				      <div class="form-step active">
				        <h4>Thông tin khách hàng</h4>
				        <p class="text-muted">Vui lòng nhập thông tin liên hệ của bạn</p>
				        <div class="row g-3 mb-3">
				          <div class="col-md-12">
				            <label class="form-label">Họ</label>
				            <input type="text" class="form-control" placeholder="Họ" name="fullname" value="${sessionScope.order_view.receiverName}" required>
				          </div>
				          <div class="col-12">
				            <label class="form-label">Địa chỉ Email</label>
				            <input type="email" class="form-control" placeholder="Email của bạn" name="email" value="${sessionScope.order_view.email}" required>
				          </div>
				          <div class="col-12">
				            <label class="form-label">Số điện thoại</label>
				            <input type="tel" class="form-control" placeholder="Số điện thoại" name="phone" value="${sessionScope.order_view.receiverMobilephone}" required>
				          </div>
				        </div>
				        <div class="d-flex justify-content-end">
				          <button type="button" class="btn btn-next">Tiếp tục đến Vận chuyển</button>
				        </div>
				      </div>
				
				      <!-- Bước 2: Vận chuyển -->
				      <div class="form-step">
				        <h4>Địa chỉ giao hàng</h4>
				        <p class="text-muted">Chúng tôi nên giao đơn hàng của bạn đến đâu?</p>
				        <div class="mb-3">
				          <label class="form-label">Địa chỉ</label>
				          <input type="text" class="form-control" placeholder="Địa chỉ" name="address" value="${sessionScope.order_view.address}" required>
				        </div>
				        <div class="row g-3 mb-3">
				          <div class="col-md-4">
				            <label class="form-label">Tỉnh/Thành phố</label>
				            <input type="text" class="form-control" placeholder="Tỉnh/Thành phố" name="province" value="${sessionScope.order_view.province}" required>
				          </div>
				          <div class="col-md-4">
				            <label class="form-label">Quận/Huyện</label>
				            <input type="text" class="form-control" placeholder="Quận/Huyện" name="district" value="${sessionScope.order_view.district}" required>
				          </div>
				          <div class="col-md-4">
				            <label class="form-label">Phường/Xã</label>
				            <input type="text" class="form-control" placeholder="Phường/Xã" name="ward" value="${sessionScope.order_view.ward}" required>
				          </div>
				        </div>
				        <div class="d-flex justify-content-between">
				          <button type="button" class="btn btn-back">Quay lại Thông tin</button>
				          <button type="button" class="btn btn-next">Tiếp tục đến Thanh toán</button>
				        </div>
				      </div>
				
				      <!-- Bước 3: Thanh toán -->
				      <div class="form-step">
				        <h4>Phương thức thanh toán</h4>
				        <p class="text-muted">Chọn cách bạn muốn thanh toán</p>
				
				        <div class="container mt-5">
						  <div class="form-check custom-radio-box">
						    <input class="form-check-input check" type="radio" name="option" id="radio1" value="COD" <c:if test="${sessionScope.order_view.paymentMethod == 'COD'}">checked</c:if>>
						    <label class="form-check-label label" for="radio1">
						      Thanh toán khi nhận hàng
						    </label>
						    <div id="COD" class="form-content">
						      - Nhân viên sẽ liên hệ lại bạn sau khi hoàn tất đơn để hướng dẫn / thông báo chi tiết hơn.
						    </div>
						  </div>
						
						  <div class="form-check custom-radio-box">
						    <input class="form-check-input check" type="radio" name="option" id="radio2" value="VNPay" <c:if test="${sessionScope.order_view.paymentMethod == 'VNPay'}">checked</c:if>>
						    <label class="form-check-label label" for="radio2">
						      Thanh toán Online
						    </label>
						    <div id="form2" class="form-content">
						      - Nhân viên sẽ liên hệ lại bạn sau khi hoàn tất đơn để hướng dẫn / thông báo chi tiết hơn.
						    </div>
						  </div>
						</div>
				
				        <div class="d-flex justify-content-between">
				          <button type="button" class="btn btn-back">Quay lại Vận chuyển</button>
				          <button type="button" class="btn btn-next">Xem trước Đơn hàng</button>
				        </div>
				      </div>
				
				      <!-- Bước 4: Xem lại -->
				      <div class="form-step">
				        <h4>Xem lại đơn hàng</h4>
				        <p class="text-muted">Vui lòng kiểm tra thông tin trước khi đặt hàng</p>
				
				        <!-- Thông tin liên hệ -->
				        <div class="mb-3 p-3 border rounded">
				          <div class="d-flex justify-content-between">
				            <strong>Thông tin liên hệ</strong>
				            <a href="#" class="edit-step text-decoration-none" data-step="0">Chỉnh sửa</a>
				          </div>
				          <p class="mb-0" id="review-name">Nguyễn Văn A</p>
				          <p class="mb-0" id="review-email">vana@example.com</p>
				          <p class="mb-0" id="review-phone">+84 123 456 789</p>
				        </div>
				        <!-- Địa chỉ giao hàng -->
				        <div class="mb-3 p-3 border rounded">
				          <div class="d-flex justify-content-between">
				            <strong>Địa chỉ giao hàng</strong>
				            <a href="#" class="edit-step text-decoration-none" data-step="1">Chỉnh sửa</a>
				          </div>
				          <p class="mb-0" id="review-address"></p>
				        </div>
				        <!-- Phương thức thanh toán -->
				        <div class="mb-3 p-3 border rounded">
				          <div class="d-flex justify-content-between">
				            <strong>Phương thức thanh toán</strong>
				            <a href="#" class="edit-step text-decoration-none" data-step="2">Chỉnh sửa</a>
				          </div>
				          <p class="mb-0" id="review-payment">Thẻ tín dụng kết thúc bằng 3456</p>
				        </div>
				
				        <div class="d-flex justify-content-between">
				          <button type="button" class="btn btn-back">Quay lại Thanh toán</button>
				          <button type="button" onclick="changeActionAndSubmit('add-order')" class="order-button">Đặt hàng</button>
				        </div>
				      </div>
				  </div>
                </div>
                
                <div class="col-lg-4">
                    <div class="order-container">
				        <h5>Đơn hàng (${totalCartItem} sản phẩm)</h5>
				        <hr>
				        <c:forEach var="ci" items="${sessionScope.cart}">
				        	<div class="product-items">
					            <div class="product-images">
					                <img src="/product_images/${ci.imageUrl}" alt="${ci.productName}">
					                <span class="badges">${ci.quantity}</span>
					            </div>
					            <div class="product-inf">
					                <p class="product-name">${ci.productName}</p>
					                <p class="product-price formatted-value">${ci.subtotal}</p>
					            </div>
					        </div>
				        </c:forEach>
				        <hr>
				        <div class="discount-code">
			            	<input type="text" placeholder="Nhập mã giảm giá" name="discount_code" autocomplete="off">
		            		<button type="button" onclick="changeActionAndSubmit('apply-code')">Áp dụng</button>
				        </div>
				        <hr>
				        <c:set var="totalCartValue" value="0" />
						<c:forEach var="ci" items="${sessionScope.cart}">
						    <c:set var="totalCartValue" value="${totalCartValue + ci.subtotal}" />
						</c:forEach>
						
						<c:choose>
						  <c:when test="${sessionScope.discount_money != 0}">
						    <c:set var="discountMoney" value="${sessionScope.discount_money}" />
						  </c:when>
						  <c:otherwise>
						    <c:set var="discountMoney" value="0" />
						  </c:otherwise>
						</c:choose>

						
						<!-- Tính tổng tiền -->
						<c:set var="totalAmount" value="${totalCartValue - discountMoney}" />
				        <div class="price-summary">
				            <div class="row">
				                <span class="col">Tạm tính</span>
				                <span class="col text-end formatted-value">${totalCartValue}</span>
				            </div>
				            <div class="row">
				                <span class="col">Giảm giá</span>
				                <span class="col text-end formatted-value">${discountMoney}</span>
				            </div>
				            <div class="row total">
				                <strong class="col">Tổng cộng</strong>
				                <strong class="col total-price text-end formatted-value">${totalAmount}</strong>
				            </div>
				        </div>
				
				        <div class="actions">
				            <a href="page?view=cart"><i class="bi bi-chevron-left"></i> Quay về giỏ hàng</a>
				        </div>
				    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<script src="js/functions.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
    let value = td.textContent.trim();
    td.textContent = formatTd(value);
});

document.addEventListener('DOMContentLoaded', function () {
  const steps      = document.querySelectorAll(".progress-step");
  const lines      = document.querySelectorAll(".progress-line");
  const formSteps  = document.querySelectorAll('.form-step');
  const nextBtns   = document.querySelectorAll(".btn-next");
  const backBtns   = document.querySelectorAll(".btn-back");
  const editBtns   = document.querySelectorAll(".edit-step");
  let   currentStep = 0;

  function updateProgress() {
    steps.forEach((s,i) => {
      s.classList.toggle("completed", i < currentStep);
      s.classList.toggle("active",    i === currentStep);
    });
    formSteps.forEach((f,i) =>
      f.classList.toggle('active', i === currentStep)
    );
    lines.forEach((l,i) =>
      l.classList.toggle("completed", i < currentStep)
    );
  }

  function updateReview() {
    const form = document.getElementById("multiStepForm");
    const addrVal     = form.querySelector('input[name="address"]').value;
    const wardVal     = form.querySelector('input[name="ward"]').value;
    const districtVal = form.querySelector('input[name="district"]').value;
    const provVal     = form.querySelector('input[name="province"]').value;
    
    const fullAddress = addrVal +", "+wardVal+", "+districtVal+", "+provVal;
    
    document.getElementById("review-name").innerText = form.fullname.value;
    document.getElementById("review-email").innerText = form.email.value;
    document.getElementById("review-phone").innerText = form.phone.value;
    document.getElementById("review-address").innerText = fullAddress;
    const pay = form.option.value;
    document.getElementById("review-payment").innerText =pay === "COD"? "Thanh toán khi nhận hàng": "Chuyển khoản ngân hàng";
  }

  function goToStep(n) {
    currentStep = n;
    updateProgress();
    if (currentStep === formSteps.length - 1) {
      updateReview();
    }
  }

  nextBtns.forEach(btn =>
    btn.addEventListener('click', () => goToStep(currentStep + 1))
  );
  backBtns.forEach(btn =>
    btn.addEventListener('click', () => goToStep(currentStep - 1))
  );

  editBtns.forEach(btn =>
    btn.addEventListener('click', e => {
      e.preventDefault();
      goToStep(parseInt(btn.dataset.step, 10));
    })
  );

  // Khởi tạo
  updateProgress();
});

  const radios = document.querySelectorAll('input[name="option"]');
  const radioBoxes = document.querySelectorAll('.custom-radio-box');

  radios.forEach(radio => {
    radio.addEventListener('change', () => {
      radioBoxes.forEach(box => box.classList.remove('active'));
      radio.closest('.custom-radio-box').classList.add('active');
    });
  });
  
  function changeActionAndSubmit(servletName) {
    	const form = document.getElementById("multiStepForm");

	    /* ----- 1. Xử lý riêng nút ÁP DỤNG MÃ GIẢM GIÁ ----- */
	    if (servletName === 'apply-code') {
	        form.action = 'apply-code';          // servlet/Controller đang validate coupon
	        form.submit();
	        return;                              // kết thúc nhánh này
	    }

	    /* ----- 2. Nhánh ĐẶT HÀNG (submit thật) ----- */
	    if (servletName === 'add-order') {
	        const payOpt = document.querySelector('input[name="option"]:checked');
	        if (!payOpt) {                       // chưa chọn COD/Online
	            alert("Vui lòng chọn phương thức thanh toán.");
	            return;
	        }

	        /* Phân luồng theo phương thức đã chọn */
	        if (payOpt.value === 'Online') {
	            form.action = 'vnpay-payment';   // servlet khởi tạo URL + redirect sang VNPay
	        } else {
	            form.action = 'add-order';       // servlet ghi đơn & để trạng thái CHƯA THANH TOÁN
	        }
	        form.submit();
	    }
	}
</script>