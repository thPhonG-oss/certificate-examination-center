function updateClock() {
  const clockElement = document.getElementById("clock");
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

// Cập nhật mỗi giây
setInterval(updateClock, 1000);
updateClock(); // gọi ngay lần đầu
