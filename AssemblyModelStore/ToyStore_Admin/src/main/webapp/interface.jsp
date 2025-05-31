<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Quản lý giao diện</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item">Giao diện</li>
    </ol>
  </nav>
</div>

<section class="section">
	<div class="container my-4">
	
	    <!-- Banner Section -->
	    <div class="sec">
	        <div class="d-flex justify-content-between align-items-center mb-4">
	            <h2>Ảnh Banner</h2>
	            <button class="btn-add" data-bs-toggle="modal" data-bs-target="#addBanner">
	            	<i class="bi bi-plus-square"></i> Thêm Ảnh
            	</button>
	        </div>
	        <div class="row g-4">
	        	<c:forEach var="b" items="${banner}">
	        		<div class="col-lg-3 col-md-4">
		                <div class="image-card">
		                    <img src="/images/${b.image_url}" alt="${b.image_url}">
		                    <c:if test="${b.active}">
		                    	<div class="active-label">Đang hiển thị</div>
		                    </c:if>
		                    <div class="image-info">${b.image_url}</div>
		                    <div class="btn-group">
	                    		<button class="btn-delete" onclick="passIdToModal('${b.image_id}')" data-bs-toggle="modal" data-bs-target="#delModal">
	                    			Xóa
	                    		</button>
		                    	<form action="api/interface/editImage" method="post">
		                    		<input type="hidden" name="image_id" value="${b.image_id}">
		                    		<c:choose>
								    	<c:when test="${not b.active}">
								    		<input type="hidden" name="active" value="1">
								    		<button type="submit" class="btn-set-active">Hiển thị</button>
								    	</c:when>
									    <c:otherwise>
									    	<input type="hidden" name="active" value="0">
								    		<button type="submit" class="btn-set-unactive">Không hiển thị</button>
									    </c:otherwise>
								  	</c:choose>
		                    	</form>
		                    </div>
		                </div>
		            </div>
	        	</c:forEach>
	        </div>
	    </div>
	
	    <!-- Promotion Section -->
	    <div class="sec">
	        <div class="d-flex justify-content-between align-items-center mb-4">
	            <h2>Ảnh Khuyến Mãi</h2>
	            <button class="btn-add" data-bs-toggle="modal" data-bs-target="#addPromo">
	            	<i class="bi bi-plus-square"></i> Thêm Ảnh
	            </button>
	        </div>
	        <div class="row g-4">
	            <c:forEach var="p" items="${promo}">
	        		<div class="col-lg-3 col-md-4">
		                <div class="image-card">
		                    <img src="/images/${p.image_url}" alt="${p.image_url}">
		                    <c:if test="${p.active}">
		                    	<div class="active-label">Đang hiển thị</div>
		                    </c:if>
		                    <div class="image-info">${p.image_url}</div>
		                    <div class="btn-group">
		                    	<button class="btn-delete" onclick="passIdToModal('${p.image_id}')" data-bs-toggle="modal" data-bs-target="#delModal">
	                    			Xóa
	                    		</button>
		                    	<form action="api/interface/editImage" method="post">
		                    		<input type="hidden" name="image_id" value="${p.image_id}">
		                    		<c:choose>
								    	<c:when test="${not p.active}">
								    		<input type="hidden" name="active" value="1">
								    		<button type="submit" class="btn-set-active">Hiển thị</button>
								    	</c:when>
									    <c:otherwise>
									    	<input type="hidden" name="active" value="0">
								    		<button type="submit" class="btn-set-unactive">Không hiển thị</button>
									    </c:otherwise>
								  	</c:choose>
		                    	</form>
		                    </div>
		                </div>
		            </div>
	        	</c:forEach>
	        </div>
	    </div>
	</div>
	
	<div class="modal fade" id="addBanner" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-card-image me-2 text-primary"></i> Thêm ảnh banner
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form action="api/interface/addImage" method="post"  enctype="multipart/form-data">
	        <div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
	          <div class="row">
	            <div class="col-lg-12">
	              <div class="form-group mb-3">
	                <div class="row">
					    <div class="col-auto">
					      <label class="mb-0">Hình ảnh</label>
					    </div>
					    <div class="col">
					      <div class="interface-image mt-2">
					        <img id="imagePreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					      </div>
					    </div>
					  </div>
	                <input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event,'imagePreview')">
	                <input type="hidden" name="type" value="banner">
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
	
	<div class="modal fade" id="addPromo" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content p-3">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-card-image me-2 text-primary"></i> Thêm ảnh khuyến mãi
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form  action="api/interface/addImage" method="post" onsubmit="prepareData()" enctype="multipart/form-data">
	        <div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
	          <div class="row">
	            <div class="col-lg-12">
	              <div class="form-group mb-3">
	                <div class="row">
					    <div class="col-auto">
					      <label class="mb-0">Hình ảnh</label>
					    </div>
					    <div class="col">
					      <div class="interface-image mt-2">
					        <img id="imagePromoPreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					      </div>
					    </div>
					  </div>
	                <input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event,'imagePromoPreview')">
	                <input type="hidden" name="type" value="promotion">
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
	
	<!-- Modal xóa ảnh -->
	<div class="modal fade" id="delModal" tabindex="-1">
       <div class="modal-dialog modal-dialog-centered">
         <div class="modal-content">
           <div class="modal-header">
             <h5 class="modal-title"><i class="bi bi-question-circle text-info"></i> Thông báo xác nhận </h5>
             <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
           </div>
           <div class="modal-body">
             Bạn có chắc muốn xóa ảnh này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/interface/delImage" method="post">
             	<input type="hidden" name="del_image_id" id="delId">
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
</section>
<script>
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
function passIdToModal(id) { 
    document.getElementById("delId").value = id;
}
</script>