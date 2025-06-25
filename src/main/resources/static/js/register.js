$(document).ready(function() {

    $('a[href="/api/auth/sign-out"]').click(function(e) {
        e.preventDefault();

        $.ajax({
            url: '/api/auth/sign-out',
            method: 'POST',
            xhrFields: {
                withCredentials: true
            },
            success: function(response) {
                if (response.status === 'SUCCESS') {
                    window.location.href = '/login';
                } else {
                    alert('Đăng xuất thất bại: ' + response.message);
                }
            },
            error: function(xhr) {
                alert('Lỗi đăng xuất: ' + xhr.responseText);
            }
        });
    });
    // Load schedules when page loads
    $.ajax({
        url: '/schedulewithcertificate',
        method: 'GET',
        xhrFields: {
            withCredentials: true
        },
        success: function(response) {
            populateScheduleTable(response);
        },
        error: function(xhr) {
            if (xhr.status === 401 || xhr.status === 403) {
                window.location.href = '/login?redirect=/registrations/individual/form';
            } else {
                alert('Lỗi tải lịch thi: ' + xhr.responseText);
            }
        }
    });

    // Populate schedule table
    function populateScheduleTable(schedules) {
        const tbody = $('#scheduleTable tbody');
        tbody.empty();

        schedules.forEach(schedule => {
            const row = `
                <tr>
                    <td>
                        <input type="checkbox" class="form-check-input schedule-checkbox"
                               data-schedule='${JSON.stringify(schedule)}'>
                    </td>
                    <td>${schedule.certificateName}</td>
                    <td>${formatDate(schedule.date)}</td>
                    <td>${schedule.time}</td>
                    <td>${schedule.currentParticipants}</td>
                    <td>${schedule.maxParticipants}</td>
                </tr>
            `;
            tbody.append(row);
        });
    }

    // Format date to Vietnamese locale
    function formatDate(dateStr) {
        const date = new Date(dateStr);
        return date.toLocaleDateString('vi-VN');
    }

    // Handle form submission
    $('#registrationForm').submit(function(e) {
        e.preventDefault();
        console.log('Form submitted'); // Debug log

        const selectedSchedules = [];
        $('.schedule-checkbox:checked').each(function() {
            const schedule = $(this).data('schedule'); // Lấy object trực tiếp
            console.log('Schedule:', schedule);
            selectedSchedules.push(schedule);
        });

        if (selectedSchedules.length === 0) {
            alert('Vui lòng chọn ít nhất một lịch thi');
            return;
        }

        const registrationData = {
            customer: {
                name: $('#customerName').val(),
                organization: "",
                email: $('#customerEmail').val(),
                phoneNumber: $('#customerPhone').val(),
                address: $('#customerAddress').val(),
                citizen_id: $('#customerCitizenId').val(),
                customer_type: "INDIVIDUAL",
                dob: $('#customerDob').val()
            },
            candidate: {
                name: $('#candidateName').val(),
                gender: $('#candidateGender').val(),
                email: $('#candidateEmail').val(),
                phoneNumber: $('#candidatePhone').val(),
                dob: $('#candidateDob').val(),
                address: $('#candidateAddress').val(),
                citizen_id: $('#candidateCitizenId').val()
            },
            schedules: selectedSchedules
        };

        console.log('Registration data:', registrationData); // Debug log

        $.ajax({
            url: '/registrations/individual/submit',
            method: 'POST',
            contentType: 'application/json',
            xhrFields: {
                withCredentials: true
            },
            data: JSON.stringify(registrationData),
            success: function(response) {
                console.log('Success response:', response); // Debug log
                if (response.status === 'SUCCESS') {
                    window.location.href = '/registrations/individual/success';
                } else {
                    alert('Đăng ký thất bại: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
                console.log('Error:', error); // Debug log
                if (xhr.status === 401 || xhr.status === 403) {
                    window.location.href = '/login?redirect=/registrations/individual/form';
                } else {
                    alert('Lỗi đăng ký: ' + xhr.responseText);
                }
            }
        });
    });
});