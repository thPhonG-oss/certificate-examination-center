$(document).ready(function() {

    // Make entire row clickable for schedule selection
    $('.table tbody tr').click(function(e) {
        if (!$(e.target).is('input[type="checkbox"]')) {
            const checkbox = $(this).find('input[type="checkbox"]');
            checkbox.prop('checked', !checkbox.prop('checked'));
        }
    });

    // Form submission handling
    $('#registrationForm').on('submit', function(e) {
        e.preventDefault();

        // Validate at least one schedule is selected
        if ($('.schedule-checkbox:checked').length === 0) {
            alert('Please select at least one schedule');
            return;
        }

        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }

        // Get all selected schedules
        const selectedSchedules = $('.schedule-checkbox:checked').map(function() {
            const checkbox = $(this);
            return {
                id: parseInt(checkbox.val()),
                date: checkbox.data('date'),
                time: checkbox.data('time'),
                certification_id: parseInt(checkbox.data('certificate-id')),
                currentParticipants: parseInt(checkbox.data('current')),
                maxParticipants: parseInt(checkbox.data('max'))
            };
        }).get();

        const formData = {
            customer: {
                name: $('#customerName').val(),
                email: $('#customerEmail').val(),
                phoneNumber: $('#customerPhone').val(),
                address: $('#customerAddress').val(),
                citizen_id: $('#customerCitizenId').val(),
                customer_type: 'INDIVIDUAL',
                organization: $('#organization').val(),
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
            schedules: selectedSchedules,
            registrationForm: null
        };

        const token = localStorage.getItem('token');

        // Submit the form data
        $.ajax({
            url: '/registrations/individual/submit',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            data: JSON.stringify(formData),
            success: function(response) {
                alert('Registration submitted successfully!');
                window.location.href = '/registrations/success';
            },
            error: function(xhr, status, error) {
                if (xhr.status === 401) {
                    window.location.href = '/api/auth/sign-in';
                } else {
                    alert('Error submitting registration. Please try again.');
                    console.error(error);
                }
            }
        });
    });

    // Optional: Auto-fill address if same as customer
    $('#sameAsCustomer').change(function() {
        if ($(this).is(':checked')) {
            $('#candidateAddress').val($('#customerAddress').val());
        }
    });
});