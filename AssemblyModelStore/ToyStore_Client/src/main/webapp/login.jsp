<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="page?view=home"><i class="fa fa-home"></i> Trang chủ</a>
                    <span>Đăng nhập</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container d-flex justify-content-center align-items-center py-5">
    <div class="card form-card shadow">
      <div class="form-toggle d-flex justify-content-center mb-4">
        <button id="loginToggle" class="toggle-btn active"><i class="fa-solid fa-right-to-bracket"></i> Đăng nhập</button>
        <button id="registerToggle" class="toggle-btn"><i class="fa-solid fa-user-plus"></i> Đăng ký</button>
      </div>
      <div class="form-wrapper">
        <form action="login" method="post" id="loginForm" class="form-con active">
          <div class="mb-3">
            <label for="loginEmail" class="form-label" style="font-weight: 600;">Email</label>
            <input type="email" class="form-control" id="loginEmail" name="loginEmail" required/>
          </div>
          <div class="mb-3">
            <label for="loginPassword" class="form-label" style="font-weight: 600;">Mật khẩu</label>
		      <input type="password" class="form-control" id="loginPassword" name="loginPassword" required>
          </div>
          <div class="d-flex justify-content-between align-items-center mb-3">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="rememberMe" />
              <label class="form-check-label" for="rememberMe">Ghi nhớ</label>
            </div>
            <a href="#" class="text-decoration-none text-danger">Quên mật khẩu?</a>
          </div>
          <button type="submit" class="btn btn-danger w-100" style="font-weight: bold;">Đăng nhập</button>
        </form>

        <form action="register" method="post" id="registerForm" class="form-con" onsubmit="return validatePasswords()">
          <div class="row g-3">
            <div class="col-md-6">
              <label for="surname" class="form-label" style="font-weight: 600;">Họ</label>
              <input type="text" class="form-control" id="surname" name="surname" required/>
            </div>
            <div class="col-md-6">
              <label for="name" class="form-label" style="font-weight: 600;">Tên</label>
              <input type="text" class="form-control" id="name" name="name" required/>
            </div>
            <div class="col-md-6">
              <label for="phone" class="form-label" style="font-weight: 600;">Số điện thoại</label>
              <input type="text" class="form-control" id="phone" name="phone" required/>
            </div>
            <div class="col-md-6">
              <label for="registerEmail" class="form-label" style="font-weight: 600;">Email</label>
              <input type="email" class="form-control" id="registerEmail" name="registerEmail" required/>
            </div>
            <div class="col-md-6">
              <label for="registerPassword" class="form-label" style="font-weight: 600;">Mật khẩu</label>
			  <input type="password" class="form-control" id="registerPassword" name="registerPassword" required>
            </div>
            <div class="col-md-6">
              <label for="confirmPassword" class="form-label" style="font-weight: 600;">Xác nhận mật khẩu</label>
		      <input type="password" class="form-control" id="confirmPassword"required>
		      <small id="passwordError" class="text-danger d-block" style="font-size: 0.875rem;"></small>
            </div>
          </div>
          <div class="d-flex justify-content-center">
       			<button type="submit" class="btn btn-danger mt-3"  style="font-weight: bold; width:300px;">Đăng ký</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  
  <script>
    const loginToggle = document.getElementById('loginToggle');
    const registerToggle = document.getElementById('registerToggle');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const formCard = document.querySelector('.form-card');
    
    loginToggle.addEventListener('click', () => {
      loginToggle.classList.add('active');
      registerToggle.classList.remove('active');
      loginForm.classList.add('active');
      registerForm.classList.remove('active');
      formCard.classList.remove('register-mode');
    });
    
    registerToggle.addEventListener('click', () => {
      registerToggle.classList.add('active');
      loginToggle.classList.remove('active');
      registerForm.classList.add('active');
      loginForm.classList.remove('active');
      formCard.classList.add('register-mode');
    });
  </script>
  <script>
  function validatePasswords() {
    var pass = document.getElementById("registerPassword").value;
    var confirm = document.getElementById("confirmPassword").value;
    var error = document.getElementById("passwordError");

    if (pass !== confirm) {
   	  error.innerHTML = '<i class="bi bi-shield-exclamation"></i> <span>Mật khẩu xác nhận không khớp!</span>';
   	  return false;
   	}

    error.textContent = "";
    return true;
  }
</script>