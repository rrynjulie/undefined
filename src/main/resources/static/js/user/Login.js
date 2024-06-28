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
        if (!document.getElementById('agree1').checked) {
            alert('만 14세 이상 이용, 서비스 이용약관, 개인정보 수집 및 이용 동의는 필수입니다.');
            return false;
        }else {
            window.location.href = '/user/Register';
        }
    });

    document.getElementById('agreeAll').addEventListener('change', function() {
        const isChecked = this.checked;
        document.querySelectorAll('.form-check-input').forEach(function(input) {
            input.checked = isChecked;
        });
    });

    document.querySelectorAll('.form-check-input').forEach(function(input) {
        input.addEventListener('change', function() {
            const allChecked = document.querySelectorAll('.form-check-input').length === document.querySelectorAll('.form-check-input:checked').length;
            document.getElementById('agreeAll').checked = allChecked;
        });
    });

    document.querySelectorAll('.terms-list .form-check-input').forEach(function(input) {
        input.addEventListener('change', function() {
            const individualCheckboxes = document.querySelectorAll('.terms-list .form-check-input');
            const allChecked = Array.from(individualCheckboxes).every(function(checkbox) {
                return checkbox.checked;
            });
            document.getElementById('agreeAll').checked = allChecked;
        });
    });

});