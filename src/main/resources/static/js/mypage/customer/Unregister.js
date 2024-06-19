document.addEventListener('DOMContentLoaded', function() {
    var confirmButton = document.querySelector('.submit-container button');

    confirmButton.addEventListener('click', function() {
        var passwordInput = document.getElementById('password').value;

        if (passwordInput.trim() === '') {
            alert('비밀번호를 입력해주세요.');
        } else {
            window.location.href = '../../../../templates/views/Home.html';
            // console.log("비밀번호 입력 완료")
        }
    });
});