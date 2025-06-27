document.addEventListener("DOMContentLoaded", function () {
  // Đăng xuất
  const signOutLink = document.querySelector('a[href="/api/auth/sign-out"]');
  if (signOutLink) {
    signOutLink.addEventListener("click", function (e) {
      e.preventDefault();

      fetch("/api/auth/sign-out", {
        method: "POST",
        credentials: "include",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.status === "SUCCESS") {
            window.location.href = "/login";
          } else {
            alert("Đăng xuất thất bại: " + data.message);
          }
        })
        .catch((error) => {
          alert("Lỗi đăng xuất: " + error);
        });
    });
  }

  // Tải danh sách lịch thi khi trang load
  fetch("/schedulewithcertificate", {
    method: "GET",
    credentials: "include",
  })
    .then((response) => {
      if (!response.ok) {
        if (response.status === 401 || response.status === 403) {
          window.location.href =
            "/login?redirect=/registrations/individual/form";
        }
        throw new Error("Lỗi tải lịch thi");
      }
      return response.json();
    })
    .then((data) => populateScheduleTable(data))
    .catch((error) => {
      alert("Lỗi tải lịch thi: " + error.message);
    });

  function populateScheduleTable(schedules) {
    const tbody = document.querySelector("#scheduleTable tbody");
    tbody.innerHTML = "";

    schedules.forEach((schedule) => {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>
                    <input type="checkbox" class="form-check-input schedule-checkbox"
                           data-schedule='${JSON.stringify(schedule)}'>
                </td>
                <td>${schedule.certificateName}</td>
                <td>${formatDate(schedule.date)}</td>
                <td>${schedule.time}</td>
                <td>${schedule.currentParticipants}</td>
                <td>${schedule.maxParticipants}</td>
            `;
      tbody.appendChild(row);
    });
  }

  function formatDate(dateStr) {
    const date = new Date(dateStr);
    return date.toLocaleDateString("vi-VN");
  }

  // Xử lý submit form
  const form = document.getElementById("registrationForm");
  if (form) {
    form.addEventListener("submit", function (e) {
      e.preventDefault();
      console.log("Form submitted");

      const checkboxes = document.querySelectorAll(
        ".schedule-checkbox:checked"
      );
      const selectedSchedules = Array.from(checkboxes).map((cb) =>
        JSON.parse(cb.getAttribute("data-schedule"))
      );

      if (selectedSchedules.length === 0) {
        alert("Vui lòng chọn ít nhất một lịch thi");
        return;
      }

      const registrationData = {
        customer: {
          name: document.getElementById("customerName").value,
          organization: "",
          email: document.getElementById("customerEmail").value,
          phoneNumber: document.getElementById("customerPhone").value,
          address: document.getElementById("customerAddress").value,
          citizen_id: document.getElementById("customerCitizenId").value,
          customer_type: "INDIVIDUAL",
          dob: document.getElementById("customerDob").value,
        },
        candidate: {
          name: document.getElementById("candidateName").value,
          gender: document.getElementById("candidateGender").value,
          email: document.getElementById("candidateEmail").value,
          phoneNumber: document.getElementById("candidatePhone").value,
          dob: document.getElementById("candidateDob").value,
          address: document.getElementById("candidateAddress").value,
          citizen_id: document.getElementById("candidateCitizenId").value,
        },
        schedules: selectedSchedules,
      };

      console.log("Registration data:", registrationData);

      fetch("/registrations/individual/submit", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(registrationData),
      })
        .then((response) => {
          if (!response.ok) {
            if (response.status === 401 || response.status === 403) {
              window.location.href =
                "/login?redirect=/registrations/individual/form";
            }
            return response.text().then((text) => {
              throw new Error(text);
            });
          }
          return response.json();
        })
        .then((data) => {
          console.log("Success response:", data);
          if (data.status === "SUCCESS") {
            window.location.href = "/registrations/individual/success";
          } else {
            alert("Đăng ký thất bại: " + data.message);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Lỗi đăng ký: " + error.message);
        });
    });
  }
});
