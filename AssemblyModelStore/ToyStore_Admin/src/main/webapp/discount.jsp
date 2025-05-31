<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Giảm giá</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item active">Giảm giá</li>
    </ol>
  </nav>
</div><!-- End Page Title -->

<section class="section">
  <div class="container my-4"></div>
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
                    <th>Tên</th>
                    <th>Giá trị</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Loại</th>
                    <th>Trạng thái</th>
                    <th class="text-center">Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="d" items="${discount}">
		                <tr>
		                    <td class="dis-id">${d.discount_id}</td>
		                    <td>${d.discount_name}</td>
		                    <td><span class="formatted-value">${d.discount_value}</span>${d.discount_value_type}</td>
		                    <td class="formatted-date">${d.start_date}</td>
		                    <td class="formatted-date">${d.end_date}</td>
		                    <td>${d.discount_type}</td>
		                    <td>
							  <c:choose>
							    <c:when test="${not d.active}"><span class="badge rounded-pill bg-secondary">Chưa kích hoạt</span></c:when>
							    <c:otherwise><span class="badge rounded-pill bg-primary">Đã kích hoạt</span></c:otherwise>
							  </c:choose>
							</td>

		                    <td>
							  <div class="d-flex align-items-center gap-1 flex-nowrap">
							    <button type="button" class="btn btn-warning btn-sm py-1 px-2" data-bs-toggle="modal" data-bs-target="#editModal"
							      onclick="passIdToModal('${d.discount_id}')">
							      <i class="bi bi-pencil-square me-1"></i> Sửa
							    </button>
							
							    <button type="button" class="btn btn-danger btn-sm py-1 px-2" data-bs-toggle="modal" data-bs-target="#delModal"
							      onclick="passIdToDelModal('${d.discount_id}')">
							      <i class="bi bi-trash-fill me-1"></i> Xóa
							    </button>
								<c:if test="${d.discount_type =='Khuyến mãi'}">
									<button type="button" class="btn btn-primary btn-sm py-1 px-2" data-bs-toggle="modal" data-bs-target="#applyModal"
								    	onclick="loadUsedProducts('${d.discount_id}')">
								      <i class="bi bi-tags me-1"></i> Dùng
								    </button>
								</c:if>
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
	<!-- Modal: Thêm giảm giá -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-ticket-perforated me-2 text-primary"></i> Thêm giảm giá
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addDiscountForm" action="api/discount/add" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="discount_name">Tên giảm giá</label>
	                <input name="discount_name" id="discount_name" class="form-control" placeholder="Nhập tên chương trình">
	                <div class="text-danger error-discount_name"></div>
	              </div>
	              
	              <div class="form-group mb-3">
	                <label for="discount_type">Loại chương trình</label>
	                <select name="discount_type" id="discount_type" class="form-select">
	                  <option value="Mã giảm giá">Mã giảm giá</option>
	                  <option value="Khuyến mãi">Khuyến mãi</option>
	                </select>
	              </div>

	              <div class="form-group mb-3">
	                <label for="discount_value">Giá trị giảm</label>
	                <div class="input-group discount-group">
					  <input type="text" class="form-control discount_value_input remove-dot" id="discount_value" name="discount_value" placeholder="Nhập giảm giá" oninput="formatNumber(this)">
					  <div class="btn-group" role="group" id="discountTypeButtons">
					    <button type="button" class="btn btn-outline-primary active" data-type="VND">VND</button>
					    <button type="button" class="btn btn-outline-primary" data-type="%">%</button>
					  </div>
					  <input type="hidden" name="discount_value_type" id="discount_value_type" value="VND" class="discount_type_input">
					  <div class="text-danger error-discount_value"></div>
					</div>
	              </div>
	              <div class="form-group mb-3">
					  <label for="is_active">Trạng thái</label>
					  <div class="d-flex gap-3">
					    <div>
					      <input type="radio" id="is_active_1" name="is_active" value="1" class="form-check-input" checked>
					      <label for="is_active_1" class="form-check-label">Kích hoạt</label>
					    </div>
					    <div>
					      <input type="radio" id="is_active_0" name="is_active" value="0" class="form-check-input">
					      <label for="is_active_0" class="form-check-label">Không kích hoạt</label>
					    </div>
					  </div>
					</div>
	            </div>
	
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="start_date">Ngày bắt đầu</label>
	                <input type="date" name="start_date" id="start_date" class="form-control">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="end_date">Ngày kết thúc</label>
	                <input type="date" name="end_date" id="end_date" class="form-control">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="max_users">Số người dùng tối đa</label>
	                <input type="number" name="max_users" id="max_users" class="form-control" placeholder="Nhập số người dùng tối đa">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="max_usage">Số lần sử dụng tối đa</label>
	                <input type="number" name="max_usage" id="max_usage" class="form-control" placeholder="Nhập lần sử dụng tối đa">
	              </div>
	            </div>
	          </div>
	        </div>
	
	        <div class="modal-footer border-0 pt-0">
	          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
	          <button type="submit" class="btn btn-primary">Lưu</button>
	        </div>
	      </form>
	    </div>
	  </div>
	</div>

	
	<!-- Modal: Sửa giảm giá -->
	<div class="modal fade" id="editModal" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
		          <i class="bi bi-ticket-detailed me-2 text-warning"></i> Sửa thông tin giảm giá
		        </h5>
		        <span class="text-muted text-end">ID: <span class="dis-id" id="edit_dis_id"></span></span>
			</div>  
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form id="editDiscountForm" action="api/discount/edit" method="post">
	        <div class="modal-body">
	          <div class="row">
	            <div class="col-md-6">
	            <input type="hidden" name="discount_id" id="discount_id">
	              <div class="form-group mb-3">
	                <label for="edit_discount_name">Tên giảm giá</label>
	                <input name="edit_discount_name" id="edit_discount_name" class="form-control" placeholder="Nhập tên chương trình">
	                <div class="text-danger error-edit_discount_name"></div>
	              </div>
	              
	              <div class="form-group mb-3">
	                <label for="edit_discount_type">Loại chương trình</label>
	                <select name="edit_discount_type" id="edit_discount_type" class="form-select">
	                  <option value="Mã giảm giá">Mã giảm giá</option>
	                  <option value="Khuyến mãi">Khuyến mãi</option>
	                </select>
	              </div>

	              <div class="form-group mb-3">
	                <label for="edit_discount_value">Giá trị giảm</label>
	                <div class="input-group discount-group">
					  <input type="text" class="form-control discount_value_input remove-dot" id="edit_discount_value" name="edit_discount_value" placeholder="Nhập giảm giá" oninput="formatNumber(this)">
					  <div class="btn-group ms-2" role="group" id="discountTypeButtons">
					    <button type="button" class="btn btn-outline-primary active" data-type="VND">VND</button>
					    <button type="button" class="btn btn-outline-primary" data-type="%">%</button>
					  </div>
					  <input type="hidden" name="edit_discount_value_type" id="edit_discount_value_type" class="discount_type_input">
					  <div class="text-danger error-edit_discount_value"></div>
					</div>
	              </div>
	              <div class="form-group mb-3">
					  <label for="edit_is_active">Trạng thái</label>
					  <div class="d-flex gap-3">
					    <div>
					      <input type="radio" id="edit_is_active_1" name="edit_is_active" value="1" class="form-check-input" checked>
					      <label for="edit_is_active_1" class="form-check-label">Kích hoạt</label>
					    </div>
					    <div>
					      <input type="radio" id="edit_is_active_0" name="edit_is_active" value="0" class="form-check-input">
					      <label for="iedit_s_active_0" class="form-check-label">Không kích hoạt</label>
					    </div>
					  </div>
					</div>
	            </div>
	
	            <div class="col-md-6">
	              <div class="form-group mb-3">
	                <label for="edit_start_date">Ngày bắt đầu</label>
	                <input type="date" name="edit_start_date" id="edit_start_date" class="form-control">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="edit_end_date">Ngày kết thúc</label>
	                <input type="date" name="edit_end_date" id="edit_end_date" class="form-control">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="edit_max_users">Số người dùng tối đa</label>
	                <input type="number" name="edit_max_users" id="edit_max_users" class="form-control" placeholder="Nhập số người dùng tối đa">
	              </div>
	
	              <div class="form-group mb-3">
	                <label for="edit_max_usage">Số lần sử dụng tối đa</label>
	                <input type="number" name="edit_max_usage" id="edit_max_usage" class="form-control" placeholder="Nhập lần sử dụng tối đa">
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
	
    <!-- Modal: Thông báo xác nhận xóa -->
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
             <form action="api/discount/del" method="post">
             	<input type="hidden" name="discount_id_del" id="delId">
             	<button type="submit" class="btn btn-info">OK</button>
             </form>
           </div>
         </div>
       </div>
      </div>
       
     <!-- Modal: Hiển thị danh sách sản phẩm  -->
	<div class="modal fade" id="applyModal" tabindex="-1">
	  <div class="modal-dialog modal-xl">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title"><i class="bi bi-card-checklist text-primary"></i> Áp dụng khuyến mãi</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <div class="modal-body">
	        <ul class="nav nav-tabs" id="productTab" role="tablist">
	          <li class="nav-item" role="presentation">
	            <button class="nav-link active" id="used-tab" data-bs-toggle="tab" data-bs-target="#used-products" type="button" role="tab">Đã áp dụng</button>
	          </li>
	          <li class="nav-item" role="presentation">
	            <button class="nav-link" id="not-used-tab" data-bs-toggle="tab" data-bs-target="#not-used-products" type="button" role="tab">Chưa áp dụng</button>
	          </li>
	        </ul>
	
	        <div class="tab-content" id="productTabContent">
				
	          <!-- Tab: Đã áp dụng -->
	          <div class="tab-pane fade show active" id="used-products" role="tabpanel">
	          	<div class="d-flex justify-content-end mt-2 mb-2">
				  <input type="text" id="searchUsedBox" class="form-control w-auto" placeholder="Tìm kiếm...">
				</div>
				<form action="api/deleteProductDiscount" method="post" id="productDiscountForm">
					<table id="usedProductsTable" class="table border rounded-4">
		              <thead>
		                <tr>
		                  <th></th>
		                  <th>ID</th>
		                  <th>Ảnh</th>
		                  <th>Tên sản phẩm</th>
		                  <th>Giá bán</th>
		                  <th>Giá khuyến mãi</th>
		                </tr>
		              </thead>
		              <tbody>
	              		
		              </tbody>
		            </table>
					<div id="usedPagination" class="d-flex justify-content-end mt-2 mb-2"></div>
		            <div class="d-flex align-items-center justify-content-end gap-2">
		              <button type="submit" class="btn btn-primary"> Bỏ giảm giá</button>
		              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
		            </div>
				</form>
	          </div>
	
	          <!-- Tab: Chưa áp dụng -->
	          <div class="tab-pane fade" id="not-used-products" role="tabpanel">
	          	<div class="d-flex justify-content-end mt-2 mb-2">
				  <input type="text" id="searchBox" class="form-control w-auto" placeholder="Tìm kiếm...">
				</div>
	            <form action="api/applyDiscount" method="post" id="discountForm">
	            	<table id="notUsedProductsTable" class="table border rounded-4 table-responsive">
		              <thead>
		                <tr>
		                  <th></th>
		                  <th>ID</th>
		                  <th>Ảnh</th>
		                  <th>Tên sản phẩm</th>
		                  <th>Giá</th>
		                </tr>
		              </thead>
		              <tbody>
		              	
		              </tbody>
		            </table>
	             	<div id="pagination" class="d-flex justify-content-end mt-2 mb-2"></div>
					<input type="hidden" name="discountId" id="discountId">
		            <div class="d-flex align-items-center justify-content-end gap-2">
		              <button type="submit" class="btn btn-success">Áp dụng</button>
		              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
		            </div>
	            </form>
	          </div>
	
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

</section>

<script src="assets/js/form-validator.js"></script>
<script src="assets/js/functions.js"></script>
<script>
document.querySelectorAll('.format-value').forEach(function(td) {
   let value = td.textContent.trim();
   td.textContent = formatTd(value);
 });

document.querySelectorAll(".pro-id").forEach(function (cell) {
   cell.textContent = formatId(cell.textContent.trim(),"SP-");
 });
	 
document.querySelectorAll(".dis-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"GG-");
});

  document.getElementById("addDiscountForm").addEventListener("submit", function (e) {
    const rules = {
   		discount_name: {
		      required: true,
		      requiredMessage: "Tên giảm giá không được để trống",
		      minLength: 4,
		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
		    },
	    discount_value: {
		      required: true,
		      requiredMessage: "Giá trị giảm giá không được để trống",
		    },
    };

    if (!validateForm("addDiscountForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editDiscountForm").addEventListener("submit", function (e) {
    const rules1 = {
   		edit_discount_name: {
		      required: true,
		      requiredMessage: "Tên giảm giá không được để trống",
		      minLength: 4,
		      minLengthMessage: "Tên phải có ít nhất 4 ký tự"
		    },
	    edit_discount_value: {
		      required: true,
		      requiredMessage: "Giá trị giảm giá không được để trống",
		    },
    };

    if (!validateForm("editDiscountForm", rules1)) {
      e.preventDefault();
    }
  });

document.querySelectorAll('.formatted-value').forEach(function(td) {
  let value = td.textContent.trim();
  td.textContent = formatVNNumber(value);
});

document.querySelectorAll(".formatted-date").forEach(function (cell) {
  cell.textContent = formatDateOnly(cell.textContent.trim());
});
	
  function passIdToModal(id) { 
      document.getElementById("discount_id").value = id;
      $.ajax({
          url: "<%= request.getContextPath() %>/api/getDiscount",
          method: "GET",
          data: { discount_id: id },
          success: function(data) {
              $('#edit_discount_name').val(data.name);
              $('#edit_discount_type').val(data.type);
              $('#edit_discount_value').val(formatVNNumber(data.value));
              $('#edit_discount_value_type').val(data.valueType);
              $('#edit_start_date').val(data.startDate.substring(0, 10));
              $('#edit_end_date').val(data.endDate.substring(0, 10));
              $('#edit_max_users').val(data.maxUsers);
              $('#edit_max_usage').val(data.maxUsage);
              
              if (data.isActive == 1) {
                  $('#edit_is_active_1').prop('checked', true);
              } else {
                  $('#edit_is_active_0').prop('checked', true);
              }

              $('#discountTypeButtons button').removeClass('active');
              $('#discountTypeButtons button[data-type="' + data.valueType + '"]').addClass('active');
              $('#edit_dis_id').text(formatId(data.discountId,"GG-"));
          }
      });
  }
  
  function passIdToDelModal(id) { 
      document.getElementById("delId").value = id;
  }
  
function loadUsedProducts(discountId) {
 document.getElementById("discountId").value = discountId;
 let selectedProductIds = [];

 $.ajax({
     url: "<%= request.getContextPath() %>/api/getProductDiscountById",
    method: "GET",
    data: { discount_id: discountId },
    dataType: "json",
    success: function (products) {
        const formattedProducts = products.map(p => ({
            pd_id:p.pd_id,
        	id: p.product_id,
            name: p.product_name,
            price: p.product_price,
            salePrice: p.product_sale_price,
            image: "/product_images/" + p.image_url
        }));

        createPaginationAndSearch({
            data: formattedProducts,
            tableBodySelector: "#usedProductsTable tbody",
            paginationContainerSelector: "#usedPagination",
            searchBoxSelector: "#searchUsedBox",
            pageSize: 5,
            renderRow: function (p) {
                const tr = document.createElement("tr");

                const tdCheckbox = document.createElement("td");
                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.classList.add("used-product-checkbox", "form-check-input");
                checkbox.value = p.pd_id;

                checkbox.addEventListener("change", function () {
                    const productId = this.value;
                    if (this.checked) {
                        if (!selectedProductIds.includes(productId)) {
                            selectedProductIds.push(productId);
                        }
                    } else {
                        selectedProductIds = selectedProductIds.filter(id => id !== productId);
                    }
                });

                tdCheckbox.appendChild(checkbox);
                tr.appendChild(tdCheckbox);

                const tdId = document.createElement("td");
                tdId.textContent = formatId(p.id, "SP-");
                tr.appendChild(tdId);

                const tdImg = document.createElement("td");
                const img = document.createElement("img");
                img.src = p.image;
                img.style.width = "40px";
                tdImg.appendChild(img);
                tr.appendChild(tdImg);

                const tdName = document.createElement("td");
                tdName.textContent = p.name;
                tr.appendChild(tdName);

                const tdPrice = document.createElement("td");
                tdPrice.textContent = formatTd(p.price);
                tr.appendChild(tdPrice);

                const tdSalePrice = document.createElement("td");
                tdSalePrice.textContent = formatTd(p.salePrice);
                tr.appendChild(tdSalePrice);

                return tr;
            }
        });
    },
    error: function () {
        alert("Không thể tải sản phẩm đã chọn.");
    }
});

    // Bắt sự kiện submit để gắn id vào form
  const productDiscountForm = document.getElementById("productDiscountForm");
  productDiscountForm.addEventListener("submit", function (e) {
      e.preventDefault(); // Ngăn submit mặc định

      if (selectedProductIds.length === 0) {
          alert("Vui lòng chọn ít nhất một sản phẩm để bỏ giảm giá.");
          return;
      }
      
      selectedProductIds.forEach(id => {
    	    // Tạo input cho ID
   	    const hiddenId = document.createElement("input");
   	    hiddenId.type = "hidden";
   	    hiddenId.name = "selectedProductIds";
   	    hiddenId.value = id;
   	    this.appendChild(hiddenId);

   	    // Tìm product tương ứng từ danh sách sản phẩm allProducts
   	    const product = allProducts.find(p => p.id == id);
   	    if (product) {
   	      const hiddenPrice = document.createElement("input");
   	      hiddenPrice.type = "hidden";
   	      hiddenPrice.name = "product_price";
   	      hiddenPrice.value = product.price;
   	      this.appendChild(hiddenPrice);
   	    }
   	  });

      // Submit form thật sự
      productDiscountForm.submit();
  });
}
  
document.addEventListener("DOMContentLoaded", function () {
   const groups = document.querySelectorAll(".discount-group");

   groups.forEach((group) => {
     const discountButtons = group.querySelectorAll("button[data-type]");
     const discountTypeInput = group.querySelector(".discount_type_input");
     const discountValueInput = group.querySelector(".discount_value_input");

     discountButtons.forEach((btn) => {
       btn.addEventListener("click", function () {
         discountButtons.forEach((b) => b.classList.remove("active"));
         this.classList.add("active");

         const type = this.getAttribute("data-type");
         discountTypeInput.value = type;

         if (type === "%") {
           discountValueInput.min = 1;
           discountValueInput.max = 100;
           discountValueInput.placeholder = "1 - 100 %";
         } else {
           discountValueInput.removeAttribute("min");
           discountValueInput.removeAttribute("max");
           discountValueInput.placeholder = "Nhập giảm giá (VND)";
         }

         if (type === "%" && (discountValueInput.value < 1 || discountValueInput.value > 100)) {
           discountValueInput.value = "";
         }
       });
     });

     discountValueInput.addEventListener("input", function () {
       const selectedTypeBtn = group.querySelector("button.active");
       if (selectedTypeBtn?.getAttribute("data-type") === "%") {
         if (this.value > 100) this.value = 100;
         if (this.value < 1) this.value = 1;
       }
     });
   });
   
   handleFormSubmit("addDiscountForm");
   handleFormSubmit("editDiscountForm");
 });

const allProducts = [
   <c:forEach var="p" items="${productNoDiscount}" varStatus="status">
     {
       id: "${p.productId}",
       name: "${p.productName}",
       price: "${p.productPrice}",
       image: "/product_images/${p.imageUrl}"
     }<c:if test="${!status.last}">,</c:if>
   </c:forEach>
 ];

 const selectedProductIds = createPaginationAndSearch({
   data: allProducts,
   tableBodySelector: "#notUsedProductsTable tbody",
   paginationContainerSelector: "#pagination",
   searchBoxSelector: "#searchBox",
   pageSize: 5,
   renderRow: function (product, selectedIds, handleCheckbox) {
     const tr = document.createElement("tr");

     const tdCheck = document.createElement("td");
     const checkbox = document.createElement("input");
     checkbox.type = "checkbox";
     checkbox.value = product.id;
     checkbox.name = "productCheckboxTemp"; // tạm, không gửi
     checkbox.classList.add("form-check-input");

     handleCheckbox(checkbox, product.id);
     tdCheck.appendChild(checkbox);
     tr.appendChild(tdCheck);

     const tdId = document.createElement("td");
     tdId.textContent = formatId(product.id,"SP-");
     tr.appendChild(tdId);

     const tdImg = document.createElement("td");
     const img = document.createElement("img");
     img.src = product.image;
     img.width = 40;
     tdImg.appendChild(img);
     tr.appendChild(tdImg);

     const tdName = document.createElement("td");
     tdName.textContent = product.name;
     tr.appendChild(tdName);

     const tdPrice = document.createElement("td");
     tdPrice.textContent = formatTd(product.price);
     tr.appendChild(tdPrice);

     return tr;
   }
 });

 document.getElementById("discountForm").addEventListener("submit", function (e) {
  // Xóa các input cũ nếu có
  document.querySelectorAll('input[name="productIds"]').forEach(el => el.remove());
  document.querySelectorAll('input[name="product_price"]').forEach(el => el.remove());
  
  selectedProductIds.forEach(id => {
    // Tạo input cho ID
    const hiddenId = document.createElement("input");
    hiddenId.type = "hidden";
    hiddenId.name = "productIds";
    hiddenId.value = id;
    this.appendChild(hiddenId);

    // Tìm product tương ứng từ danh sách sản phẩm allProducts
    const product = allProducts.find(p => p.id == id);
    if (product) {
      const hiddenPrice = document.createElement("input");
      hiddenPrice.type = "hidden";
      hiddenPrice.name = "product_price";
      hiddenPrice.value = product.price;
      this.appendChild(hiddenPrice);
    }
  });
});
</script>

