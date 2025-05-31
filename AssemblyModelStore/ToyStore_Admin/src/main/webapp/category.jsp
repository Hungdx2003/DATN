<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Danh mục sản phẩm</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item">Sản phẩm</li>
      <li class="breadcrumb-item active">Danh mục</li>
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
                    <th>Tên danh mục</th>
                    <th>Thuộc</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="pc" items="${category}">
		                <tr>
		                    <td class="cat-id">${pc.category_id}</td>
		                    <td>${pc.category_name}</td>
		                    <td>
		                        <c:forEach var="c" items="${category}">
		                        	<c:if test="${pc.parent_category_id ==c.category_id}">
			                            ${c.category_name}
			                        </c:if>
		                        </c:forEach>
		                    </td>
		                    <td>
		                        <button type="button" class="btn btn-warning btn-sm me-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="passIdToModal('${pc.category_id}')" ><i class="bi bi-pencil-square"> Sửa</i></button>
            					<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#delModal" onclick="passIdToDelModal('${pc.category_id}')" ><i class="bi bi-trash-fill"> Xóa</i></button>
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
	<!-- Modal: Thêm danh mục -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-bookmark-plus-fill me-2 text-primary"></i> Thêm danh mục
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addCategoryForm" action="api/category/add" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-12">
	              <div class="form-group mb-3">
	                <label for="category_name">Tên danh mục</label>
	                <input name="category_name" id="category_name" class="form-control" placeholder="Nhập tên danh mục">
	                <div class="text-danger error-category_name"></div>
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
	
	<!-- Modal: Sửa danh mục -->
	<div class="modal fade" id="editModal" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered" style="max-width: 600px;">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
			    <i class="bi bi-bookmark-check-fill me-2 text-warning"></i> Sửa thông tin danh mục
			  </h5>
			  <span class="text-muted text-end">ID: <span class="cat-id" id="edit_cat_id"></span></span>
			</div>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
			<div class="px-3 text-muted small">
			  Ngày tạo: <span id="createdDate"></span> | 
			  Người tạo: <span id="createdBy"></span>
			</div>
			<div class="px-3 text-muted small">
			  Ngày sửa gần nhất: <span id="updatedDate"></span> | 
			  Người sửa: <span id="updatedBy"></span>
			</div>
	      <form id="editCategoryForm" action="api/category/edit" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-12">
	            	<input type="hidden" name="category_id" id="category_id">
	              <div class="form-group mb-3">
	                <label for="category_name">Tên danh mục</label>
	                <input name="edit_category_name" id="edit_category_name" class="form-control" placeholder="Nhập tên danh mục">
	                <div class="text-danger error-category_name"></div>
	              </div>
	              <div class="form-group mb-3">
					  <label for="edit_category_parent">Thuộc</label>
					  <select name="edit_category_parent" id="edit_category_parent" class="form-select">
						  <option>-- Chọn danh mục --</option>
						
						  <!-- Cấp 1: Danh mục cha -->
						  <c:forEach var="level1" items="${category}">
						    <c:if test="${level1.parent_category_id == null || level1.parent_category_id == 0}">
						      <option value="${level1.category_id}" class="text-primary level-1">${level1.category_name}</option>
						
						      <!-- Cấp 2: Danh mục con -->
						      <c:forEach var="level2" items="${category}">
						        <c:if test="${level2.parent_category_id == level1.category_id}">
						          <option value="${level2.category_id}" class="text-dark level-2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${level2.category_name}</option>
						
						          <!-- Cấp 3: Danh mục con của cấp 2 -->
						          <c:forEach var="level3" items="${category}">
						            <c:if test="${level3.parent_category_id == level2.category_id}">
						              <option class="text-secondary level-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${level3.category_name}</option>
						            </c:if>
						          </c:forEach>
						        </c:if>
						      </c:forEach>
						    </c:if>
						  </c:forEach>
						</select>
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
             Bạn có chắc muốn xóa danh mục này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/category/del" method="post">
             	<input type="hidden" name="category_id_del" id="delId">
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
  document.getElementById("addCategoryForm").addEventListener("submit", function (e) {
    const rules = {
   		category_name: {
		      required: true,
		      requiredMessage: "Tên danh mục không được để trống",
		      minLength: 4,
		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
		    }
    };

    if (!validateForm("addCategoryForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editCategoryForm").addEventListener("submit", function (e) {
	    const rules = {
    		edit_category_name: {
  		      required: true,
  		      requiredMessage: "Tên danh mục không được để trống",
  		      minLength: 4,
  		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
  		    }
	    };

	    if (!validateForm("editCategoryForm", rules)) {
	      e.preventDefault();
	    }
	  });
  function formatDate(isoString) {
	    const date = new Date(isoString);
	    return date.toLocaleString("vi-VN");
	}
  document.querySelectorAll(".cat-id").forEach(function (cell) {
      cell.textContent = formatId(cell.textContent.trim(),"DM-");
    });
  function passIdToModal(id) { 
      document.getElementById("category_id").value = id;
      $.ajax({
          url: "<%= request.getContextPath() %>/api/getCategory",
          method: "GET",
          data: { category_id: id },
          success: function(data) {
              $('#edit_category_name').val(data.category_name);
              $("#createdDate").text(formatDate(data.category_created_date));
              $("#updatedDate").text(formatDate(data.category_modified_date));
              
              $.get("<%= request.getContextPath() %>/getUser", { user_id: data.category_created_by }, function(user) {
                  $("#createdBy").text(user.fullname);
              });

              $.get("<%= request.getContextPath() %>/getUser", { user_id: data.category_modified_by }, function(user) {
                  $("#updatedBy").text(user.fullname);
              });
              $("#edit_category_parent").val(data.parent_category_id);
              $("#edit_cat_id").text(formatId(data.category_id,"DM-"));
          }
      });
  }
  function passIdToDelModal(id) { 
      document.getElementById("delId").value = id;
  }
</script>

