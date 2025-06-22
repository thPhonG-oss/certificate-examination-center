$(document).ready(function() {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = '/auth/login';
        return;
    }

    $.ajax({
        url: '/api/dashboard',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function(response) {
            // Hiển thị dữ liệu dashboard
            $('#dashboardContent').text(response); // Giả sử response là chuỗi
        },
        error: function(xhr) {
            const errorMsg = xhr.responseJSON?.message || 'Failed to load dashboard.';
            $('#errorMessage').text(errorMsg).removeClass('d-none');
            if (xhr.status === 401) {
                window.location.href = '/auth/login'; // Chuyển hướng nếu token không hợp lệ
            }
        }
    });
});