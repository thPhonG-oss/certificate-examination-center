const apiURL = "http://localhost:8081/api/list-registration-forms";

const getStatusClass = (status) => {
  switch (status) {
    case 0:
      return "yellow";
    case 1:
      return "green";
    case 2:
      return "red";
    default:
      return "";
  }
};

function formatSchedule(datetimeStr) {
  const date = new Date(datetimeStr);
  const options = { hour: "2-digit", minute: "2-digit" };
  const time = date.toLocaleTimeString("vi-VN", options);
  const dateStr = date.toLocaleDateString("vi-VN");
  return `${time} - ${dateStr}`;
}

async function loadRegistrationForms() {
  try {
    const response = await fetch(apiURL);
    const data = await response.json();
    console.log("Dữ liệu đăng ký:", data);
    const tbody = document.getElementById("registration-body");
    tbody.innerHTML = "";
    data.forEach((item) => {
      const row = document.createElement("tr");
      const statusClass = getStatusClass(item.status);
      const scheduleFormatted = formatSchedule(item.examSchedule);
      const buttonDisabled = item.status === 1 ? "" : "disabled";
      const formId = item.registrationFormId;
      row.innerHTML = `
        <td>${formId}</td>
        <td><span class="dot ${statusClass}"></span>${item.customerName}</td>
        <td>${item.registerDate}</td>
        <td>${item.extensionCount}</td>
        <td>${item.candidateName}</td>
        <td>${scheduleFormatted}</td>
        <td>${item.certificateName}</td>
        <td>
          <button class="update-btn"
                      data-registration-id="${formId}"
                      data-certificate-id="${item.certificateId}"
                      ${buttonDisabled}>
            Cập nhật
          </button>
        </td>
      `;
      tbody.appendChild(row);
    });
    // ⚠ Gắn lại sự kiện sau khi append xong
    document.querySelectorAll(".update-btn").forEach((button) => {
      button.addEventListener("click", function () {
        const registrationId = this.dataset.registrationId;
        if (!registrationId) return alert("Không có ID");
        window.location.href = `/detail-registration-form?formId=${registrationId}&certificateId=${this.dataset.certificateId}`;
      });
    });
  } catch (error) {
    console.error("Lỗi khi tải dữ liệu đăng ký:", error);
  }
}

// Gọi sau khi DOM sẵn sàng
window.addEventListener("DOMContentLoaded", loadRegistrationForms);

/* New code for modal functionality */
const customerTypeModal = document.getElementById("customerTypeModal");
const btnCreate = document.querySelector(".btn-create");
const closeButton = document.querySelector(".close-button");
const individualBtn = document.getElementById("individualBtn");
const organizationBtn = document.getElementById("organizationBtn");

// When the user clicks the "Lập phiếu đăng ký" button, open the modal
btnCreate.addEventListener("click", (event) => {
  event.preventDefault(); // Prevent default navigation
  customerTypeModal.style.display = "flex"; // Use flex to center content
});

// When the user clicks on <span> (x), close the modal
closeButton.addEventListener("click", () => {
  customerTypeModal.style.display = "none";
});

// When the user clicks anywhere outside of the modal content, close it
window.addEventListener("click", (event) => {
  if (event.target == customerTypeModal) {
    customerTypeModal.style.display = "none";
  }
});

// Handle "Cá nhân" button click
individualBtn.addEventListener("click", () => {
  window.location.href = "/registrations/individual/form";
  customerTypeModal.style.display = "none";
});

// Handle "Đơn vị" button click
organizationBtn.addEventListener("click", () => {
  alert("Chức năng này hiện chưa được triển khai.");
  customerTypeModal.style.display = "none";
});
