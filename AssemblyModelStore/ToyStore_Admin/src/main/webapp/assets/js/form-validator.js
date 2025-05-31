function validateForm(formId, rules) {
  const form = document.getElementById(formId);
  let isValid = true;

  form.querySelectorAll(".text-danger").forEach(el => el.innerHTML = '');

  for (const [fieldName, rule] of Object.entries(rules)) {
    const input = form.querySelector(`[name="${fieldName}"]`);
    const errorEl = form.querySelector(`.error-${fieldName}`);
    const value = input.value.trim();

    if (rule.required && !value) {
      const message = rule.requiredMessage || 'Trường này là bắt buộc.';
      errorEl.innerHTML = `<i class="bi bi-exclamation-circle-fill me-1"></i> ${message}`;
      isValid = false;
      continue;
    }

    if (value) {
      if (rule.pattern && !rule.pattern.test(value)) {
        const message = rule.patternMessage || 'Giá trị không hợp lệ.';
        errorEl.innerHTML = `<i class="bi bi-exclamation-circle-fill me-1"></i> ${message}`;
        isValid = false;
        continue;
      }

      if (rule.minLength && value.length < rule.minLength) {
        const message = rule.minLengthMessage || `Phải có ít nhất ${rule.minLength} ký tự.`;
        errorEl.innerHTML = `<i class="bi bi-exclamation-circle-fill me-1"></i> ${message}`;
        isValid = false;
        continue;
      }

      if (rule.maxLength && value.length > rule.maxLength) {
        const message = rule.maxLengthMessage || `Tối đa ${rule.maxLength} ký tự.`;
        errorEl.innerHTML = `<i class="bi bi-exclamation-circle-fill me-1"></i> ${message}`;
        isValid = false;
        continue;
      }

      if (rule.custom && typeof rule.custom === 'function' && !rule.custom(value)) {
        const message = rule.customMessage || 'Giá trị không hợp lệ.';
        errorEl.innerHTML = `<i class="bi bi-exclamation-circle-fill me-1"></i> ${message}`;
        isValid = false;
        continue;
      }
    }
  }

  return isValid;
}
