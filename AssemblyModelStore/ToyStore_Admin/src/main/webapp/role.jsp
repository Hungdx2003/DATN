<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pagetitle">
  <h1>Phân quyền</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item">Người dùng</li>
      <li class="breadcrumb-item active">Phân quyền</li>
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
              <table class="table datatable border rounded-4">
                <thead>
                  <tr>
                  	<th>ID</th>
                    <th>Tên quyền</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="r" items="${roleList}">
		                <tr>
		                    <td class="role-id">${r.role_id}</td>
		                    <td>${r.role_name}</td>
		                    <td>
		                        <button type="button" class="btn btn-warning btn-sm me-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="passIdToModal('${r.role_id}')" ><i class="bi bi-pencil-square"> Sửa</i></button>
            					<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#delModal" onclick="passIdToDelModal('${r.role_id}')" ><i class="bi bi-trash-fill"> Xóa</i></button>
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
	<!-- Modal: Thêm quyền -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-bookmark-plus-fill me-2 text-primary"></i> Thêm quyền
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addRoleForm" action="api/role/add" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-12">
	              <div class="form-group mb-3">
	                <label for="role_name">Tên vai trò</label>
	                <input name="role_name" id="role_name" class="form-control" placeholder="Nhập tên quyền">
	                <div class="text-danger error-role_name"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="role_name">Quyền hạn</label>
	                <table id="permissionTable" class="table border text-center rounded-4">
	                  <thead>
	                    <tr>
	                      <th>#</th>
	                      <th>Đối tượng</th>
	                      <th>Xem</th>
	                      <th>Thêm</th>
	                      <th>Sửa</th>
	                      <th>Xóa</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach var="per" items="${objectNames}" varStatus="status">
	                      <tr>
	                        <td>
	                          <input type="hidden" name="object_name_${status.index}" value="${per.object_name}">
	                          <input type="hidden" name="object_display_name_${status.index}" value="${per.object_display_name}">
	                          <input type="checkbox" onclick="toggleRow(this)" class="form-check-input">
	                        </td>
	                        <td class="object-name">${per.object_display_name}</td>
	                        <td><input type="checkbox" name="view_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="add_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="edit_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="del_${status.index}" class="form-check-input"></td>
	                      </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>
	                <input type="hidden" name="total_permissions" value="${fn:length(objectNames)}">
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
	
	<!-- Modal: Sửa quyền -->
	<div class="modal fade" id="editModal" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered" style="max-width: 600px;">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
			    <i class="bi bi-bookmark-check-fill me-2 text-warning"></i> Sửa thông tin quyền
			  </h5>
			  <span class="text-muted text-end">ID: <span class="role-id" id="edit_role_id"></span></span>
			</div>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form id="editRoleForm" action="api/role/edit" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-12">
            		<input type="hidden" name="role_id" id="role_id">
	              <div class="form-group mb-3">
	                <label for="edit_role_name">Tên vai trò</label>
	                <input name="edit_role_name" id="edit_role_name" class="form-control" placeholder="Nhập tên quyền">
	                <div class="text-danger error-edit_role_name"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="role_name">Quyền hạn</label>
	                <table id="permissionTable" class="table border text-center rounded-4">
	                  <thead>
	                    <tr>
	                      <th>#</th>
	                      <th>Đối tượng</th>
	                      <th>Xem</th>
	                      <th>Thêm</th>
	                      <th>Sửa</th>
	                      <th>Xóa</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach var="per" items="${objectNames}" varStatus="status">
	                      <tr>
	                        <td>
	                          <input type="hidden" name="edit_object_name_${status.index}" value="${per.object_name}">
	                          <input type="hidden" name="edit_permission_id_${status.index}" value="${per.permission_id}">
	                          <input type="hidden" name="edit_object_display_name_${status.index}" value="${per.object_display_name}">
	                          <input type="checkbox" onclick="toggleRow(this)" class="form-check-input">
	                        </td>
	                        <td class="object-name">${per.object_display_name}</td>
	                        <td><input type="checkbox" name="edit_view_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="edit_add_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="edit_edit_${status.index}" class="form-check-input"></td>
	                        <td><input type="checkbox" name="edit_del_${status.index}" class="form-check-input"></td>
	                      </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>
	                <input type="hidden" name="edit_total_permissions" value="${fn:length(objectNames)}">
	              </div>
              	</div>
	          </div>
	        </div>
	
	        <div class="modal-footer border-0 pt-0">
	          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
	          <button type="submit" name="log" class="btn btn-warning">Lưu</button>
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
             Bạn có chắc muốn xóa vai trò này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/role/del" method="post">
             	<input type="hidden" name="role_id_del" id="delId">
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
  document.getElementById("addRoleForm").addEventListener("submit", function (e) {
    const rules = {
   		role_name: {
		      required: true,
		      requiredMessage: "Tên quyền không được để trống",
		      minLength: 4,
		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
		    }
    };

    if (!validateForm("addRoleForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editRoleForm").addEventListener("submit", function (e) {
	    const rules = {
    		edit_role_name: {
  		      required: true,
  		      requiredMessage: "Tên quyền không được để trống",
  		      minLength: 4,
  		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
  		    }
	    };

	    if (!validateForm("editRoleForm", rules)) {
	      e.preventDefault();
	    }
	  });
  function formatDate(isoString) {
	    const date = new Date(isoString);
	    return date.toLocaleString("vi-VN");
	}
  document.querySelectorAll(".role-id").forEach(function (cell) {
      cell.textContent = formatId(cell.textContent.trim(),"Q-");
    });
  function passIdToModal(id) { 
      document.getElementById("role_id").value = id;
      $.ajax({
   	    url: 'api/getRoleDetail',
   	    method: 'GET',
   	    data: { role_id: id },
   	    dataType: 'json',
		 success: function(response) {
		 	$('#edit_role_id').text(formatId(response.id,"Q-"));
		    $('#edit_role_name').val(response.role_name);
		
		    // Reset tất cả các checkbox và hidden input
		    $('#permissionTable tbody tr').each(function () {
		        const row = $(this);
		        row.find('input[type="checkbox"]').prop('checked', false);
		        row.find('input[type="hidden"][name^="edit_permission_id_"]').val('');
		
		        const displayName = row.find('.object-name').text().trim();
		
		        // Tìm perm tương ứng theo display_name
		        const perm = response.permissions.find(p => p.display_name === displayName);
		        if (!perm) return; // Không tìm thấy thì bỏ qua dòng này
		
		        // Tìm index của dòng hiện tại
		        const rowIndex = row.index();
		
		        // Gán dữ liệu nếu tìm thấy
		        row.find('input[name="edit_permission_id_' + rowIndex + '"]').val(perm.permission_id);
		        if (perm.view) row.find('input[name="edit_view_' + rowIndex + '"]').prop('checked', true);
		        if (perm.add) row.find('input[name="edit_add_' + rowIndex + '"]').prop('checked', true);
		        if (perm.edit) row.find('input[name="edit_edit_' + rowIndex + '"]').prop('checked', true);
		        if (perm.del) row.find('input[name="edit_del_' + rowIndex + '"]').prop('checked', true);
		    });
		},
   	    error: function(xhr, status, error) {
   	        alert('Không thể tải dữ liệu vai trò. Vui lòng thử lại.');
   	        console.error('Lỗi AJAX:', error);
   	    }
   	});
  }
  
  function passIdToDelModal(id) { 
      document.getElementById("delId").value = id;
  }
  
  function toggleRow(masterCheckbox) {
     const row = masterCheckbox.closest("tr");
     const checkboxes = row.querySelectorAll('td input[type="checkbox"]');
     checkboxes.forEach(cb => cb.checked = masterCheckbox.checked);
   }
</script>

