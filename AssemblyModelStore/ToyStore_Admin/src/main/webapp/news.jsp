<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
  <h1>Tin tức</h1>
  <nav>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
      <li class="breadcrumb-item active">Tin Tức</li>
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
                    <th>Tiêu đề</th>
                    <th>Ảnh bìa</th>
                    <th>Tác giả</th>
                    <th>Ngày tạo</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="n" items="${news}">
		                <tr>
		                    <td class="n-id">${n.news_id}</td>
		                    <td>${n.news_title}</td>
		                    <td>
		                    	 <img src="/images/${n.news_cover_image}" alt="Ảnh" class="img-thumbnail" style="height: 80px; width: auto;object-fit: cover; border-radius: 4px;">
	                    	</td>
		                    <td>${n.news_author}</td>
		                    <td>${n.news_create_date}</td>
		                    <td>
		                        <button type="button" class="btn btn-warning btn-sm me-2" data-bs-toggle="modal" data-bs-target="#editModal" 
		                        	onclick="passIdToModal('${n.news_id}')" >
	                        		<i class="bi bi-pencil-square"> Sửa</i>
                        		</button>
            					<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#delModal" 
            						onclick="passIdToDelModal('${n.news_id}','${n.news_cover_image}')" >
            						<i class="bi bi-trash-fill"> Xóa</i>
           						</button>
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
	<!-- Modal: Thêm tin tức -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
	  <div class="modal-dialog modal-xl">
	    <div class="modal-content p-1">
	      <div class="modal-header border-0">
	        <h5 class="modal-title">
	          <i class="bi bi-journal-plus me-2 text-primary"></i> Thêm bài viết
	        </h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	
	      <form id="addNewsForm" action="api/news/add" method="post" onsubmit="prepareAddData()" enctype="multipart/form-data">
	        <div class="modal-body">
	          <div class="row">
	          	<div class="col-md-3">
	          		<div class="form-group mb-3">
				      <label class="mb-0">Ảnh bìa</label>
				      <div class="news-image mt-2">
					        <img id="imagePreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					  </div>
              		<input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event,'imagePreview')">
	              </div>
	              <div class="form-group mb-3">
	              	<label for="title">Tiêu đề</label>
	                <textarea name="title" id="title" class="form-control" placeholder="Nhập tiêu đề tin tức" style="height: 100px"></textarea>
	                <div class="text-danger error-title"></div>
	              </div>
	              <div class="form-group mb-3">
	              	<label for="author">Tác giả</label>
	                <input name="author" id="author" class="form-control" placeholder="Nhập tác giả">
	                <div class="text-danger error-author"></div>
	              </div>
	          	</div>
				<div class="col-lg-9">
					<label for="editor-add">Chi tiết</label>
				  	<div id="editor-add" class="quill-editor-default editor-container"></div>
				  	<input type="hidden" name="content" id="content">
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
	
	<!-- Modal: Sửa tin tức -->
	<div class="modal fade" id="editModal" tabindex="-1">
      <div class="modal-dialog modal-xl">
        <div class="modal-content p-1">
          <div class="modal-header border-0">
            <div class="d-flex justify-content-between align-items-center w-100">
			  <h5 class="modal-title mb-0">
		          <i class="bi bi-journal-text me-2 text-warning"></i> Sửa thông tin tin tức
		        </h5>
		        <span class="text-muted text-end">ID: <span class="nw-id" id="nw_id"></span></span>
			</div>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
          </div>
          <!-- Thông tin ngày tạo và ngày sửa -->
          <div class="px-3 text-muted small">
            Ngày tạo: <span id="createdDate"></span> | 
            Người tạo: <span id="createdBy"></span>
          </div>
          <form action="api/news/edit" id="editNewsForm" method="post" onsubmit="prepareEditData()" enctype="multipart/form-data">
          	<input type="hidden" name="newsId" id="newsId">
          	<div class="modal-body" style="padding: 10px;">
	          <div class="row">
	          	<div class="col-md-3">
	          		<div class="form-group mb-3">
				      <label class="mb-0">Ảnh bìa</label>
				      <div class="news-image mt-2">
					        <img id="editimagePreview" src="#" alt="Xem trước ảnh" style="display: none;cursor: zoom-in;max-width: 100%;" onclick="openImageModal()"/>
					  </div>
              		<input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event,'editimagePreview')">
              		<input type="hidden" name="old_image" id="old_image">
	              </div>
	              <div class="form-group mb-3">
	              	<label for="edit_title">Tiêu đề</label>
	                <textarea name="edit_title" id="edit_title" class="form-control" placeholder="Nhập tiêu đề tin tức" style="height: 100px"></textarea>
	                <div class="text-danger error-edit_title"></div>
	              </div>
	              <div class="form-group mb-3">
	              	<label for="edit_author">Tác giả</label>
	                <input name="edit_author" id="edit_author" class="form-control" placeholder="Nhập tác giả">
	                <div class="text-danger error-edit_author"></div>
	              </div>
	          	</div>
				<div class="col-lg-9">
					<label for="editor-edit">Chi tiết</label>
				  	<div id="editor-edit" class="quill-editor-full editor-container"></div>
				  	<input type="hidden" name="edit_content" id="edit_content">
				</div>	          	
	          </div>
	        </div>
            <div class="modal-footer border-0 pt-0">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
              <button type="submit" class="btn btn-warning">Cập nhật</button>
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
             Bạn có chắc muốn xóa tin tức này không?
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
             <form action="api/news/del" method="post">
             	<input type="hidden" name="news_id_del" id="delId">
             	<input type="hidden" name="news_image_del" id="delImage">
             	<button type="submit" class="btn btn-info">OK</button>
             </form>
           </div>
         </div>
       </div>
     </div>
     
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
document.querySelectorAll(".n-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"TT-");
});
  document.getElementById("addNewsForm").addEventListener("submit", function (e) {
    const rules = {
		title: {
		      required: true,
		      requiredMessage: "Tiêu để không được để trống",
		      minLength: 10,
		      minLengthMessage: "Tiêu để phải có ít nhất 10 ký tự"
		    },
	    author: {
	        required: true,
	        requiredMessage: "Tác giả không được để trống",
	      }
    };

    if (!validateForm("addNewsForm", rules)) {
      e.preventDefault();
    }
  });
  
  document.getElementById("editNewsForm").addEventListener("submit", function (e) {
	    const rules = {
			edit_title: {
  		      required: true,
  		      requiredMessage: "Tiêu để không được để trống",
  		      minLength: 10,
  		      minLengthMessage: "Tiêu để phải có ít nhất 10 ký tự"
  		    },
  		  edit_author: {
	  	        required: true,
	  	        requiredMessage: "Tác giả không được để trống",
	  	      }
	    };

	    if (!validateForm("editNewsForm", rules)) {
	      e.preventDefault();
	    }
	  });
  function passIdToModal(id) { 
      document.getElementById("newsId").value = id;
      
      $.ajax({
          url: "<%= request.getContextPath() %>/api/getNews",
          method: "GET",
          data: { news_id: id },
          success: function(data) {
              $('#nw_id').text(formatId(data.id,"TT-"));
              $('#edit_title').val(data.title);
              $('#edit_author').val(data.author);
              if (data.image) {
                  $('#editimagePreview').attr('src', '/images/' +data.image).show();
                } else {
                  $('#editimagePreview').hide();
                }
              $('#old_image').val(data.image);
              $.get("<%= request.getContextPath() %>/api/getUser", { user_id: data.createdBy }, function(user) {
                  $("#createdBy").text(user.fullname);
              });
              $("#createdDate").text(formatDate(data.createdDate));
              document.querySelector("#editor-edit .ql-editor").innerHTML = data.content;
              $("#edit_content").val(data.content);
          }
      });
  }
  
  function passIdToDelModal(id,image) { 
      document.getElementById("delId").value = id;
      document.getElementById("delImage").value = image;
  }
  
  function prepareAddData() {
    const editor = document.querySelector("#editor-add .ql-editor");
    document.getElementById("content").value = editor.innerHTML;
    return true;
  }
  function prepareEditData() {
    const editor = document.querySelector("#editor-edit .ql-editor");
    document.getElementById("edit_content").value = editor.innerHTML;
    return true;
  }
  
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
</script>

