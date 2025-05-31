<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng nhập - Gundam Store</title>
  <link href="assets/img/logo-icon.png" rel="icon">
  <link href="assets/img/logo-icon.png" rel="apple-touch-icon">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <style>
    body {
      background: url("assets/img/background.jpg") no-repeat center center fixed;
      background-size: contain;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      font-family: 'Segoe UI', sans-serif;
    }

    .login-glass {
      background: rgba(255, 255, 255, 0.1);
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);
      border-radius: 15px;
      padding: 30px;
      border: 1px solid rgba(255, 255, 255, 0.2);
      box-shadow: 0 0 30px rgba(0, 0, 0, 0.3);
      color: white;
      width: 100%;
      max-width: 400px;
    }

    .login-glass h5,
    .login-glass p,
    .login-glass label {
      color: #636363;
    }

    .btn-primary {
      background-color: #ff3c00;
      border-color: #ff3c00;
    }

    .btn-primary:hover {
      background-color: #e03200;
      border-color: #e03200;
    }

    .logo {
      font-size: 2rem;
      font-weight: bold;
      color: #636363;
      text-align: center;
      margin-top: 20px;
    }

    .logo span {
      color: #ff3c00;
    }
  </style>
</head>

<body>
<jsp:include page="notification-message.jsp" />
  <div class="login-glass">
	<div class="d-flex align-items-center justify-content-center mb-4">
	  <img src="assets/img/logo-web.png" alt="Gundam Store Logo" style="height: 100px;padding: 0;margin: 0;display: block; ">
	</div>
    <h5 class="text-center pb-2">Đăng nhập tài khoản</h5>
    <p class="text-center small">Nhập tên đăng nhập và mật khẩu để tiếp tục</p>

    <form action="login" method="post" class="row g-3 needs-validation" novalidate>
      <div class="col-12">
        <label for="yourUsername" class="form-label">Tên đăng nhập</label>
        <div class="input-group has-validation">
          <span class="input-group-text">@</span>
          <input type="text" name="username" class="form-control" id="username" required>
          <div class="invalid-feedback">Vui lòng nhập tên đăng nhập.</div>
        </div>
      </div>

      <div class="col-12">
        <label for="yourPassword" class="form-label">Mật khẩu</label>
        <input type="password" name="password" class="form-control" id="password" required>
        <div class="invalid-feedback">Vui lòng nhập mật khẩu!</div>
      </div>

      <div class="col-12">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
          <label class="form-check-label" for="rememberMe">Ghi nhớ tôi</label>
        </div>
      </div>

      <div class="col-12">
        <button class="btn btn-primary w-100" type="submit">Đăng nhập</button>
      </div>
    </form>
  </div>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script>
    (() => {
      'use strict'
      const forms = document.querySelectorAll('.needs-validation')
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
          form.classList.add('was-validated')
        }, false)
      })
    })()
  </script>

</body>

</html>
