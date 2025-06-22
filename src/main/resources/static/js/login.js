document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  const usernameInput = form.querySelector('input[type="text"]');
  const passwordInput = form.querySelector('input[type="password"]');

  let errorDiv = form.querySelector(".error-message");
  if (!errorDiv) {
    errorDiv = document.createElement("div");
    errorDiv.className = "error-message";
    form.insertBefore(errorDiv, form.querySelector("button"));
  }

  form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = usernameInput.value.trim(); // Vì BE dùng `email`
    const password = passwordInput.value.trim();

    errorDiv.textContent = "";
    usernameInput.classList.remove("error");
    passwordInput.classList.remove("error");

    if (!email || !password) {
      errorDiv.textContent = "Vui lòng nhập đầy đủ thông tin.";
      if (!email) usernameInput.classList.add("error");
      if (!password) passwordInput.classList.add("error");
      return;
    }

    const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
    if (!gmailRegex.test(email)) {
      errorDiv.textContent = "Tên đăng nhập phải là Gmail hợp lệ.";
      usernameInput.classList.add("error");
      return;
    }

    const passwordRegex = /^.{8,20}$/;
    if (!passwordRegex.test(password)) {
      errorDiv.textContent = "Mật khẩu phải từ 8–20 ký tự.";
      passwordInput.classList.add("error");
      return;
    }

    try {
      const response = await fetch("http://localhost:8081/api/auth/sign-in", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();

      if (!response.ok || data.status !== "SUCCESS") {
        errorDiv.textContent = data.message || "Đăng nhập thất bại.";
        return;
      }

      // ✅ Lưu token và thông tin user vào localStorage
      localStorage.setItem("token", data.response.token);
      localStorage.setItem("user", JSON.stringify(data.response));

      // ✅ Điều hướng sau đăng nhập
      window.location.href = "/home.html";
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      errorDiv.textContent = "Không thể kết nối đến máy chủ.";
    }
  });

  [usernameInput, passwordInput].forEach((input) =>
    input.addEventListener("input", () => {
      input.classList.remove("error");
      errorDiv.textContent = "";
    })
  );
});
