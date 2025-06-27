document.addEventListener("DOMContentLoaded", function () {
  const resultSection = document.querySelector(".result-section");
  const resultBody = document.getElementById("resultBody");
  const searchBtn = document.getElementById("search-btn");
  const searchInput = document.getElementById("searchInput");

  // Gọi API ngay khi trang load
  fetchResults();

  // Gọi API GET tất cả kết quả
  async function fetchResults() {
    try {
      const response = await fetch("http://localhost:8081/api/results");
      const data = await response.json();
      renderTable(data);
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      resultBody.innerHTML = `<tr><td colspan="5">Không thể tải dữ liệu</td></tr>`;
    }
  }

  // Gọi API tìm kiếm theo ID thí sinh
  async function searchById(id) {
    try {
      const response = await fetch(
        `http://localhost:8081/api/results/search?idThiSinh=${id}`
      );
      const data = await response.json();
      renderTable(data);
    } catch (error) {
      console.error("Lỗi khi tìm kiếm:", error);
      resultBody.innerHTML = `<tr><td colspan="5">Không tìm thấy dữ liệu</td></tr>`;
    }
  }

  // Hiển thị dữ liệu ra bảng
  function renderTable(data) {
    resultBody.innerHTML = "";

    if (!data || data.length === 0) {
      resultBody.innerHTML = `<tr><td colspan="5">Không có dữ liệu</td></tr>`;
      return;
    }

    data.forEach((item, index) => {
      const row = `
        <tr>
          <td>${String(index + 1).padStart(3, "0")}</td>
          <td>${item.idThiSinh}</td>
          <td>${item.tenChungChi}</td>
          <td>${item.diem}</td>
          <td><button class="detail-btn" data-id="${
            item.idKetQua
          }">Chi tiết</button></td>
        </tr>
      `;
      resultBody.innerHTML += row;
    });
  }

  // Sự kiện nút tìm kiếm
  searchBtn.addEventListener("click", () => {
    const id = searchInput.value.trim();
    if (id) {
      searchById(id);
    } else {
      fetchResults(); // Nếu không nhập thì hiện tất cả
    }
  });

  // Sự kiện Enter trong ô input
  searchInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      searchBtn.click();
    }
  });

  // Sự kiện click vào nút Chi tiết
  resultBody.addEventListener("click", (e) => {
    if (e.target.classList.contains("detail-btn")) {
      const idKetQua = e.target.getAttribute("data-id");
      window.location.href = `http://localhost:8081/results/detail?idKetQua=${idKetQua}`;
    }
  });
});
