$(document).ready(function () {
  // Đăng xuất
  $('a[href="/api/auth/sign-out"]').on("click", function (e) {
    e.preventDefault();
    $.ajax({
      url: "/api/auth/sign-out",
      method: "POST",
      xhrFields: { withCredentials: true },
      success: function (data) {
        if (data.status === "SUCCESS") {
          window.location.href = "/login";
        } else {
          alert("Đăng xuất thất bại: " + data.message);
        }
      },
      error: function (xhr, status, error) {
        alert("Lỗi đăng xuất: " + error);
      }
    });
  });

  // Tải danh sách lịch thi
  $.ajax({
    url: "/schedulewithcertificate",
    method: "GET",
    xhrFields: { withCredentials: true },
    success: function (data) {
      populateScheduleTable(data);
    },
    error: function (xhr, status, error) {
      if (xhr.status === 401 || xhr.status === 403) {
        window.location.href = "/login?redirect=/registrations/individual/form";
      } else {
        alert("Lỗi tải lịch thi: " + error);
      }
    }
  });

  function populateScheduleTable(schedules) {
    const $tbody = $("#scheduleTable tbody");
    $tbody.empty();
    $.each(schedules, function (index, schedule) {
      const row = `
                <tr>
                    <td data-label="Chọn">
                        <input type="checkbox" class="form-check-input schedule-checkbox"
                               data-schedule='${JSON.stringify(schedule)}'>
                    </td>
                    <td data-label="Chứng chỉ">${schedule.certificateName}</td>
                    <td data-label="Ngày thi">${formatDate(schedule.date)}</td>
                    <td data-label="Giờ thi">${schedule.time}</td>
                    <td data-label="Số lượng ĐK">${schedule.currentParticipants}</td>
                    <td data-label="Số lượng tối đa">${schedule.maxParticipants}</td>
                </tr>`;
      $tbody.append(row);
    });

    // Giới hạn chọn một lịch thi duy nhất
    $(".schedule-checkbox").on("change", function () {
      if ($(this).is(":checked")) {
        // Bỏ chọn tất cả các checkbox khác
        $(".schedule-checkbox").not(this).prop("checked", false);
      }
    });
  }

  function formatDate(dateStr) {
    const date = new Date(dateStr);
    return date.toLocaleDateString("vi-VN");
  }

  // Xem trước ảnh
  $("#candidateImage").on("change", function () {
    const file = this.files[0];
    console.log("Selected file:", file);
    const $previewImage = $("#imagePreview");
    const $noImageText = $("#noImageText");

    if (file) {
      if (!file.type.startsWith("image/")) {
        alert("Vui lòng chọn file ảnh!");
        this.value = "";
        return;
      }
      const reader = new FileReader();
      reader.onload = function (e) {
        $previewImage.attr("src", e.target.result).removeClass("d-none");
        $noImageText.addClass("d-none");
      };
      reader.readAsDataURL(file);
    } else {
      $previewImage.attr("src", "#").addClass("d-none");
      $noImageText.removeClass("d-none");
    }
  });

  // Submit form
  $("#registrationForm").on("submit", function (e) {
    e.preventDefault();
    console.log("Form submitted");

    const $checkboxes = $(".schedule-checkbox:checked");
    if ($checkboxes.length === 0) {
      alert("Vui lòng chọn một lịch thi");
      return;
    }

    const selectedSchedule = JSON.parse($checkboxes.attr("data-schedule"));

    const fileInput = $("#candidateImage")[0];
    const formData = new FormData();
    let imageUrl = "";

    if (fileInput.files[0]) {
      formData.append("file", fileInput.files[0]);
      $.ajax({
        url: "/api/image/upload",
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        xhrFields: { withCredentials: true },
        success: function (response) {
          console.log("Upload response:", response);
          if (response.status === "SUCCESS") {
            imageUrl = response.response;
            submitRegistration(imageUrl);
          } else {
            alert("Lỗi upload ảnh: " + response.message);
          }
        },
        error: function (xhr, status, error) {
          console.error("Upload error:", xhr.responseText);
          alert("Lỗi upload ảnh: " + (xhr.responseJSON?.message || error));
        }
      });
    } else {
      submitRegistration(imageUrl);
    }

    function submitRegistration(imageUrl) {
      const registrationData = {
        customer: {
          name: $("#customerName").val(),
          organization: "",
          email: $("#customerEmail").val(),
          phoneNumber: $("#customerPhone").val(),
          address: $("#customerAddress").val(),
          citizen_id: $("#customerCitizenId").val(),
          customer_type: "INDIVIDUAL",
          dob: $("#customerDob").val(),
        },
        candidate: {
          name: $("#candidateName").val(),
          gender: $("#candidateGender").val(),
          email: $("#candidateEmail").val(),
          phoneNumber: $("#candidatePhone").val(),
          dob: $("#candidateDob").val(),
          address: $("#candidateAddress").val(),
          citizen_id: $("#candidateCitizenId").val(),
          imageUrl: imageUrl
        },
        schedule: selectedSchedule, // Gửi một đối tượng Schedule thay vì mảng
        registrationForm: null
      };

      console.log("Registration data:", registrationData);

      $.ajax({
        url: "/registrations/individual/submit",
        method: "POST",
        contentType: "application/json",
        xhrFields: { withCredentials: true },
        data: JSON.stringify(registrationData),
        success: function (data) {
          console.log("Success response:", data);
          if (data.status === "SUCCESS") {
            window.location.href = "/registrations/individual/success";
          } else {
            alert("Đăng ký thất bại: " + data.message);
          }
        },
        error: function (xhr, status, error) {
          console.error("Submit error:", xhr.responseText);
          if (xhr.status === 401 || xhr.status === 403) {
            window.location.href = "/login?redirect=/registrations/individual/form";
          } else {
            alert("Lỗi đăng ký: " + (xhr.responseJSON?.message || error));
          }
        }
      });
    }
  });
});