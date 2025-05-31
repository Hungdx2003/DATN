<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Đơn hàng</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item active">Đơn hàng</li>
    </ol>
  </nav>
</div><!-- End Page Title -->

<section class="section">
  <div class="container my-4">
    <div class="row" id="ownerList">
		<div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <table class="table datatable border rounded-4">
                <thead>
                  <tr>
                  	<th>ID</th>
                    <th>Người nhận</th>
                    <th>Số điện thoại</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="o" items="${order}">
		                <tr>
		                    <td  class="od-id">${o.order_id}</td>
		                    <td>${o.receiver_name}</td>
		                    <td >${o.receiver_mobilephone}</td>
		                    <td class="formatted-date">${o.order_date}</td>
		                    <td class="formatted-value">${o.total_amount}</td>
		                    <td>
		                    	<c:set var="status" value="danger" />
								<c:choose>
								    <c:when test="${o.order_status == 'Hoàn thành'}">
								        <c:set var="status" value="success" />
								    </c:when>
							     	<c:when test="${o.order_status == 'Đang xử lý'}">
								        <c:set var="status" value="primary" />
								    </c:when>
								    <c:when test="${o.order_status == 'Đang giao'}">
								        <c:set var="status" value="warning" />
								    </c:when>
								</c:choose>
		                    	<span class="badge bg-${status}">${o.order_status}</span>
							</td>
		                    <td>
		                        <button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal" data-bs-target="#detailModal" onclick="passIdToModal('${o.order_id}')" ><i class="bi bi-eye"></i> Xem</button>
		                    </td>
		                </tr>
		            </c:forEach>
                </tbody>
              </table>

            </div>
          </div>
          
        </div>
    </div>
	
	<!-- Modal: Xem chi tiết đơn hàng -->
	<div class="modal fade" id="detailModal" tabindex="-1"> 
	  <div class="modal-dialog modal-dialog-centered modal-xl">
	    <div class="modal-content">
	      <div class="modal-header border-bottom">
	        <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
		           <i class="bi bi-receipt-cutoff me-2 text-primary"></i> Chi tiết đơn hàng
		        </h5>
		        <span class="text-muted text-end">ID: <span class="od-id" id="od_id"></span></span>
			</div> 
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
			<form action="api/order/edit" method="post">
		      <div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
		        <div class="row g-4">
		        <input type="hidden" name="order_id" id="order_id">
		          <div class="col-lg-4">
		            <div class="card shadow-sm fixed-card">
		              <div class="card-header bg-light fw-bold ">Thông tin đơn hàng</div>
		              <div class="card-body">
		                <div class="mb-2" id="user_id"><strong>Người đặt:</strong> </div>
		                <div class="mb-2" id="receiver_name"><strong>Người nhận:</strong></div>
		                <div class="mb-2" id="receiver_mobilephone"><strong>Điện thoại:</strong></div>
		                <div class="mb-2" id="delivery_address"><strong>Địa chỉ:</strong></div>
		                <div class="mb-2" id="email"><strong>Email:</strong></div>
		                <div class="mb-2" id="order_date"><strong>Thời gian:</strong></div>
		                <div class="mb-2">
						  <strong>Tình trạng đơn hàng:</strong>
						  <span id="status-badge" class="badge rounded-pill bg-primary" style="cursor: pointer;">
						    Đang xử lý
						  </span>
						  <select id="status-select" class="form-select d-none" name="status">
							  <option value="Đang xử lý">Đang xử lý</option>
							  <option value="Đang giao">Đang giao</option>
							  <option value="Hoàn thành">Hoàn thành</option>
							  <option value="Đã huỷ">Đã huỷ</option>
							</select>
						</div>
		                <hr>
		                <div class="row p-1">
			                <strong class="col">Phương thức thanh toán: </strong>
			                <span class="col" id="pay_method"></span>
			            </div>
			            <div class="row p-1">
			                <strong class="col">Trạng thái: </strong>
			                <span class="col" id="pay_status" style="font-weight: 600;"></span>
			            </div>
		              </div>
		            </div>
		          </div>
		
		          <div class="col-lg-8">
					  <div class="card shadow-sm">
					    <div style="overflow-x: auto;">
					      <!-- Gộp cả header và nội dung vào đây -->
					      <div style="min-width: 600px;">
					        <!-- Header các cột -->
					        <div class="card-header bg-light fw-bold fixed-header">
					          <div class="row fw-bold text-center align-items-center">
					            <div class="col-5">Sản phẩm</div>
					            <div class="col-2">Đơn giá</div>
					            <div class="col-2">Số lượng</div>
					            <div class="col-3">Thành tiền</div>
					          </div>
					        </div>
					
					        <!-- Danh sách sản phẩm -->
					        <div id="product_details_container">
					          <!-- JS sẽ tạo các sản phẩm tại đây -->
					        </div>
					      </div>
					    </div>
					
					    <!-- Tổng tiền -->
					    <div class="card-body pt-3 border-top">
					      <div class="d-flex justify-content-between mb-2">
					        <span>Tạm tính:</span>
					        <span id="sub_total">0 đ</span>
					      </div>
					      <div class="d-flex justify-content-between mb-2">
					        <span>Giảm giá:</span>
					        <span id="discount">0 đ</span>
					      </div>
					      <div class="d-flex justify-content-between fw-bold fs-5 border border-secondary p-3 rounded-2">
					        <span>Tổng tiền:</span>
					        <span class="total-value" id="total_amount">0 đ</span>
					      </div>
					    </div>
					  </div>
					</div>

		        </div>
		      </div>
		
		      <div class="modal-footer border-top">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
		        <button type="submit" name="log" class="btn btn-primary">Cập nhật</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>

<!-- Mẫu Sản phẩm ẩn để sử dụng lại -->
<div class="product-template d-none">
  <div class="card mb-2 border-1 shadow-sm">
    <div class="card-body py-2">
      <div class="row align-items-center">
        <div class="col-5 d-flex align-items-center gap-2">
          <div class="detail-image">
            <img src="#" class="image" alt="Xem trước ảnh" style="cursor: zoom-in;max-width: 100%;">
          </div>
          <div>
            <div class="fw-semibold product-name"></div>
          </div>
        </div>
        <div class="col-2 product-price text-center"></div>
        <div class="col-2 text-center product-quantity"></div>
        <div class="col-3 text-end fw-bold underline-price product-subtotal"></div>
      </div>
    </div>
  </div>
</div>

	
  </div>
</section>

<script src="assets/js/form-validator.js"></script>
<script src="assets/js/functions.js"></script>
<script>
document.querySelectorAll(".od-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"DH-");
});
document.querySelectorAll('.formatted-value').forEach(function(td) {
  let value = td.textContent.trim();
  td.textContent = formatTd(value);
});

document.querySelectorAll(".formatted-date").forEach(function (cell) {
  cell.textContent = formatDateOnly(cell.textContent.trim());
});

document.addEventListener("DOMContentLoaded", function () {
  const badge = document.getElementById("status-badge");
  const select = document.getElementById("status-select");

  badge.addEventListener("click", function () {
    badge.classList.add("d-none");
    select.classList.remove("d-none");
    // Gán giá trị hiện tại cho select
    const currentStatus = badge.textContent.trim();
    for (const option of select.options) {
      if (option.textContent.trim() === currentStatus) {
        select.value = option.value;
        break;
      }
    }
  });

  select.addEventListener("change", function () {
    // Cập nhật badge
    const selectedText = select.options[select.selectedIndex].text;
    badge.textContent = selectedText;

    // Cập nhật màu (tuỳ theo trạng thái)
    badge.className = "badge rounded-pill";
    switch (select.value) {
      case "Đang xử lý":
        badge.classList.add("bg-primary");
        break;
      case "Đang giao":
        badge.classList.add("bg-warning");
        break;
      case "Hoàn thành":
        badge.classList.add("bg-success");
        break;
      case "Đã hủy":
        badge.classList.add("bg-danger");
        break;
    }

    // Ẩn select, hiện lại badge
    select.classList.add("d-none");
    badge.classList.remove("d-none");

  });
});
</script>
<script>
function passIdToModal(id) {  
	document.getElementById("order_id").value = id;
	
	$.ajax({
        url: "<%= request.getContextPath() %>/getPayment",
        method: "GET",
        data: { order_id: id },
        success: function(pay) {
          $('#pay_method').text(pay.payMethod);
          $('#pay_status').text(pay.payStatus);
        }
      });
	
  $.ajax({
    url: "<%= request.getContextPath() %>/api/getOrder",
    method: "GET",
    data: { order_id: id },
    success: function(data) {
      $('#receiver_name').html('<strong>Người nhận:</strong> ' + data.receiverName);
      $('#receiver_mobilephone').html('<strong>Điện thoại:</strong> ' + data.receiverPhone);
      $('#delivery_address').html('<strong>Địa chỉ:</strong> ' + data.deliveryAddress);
      $('#email').html('<strong>Email:</strong> ' + data.email);
      $('#total_amount').text(formatTd(data.totalAmount));
      $('#od_id').text(formatId(data.orderId,"DH-"));
      $('#sub_total').text(formatTd(data.totalOrderValue));
      $('#discount').text(formatTd(data.discountMoney));
      $('#order_date').html('<strong>Thời gian:</strong> ' + formatDate(data.orderDate));
		
      const badge = $('#status-badge');
      const select = $('#status-select');
      
      badge.text(data.status);
      badge.removeClass('bg-primary bg-warning bg-success bg-danger bg-secondary').addClass(getStatusBadgeClass(data.status));
      select.val(data.status);

      $.ajax({
        url: "<%= request.getContextPath() %>/api/getUser",
        method: "GET",
        data: { user_id: data.userId },
        success: function(user) {
          $('#user_id').html('<strong>Người đặt:</strong> ' + user.fullname);
        }
      });
    }
  });
  
  $.ajax({
      url: "<%= request.getContextPath() %>/api/getOrderDetails",
      method: "GET",
      data: { order_id: id },
      success: function(orderDetails) {
        const container = $('#product_details_container');
        const template = $('.product-template').html();
        container.empty();

        orderDetails.forEach(orderDetail => {
          $.ajax({
            url: "<%= request.getContextPath() %>/api/getProduct", 
            method: "GET",
            data: { product_id: orderDetail.productId },
            success: function(product) {
              let item = $(template);

              item.find('.image').attr('src', '/product_images/' +product.image_url);
              item.find('.product-name').text(product.name);
              item.find('.product-price').text(formatTd(orderDetail.productPrice));
              item.find('.product-quantity').text(orderDetail.quantity);
              item.find('.product-subtotal').text(formatTd(orderDetail.subtotal));

              container.append(item);
            }
          });
        });
      }
    });
}
	
function getStatusBadgeClass(status) {
  switch (status) {
    case 'Đang xử lý': return 'bg-primary';
    case 'Đang giao': return 'bg-warning';
    case 'Hoàn thành': return 'bg-success';
    case 'Đã hủy': return 'bg-danger';
    default: return 'bg-secondary';
  }
}
</script>
