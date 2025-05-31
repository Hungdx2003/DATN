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