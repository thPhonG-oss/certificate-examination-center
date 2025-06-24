$(document).ready(function() {
    const token = localStorage.getItem('token');

    // Load schedules
    $.ajax({
        url: '/schedulewithcertificate',
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function(response) {
            populateScheduleTable(response);
        },
        error: function(xhr) {
            if (xhr.status === 401 || xhr.status === 403) {
                window.location.href = '/login?redirect=/registrations/individual/form';
            } else {
                alert('Error loading schedules: ' + xhr.responseText);
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

    // Format date
    function formatDate(dateStr) {
        const date = new Date(dateStr);
        return date.toLocaleDateString('vi-VN');
    }

    // Handle form submission
    $('#registrationForm').on('submit', function(e) {
        e.preventDefault();

        const selectedSchedules = [];
        $('.schedule-checkbox:checked').each(function() {
            selectedSchedules.push(JSON.parse($(this).data('schedule')));
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

        $.ajax({
            url: '/registrations/individual/submit',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            credentials: 'include',
            data: JSON.stringify(registrationData),
            success: function(response) {
                if (response.status === 'SUCCESS') {
                    window.location.href = '/registrations/individual/success';
                } else {
                    alert('Đăng ký thất bại: ' + response.message);
                }
            },
            error: function(xhr) {
                if (xhr.status === 401 || xhr.status === 403) {
                    window.location.href = '/login?redirect=/registrations/individual/form';
                } else {
                    alert('Lỗi đăng ký: ' + xhr.responseText);
                }
            }
        });
    });
});