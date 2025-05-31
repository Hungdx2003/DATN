<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <span>Tài khoản</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container py-5">
    <div class="row">
      <!-- Sidebar -->
      <div class="col-md-3 sidebar">
        <div class="mb-4 text-center">
          <div class="avatar">${FisrtCharName}</div>
          <div>${user.user_fullname}</div>
        </div>
        <a href="#" class="menu-item" data-target="info">Thông tin tài khoản</a>
        <a href="#" class="menu-item" data-target="orders">Danh sách đơn hàng</a>
        <a href="#" class="menu-item" data-target="password">Đổi mật khẩu</a>
      </div>

      <!-- Content Area -->
      <div class="col-md-9">
        <div class="card user-info" id="info">
          <div class="section-title">
	         <h4>Thông tin tài khoản</h4>
	     </div>
	     	<form action="modify-user" method="post">
	          <div class="row g-3 mt-3">
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Họ tên</label>
	              <input class="form-control" name="fullname" placeholder="Nhập họ tên" value="${user.user_fullname}" required>
	            </div>
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Email</label>
	              <input class="form-control" placeholder="Nhập email" name="email" value="${user.user_email}" required>
	            </div>
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Số Điện thoại</label>
	              <input class="form-control" placeholder="Nhập số điện thoại" name="phone" value="${user.user_mobilephone}" required>
	            </div>
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Ngày sinh</label>
	              <input type="date" class="form-control" name="birthday" value="${user.user_birthday}" required>
	            </div>
	            <div class="col-md-12">
		          <label class="form-label" style="font-weight:bold;">Địa chỉ</label>
		          <input type="text" class="form-control" placeholder="Địa chỉ" name="address" value="${address}" required>
		        </div>
		          <div class="col-md-4">
		            <label class="form-label" style="font-weight:bold;">Tỉnh/Thành phố</label>
		            <input type="text" class="form-control" placeholder="Tỉnh/Thành phố" name="province" value="${ward}" required>
		          </div>
		          <div class="col-md-4">
		            <label class="form-label" style="font-weight:bold;">Quận/Huyện</label>
		            <input type="text" class="form-control" placeholder="Quận/Huyện" name="district" value="${district}" required>
		          </div>
		          <div class="col-md-4">
		            <label class="form-label" style="font-weight:bold;">Phường/Xã</label>
		            <input type="text" class="form-control" placeholder="Phường/Xã" name="ward" value="${city}" required>
		          </div>
	            <div class="col-md-12">
	              <label class="form-label" style="font-weight:bold;">Giới tính</label><br>
	              <div class="d-flex gap-4">
					  <div class="form-check">
					    <input class="form-check-input" value="Nam" id="nam" type="radio" name="gender" 
					           <c:if test="${user.user_gender == 'Nam'}">checked</c:if>> 
					    <label for="nam" class="form-check-label">Nam</label>
					  </div> 
					  <div class="form-check">
					    <input class="form-check-input" value="Nữ" id="nu" type="radio" name="gender" 
					           <c:if test="${user.user_gender == 'Nữ'}">checked</c:if>> 
					    <label for="nu" class="form-check-label">Nữ</label>
					  </div>
					  <div class="form-check">
					    <input class="form-check-input" value="Khác" id="khac" type="radio" name="gender" 
					           <c:if test="${user.user_gender == 'Khác'}">checked</c:if>>
					    <label for="khac" class="form-check-label">Khác</label>
					  </div>
					</div>
	            </div>
	            <div class="col-md-12 d-flex justify-content-end">
	              <button type="submit" class="btn btn-danger mt-3">Lưu thay đổi</button>
	            </div>
	          </div>
          	</form>
        </div>

        <!-- Other Sections (Ẩn ban đầu) -->
        <div class="order-box d-none" id="orders">
        	<div class="section-title">
		         <h4 style="color: #2c3e50;">Hóa đơn</h4>
		     </div>
	    <div class="table-responsive">
		    <table class="table align-middle">
		      <thead class="text-muted">
		        <tr>
		          <th>Order #</th>
		          <th>Ngày đặt</th>
		          <th>Trạng thái</th>
		          <th>Tổng tiền</th>
		          <th class="text-center">Chi tiết</th>
		        </tr>
		      </thead>
			      <tbody>
			      	<c:forEach var="o" items="${orders}">
				        <tr class="order-row">
				          <td class="order-id">${o.order_id}</td>
				          <td class="order-date">${o.order_date}</td>
				          <c:set var="statusClass" value="canceled" />
							<c:choose>
							    <c:when test="${o.order_status == 'Đang xử lý'}">
							        <c:set var="statusClass" value="in-progress" />
							    </c:when>
							    <c:when test="${o.order_status == 'Đang giao'}">
							        <c:set var="statusClass" value="delivered" />
							    </c:when>
							    <c:when test="${o.order_status == 'Hoàn thành'}">
							        <c:set var="statusClass" value="completed" />
							    </c:when>
							</c:choose>
							<td class="order-status ${statusClass}">● ${o.order_status}</td>
				          <td class="formatted-value" style="font-weight: 700;">${o.total_amount}</td>
				          <td class="text-center">
				            <a href="page?view=order&order_id=${o.order_id}" class="order-btn">Xem chi tiết</a>
				          </td>
				        </tr>
			        </c:forEach>
			      </tbody>
		    </table>
		  </div>
        </div>
        
        <div class="card user-info d-none" id="password">
          <div class="section-title">
	         <h4>Đổi mật khẩu</h4>
	     </div>
	     	<form action="change-password" method="post" onsubmit="return validatePasswords()">
	          <div class="row g-3 mt-3">
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Mật khẩu cũ</label>
	              <input type="password" class="form-control" name="old_pass" id="old_pass" placeholder="Nhập mật khẩu cũ" required>
	            </div>
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Mật khẩu mới</label>
	              <input type="password" class="form-control" placeholder="Nhập mật khẩu mới" name="new_pass" id="new_pass" required>
	            </div>
	            <div class="col-md-6">
	              <label class="form-label" style="font-weight:bold;">Xác nhận mật khẩu mới</label>
	              <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
	              <small id="passwordError" class="text-danger d-block" style="font-size: 0.875rem;"></small>
	            </div>
	            <div class="col-md-12 d-flex justify-content-end">
	              <button type="submit" class="btn btn-danger mt-3">Lưu thay đổi</button>
	            </div>
	          </div>
          	</form>
        </div>
      </div>
    </div>
  </div>
  <script src="js/functions.js"></script>
  <script>
  document.addEventListener("DOMContentLoaded", function () {
    // Format order ID
    document.querySelectorAll(".order-id").forEach(function (cell) {
      cell.textContent = formatOrderId(cell.textContent.trim());
    });

    // Format order date
    document.querySelectorAll(".order-date").forEach(function (cell) {
      cell.textContent = formatDateOnly(cell.textContent.trim());
    });

    function formatOrderId(id) {
      id = parseInt(id);
      if (isNaN(id)) return "";
      let idStr = id.toString();
      if (idStr.length <= 5) {
        idStr = idStr.padStart(5, "0");
      }
      return "DH-" + idStr;
    }

    function formatDateOnly(isoString) {
      if (!isoString || isoString.trim() === "") return "";
      const date = new Date(isoString);
      if (isNaN(date.getTime())) return "";
      const day = String(date.getDate()).padStart(2, "0");
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const year = date.getFullYear();
      return day+'/'+month+'/'+year;
    }
  });
</script>
<script>
  document.querySelectorAll('.formatted-value').forEach(function(td) {
	    let value = td.textContent.trim();
	    td.textContent = formatTd(value);
	});
  
    const menuItems = document.querySelectorAll('.menu-item');
    const allSections = ['info', 'orders', 'password'];

    function getUrlParam(key) {
      const params = new URLSearchParams(window.location.search);
      return params.get(key);
    }

    function activateSection(sectionId) {
      allSections.forEach(id => {
        const sec = document.getElementById(id);
        if (sec) sec.classList.add('d-none');
      });

      const target = document.getElementById(sectionId);
      if (target) target.classList.remove('d-none');

      menuItems.forEach(item => {
        item.classList.remove('active');
        if (item.dataset.target === sectionId) {
          item.classList.add('active');
        }
      });
    }

    menuItems.forEach(item => {
      item.addEventListener('click', function (e) {
        e.preventDefault();
        const sectionId = this.dataset.target;
        history.replaceState(null, "", `?view=profile&side=${sectionId}`);
        activateSection(sectionId);
      });
    });

    window.addEventListener('DOMContentLoaded', () => {
      const section = getUrlParam('side') || 'info';
      if (allSections.includes(section)) {
        activateSection(section);
      } else {
        activateSection('info');
      }
    });
    
    function validatePasswords() {
      var pass = document.getElementById("new_pass").value;
      var confirm = document.getElementById("confirmPassword").value;
      var error = document.getElementById("passwordError");

      if (pass !== confirm) {
     	  error.innerHTML = '<i class="bi bi-shield-exclamation"></i> <span>Mật khẩu xác nhận không khớp!</span>';
     	  return false;
     	}

      error.textContent = "";
      return true;
    }
  </script>