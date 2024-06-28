document.addEventListener('DOMContentLoaded', function() {
    var passwordInput = document.getElementById('password');
    var confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));
    var confirmButton = document.getElementById('confirmButton');

    document.querySelector('.submit-container button').addEventListener('click', function(event) {
        event.preventDefault();

        // 비밀번호 확인을 위한 AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/mypage/customer/Unregister', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200 && xhr.responseText === 'success') {
                    confirmModal.show(); // 비밀번호 확인이 성공하면 모달 창을 띄움
                } else {
                    alert('비밀번호가 일치하지 않습니다.');
                    console.log('비밀번호 확인 실패:', xhr.status, xhr.responseText);
                }
            }
        };
        xhr.send('password=' + encodeURIComponent(passwordInput.value));
    });

    confirmButton.addEventListener('click', function() {
        // 실제 회원 탈퇴를 위한 AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/mypage/customer/UnregisterConfirm', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('회원 탈퇴에 실패하였습니다.');
                    console.log('회원 탈퇴 실패:', xhr.status, xhr.responseText);
                } else {
                    alert('회원 탈퇴가 완료되었습니다.');
                    window.location.href = '/Home'; // 홈 페이지로 리다이렉트
                }
            }
        };
        xhr.send();
    });
});
