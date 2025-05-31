<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pagetitle">
      <h1>Tổng quan</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
          <li class="breadcrumb-item active">Tổng quan</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
        <div class="col-xxl-4 col-md-6">
          <div class="card info-card customers-card">
          	<div class="filter">
           		<a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
               	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                   <li class="dropdown-header text-start">
                     <h6><i class="bx bx-filter-alt"></i> Lọc</h6>
                   </li>

                   	<li><a class="dropdown-item" href="#" onclick="changeData('daily', ${dailySold}, 'soldTitle', 'timePeriod_sold', 'soldValue')">Hôm nay</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeData('monthly', ${monthlySold}, 'soldTitle', 'timePeriod_sold', 'soldValue')">Tháng này</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeData('yearly', ${yearlySold}, 'soldTitle', 'timePeriod_sold', 'soldValue')">Năm nay</a></li>
                 </ul>
            </div>
            <div class="card-body">
              <h5 class="card-title" id="soldTitle">Số lượng bán <span id="timePeriod_sold">| Hôm nay</span></h5>

              <div class="d-flex align-items-center">
                <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                  <i class="bi bi-cart"></i>
                </div>
                <div class="ps-3">
                  <h6><span id="soldValue"><c:out value="${dailySold}" /></span> sản phẩm</h6>

                </div>
              </div>
            </div>
          </div>
        </div>
        
		<div class="col-xxl-4 col-md-6">

          <div class="card info-card order-card">

            <div class="card-body">
            	<div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6><i class="bx bx-filter-alt"></i> Lọc</h6>
                    </li>

                    <li><a class="dropdown-item" href="#" onclick="changeData('daily', ${dailyOrder}, 'orderTitle', 'timePeriod_od', 'orderValue')">Hôm nay</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeData('monthly', ${monthlyOrder}, 'orderTitle', 'timePeriod_od', 'orderValue')">Tháng này</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeData('yearly', ${yearlyOrder}, 'orderTitle', 'timePeriod_od', 'orderValue')">Năm nay</a></li>
                  </ul>
                </div>
               <h5 class="card-title" id="orderTitle">Đơn hàng <span id="timePeriod_od">| Hôm nay</span></h5>

               <div class="d-flex align-items-center">
                 <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                   <i class="bi bi-file-post"></i>
                 </div>
                 <div class="ps-3">
                   <h6><span id="orderValue"><c:out value="${dailyOrder}" /></span> đơn</h6>
                 </div>
               </div>
             </div>
           </div>
        </div>
        
        <div class="col-xxl-4 col-md-6">
		  <div class="card info-card sale-card">
		    <div class="filter">
		      <a class="icon" href="#" data-bs-toggle="dropdown">
		        <i class="bi bi-three-dots"></i>
		      </a>
		      <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
				    <li class="dropdown-header text-start">
				        <h6><i class="bx bx-filter-alt"></i> Lọc</h6>
				    </li>
				    <li><a class="dropdown-item" href="#" onclick="changeRevenue('daily', ${dailyRevenue}, 'revenueTitle', 'timePeriod_reve', 'revenueValue')">Hôm nay</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeRevenue('monthly', ${monthlyRevenue}, 'revenueTitle', 'timePeriod_reve', 'revenueValue')">Tháng này</a></li>
				    <li><a class="dropdown-item" href="#" onclick="changeRevenue('yearly', ${yearlyRevenue}, 'revenueTitle', 'timePeriod_reve', 'revenueValue')">Năm nay</a></li>
				</ul>
		    </div>
		    <div class="card-body">
		      <h5 class="card-title" id="revenueTitle">Doanh thu <span id="timePeriod_reve">| Hôm nay</span></h5>
		
		      <div class="d-flex align-items-center">
		        <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
		          <i class="bi bi-cash-coin"></i>
		        </div>
		        <div class="ps-3">
		          <h6 id="revenueValue" class="formatted-value"><c:out value="${dailyRevenue}" /></h6>
		          
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
      </div>
      
      <div class="row">
        <!-- Top Selling -->
        <div class="col-6">
          <div class="card top-selling overflow-auto">

            <div class="filter">
              <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li class="dropdown-header text-start">
                  <h6><i class="bx bx-filter-alt"></i> Lọc</h6>
                </li>

                <li><a class="dropdown-item" href="#" onclick="filterData('daily')">Hôm nay</a></li>
                <li><a class="dropdown-item" href="#" onclick="filterData('monthly')">Tháng này</a></li>
                <li><a class="dropdown-item" href="#" onclick="filterData('yearly')">Năm nay</a></li>
              </ul>
            </div>

            <div class="card-body pb-0">
              <h5 class="card-title">Sản phẩm bán chạy <span id="filterLabel">| Hôm nay</span></h5>

              <table class="table table-borderless">
                <thead>
                  <tr>
                    <th scope="col">Ảnh</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Bán ra</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- Dữ liệu sản phẩm bán chạy hôm nay -->
                    <c:forEach var="product" items="${dailyBestSold}">
                        <tr class="product-row" data-filter="daily">
                            <th scope="row"><a href="#"><img src="/product_images/${product.imageUrl}" alt=""></a></th>
                            <td><a href="#" class="text-primary fw-bold">${product.productName}</a></td>
                            <td class="formatted-value">
							    <c:choose>
							        <c:when test="${product.productSalePrice != 0}">
							             ${product.productSalePrice}
							        </c:when>
							        <c:otherwise>
							            ${product.productPrice}
							        </c:otherwise>
							    </c:choose>
							</td>
                            <td class="fw-bold">${product.productSold}</td>
                        </tr>
                    </c:forEach>

                    <!-- Dữ liệu sản phẩm bán chạy trong tháng -->
                    <c:forEach var="product" items="${monthlyBestSold}">
                        <tr class="product-row" data-filter="monthly">
                            <th scope="row"><a href="#"><img src="/product_images/${product.imageUrl}" alt=""></a></th>
                            <td><a href="#" class="text-primary fw-bold">${product.productName}</a></td>
                            <td class="formatted-value">
							    <c:choose>
							        <c:when test="${product.productSalePrice != 0}">
							             ${product.productSalePrice}
							        </c:when>
							        <c:otherwise>
							            ${product.productPrice}
							        </c:otherwise>
							    </c:choose>
							</td>
                            <td class="fw-bold">${product.productSold}</td>
                        </tr>
                    </c:forEach>

                    <!-- Dữ liệu sản phẩm bán chạy trong năm -->
                    <c:forEach var="product" items="${yearlyBestSold}">
                        <tr class="product-row" data-filter="yearly">
                            <th scope="row"><a href="#"><img src="/product_images/${product.imageUrl}" alt=""></a></th>
                            <td><a href="#" class="text-primary fw-bold">${product.productName}</a></td>
                            <td class="formatted-value">
							    <c:choose>
							        <c:when test="${product.productSalePrice != 0}">
							             ${product.productSalePrice}
							        </c:when>
							        <c:otherwise>
							            ${product.productPrice}
							        </c:otherwise>
							    </c:choose>
							</td>
                            <td class="fw-bold text-center">${product.productSold}</td>
                        </tr>
                    </c:forEach>
                </tbody>
              </table>

            </div>

          </div>
        </div>
        <!-- End Top Selling -->
		<div class="col-6">
          <div class="card top-selling overflow-auto">
            <div class="card-body pb-0">
              <h5 class="card-title">Sản phẩm chậm</h5>

              <table class="table table-borderless">
                <thead>
                  <tr>
                    <th scope="col">Ảnh</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Bán ra</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${slowSell}">
                        <tr class="product">
                            <th scope="row"><a href="#"><img src="/product_images/${product.imageUrl}" alt=""></a></th>
                            <td><a href="#" class="text-primary fw-bold">${product.productName}</a></td>
                            <td class="formatted-value">
							    <c:choose>
							        <c:when test="${product.productSalePrice != 0}">
							             ${product.productSalePrice}
							        </c:when>
							        <c:otherwise>
							            ${product.productPrice}
							        </c:otherwise>
							    </c:choose>
							</td>
                            <td class="fw-bold">${product.productSold}</td>
                        </tr>
                    </c:forEach>
                </tbody>
              </table>

            </div>

          </div>
        </div>
      </div>
      <div class="row">
      	<div class="col-6">
          <div class="card top-selling overflow-auto">

            <div class="card-body pb-0">
              <h5 class="card-title">Sản phẩm tồn kho ít</h5>

              <table class="table table-borderless datatable">
                <thead>
                  <tr>
                  	<th scope="col">ID</th>
                    <th scope="col">Ảnh</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Còn</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${lowQuantity}">
                        <tr>
                            <td class="pro-id">${product.productId}</td>
                            <th scope="row"><a href="#"><img src="/product_images/${product.imageUrl}" alt=""></a></th>
                            <td><a href="#" class="text-primary fw-bold">${product.productName}</a></td>
                            <td class="formatted-value">
							    <c:choose>
							        <c:when test="${product.productSalePrice != 0}">
							             ${product.productSalePrice}
							        </c:when>
							        <c:otherwise>
							            ${product.productPrice}
							        </c:otherwise>
							    </c:choose>
							</td>
                            <td class="fw-bold text-center">${product.productQuantity}</td>
                        </tr>
                    </c:forEach>

                </tbody>
              </table>

            </div>

          </div>
        </div>
      </div>
    </section>
<script src="assets/js/functions.js"></script>
<script>
document.querySelectorAll('.formatted-value').forEach(function(td) {
  let value = td.textContent.trim();
  td.textContent = formatTd(value);
});
document.querySelectorAll(".pro-id").forEach(function (cell) {
  cell.textContent = formatId(cell.textContent.trim(),"SP-");
});
function changeRevenue(period, data, titleId, timePeriodId, valueId) {
    var title = document.getElementById(titleId);
    var timePeriod = document.getElementById(timePeriodId);
    var value = document.getElementById(valueId);

    var val = data === null ? 0 : data;

    if (period === 'daily') {
        timePeriod.textContent = "| Hôm nay";
    } else if (period === 'monthly') {
        timePeriod.textContent = "| Tháng này";
    } else if (period === 'yearly') {
        timePeriod.textContent = "| Năm nay";
    }

    value.innerHTML = formatTd(val);
}

function changeData(period, data, titleId, timePeriodId, valueId) {
    var title = document.getElementById(titleId);
    var timePeriod = document.getElementById(timePeriodId);
    var value = document.getElementById(valueId);

    var val = data === null ? 0 : data;

    if (period === 'daily') {
        timePeriod.textContent = "| Hôm nay";
    } else if (period === 'monthly') {
        timePeriod.textContent = "| Tháng này";
    } else if (period === 'yearly') {
        timePeriod.textContent = "| Năm nay";
    }

    value.innerHTML = val;
}

function filterData(filterType) {
    let filterLabel = filterType === 'daily' ? 'Hôm nay' : (filterType === 'monthly' ? 'Tháng này' : 'Năm nay');
    document.getElementById('filterLabel').innerText = "| "+filterLabel;

    let allRows = document.querySelectorAll('.product-row');
    allRows.forEach(row => {
        if (row.getAttribute('data-filter') === filterType) {
            row.style.display = 'table-row';
        } else {
            row.style.display = 'none';
        }
    });
}
window.addEventListener('DOMContentLoaded', () => {
    filterData('daily');
});
</script>