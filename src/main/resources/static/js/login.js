$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        const loginData = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: '/api/auth/sign-in',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function(response) {
                if (response.status === 'SUCCESS') {
                    // Lưu token vào localStorage
                    localStorage.setItem('token', response.response.token);

                    // Thiết lập header Authorization cho tất cả các request AJAX tiếp theo
                    $.ajaxSetup({
                        headers: {
                            'Authorization': 'Bearer ' + response.response.token
                        }
                    });

                    // Chuyển hướng trực tiếp đến /dashboard, để server-side xử lý render HTML
                    window.location.href = '/dashboard';
                } else {
                    $('#errorMessage').text(response.message).removeClass('d-none');
                }
            },
            error: function(xhr) {
                const errorMsg = xhr.responseJSON?.message || 'Login failed. Please try again.';
                $('#errorMessage').text(errorMsg).removeClass('d-none');
            }
        });
    });
});