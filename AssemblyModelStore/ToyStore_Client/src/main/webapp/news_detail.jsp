<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <a href="page?view=news">Tin tức</a>
                    <span>${news.news_title}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Blog Details Section Begin -->
<section class="blog-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-8">
                <div class="blog__details__content">
                    <div class="blog__details__item">
                        <img src="/images/${news.news_cover_image}" alt="">
                        <div class="blog__details__item__title">
                            <h4>${news.news_title}</h4>
                            <ul>
                                <li>bởi <span>${news.news_author}</span></li>
                                <li class="formatted-date">${news.news_create_date}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="blog__details__desc ql-editor">
                        <div id="content"></div>
                    </div>
                    
                    <div class="blog__details__btns">
					    <div class="row">
					        <div class="col-lg-6 col-md-6 col-sm-6">
					            <div class="blog__details__btn__item">
					                <h6>
					                    <c:choose>
					                        <c:when test="${pre_id != null}">
					                            <a href="page?view=news_detail&news_id=${pre_id}">
					                                <i class="fa fa-angle-left"></i> Bài viết trước
					                            </a>
					                        </c:when>
					                        <c:otherwise>
					                            
					                        </c:otherwise>
					                    </c:choose>
					                </h6>
					            </div>
					        </div>
					        <div class="col-lg-6 col-md-6 col-sm-6">
					            <div class="blog__details__btn__item blog__details__btn__item--next">
					                <h6>
					                    <c:choose>
					                        <c:when test="${next_id != null}">
					                            <a href="page?view=news_detail&news_id=${next_id}">
					                                Bài viết tiếp theo <i class="fa fa-angle-right"></i>
					                            </a>
					                        </c:when>
					                        <c:otherwise>
					                            
					                        </c:otherwise>
					                    </c:choose>
					                </h6>
					            </div>
					        </div>
					    </div>
					</div>
                </div>
            </div>
            <div class="col-lg-4 col-md-4">
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

const detail = document.getElementById("content");
detail.innerHTML = `${news.news_content}`;
</script>