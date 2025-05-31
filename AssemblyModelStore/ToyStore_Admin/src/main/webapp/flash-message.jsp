<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String flashMessage = (String) session.getAttribute("flash_message");
    String flashType = (String) session.getAttribute("flash_type");

    if (flashMessage != null) {
%>
    <div class="alert alert-<%= flashType %> alert-dismissible fade show d-flex align-items-center big-alert" role="alert" id="flashAlert">
        <i class="bi <%= "success".equals(flashType) ? "bi-check-circle-fill" :
		                "danger".equals(flashType) ? "bi-exclamation-circle-fill" :
		                "warning".equals(flashType) ? "bi-exclamation-triangle-fill" : "" %> me-3 icon-large"></i>
        <div><%= flashMessage %></div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
        session.removeAttribute("flash_message");
        session.removeAttribute("flash_type");
    }
%>

<style>
/* Vị trí và kiểu dáng to hơn */
#flashAlert.big-alert {
    position: fixed;
    top: 30px;
    right: 30px;
    z-index: 9999;
    min-width: 400px;
    padding: 20px 30px;
    font-size: 1.25rem; /* To hơn mặc định */
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
}

/* Icon to hơn */
#flashAlert .icon-large {
    font-size: 1.75rem;
}
</style>

<script>
// Tự động ẩn sau 4.5 giây
setTimeout(function() {
    var alert = document.getElementById("flashAlert");
    if (alert) {
        var bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
        bsAlert.close();
    }
}, 4500);
</script>
