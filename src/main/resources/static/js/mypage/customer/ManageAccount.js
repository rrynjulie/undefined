document.addEventListener("DOMContentLoaded", function() {
    // Event listeners for buttons
    document.getElementById("manage-info-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/ManageAccount.html";
    });

    document.getElementById("booking-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/BookingList.html";
    });


    document.getElementById("coupon-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/CouponList.html";
    });

    // 로컬 스토리지에 저장된 데이터를 폼에 불러오기
    loadFormData();

    const manageAccountForm = document.getElementById('manage-account-form');

    manageAccountForm.addEventListener('submit', function(event) {
        event.preventDefault();

        // 폼 데이터 가져오기
        const formData = {
            userId: document.getElementById('user-id').value,
            email: document.getElementById('email').value,
            nickname: document.getElementById('nickname').value,
            password: document.getElementById('password').value,
            phone: document.getElementById('phone').value
        };

        // 데이터 검증 (필요에 따라 추가 검증 로직을 여기에 작성)
        if (!formData.nickname) {
            alert('닉네임을 입력해주세요.');
            return;
        }

        if (!formData.password) {
            alert('비밀번호를 입력해주세요.');
            return;
        }

        if (!formData.phone) {
            alert('휴대전화번호를 입력해주세요.');
            return;
        }

        // 로컬 스토리지에 데이터 저장
        localStorage.setItem('userData', JSON.stringify(formData));
        alert('정보가 성공적으로 저장되었습니다.');
    });

    function loadFormData() {
        const savedData = localStorage.getItem('userData');
        if (savedData) {
            const formData = JSON.parse(savedData);
            document.getElementById('user-id').value = formData.userId;
            document.getElementById('email').value = formData.email;
            document.getElementById('nickname').value = formData.nickname;
            document.getElementById('password').value = formData.password;
            document.getElementById('phone').value = formData.phone;
        }
    }
});


document.addEventListener('DOMContentLoaded', (event) => {
    fetch('/mypage/customer/getProvider')
        .then(response => response.text())
        .then(provider => {
            if (provider === 'KAKAO' || provider === 'google') {
                document.getElementById('nickname').disabled = true;
                document.getElementById('password').disabled = true;
                document.getElementById('email').disabled = true;
                document.getElementById('phone').disabled = true;
                document.getElementById('save-btn').disabled = true;
            }
        })
        .catch(error => console.error('Error fetching provider:', error));
});


document.addEventListener('DOMContentLoaded', function() {
    var manageAccountForm = document.getElementById('manage-account-form');
    var currentPasswordInput = document.getElementById('current-password');
    var currentPasswordError = document.getElementById('current-password-error');
    var newPasswordInput = document.getElementById('new-password');
    var confirmPasswordInput = document.getElementById('confirm-password');
    var confirmPasswordError = document.getElementById('confirm-password-error');
    var currentPasswordValid = false; // 현재 비밀번호의 유효성을 저장하는 변수

    currentPasswordInput.addEventListener('input', function() {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/check-password', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200 && xhr.responseText === 'success') {
                    currentPasswordError.style.display = 'none';
                    currentPasswordValid = true;
                } else {
                    currentPasswordError.style.display = 'inline';
                    currentPasswordValid = false;
                }
            }
        };
        xhr.send('currentPassword=' + encodeURIComponent(currentPasswordInput.value));
    });

    manageAccountForm.addEventListener('submit', function(event) {
        event.preventDefault();  // 기본 폼 제출 방지

        // 현재 비밀번호가 유효한지 확인
        if (!currentPasswordValid) {
            currentPasswordError.style.display = 'inline';
            return;
        }

        // 새 비밀번호와 확인 비밀번호가 일치하는지 확인
        if (newPasswordInput.value !== confirmPasswordInput.value) {
            confirmPasswordError.style.display = 'inline';
            return;
        } else {
            confirmPasswordError.style.display = 'none';
        }

        // 비밀번호가 일치하면 폼을 실제로 제출
        manageAccountForm.submit();
        alert('수정되었습니다.');
        window.location.href = '/home'; // 홈으로 이동
    });
});
