<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <span>Tin tức</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Blog Section Begin -->
<section class="blog spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-9">
            	<div class="row">
            		<c:forEach var="n" items="${news}">
	            		<div class="col-lg-6 col-md-6 col-sm-12">
			                <div class="blog__item">
			                    <div class="blog__item__pic set-bg" data-setbg="/images/${n.news_cover_image}"></div>
			                    <div class="blog__item__text">
			                        <h6><a href="page?view=news_detail&news_id=${n.news_id}">${n.news_title}</a></h6>
			                        <ul>
			                            <li>bởi <span>${n.news_author}</span></li>
			                            <li class="formatted-date">${n.news_create_date}</li>
			                        </ul>
			                    </div>
			                </div>
			            </div>
	            	</c:forEach>
	            	<div class="col-lg-12 text-center">
					    <div class="pagination__option">
							<c:if test="${currentPage > 1}">
					            <a href="?view=news&page=${currentPage - 1}"><i class="fa fa-angle-left"></i></a>
					        </c:if>
					        <c:forEach var="page" items="${paginationData}">
					            <c:choose>
					                <c:when test="${page == 0}">
					                    <span>...</span>
					                </c:when>
					
					                <c:when test="${page == currentPage}">
					                    <a class="active" href="?view=news&page=${page}">${page}</a>
					                </c:when>
					
					                <c:otherwise>
					                    <a href="?view=news&page=${page}">${page}</a>
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
					
					        <c:if test="${currentPage < totalPages}">
					            <a href="?view=news&page=${currentPage + 1}">
					                <i class="fa fa-angle-right"></i>
					            </a>
					        </c:if>
					
					    </div>
					</div>
            	</div>
            </div>
            <div class="col-lg-3">
            	<jsp:include page="news_sidebar.jsp"/>
            </div>
        </div>
    </div>
</section>
<script src="js/functions.js"></script>
<script>
document.querySelectorAll(".formatted-date").forEach(function (cell) {
  cell.textContent = formatDateOnly(cell.textContent.trim());
});
</script>