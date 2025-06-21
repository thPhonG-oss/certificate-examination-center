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
                    // Store token
                    localStorage.setItem('token', response.response.token);

                    // Set default authorization header for future requests
                    $.ajaxSetup({
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader('Authorization', 'Bearer ' + response.response.token);
                        }
                    });

                    // Redirect to dashboard
                    window.location.href = '/api/dashboard';
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