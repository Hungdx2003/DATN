// Hàm format hiển thị tiền có dấu chấm ngăn cách và thêm " đ"
function formatTd(value) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
}

function formatVNNumber(amount) {
  return new Intl.NumberFormat('vi-VN').format(amount);
}
// Hàm format ô input khi nhập số tiền
function formatNumber(input) {
    const value = input.value.replace(/[^0-9]/g, '');
    if (value) {
        input.value = Number(value).toLocaleString('vi-VN');
    } else {
        input.value = '';
    }
}

// Hàm remove dấu chấm (.) trong ô input khi submit
function removeDot(input) {
  input.value = input.value.replace(/\./g, '');
}

// Xử lý remove dấu chấm trước khi submit form
function handleFormSubmit(formId) {
  const form = document.getElementById(formId);

  form.addEventListener("submit", function (e) {
    const inputs = form.querySelectorAll('.remove-dot');
    inputs.forEach(function (input) {
      removeDot(input);
    });
  });
}

// Format ngày tháng từ chuỗi ISO
function formatDate(isoString) {
    if (!isoString || isoString.trim() === "") return "";
    const date = new Date(isoString);
    return isNaN(date.getTime()) ? "" : date.toLocaleString("vi-VN");
}

function formatDateOnly(isoString) {
    if (!isoString || isoString.trim() === "") return "";
    const date = new Date(isoString);
    if (isNaN(date.getTime())) return "";
    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const year = date.getFullYear();
    return day+'/'+month+'/'+year;
  }

function formatId(id,startId) {
  id = parseInt(id);
  if (isNaN(id)) return "";
  let idStr = id.toString();
  if (idStr.length <= 5) {
    idStr = idStr.padStart(5, "0");
  }
  return startId + idStr;
}

function createPaginationAndSearch({
  data,
  tableBodySelector,
  paginationContainerSelector,
  searchBoxSelector,
  pageSize = 5,
  renderRow,
  onCheckboxChange = null,
}) {
  let currentPage = 1;
  let filteredData = [...data];
  const selectedIds = new Set();

  const tableBody = document.querySelector(tableBodySelector);
  const paginationContainer = document.querySelector(paginationContainerSelector);
  const searchBox = document.querySelector(searchBoxSelector);

  function renderTable() {
    const start = (currentPage - 1) * pageSize;
    const end = start + pageSize;
    const pageItems = filteredData.slice(start, end);
    tableBody.innerHTML = "";

    pageItems.forEach(item => {
      const row = renderRow(item, selectedIds, checkboxHandler);
      tableBody.appendChild(row);
    });
  }

  function renderPagination() {
    const totalPages = Math.ceil(filteredData.length / pageSize);
    paginationContainer.innerHTML = "";

    for (let i = 1; i <= totalPages; i++) {
      const btn = document.createElement("button");
      btn.textContent = i;
      btn.classList.add("btn", "btn-sm", "mx-1", i === currentPage ? "btn-primary" : "btn-outline-primary");
      btn.addEventListener("click", () => {
        currentPage = i;
        renderTable();
        renderPagination();
      });
      paginationContainer.appendChild(btn);
    }
  }

  function checkboxHandler(checkbox, itemId) {
    checkbox.checked = selectedIds.has(itemId);
    checkbox.addEventListener("change", () => {
      if (checkbox.checked) selectedIds.add(itemId);
      else selectedIds.delete(itemId);
      if (onCheckboxChange) onCheckboxChange(checkbox, itemId, selectedIds);
    });
  }

  searchBox.addEventListener("input", function () {
    const keyword = this.value.toLowerCase();
    filteredData = data.filter(item =>
	  item.name.toLowerCase().includes(keyword) ||
	  formatId(item.id, "SP-").toLowerCase().includes(keyword)
	);
    currentPage = 1;
    renderPagination();
    renderTable();
  });

  // Initial render
  renderPagination();
  renderTable();

  // Trả ra selectedIds để form có thể dùng khi submit
  return selectedIds;
}

