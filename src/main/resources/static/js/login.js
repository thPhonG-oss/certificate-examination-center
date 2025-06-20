document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  const usernameInput = form.querySelector('input[type="text"]');
  const passwordInput = form.querySelector('input[type="password"]');

  // Tạo div lỗi nếu chưa có
  let errorDiv = form.querySelector(".error-message");
  if (!errorDiv) {
    errorDiv = document.createElement("div");
    errorDiv.className = "error-message";
    form.insertBefore(errorDiv, form.querySelector("button"));
  }

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    // Reset trạng thái lỗi
    errorDiv.textContent = "";
    usernameInput.classList.remove("error");
    passwordInput.classList.remove("error");

    // Kiểm tra không để trống
    if (!username || !password) {
      errorDiv.textContent = "Không được để trống tên đăng nhập hoặc mật khẩu.";
      if (!username) usernameInput.classList.add("error");
      if (!password) passwordInput.classList.add("error");
      return;
    }

    // Kiểm tra định dạng username: chỉ chữ và số
    const usernameRegex = /^[a-zA-Z0-9]+$/;
    if (!usernameRegex.test(username)) {
      errorDiv.textContent = "Tên đăng nhập không hợp lệ !!!";
      usernameInput.classList.add("error");
      return;
    }

    // Nếu hợp lệ (giả lập)
    errorDiv.textContent = "";
    console.log("Đăng nhập thành công!");
  });
});
