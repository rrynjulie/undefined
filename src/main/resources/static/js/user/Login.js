document.addEventListener('DOMContentLoaded', function() {
    var resetModal = document.getElementById('resetModal');

    if (resetModal) {
        resetModal.addEventListener('show.bs.modal', function() {
            document.getElementById('reset-form').classList.remove('d-none');
            document.getElementById('confirmation').classList.add('d-none');
        });
    }

    document.addEventListener('DOMContentLoaded', function() {
        var loginForm = document.getElementById('login-form');

        if (loginForm) {
            loginForm.addEventListener('submit', function(e) {
                e.preventDefault();
                window.location.href = '/Home';
            });
        } else {
            console.error('로그인 폼을 찾을 수 없습니다.');
        }
    });

    document.getElementById('agreeButton').addEventListener('click', function() {
        window.location.href = '/user/Register';
    });

    document.getElementById('agreeAll').addEventListener('change', function() {
        const isChecked = this.checked;
        document.querySelectorAll('.form-check-input').forEach(function(input) {
            input.checked = isChecked;
        });
    });

});