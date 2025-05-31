<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String notificationMessage = (String) session.getAttribute("notification_message");
    String notificationType = (String) session.getAttribute("notification_type"); // success | danger | warning
    boolean hasNotification = notificationMessage != null && notificationType != null;
    if (hasNotification) {
        session.removeAttribute("notification_message");
        session.removeAttribute("notification_type");
    }
%>

<% if (hasNotification) { %>
<!-- Modal -->
<div class="modal fade" id="notificationModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content shadow" style="max-width: 320px; margin: auto; text-align: center; border-radius: 1rem;">
      <div class="modal-body">
        <div style="font-size: 2.5rem;" class="mb-2 text-<%= notificationType %>">
          <i class="bi 
            <%= "success".equals(notificationType) ? "bi-check-circle-fill" :
                "danger".equals(notificationType) ? "bi-exclamation-circle-fill" :
                "warning".equals(notificationType) ? "bi-exclamation-triangle-fill" : "" %>">
          </i>
        </div>
        <h5 class="mb-1" style="font-size: 1.5rem;">
          <%= "success".equals(notificationType) ? "Thành công" :
              "danger".equals(notificationType) ? "Thất bại" :
              "warning".equals(notificationType) ? "Cảnh báo" : "" %>
        </h5>
        <p class="text-dark mb-2"><%= notificationMessage %></p>
        <% if ("success".equals(notificationType)) { %>
            <p class="text-muted mb-1">Chào mừng bạn quay lại!</p>
        <% } else if ("danger".equals(notificationType)) { %>
            <p class="text-muted mb-1">Bạn không có quyền đăng nhập</p>
        <% } else if ("warning".equals(notificationType)) { %>
            <p class="text-muted mb-1">Mật khẩu hoặc tên đăng nhập sai</p>
        <% } %>
        <button type="button" class="btn btn-outline-<%= notificationType %>" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>
<% } %>

<% if (hasNotification) { %>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const modal = new bootstrap.Modal(document.getElementById('notificationModal'));
    modal.show();

    setTimeout(() => {
      modal.hide();
    }, 4500);
  });
</script>
<% } %>
