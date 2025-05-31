<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Người dùng</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item active">Người dùng</li>
    </ol>
  </nav>
</div><!-- End Page Title -->

<section class="section">
  <div class="container my-4">
    <div class="row" id="ownerList">
		<div class="col-lg-12">

          <div class="card">
            <div class="card-body">
			<div class="row mb-2 mt-4">
		      <div class="col-12 d-flex justify-content-end align-items-center">
		        <button id="add" class="btn btn-success add-btn ms-3" data-bs-toggle="modal" data-bs-target="#verticalycentered">
		        	<i class="bi bi-plus-circle"></i> Thêm
		        </button>
		      </div>
		    </div>  
              <!-- Table with stripped rows -->
              <table id="user" class="table datatable border rounded-4">
                <thead>
                  <tr>
                  	<th>ID</th>
                    <th>Họ tên</th>
                    <th>Giới tính</th>
                    <th>Số điên thoại</th>
                    <th>Email</th>
                    <th>Vai trò</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="user" items="${users}">
		                <tr>
		                    <td class="u-id">${user.user_id}</td>
		                    <td>${user.user_fullname}</td>
		                    <td>${user.user_gender}</td>
		                    <td>${user.user_mobilephone}</td>
		                    <td>${user.user_email}</td>
		                    <td>
			                     <c:forEach var="role" items="${roleList}">
						            <c:if test="${user.user_roles == role.role_id}">
								        <span class="badge 
								        <c:choose>
								          <c:when test="${role.role_name == 'Quản trị viên'}">bg-warning</c:when>
								          <c:when test="${role.role_name == 'Nhân viên'}">bg-primary</c:when>
								          <c:when test="${role.role_name == 'Khách hàng'}">bg-success</c:when>
								          <c:otherwise>bg-secondary</c:otherwise>
								        </c:choose>
								      ">
								        ${role.role_name}
								      </span>
								    </c:if>
						        </c:forEach>
		                    </td>
		                    <td>
		                        <button type="button" class="btn btn-warning btn-sm me-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="passIdToModal('${user.user_id}')" ><i class="bi bi-pencil-square"> Sửa</i></button>
            					<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#delModal" onclick="passIdToDelModal('${user.user_id}')" ><i class="bi bi-trash-fill"> Xóa</i></button>
		                    </td>
		                </tr>
		            </c:forEach>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>
          
        </div>
    </div>
	<!-- Modal: Thêm nhân viên -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-person-plus-fill me-2 text-primary"></i> Thêm người dùng
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addUserForm" action="api/user/add" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="username">Tên người dùng</label>
	                <input name="username" id="username" class="form-control" placeholder="Nhập tên người dùng">
	                <div class="text-danger error-username"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="password">Mật khẩu</label>
	                <input name="password" id="password" type="password" class="form-control" placeholder="Nhập mật khẩu">
	                <div class="text-danger error-password"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="fullname">Họ tên</label>
	                <input name="fullname" id="fullname" class="form-control" placeholder="Nhập họ tên">
	                <div class="text-danger error-fullname"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="birthday">Ngày sinh</label>
	                <input type="date" name="birthday" id="birthday" class="form-control" >
	                <div class="text-danger error-birthday"></div>
	              </div>
	            </div>
	
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="gender">Giới tính</label>
	                <select class="form-select" name="gender" id="gender">
	                  <option value="Nam">Nam</option>
	                  <option value="Nữ">Nữ</option>
	                  <option value="Khác">Khác</option>
	                </select>
	                <div class="text-danger error-gender"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="email">Email</label>
	                <input name="email" id="email" class="form-control" placeholder="Nhập Email">
	                <div class="text-danger error-email"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="mobilephone">Số điện thoại</label>
	                <input name="mobilephone" id="mobilephone" class="form-control" placeholder="Nhập số điện thoại">
	                <div class="text-danger error-mobilephone"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="role">Vai trò</label>
	                <select class="form-select" name="role" id="role">
	                  <c:forEach var="role" items="${roleList}">
				            <option value="${role.role_id}">${role.role_name}</option>
				        </c:forEach>
	                </select>
	                <div class="text-danger error-role"></div>
	              </div>
	            </div>
	            <div class="col-md-12">
	            	<div class="form-group mb-3">
	                <label for="address">Địa chỉ</label>
	                <input name="address" id="address" class="form-control" placeholder="Nhập địa chỉ">
	                <div class="text-danger error-address"></div>
	              </div>
	            </div>
	          </div>
	        </div>
	
	        <div class="modal-footer border-0 pt-0">
	          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
	          <button type="submit" name="log" class="btn btn-primary">Lưu</button>
	        </div>
	      </form>
	    </div>
	  </div>
	</div>
	
	<!-- Modal: Sửa nhân viên -->
	<div class="modal fade" id="editModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content p-3">
          <div class="modal-header border-0">
            <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
		          <i class="bi bi-pencil-square me-2 text-warning"></i> Sửa thông tin người dùng
		        </h5>
		        <span class="text-muted text-end">ID: <span class="u-id" id="u_id"></span></span>
			</div>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
          </div>
          <!-- Thông tin ngày tạo và ngày sửa -->
          <div class="px-3 text-muted small">
            Ngày tạo: <span id="createdDate"></span> | 
            Ngày sửa gần nhất: <span id="updatedDate"></span>
          </div>
          <form action="api/user/edit" id="editUserForm" method="post">
            <div class="modal-body">
              <input type="hidden" name="user_id" id="editUserId">
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group mb-3">
                    <label for="editUsername">Tên người dùng</label>
                    <input name="editUsername" id="editUsername" class="form-control" disabled>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editPassword">Mật khẩu</label>
                    <input type="password" name="editPassword" id="editPassword" class="form-control">
                    <div class="text-danger error-editPassword"></div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editFullname">Họ tên</label>
                    <input name="editFullname" id="editFullname" class="form-control">
                    <div class="text-danger error-editFullname"></div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editBirthday">Ngày sinh</label>
                    <input type="date" name="editBirthday" id="editBirthday" class="form-control" >
                    <div class="text-danger error-editBirthday"></div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group mb-3">
                    <label for="editGender">Giới tính</label>
                    <select name="editGender" id="editGender" class="form-select">
                      <option value="Nam">Nam</option>
                      <option value="Nữ">Nữ</option>
                      <option value="Khác">Khác</option>
                    </select>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editEmail">Email</label>
                    <input name="editEmail" id="editEmail" class="form-control">
                    <div class="text-danger error-editEmail"></div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editPhone">Số điện thoại</label>
                    <input name="editPhone" id="editPhone" class="form-control">
                    <div class="text-danger error-editPhone"></div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="editRole">Vai trò</label>
                    <select class="form-select" name="editRole" id="editRole">
                      
                    </select>
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group mb-3">
                    <label for="editAddress">Địa chỉ</label>
                    <input name="editAddress" id="editAddress" class="form-control">
                    <div class="text-danger error-editAddress"></div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer border-0 pt-0">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
              <button type="submit" name="updateUser" class="btn btn-warning">Cập nhật</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    
	<div class="modal fade" id="delModal" tabindex="-1">
       <div class="modal-dialog modal-dialog-centered">
         <div class="modal-content">
           <div class="modal-header">
             <h5 class="modal-title"><i class="bi bi-question-circle text-info"></i> Thông báo xác nhận </h5>
             <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
           </div>
           <div class="modal-body">
             Bạn có chắc muốn xóa người dùng này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/user/del" method="post">
             	<input type="hidden" name="user_id_del" id="delId">
             	<button type="submit" class="btn btn-info">OK</button>
             </form>
           </div>
         </div>
       </div>
     </div>
  </div>
</section>

<script src="assets/js/form-validator.js"></script>
<script src="assets/js/functions.js"></script>
<script>
document.querySelectorAll(".u-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"ND-");
});
  document.getElementById("addUserForm").addEventListener("submit", function (e) {
    const rules = {
		username: {
		      required: true,
		      requiredMessage: "Tên người dùng không được để trống",
		      minLength: 4,
		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
		    },
	    password: {
	        required: true,
	        requiredMessage: "Mật khẩu không được để trống",
	        minLength: 6,
	        minLengthMessage: "Mật khẩu phải có ít nhất 6 ký tự",
	        pattern: /^(?=.*[A-Z])(?=.*\d).+$/,
	        patternMessage: "Phải có ít nhất 1 chữ hoa và 1 số"
	      },
      	fullname: { 
    	  	required: true, 
    	  	requiredMessage: "Họ tên không được để trống",
    	  	minLength: 6,
	        minLengthMessage: "Họ tên phải có ít nhất 6 ký tự",
    	  },
		birthday: {
			required: true, 
    	  	requiredMessage: "Ngày sinh không được để trống",
		    custom: (value) => {
		      const today = new Date();
		      const birth = new Date(value);
		      return birth <= today;
		    },
		    customMessage: "Ngày sinh không được ở tương lai"
		  },
		email: {
		    required: true,
		    requiredMessage: "Email không được để trống",
		    pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
		    patternMessage: "Email không hợp lệ"
		  },
		mobilephone: {
		    required: true,
		    requiredMessage: "Số điện thoại là bắt buộc",
		    pattern: /^[0-9]{10,11}$/,
		    patternMessage: "Số điện thoại phải có 10-11 chữ số"
		  },
		address: { required: true, requiredMessage: "Địa chỉ không được để trống" }
    };

    if (!validateForm("addUserForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editUserForm").addEventListener("submit", function (e) {
	    const rules = {
			editPassword: {
		        required: true,
		        requiredMessage: "Mật khẩu không được để trống",
		        minLength: 6,
		        minLengthMessage: "Mật khẩu phải có ít nhất 6 ký tự",
		        pattern: /^(?=.*[A-Z])(?=.*\d).+$/,
		        patternMessage: "Phải có ít nhất 1 chữ hoa và 1 số"
		      },
		   editFullname: { 
	    	  	required: true, 
	    	  	requiredMessage: "Họ tên không được để trống",
	    	  	minLength: 6,
		        minLengthMessage: "Họ tên phải có ít nhất 6 ký tự",
	    	  },
	    	editBirthday: {
				required: true, 
	    	  	requiredMessage: "Ngày sinh không được để trống",
			    custom: (value) => {
			      const today = new Date();
			      const birth = new Date(value);
			      return birth <= today;
			    },
			    customMessage: "Ngày sinh không được ở tương lai"
			  },
			editEmail: {
			    required: true,
			    requiredMessage: "Email không được để trống",
			    pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
			    patternMessage: "Email không hợp lệ"
			  },
			editPhone: {
			    required: true,
			    requiredMessage: "Số điện thoại là bắt buộc",
			    pattern: /^[0-9]{10,11}$/,
			    patternMessage: "Số điện thoại phải có 10-11 chữ số"
			  },
			editAddress: { required: true, requiredMessage: "Địa chỉ không được để trống" }
	    };

	    if (!validateForm("editUserForm", rules)) {
	      e.preventDefault();
	    }
	  });
  function formatDate(isoString) {
	    const date = new Date(isoString);
	    return date.toLocaleString("vi-VN"); // "15/04/2025, 08:45:00"
	}
  function passIdToModal(id) { 
      document.getElementById("editUserId").value = id;
      $.ajax({
          url: "<%= request.getContextPath() %>/api/getUser",
          method: "GET",
          data: { user_id: id },
          success: function(data) {
              $('#editUsername').val(data.username);
              $('#editPassword').val(data.password);
              $('#editFullname').val(data.fullname);
              $('#editEmail').val(data.email);
              $('#editGender').val(data.gender);
              $('#createdDate').text(formatDate(data.create_date));
              $('#updatedDate').text(formatDate(data.last_modified));
              $('#editBirthday').val(data.birthday);
              $('#editPhone').val(data.phone);
              $('#editAddress').val(data.address);
              $('#u_id').text(formatId(data.userId,"ND-"));
              $.ajax({
                  url: "<%= request.getContextPath() %>/api/getRole",
                  method: "GET",
                  success: function(roleData) {
                      let roleDropdown = $('#editRole');
                      roleDropdown.empty(); 
                      roleData.forEach(function(role) {
                          let selected = role.id === data.role ? 'selected' : '';
                          roleDropdown.append('<option value="' + role.id + '" ' + selected + '>' + role.name + '</option>');
                      });
                  }
              });
          }
      });
  }
  function passIdToDelModal(id) { 
      document.getElementById("delId").value = id;
  }
</script>

