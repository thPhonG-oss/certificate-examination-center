document.addEventListener("DOMContentLoaded", async () => {
  const detailSection = document.querySelector(".candidate-details");
  const fullName = document.getElementById("fullName");
  const candidateId = document.getElementById("candidateId");
  const email = document.getElementById("email");
  const phone = document.getElementById("phone");
  const certificateName = document.getElementById("certificateName");
  const examDate = document.getElementById("examDate");
  const score = document.getElementById("score");
  const resultDetails = document.getElementById("resultDetails");
  const received = document.getElementById("received");
  const updateBtn = document.getElementById("update-btn");

  // Lấy idKetQua từ URL
  const urlParams = new URLSearchParams(window.location.search);
  const idKetQua = urlParams.get("idKetQua");

  if (!idKetQua) {
    alert("Thiếu ID kết quả trong URL.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8081/api/results/detail?idKetQua=${idKetQua}`);
    if (!response.ok) throw new Error("Không lấy được dữ liệu");

    const data = await response.json();

    console.log("Dữ liệu chi tiết:", data);
    // Hiển thị dữ liệu
    fullName.value = data.name || "";
    candidateId.value = "TS" + String(data.id_ket_qua).padStart(3, "0");
    email.value = data.email || "";
    phone.value = data.phone || "";
    certificateName.value = data.ten_chung_chi || "";
    examDate.value = data.ngay_thi || "";
    score.value = data.diem || "";
    resultDetails.value = data.chitietketqua || "";
    received.checked = data.trang_thai_nhan === 1;


    updateBtn.dataset.id = data.id_ket_qua;

    // Hiện form
    detailSection.style.display = "block";
  } catch (err) {
    console.error("Lỗi khi tải chi tiết:", err);
    alert("Lỗi khi tải dữ liệu.");
  }




  // Cập nhật trạng thái nhận chứng chỉ
  updateBtn.addEventListener("click", async function () {
    const receivedCheckbox = document.getElementById("received");
    const trangThaiNhan = receivedCheckbox.checked;

    try {
      const res = await fetch(`http://localhost:8081/api/results/update-status?idKetQua=${idKetQua}&trangThaiNhan=${trangThaiNhan}`, {
        method: "PUT"
      });

      if (res.ok) {
        alert("✅ Cập nhật trạng thái thành công!");
      } else {
        alert("❌ Cập nhật thất bại.");
      }
    } catch (error) {
      console.error("Lỗi khi cập nhật trạng thái:", error);
      alert("Lỗi khi gửi yêu cầu cập nhật.");
    }
  });
});