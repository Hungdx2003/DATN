<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Danh sách sản phẩm</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item">Sản phẩm</li>
      <li class="breadcrumb-item active">Danh sách</li>
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
                    <th>Tên sản phẩm</th>
                    <th>Ảnh</th>
                    <th>Số lượng</th>
                    <th>Giá bán</th>
                    <th>Loại</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody class="d-flex justify-content-center">
                  <c:forEach var="p" items="${product}">
                  	<c:set var="pi" value="${p.product_image }"/>
		                <tr>
		                    <td class="pro-id">${p.product_id}</td>
		                    <td style="width: 200px;">${p.product_name}</td>
		                    <td>
							  <img src="/product_images/${pi.image_url}" alt="Ảnh" class="img-thumbnail" style="height: 80px; width: auto;object-fit: cover; border-radius: 4px;">
							</td>
		                    <td>${p.product_quantity}</td>
		                    <td class="formatted-value">${p.product_price}</td>
		                    <td>
		                        <c:forEach var="c" items="${category}">
		                        	<c:if test="${p.product_pc_id ==c.category_id}">
			                            ${c.category_name}
			                        </c:if>
		                        </c:forEach>
		                    </td>
		                    <td>
		                    	<c:set var="status" value="danger" />
								<c:choose>
								    <c:when test="${p.product_status == 'Còn hàng'}">
								        <c:set var="status" value="success" />
								    </c:when>
								    <c:when test="${p.product_status == 'Mới'}">
								        <c:set var="status" value="info" />
								    </c:when>
								</c:choose>
		                    	<span class="badge bg-${status}">${p.product_status}</span>
	                    	</td>
		                    <td>
		                        <div class="d-flex align-items-center gap-1 flex-nowrap">
		                        	<button type="button" class="btn btn-warning btn-sm me-1 py-1 px-1" data-bs-toggle="modal" data-bs-target="#editModal" 
			                        	onclick="passIdToModal('${p.product_id}')" >
			                        	<i class="bi bi-pencil-square"></i>  Sửa
		                        	</button>
	            					<button type="button" class="btn btn-danger btn-sm py-1 px-1" data-bs-toggle="modal" data-bs-target="#delModal" 
	            						onclick="passIdToDelModal('${p.product_id}')" >
	           							<i class="bi bi-trash-fill"></i> Xóa
	       							</button>
		                        </div>
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
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-folder-plus me-2 text-primary"></i> Thêm sản phẩm
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addProductForm" action="api/product/add" method="post" onsubmit="prepareData()" enctype="multipart/form-data">
	        <div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
	          <div class="row">
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <div class="row">
					    <div class="col-auto">
					      <label class="mb-0">Hình ảnh</label>
					    </div>
					    <div class="col">
					      <div class="product-image mt-2">
					        <img id="imagePreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					      </div>
					    </div>
					  </div>
	                <input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event,'imagePreview')">
	              </div>
	              <div class="form-group mb-3">
	                <label for="product_category">Loại sản phẩm</label>
	                <select name="product_category" id="product_category" class="form-select">
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
						              <option value="${level3.category_id}" class="text-secondary level-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${level3.category_name}</option>
						            </c:if>
						          </c:forEach>
						        </c:if>
						      </c:forEach>
						    </c:if>
						  </c:forEach>
						</select>
	              </div>
	              <div class="form-group mb-3">
	                <label for="status">Trạng thái</label>
	                <select class="form-select" name="status" id="status">
	                  <option value="Còn hàng">Còn hàng</option>
	                  <option value="Hết hàng">Hết hàng</option>
	                  <option value="Khác">Khác</option>
	                </select>
	              </div>
	            </div>
	
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="name">Tên sản phẩm</label>
	                <input name="name" id="name" class="form-control" placeholder="Nhập tên sản phẩm">
	                <div class="text-danger error-name"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="quantity">Số lượng</label>
	                <input name="quantity" id="quantity" class="form-control" placeholder="Nhập số lượng">
	                <div class="text-danger error-quantity"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="original_price">Giá nhập</label>
	                <input name="original_price" id="original_price" class="form-control remove-dot" placeholder="Nhập giá nhập" oninput="formatNumber(this)"> 
	                <div class="text-danger error-original_price"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="price">Giá bán</label>
	                <input name="price" id="price" class="form-control remove-dot" placeholder="Nhập giá bán" oninput="formatNumber(this)">
	                <div class="text-danger error-price"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="brandInput">Hãng</label>
	                <input type="text" class="form-control" id="brandInput" placeholder="Nhập tên hãng" name="brand">
				    <datalist id="brands">
				    	<c:forEach var="b" items="${brand}">
				    		<option value="${b}">
				    	</c:forEach>
				    </datalist>
	              </div>
	            </div>
	            <div class="col-md-12">
            		<div class="form-group mb-3">
		                <label for="detail">Chi tiết</label>
		                <div class="quill-editor-default" id="detail">  
		            </div>
		            <input type="hidden" name="detail_data" id="detail_data">
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
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
		          <i class="bi bi-bookmark-check-fill me-2 text-warning"></i> Sửa thông tin sản phẩm
		        </h5>
		        <span class="text-muted text-end">ID: <span class="pro-id" id="pro_id"></span></span>
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
	      <form id="editProductForm" action="api/product/edit" method="post" onsubmit="prepareEditData()" enctype="multipart/form-data">
	        <div class="modal-body" style="max-height: 60vh; overflow-y: auto;">
	          <div class="row">
	            <div class="col-md-6">
	            <input type="hidden" name="product_id" id="product_id">
	              <div class="form-group mb-3">
					  <div class="row">
					    <div class="col-auto">
					      <label class="mb-0">Hình ảnh</label>
					    </div>
					    <div class="col">
					      <div class="product-image mt-2">
					        <img id="editimagePreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					      </div>
					    </div>
					  </div>
					  <input type="file" name="edit_image" class="form-control" accept="image/*" onchange="previewImage(event,'editimagePreview')">
					</div>
	              <div class="form-group mb-3">
	                <label for="edit_product_category">Loại sản phẩm</label>
	                <select name="edit_product_category" id="edit_product_category" class="form-select">
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
						              <option value="${level3.category_id}" class="text-secondary level-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${level3.category_name}</option>
						            </c:if>
						          </c:forEach>
						        </c:if>
						      </c:forEach>
						    </c:if>
						  </c:forEach>
						</select>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_status">Trạng thái</label>
	                <select class="form-select" name="edit_status" id="edit_status">
	                  <option value="Còn hàng">Còn hàng</option>
	                  <option value="Hết hàng">Hết hàng</option>
	                  <option value="Khác">Khác</option>
	                </select>
	              </div>
	            </div>
	
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="edit_name">Tên sản phẩm</label>
	                <input name="edit_name" id="edit_name" class="form-control" placeholder="Nhập tên sản phẩm">
	                <div class="text-danger error-edit_name"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_quantity">Số lượng</label>
	                <input name="edit_quantity" id="edit_quantity" class="form-control" placeholder="Nhập số lượng">
	                <div class="text-danger error-edit_quantity"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_original_price">Giá nhập</label>
	                <input name="edit_original_price" id="edit_original_price" class="form-control remove-dot" placeholder="Nhập giá nhập" oninput="formatNumber(this)">
	                <div class="text-danger error-edit_original_price"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_price">Giá bán</label>
	                <input name="edit_price" id="edit_price" class="form-control remove-dot" placeholder="Nhập giá bán" oninput="formatNumber(this)">
	                <div class="text-danger error-edit_price"></div>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_brandInput">Hãng</label>
	                <input type="text" class="form-control" id="edit_brandInput" placeholder="Nhập tên hãng" name="edit_brand" list="edit_brands">
				    <datalist id="edit_brands">
				    	<c:forEach var="b" items="${brand}">
				    		<option value="${b}">
				    	</c:forEach>
				    </datalist>
	              </div>
	              <div class="form-group mb-3">
	                <label for="edit_sold">Số lượng bán</label>
	                <input name="edit_sold" id="edit_sold" class="form-control" disabled>
	              </div>
	            </div>
	            <div class="col-md-12">
	            	<div class="form-group mb-3">
	                <label for="edit_detail">Chi tiết</label>
	                <div class="quill-editor-full" id="edit_detail">
		            </div>
		            <input type="hidden" name="edit_detail_data" id="edit_detail_data">
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
             Bạn có chắc muốn xóa sản phẩm này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/product/del" method="post">
             	<input type="hidden" name="product_id_del" id="delId">
             	<button type="submit" class="btn btn-info">OK</button>
             </form>
           </div>
         </div>
       </div>
     </div>
     
     <!-- Modal phóng to ảnh -->
	<div class="modal fade" id="imageModal" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content bg-transparent border-0 text-center">
	      <img id="zoomedImage" src="#" class="img-fluid rounded shadow" style="max-height: 90vh;" />
	    </div>
	  </div>
	</div>
	
  </div>
</section>

<script src="assets/js/form-validator.js"></script>
<script src="assets/js/functions.js"></script>
<script>
document.querySelectorAll(".pro-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"SP-");
});
  document.querySelectorAll('.formatted-value').forEach(function(td) {
	  let value = td.textContent.trim();
	  td.textContent = formatTd(value);
	});
	
  document.getElementById("addProductForm").addEventListener("submit", function (e) {
    const rules = {
		name: {
	      required: true,
	      requiredMessage: "Tên sản phẩm không được để trống",
	      minLength: 4,
	      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
	    },
	    quantity: {
			required: true,
	      	requiredMessage: "Số lượng không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Số lượng phải là số tự nhiên"
	    },
	    original_price: {
	    	required: true,
	      	requiredMessage: "Giá nhập không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Giá nhập phải lớn hơn 0"
	    },
	    price: {
	    	required: true,
	      	requiredMessage: "Giá bán không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Giá bán phải lớn hơn 0"
	    }
    };

    if (!validateForm("addProductForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editProductForm").addEventListener("submit", function (e) {
	  const rules = {
	  	edit_name: {
	      required: true,
	      requiredMessage: "Tên sản phẩm không được để trống",
	      minLength: 4,
	      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
	    },
	    edit_quantity: {
			required: true,
	      	requiredMessage: "Số lượng không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Số lượng phải là số tự nhiên"
	    },
	    edit_original_price: {
	    	required: true,
	      	requiredMessage: "Giá nhập không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Giá nhập phải lớn hơn 0"
	    },
	    edit_price: {
	    	required: true,
	      	requiredMessage: "Giá bán không được để trống",
	      	pattern: "^[1-9][0-9]*$",
	        patternMessage: "Giá bán phải lớn hơn 0"
	    }
    };

	    if (!validateForm("editProductForm", rules)) {
	      e.preventDefault();
	    }
});
  
  function passIdToModal(id) { 
      document.getElementById("product_id").value = id;
      $.ajax({
          url: "<%= request.getContextPath() %>/api/getProduct",
          method: "GET",
          data: { product_id: id },
          success: function(data) {
        	  $("#pro_id").text(formatId(data.productId,"SP-"));
              $("#createdDate").text(formatDate(data.product_created_date));
              $("#updatedDate").text(formatDate(data.product_modified_date));
              
              $.get("<%= request.getContextPath() %>/api/getUser", { user_id: data.product_created_by }, function(user) {
                  $("#createdBy").text(user.fullname);
              });

              $.get("<%= request.getContextPath() %>/api/getUser", { user_id: data.product_modified_by }, function(user) {
                  $("#updatedBy").text(user.fullname);
              });
              $('#edit_name').val(data.name);
              $('#edit_quantity').val(data.quantity);
              $('#edit_original_price').val(formatVNNumber(data.original_price));
              $('#edit_price').val(formatVNNumber(data.price));
              $('#edit_sold').val(data.sold || 0);
              $('#edit_status').val(data.status);
              $('#edit_product_category').val(data.category_id);
              if (data.image_url) {
                $('#editimagePreview').attr('src', '/product_images/' +data.image_url).show();
                document.getElementById('zoomedImage').src ='/product_images/' + data.image_url;
              } else {
                $('#editimagePreview').hide();
                document.getElementById('zoomedImage').src = '#';
              }
              $('#edit_brandInput').val(data.productBrand);
              document.querySelector("#edit_detail .ql-editor").innerHTML = data.detail;
          }
      });
  }
  function passIdToDelModal(id) { 
      document.getElementById("delId").value = id;
  }
  document.addEventListener("DOMContentLoaded", function () {
 	handleFormSubmit("addProductForm");
 	handleFormSubmit("editProductForm");
  });
  
  function previewImage(event, id) {
    const input = event.target;
    const preview = document.getElementById(id);
    const zoomed = document.getElementById('zoomedImage');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
            zoomed.src = e.target.result;  
            zoomed.style.display = 'block'; 
        };

        reader.readAsDataURL(input.files[0]);
    } else {
        preview.src = '#';
        preview.style.display = 'none'; 
        zoomed.src = '#';
        zoomed.style.display = 'none'; 
    }
}

  function openImageModal() {
    const imageModal = new bootstrap.Modal(document.getElementById('imageModal'));
    imageModal.show();
  }
  
  function prepareData() {
    const editor = document.querySelector("#detail .ql-editor");
    document.getElementById("detail_data").value = editor.innerHTML;
    return true;
  }

  function prepareEditData() {
    const editor = document.querySelector("#edit_detail .ql-editor");
    document.getElementById("edit_detail_data").value = editor.innerHTML;
    return true;
  }
  
  const input = document.getElementById('brandInput');
  const datalistId = 'brands';

  input.addEventListener('input', () => {
      if (input.value.trim() === '') {
          input.removeAttribute('list');
      } else {
          input.setAttribute('list', datalistId);
      }
  });
  
  const edit_input = document.getElementById('edit_brandInput');
  const edit_datalistId = 'edit_brands';

  edit_input.addEventListener('input', () => {
      if (edit_input.value.trim() === '') {
    	  edit_input.removeAttribute('list');
      } else {
    	  edit_input.setAttribute('list', edit_datalistId);
      }
  });
</script>

