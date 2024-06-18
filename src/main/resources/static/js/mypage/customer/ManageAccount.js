document.addEventListener("DOMContentLoaded", function() {
    // Event listeners for buttons
    document.getElementById("manage-info-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/ManageAccount.html";
    });

    document.getElementById("reservation-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/BookingList.html";
    });

    document.getElementById("review-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/ReviewList.html";
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
