document.addEventListener("DOMContentLoaded", function () {
  // ====== XỬ LÝ ĐỒNG HỒ THỜI GIAN ======
  function updateClock() {
    const clockElement = document.getElementById("clock");
    if (!clockElement) return;

    const now = new Date();
    const days = [
      "Chủ nhật",
      "Thứ 2",
      "Thứ 3",
      "Thứ 4",
      "Thứ 5",
      "Thứ 6",
      "Thứ 7",
    ];
    const dayName = days[now.getDay()];
    const timeString = now.toLocaleTimeString("vi-VN");

    clockElement.textContent = `${dayName} - ${timeString}`;
  }

  setInterval(updateClock, 1000);
  updateClock(); // Gọi ngay khi tải trang

  // ====== XỬ LÝ ĐĂNG XUẤT ======
  const logoutBtn = document.querySelector(".logout");
  if (logoutBtn) {
    logoutBtn.addEventListener("click", function (e) {
      e.preventDefault();

      if (!confirm("Bạn có chắc chắn muốn đăng xuất?")) return;

      fetch("/api/auth/sign-out", {
        method: "POST",
        credentials: "include", // Gửi cookie JWT
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (!response.ok) throw new Error("Lỗi kết nối máy chủ");
          return response.json();
        })
        .then((data) => {
          if (data.status === "SUCCESS") {
            alert("Đăng xuất thành công!");
            window.location.href = "/login";
          } else {
            alert("Đăng xuất thất bại: " + data.message);
          }
        })
        .catch((err) => {
          console.error("Lỗi đăng xuất:", err);
          alert("Không thể đăng xuất. Vui lòng thử lại.");
        });
    });
  }
});
