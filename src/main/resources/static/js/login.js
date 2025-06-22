document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  const usernameInput = form.querySelector('input[type="text"]');
  const passwordInput = form.querySelector('input[type="password"]');

  // Tạo div hiển thị lỗi nếu chưa có
  let errorDiv = form.querySelector(".error-message");
  if (!errorDiv) {
    errorDiv = document.createElement("div");
    errorDiv.className = "error-message";
    form.insertBefore(errorDiv, form.querySelector("button"));
  }

  form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    // Reset lỗi
    errorDiv.textContent = "";
    usernameInput.classList.remove("error");
    passwordInput.classList.remove("error");

    // Kiểm tra không để trống
    if (!username || !password) {
      errorDiv.textContent = "Vui lòng nhập đầy đủ thông tin.";
      if (!username) usernameInput.classList.add("error");
      if (!password) passwordInput.classList.add("error");
      return;
    }

    // Kiểm tra định dạng Gmail
    const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
    if (!gmailRegex.test(username)) {
      errorDiv.textContent = "Tên đăng nhập phải là Gmail hợp lệ.";
      usernameInput.classList.add("error");
      return;
    }

    // Kiểm tra mật khẩu: 8–20 ký tự, không có ký tự đặc biệt
    const passwordRegex = /^[A-Za-z0-9]{8,20}$/;
    if (!passwordRegex.test(password)) {
      errorDiv.textContent = "Mật khẩu 8–20 ký tự, không có ký tự đặc biệt.";
      passwordInput.classList.add("error");
      return;
    }

    // Gọi API đăng nhập
    try {
      const response = await fetch("http://localhost:8081/api/verifi-login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        errorDiv.textContent = errorData.message || "Đăng nhập thất bại.";
        return;
      }

      const data = await response.json();
      localStorage.setItem("user", JSON.stringify(data));
      window.location.href = "/home.html";
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      errorDiv.textContent = "Không thể kết nối đến máy chủ.";
    }
  });

  // Xoá lỗi khi người dùng nhập lại
  [usernameInput, passwordInput].forEach((input) =>
    input.addEventListener("input", () => {
      input.classList.remove("error");
      errorDiv.textContent = "";
    })
  );
});
