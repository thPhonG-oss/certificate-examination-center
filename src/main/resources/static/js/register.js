const $ = window.jQuery // Declare the $ variable

$(document).ready(() => {
  // Initialize form state
  let isFormSubmitting = false
  let originalFormData = {}

  // Store original form data for comparison
  function storeOriginalFormData() {
    originalFormData = {
      customerName: $("#customerName").val(),
      customerEmail: $("#customerEmail").val(),
      customerPhone: $("#customerPhone").val(),
      customerCitizenId: $("#customerCitizenId").val(),
      customerDob: $("#customerDob").val(),
      customerAddress: $("#customerAddress").val(),
      candidateName: $("#candidateName").val(),
      candidateGender: $("#candidateGender").val(),
      candidateEmail: $("#candidateEmail").val(),
      candidatePhone: $("#candidatePhone").val(),
      candidateDob: $("#candidateDob").val(),
      candidateCitizenId: $("#candidateCitizenId").val(),
      candidateAddress: $("#candidateAddress").val(),
      selectedSchedule: getSelectedSchedule(),
    }
  }

  // Check if form has been modified
  function isFormModified() {
    const currentData = {
      customerName: $("#customerName").val(),
      customerEmail: $("#customerEmail").val(),
      customerPhone: $("#customerPhone").val(),
      customerCitizenId: $("#customerCitizenId").val(),
      customerDob: $("#customerDob").val(),
      customerAddress: $("#customerAddress").val(),
      candidateName: $("#candidateName").val(),
      candidateGender: $("#candidateGender").val(),
      candidateEmail: $("#candidateEmail").val(),
      candidatePhone: $("#candidatePhone").val(),
      candidateDob: $("#candidateDob").val(),
      candidateCitizenId: $("#candidateCitizenId").val(),
      candidateAddress: $("#candidateAddress").val(),
      selectedSchedule: getSelectedSchedule(),
    }

    return JSON.stringify(currentData) !== JSON.stringify(originalFormData)
  }

  // Get selected schedule data
  function getSelectedSchedule() {
    const $checkedBox = $(".schedule-checkbox:checked")
    return $checkedBox.length > 0 ? $checkedBox.attr("data-schedule") : null
  }

  // Show loading state on buttons
  function setButtonsLoading(loading) {
    const $submitBtn = $("#registrationForm button[type='submit']")
    const $cancelBtn = $("#cancelRegistration")

    if (loading) {
      $submitBtn.prop("disabled", true).html('<i class="fas fa-spinner fa-spin me-2"></i>Đang xử lý...')
      $cancelBtn.prop("disabled", true)
    } else {
      $submitBtn.prop("disabled", false).html('<i class="fas fa-check-circle me-2"></i>Đăng ký')
      $cancelBtn.prop("disabled", false)
    }
  }

  // Show confirmation dialog
  function showConfirmDialog(message, callback) {
    if (confirm(message)) {
      callback()
    }
  }

  // Clear form data
  function clearForm() {
    $("#registrationForm")[0].reset()
    $("#imagePreview").attr("src", "#").addClass("d-none")
    $("#noImageText").removeClass("d-none")
    $(".schedule-checkbox").prop("checked", false)
    storeOriginalFormData()
  }

  // Validate form before submission
  function validateForm() {
    const requiredFields = [
      { id: "#customerName", name: "Họ và tên người đăng ký" },
      { id: "#customerEmail", name: "Email người đăng ký" },
      { id: "#customerPhone", name: "Số điện thoại người đăng ký" },
      { id: "#customerCitizenId", name: "CCCD/CMND người đăng ký" },
      { id: "#customerDob", name: "Ngày sinh người đăng ký" },
      { id: "#customerAddress", name: "Địa chỉ người đăng ký" },
      { id: "#candidateName", name: "Họ và tên thí sinh" },
      { id: "#candidateGender", name: "Giới tính thí sinh" },
      { id: "#candidateEmail", name: "Email thí sinh" },
      { id: "#candidateDob", name: "Ngày sinh thí sinh" },
      { id: "#candidateAddress", name: "Địa chỉ thí sinh" },
    ]

    for (const field of requiredFields) {
      if (!$(field.id).val().trim()) {
        alert(`Vui lòng nhập ${field.name}`)
        $(field.id).focus()
        return false
      }
    }

    // Validate email format
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    if (!emailRegex.test($("#customerEmail").val())) {
      alert("Email người đăng ký không hợp lệ")
      $("#customerEmail").focus()
      return false
    }
    if (!emailRegex.test($("#candidateEmail").val())) {
      alert("Email thí sinh không hợp lệ")
      $("#candidateEmail").focus()
      return false
    }

    // Validate phone number format
    const phoneRegex = /^[0-9]{10,11}$/
    if (!phoneRegex.test($("#customerPhone").val().replace(/\s/g, ""))) {
      alert("Số điện thoại người đăng ký không hợp lệ (10-11 số)")
      $("#customerPhone").focus()
      return false
    }

    // Validate schedule selection
    if ($(".schedule-checkbox:checked").length === 0) {
      alert("Vui lòng chọn một lịch thi")
      return false
    }

    return true
  }

  // Handle logout
  $('a[href="/api/auth/sign-out"], .logout').on("click", (e) => {
    e.preventDefault()

    if (isFormModified() && !isFormSubmitting) {
      showConfirmDialog("Bạn có thay đổi chưa được lưu. Bạn có chắc chắn muốn đăng xuất?", () => {
        performLogout()
      })
    } else {
      performLogout()
    }
  })

  function performLogout() {
    $.ajax({
      url: "/api/auth/sign-out",
      method: "POST",
      xhrFields: { withCredentials: true },
      success: (data) => {
        if (data.status === "SUCCESS") {
          window.location.href = "/login"
        } else {
          alert("Đăng xuất thất bại: " + data.message)
        }
      },
      error: (xhr, status, error) => {
        alert("Lỗi đăng xuất: " + error)
      },
    })
  }

  // Handle cancel registration button
  $("#cancelRegistration").on("click", (e) => {
    e.preventDefault()

    if (isFormModified()) {
      showConfirmDialog("Bạn có chắc chắn muốn hủy đăng ký? Tất cả thông tin đã nhập sẽ bị xóa.", () => {
        clearForm()
        alert("Đã hủy đăng ký và xóa tất cả thông tin.")
      })
    } else {
      showConfirmDialog("Bạn có chắc chắn muốn hủy đăng ký?", () => {
        // Redirect to main page or previous page
        window.location.href = "/list-registration-forms"
      })
    }
  })

  // Load schedule data
  $.ajax({
    url: "/schedulewithcertificate",
    method: "GET",
    xhrFields: { withCredentials: true },
    success: (data) => {
      populateScheduleTable(data)
      storeOriginalFormData() // Store initial state after loading
    },
    error: (xhr, status, error) => {
      if (xhr.status === 401 || xhr.status === 403) {
        window.location.href = "/login?redirect=/registrations/individual/form"
      } else {
        alert("Lỗi tải lịch thi: " + error)
      }
    },
  })

  function populateScheduleTable(schedules) {
    const $tbody = $("#scheduleTable tbody")
    $tbody.empty()

    if (!schedules || schedules.length === 0) {
      $tbody.append('<tr><td colspan="6" class="text-center">Không có lịch thi nào</td></tr>')
      return
    }

    $.each(schedules, (index, schedule) => {
      const isFullyBooked = schedule.currentParticipants >= schedule.maxParticipants
      const rowClass = isFullyBooked ? "table-warning" : ""
      const checkboxDisabled = isFullyBooked ? "disabled" : ""

      const row = `
        <tr class="${rowClass}">
          <td data-label="Chọn">
            <input type="checkbox" class="form-check-input schedule-checkbox"
                   data-schedule='${JSON.stringify(schedule)}' ${checkboxDisabled}>
          </td>
          <td data-label="Chứng chỉ">${schedule.certificateName}</td>
          <td data-label="Ngày thi">${formatDate(schedule.date)}</td>
          <td data-label="Giờ thi">${schedule.time}</td>
          <td data-label="Số lượng ĐK">${schedule.currentParticipants}</td>
          <td data-label="Số lượng tối đa">${schedule.maxParticipants}</td>
        </tr>`
      $tbody.append(row)
    })

    // Handle schedule selection (only one at a time)
    $(".schedule-checkbox").on("change", function () {
      if ($(this).is(":checked")) {
        $(".schedule-checkbox").not(this).prop("checked", false)
      }
    })
  }

  function formatDate(dateStr) {
    const date = new Date(dateStr)
    return date.toLocaleDateString("vi-VN")
  }

  // Handle image preview
  $("#candidateImage").on("change", function () {
    const file = this.files[0]
    console.log("Selected file:", file)
    const $previewImage = $("#imagePreview")
    const $noImageText = $("#noImageText")

    if (file) {
      // Validate file type
      if (!file.type.startsWith("image/")) {
        alert("Vui lòng chọn file ảnh!")
        this.value = ""
        return
      }

      // Validate file size (max 5MB)
      if (file.size > 5 * 1024 * 1024) {
        alert("Kích thước file không được vượt quá 5MB!")
        this.value = ""
        return
      }

      const reader = new FileReader()
      reader.onload = (e) => {
        $previewImage.attr("src", e.target.result).removeClass("d-none")
        $noImageText.addClass("d-none")
      }
      reader.readAsDataURL(file)
    } else {
      $previewImage.attr("src", "#").addClass("d-none")
      $noImageText.removeClass("d-none")
    }
  })

  // Handle form submission
  $("#registrationForm").on("submit", (e) => {
    e.preventDefault()

    if (isFormSubmitting) {
      return // Prevent double submission
    }

    console.log("Form submitted")

    // Validate form
    if (!validateForm()) {
      return
    }

    isFormSubmitting = true
    setButtonsLoading(true)

    const selectedSchedule = JSON.parse(getSelectedSchedule())
    const fileInput = $("#candidateImage")[0]
    const formData = new FormData()
    let imageUrl = ""

    // Handle image upload if file is selected
    if (fileInput.files[0]) {
      formData.append("file", fileInput.files[0])
      $.ajax({
        url: "/api/image/upload",
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        xhrFields: { withCredentials: true },
        success: (response) => {
          console.log("Upload response:", response)
          if (response.status === "SUCCESS") {
            imageUrl = response.response
            submitRegistration(imageUrl)
          } else {
            setButtonsLoading(false)
            isFormSubmitting = false
            alert("Lỗi upload ảnh: " + response.message)
          }
        },
        error: (xhr, status, error) => {
          console.error("Upload error:", xhr.responseText)
          setButtonsLoading(false)
          isFormSubmitting = false
          alert("Lỗi upload ảnh: " + (xhr.responseJSON?.message || error))
        },
      })
    } else {
      submitRegistration(imageUrl)
    }

    function submitRegistration(imageUrl) {
      const registrationData = {
        customer: {
          name: $("#customerName").val().trim(),
          organization: "",
          email: $("#customerEmail").val().trim(),
          phoneNumber: $("#customerPhone").val().trim(),
          address: $("#customerAddress").val().trim(),
          citizen_id: $("#customerCitizenId").val().trim(),
          customer_type: "INDIVIDUAL",
          dob: $("#customerDob").val(),
        },
        candidate: {
          name: $("#candidateName").val().trim(),
          gender: $("#candidateGender").val(),
          email: $("#candidateEmail").val().trim(),
          phoneNumber: $("#candidatePhone").val().trim(),
          dob: $("#candidateDob").val(),
          address: $("#candidateAddress").val().trim(),
          citizen_id: $("#candidateCitizenId").val().trim(),
          imageUrl: imageUrl,
        },
        schedule: selectedSchedule,
        registrationForm: null,
      }

      console.log("Registration data:", registrationData)

      $.ajax({
        url: "/registrations/individual/submit",
        method: "POST",
        contentType: "application/json",
        xhrFields: { withCredentials: true },
        data: JSON.stringify(registrationData),
        success: (data) => {
          console.log("Success response:", data)
          if (data.status === "SUCCESS") {
            window.location.href = "/registrations/individual/success"
          } else {
            setButtonsLoading(false)
            isFormSubmitting = false
            alert("Đăng ký thất bại: " + data.message)
          }
        },
        error: (xhr, status, error) => {
          console.error("Submit error:", xhr.responseText)
          setButtonsLoading(false)
          isFormSubmitting = false

          if (xhr.status === 401 || xhr.status === 403) {
            window.location.href = "/login?redirect=/registrations/individual/form"
          } else {
            const errorMessage = xhr.responseJSON?.message || error
            alert("Lỗi đăng ký: " + errorMessage)
          }
        },
      })
    }
  })

  // Warn user before leaving page if form is modified
  window.addEventListener("beforeunload", (e) => {
    if (isFormModified() && !isFormSubmitting) {
      e.preventDefault()
      e.returnValue = ""
    }
  })

  // Auto-save form data to localStorage periodically
  setInterval(() => {
    if (isFormModified()) {
      const formData = {
        customerName: $("#customerName").val(),
        customerEmail: $("#customerEmail").val(),
        customerPhone: $("#customerPhone").val(),
        customerCitizenId: $("#customerCitizenId").val(),
        customerDob: $("#customerDob").val(),
        customerAddress: $("#customerAddress").val(),
        candidateName: $("#candidateName").val(),
        candidateGender: $("#candidateGender").val(),
        candidateEmail: $("#candidateEmail").val(),
        candidatePhone: $("#candidatePhone").val(),
        candidateDob: $("#candidateDob").val(),
        candidateCitizenId: $("#candidateCitizenId").val(),
        candidateAddress: $("#candidateAddress").val(),
        timestamp: new Date().getTime(),
      }
      localStorage.setItem("registrationFormDraft", JSON.stringify(formData))
    }
  }, 30000) // Auto-save every 30 seconds

  // Load draft data on page load
  const savedDraft = localStorage.getItem("registrationFormDraft")
  if (savedDraft) {
    const draftData = JSON.parse(savedDraft)
    const timeDiff = new Date().getTime() - draftData.timestamp

    // Only load draft if it's less than 24 hours old
    if (timeDiff < 24 * 60 * 60 * 1000) {
      if (confirm("Có dữ liệu đã lưu trước đó. Bạn có muốn khôi phục không?")) {
        $("#customerName").val(draftData.customerName || "")
        $("#customerEmail").val(draftData.customerEmail || "")
        $("#customerPhone").val(draftData.customerPhone || "")
        $("#customerCitizenId").val(draftData.customerCitizenId || "")
        $("#customerDob").val(draftData.customerDob || "")
        $("#customerAddress").val(draftData.customerAddress || "")
        $("#candidateName").val(draftData.candidateName || "")
        $("#candidateGender").val(draftData.candidateGender || "")
        $("#candidateEmail").val(draftData.candidateEmail || "")
        $("#candidatePhone").val(draftData.candidatePhone || "")
        $("#candidateDob").val(draftData.candidateDob || "")
        $("#candidateCitizenId").val(draftData.candidateCitizenId || "")
        $("#candidateAddress").val(draftData.candidateAddress || "")
      }
      localStorage.removeItem("registrationFormDraft")
    }
  }

  // Store initial form state after everything is loaded
  setTimeout(storeOriginalFormData, 1000)
})
