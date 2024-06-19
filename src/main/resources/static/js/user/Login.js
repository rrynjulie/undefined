document.addEventListener('DOMContentLoaded', function() {
    var resetModal = document.getElementById('resetModal');

    resetModal.addEventListener('show.bs.modal', function() {
        document.getElementById('reset-form').classList.remove('d-none');
        document.getElementById('confirmation').classList.add('d-none');
    });

    document.getElementById('login-form').addEventListener('submit', function(e) {
        e.preventDefault();
        window.location.href = '../../../templates/views/Home.html';
    });

    document.getElementById('agreeButton').addEventListener('click', function() {
        window.location.href = '../../../templates/views/user/Register.html';
    });

    document.getElementById('agreeAll').addEventListener('change', function() {
        const isChecked = this.checked;
        document.querySelectorAll('.form-check-input').forEach(function(input) {
            input.checked = isChecked;
        });
    });

});

