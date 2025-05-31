<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
					<div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>Danh mục</h4>
                            </div>
                            <div class="categories__accordion">
							    <div class="accordion" id="accordionExample">
							        <c:forEach var="parent" items="${categories}">
							            <c:if test="${parent.parent_category_id == null || parent.parent_category_id == 0}">
							                <div class="category">
							                    <a href="page?view=category&cat_id=${parent.category_id}">${parent.category_name}</a>
							                </div>
							
							                <div class="accordion" id="accordionExample">
							                    <c:forEach var="child" items="${categories}">
							                        <c:if test="${child.parent_category_id == parent.category_id}">
							                            <c:set var="hasChild" value="false" />
							                            
							                            <c:forEach var="grandchild" items="${categories}">
							                                <c:if test="${grandchild.parent_category_id == child.category_id}">
							                                    <c:set var="hasChild" value="true" />
							                                </c:if>
							                            </c:forEach>
							
							                            <!-- Nếu có cháu, hiển thị dưới dạng accordion -->
							                            <c:if test="${hasChild}">
							                                <div class="card">
							                                    <div class="card-heading">
							                                        <a data-bs-toggle="collapse" data-bs-target="#collapse${child.category_id}">${child.category_name}</a>
							                                    </div>
							                                    <div id="collapse${child.category_id}" class="collapse" data-parent="#accordionExample">
							                                        <div class="card-body">
							                                            <ul>
							                                                <c:forEach var="grandchild" items="${categories}">
							                                                    <c:if test="${grandchild.parent_category_id == child.category_id}">
							                                                        <li><a href="page?view=category&cat_id=${grandchild.category_id}">${grandchild.category_name}</a></li>
							                                                    </c:if>
							                                                </c:forEach>
							                                                <li><a href="page?view=category&cat_id=${child.category_id}">Xem toàn bộ</a></li>
							                                            </ul>
							                                        </div>
							                                    </div>
							                                </div>
							                            </c:if>
							
							                            <!-- Nếu không có cháu, hiển thị bình thường -->
							                            <c:if test="${!hasChild}">						                                
						                                    <div class="category">
						                                        <a href="page?view=category&cat_id=${child.category_id}">${child.category_name}</a>
						                                    </div>
							                            </c:if>
							                        </c:if>
							                    </c:forEach>
							                </div>
							            </c:if>
							        </c:forEach>
							    </div>
							</div>
                        </div>
						<div class="sidebar__brand">
                            <div class="section-title">
                                <h4>Thương hiệu</h4>
                            </div>
                            <form action="page?view=brand" id="brandForm" method="get">
                            	<input type="hidden" name="view" value="brand">
                            	<div class="brand__list" style="max-height: 45vh; overflow-y: auto;">
                                <c:forEach var="b" items="${brand}">
                                	<label>
                                    	${b}
	                                    <input type="checkbox" name="brands" value="${b}"
                                    		<c:if test="${paramValues.brands != null && fn:contains(fn:join(paramValues.brands, ','), b)}">checked</c:if>>
	                                    <span class="checkmark"></span>
	                                </label>
                                </c:forEach>
                            </div>
                            </form>
                        </div>
                    </div>
                  
<script>
    const form = document.getElementById('brandForm');
    const checkboxes = form.querySelectorAll('input[type="checkbox"]');
    let timeout;

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => {
            clearTimeout(timeout);
            timeout = setTimeout(() => {
                form.submit();
            }, 1000);
        });
    });
</script>