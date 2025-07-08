// Lấy formId từ URL (?formId=...)
function getFormIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("formId");
}

function getCertificateIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("certificateId");
}

console.log("formId:", getFormIdFromURL());
console.log("certificateId:", getCertificateIdFromURL());

// Biến global để lưu lịch thi hiện tại của thí sinh
let currentExamSchedule = null;

// Gọi API và gán dữ liệu vào phần thông tin khách hàng
async function loadCustomerInfo() {
  const formId = getFormIdFromURL();
  if (!formId) {
    alert("Thiếu mã phiếu đăng ký (formId) trên URL.");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8081/customers/by-registration/${formId}`
    );
    if (!response.ok) throw new Error("Không tìm thấy dữ liệu khách hàng");

    const customer = await response.json();
    console.log("Dữ liệu khách hàng:", customer);

    document.getElementById("registration-id").textContent =
      formId ?? "Chưa rõ";
    document.getElementById("name").textContent = customer.name ?? "Chưa rõ";
    document.getElementById("customer-type").textContent = "Cá nhân";
    document.getElementById("citizen_id").textContent =
      customer.citizen_id ?? "Chưa rõ";

    const dob = customer.dob;
    document.getElementById("dob").textContent = dob
      ? new Date(dob).toLocaleDateString("vi-VN")
      : "Chưa rõ";
  } catch (error) {
    console.error("Lỗi khi tải dữ liệu khách hàng:", error);
    alert("Không thể tải thông tin khách hàng.");
  }
}

// Gọi API và gán dữ liệu vào phần thông tin thí sinh
async function loadCandidateInfo() {
  const formId = getFormIdFromURL();
  const certificateId = getCertificateIdFromURL();

  if (!formId || !certificateId) {
    alert("Thiếu formId hoặc certificateId trên URL.");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8081/candidate/detail-candidate-by-registration?registrationId=${formId}&certificateId=${certificateId}`
    );
    if (!response.ok) throw new Error("Không tìm thấy dữ liệu thí sinh");

    const candidate = await response.json();
    console.log("Dữ liệu thí sinh:", candidate);

    document.getElementById("candidate-id").textContent =
      candidate.candidate_id ?? "Chưa rõ";
    document.getElementById("certificate-id").textContent =
      candidate.certificate_id ?? "Chưa rõ";
    document.getElementById("candidate-name").textContent =
      candidate.candidate_name ?? "Chưa rõ";

    const dob = candidate.dob;
    document.getElementById("candidate-dob").textContent = dob
      ? new Date(dob).toLocaleDateString("vi-VN")
      : "Chưa rõ";

    document.getElementById("candidate-cccd").textContent =
      candidate.citizen_id ?? "Chưa rõ";

    const examSchedule = candidate.exam_schedule;
    document.getElementById("exam-schedule").textContent =
      examSchedule || "Chưa có lịch thi";

    // Lưu lịch thi hiện tại để so sánh sau này
    currentExamSchedule = examSchedule;
    console.log("Lịch thi hiện tại:", currentExamSchedule);
  } catch (error) {
    console.error("Lỗi khi tải dữ liệu thí sinh:", error);
    alert("Không thể tải thông tin thí sinh.");
  }
}

// Tải lịch thi để chọn
async function loadExamScheduleOptions() {
  const formId = getFormIdFromURL();
  const certificateId = getCertificateIdFromURL();

  if (!formId || !certificateId) {
    alert("Thiếu formId hoặc certificateId trong URL.");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8081/by-registration-certificate/${certificateId}`
    );
    if (!response.ok) throw new Error("Không tải được danh sách lịch thi");

    const data = await response.json();
    const select = document.getElementById("list-date");

    // Xóa các option cũ
    select.innerHTML = "";

    // Đổ dữ liệu mới
    data.forEach((item) => {
      const option = document.createElement("option");
      option.value = item.id;

      // Format ngày và giờ
      const [year, month, day] = item.date.split("-");
      const formattedDate = `${day}/${month}/${year}`;
      const formattedTime = item.time.slice(0, 5);
      const optionText = `${formattedTime} - ${formattedDate}`;

      option.textContent = optionText;

      // Kiểm tra nếu option này trùng với lịch thi hiện tại
      if (currentExamSchedule && optionText === currentExamSchedule) {
        option.disabled = true;
        option.style.color = "#999";
        option.textContent += " (Lịch hiện tại)";
        console.log("Disabled option:", optionText);
      }

      select.appendChild(option);
    });

    // Thêm option mặc định nếu cần
    if (select.children.length > 0) {
      const defaultOption = document.createElement("option");
      defaultOption.value = "";
      defaultOption.textContent = "-- Chọn lịch thi mới --";
      defaultOption.selected = true;
      select.insertBefore(defaultOption, select.firstChild);
    }
  } catch (error) {
    console.error("Lỗi khi tải danh sách lịch thi:", error);
    alert("Không thể tải danh sách lịch thi.");
  }
}

// Gửi dữ liệu cập nhật khi nhấn nút xác nhận
async function updateScheduleInfo() {
  const formId = getFormIdFromURL();
  if (!formId) {
    alert("Thiếu formId trong URL.");
    return;
  }

  const minhChung = document.querySelector('input[name="minhChung"]:checked');
  const selectedSchedule = document.getElementById("list-date").value;

  if (!minhChung) {
    alert("Vui lòng chọn minh chứng.");
    return;
  }

  if (!selectedSchedule) {
    alert("Vui lòng chọn lịch thi mới.");
    return;
  }

  const payload = {
    formId: formId,
    scheduleId: selectedSchedule,
    evidenceCode: Number.parseInt(minhChung.value),
  };

  console.log(JSON.stringify(payload));

  try {
    const response = await fetch(
      "http://localhost:8081/registration-form/update",
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      }
    );

    if (!response.ok) throw new Error("Cập nhật thất bại");

    alert("Cập nhật thành công!");
    window.location.href = "http://localhost:8081/list-registration-forms";
  } catch (error) {
    console.error("Lỗi khi cập nhật lịch thi:", error);
    alert("Không thể cập nhật lịch thi.");
  }
}

// Khi trang đã sẵn sàng
document.addEventListener("DOMContentLoaded", async () => {
  // Load theo thứ tự: customer -> candidate -> exam schedules
  await loadCustomerInfo();
  await loadCandidateInfo();
  await loadExamScheduleOptions(); // Chạy sau khi đã có currentExamSchedule

  // Gắn sự kiện nút xác nhận
  const confirmBtn = document.querySelector(".confirm");
  confirmBtn.addEventListener("click", updateScheduleInfo);
});
