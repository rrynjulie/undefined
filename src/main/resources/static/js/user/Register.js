let timer;
let countdownSeconds = 3;

function sendVerificationCode() {
    const email = document.getElementById('email').value;
    if (email) {
        document.getElementById('verification-form').style.display = 'block';
        resetVerificationForm();
        startCountdown();
    }
}

function startCountdown() {
    const countdownElement = document.getElementById('countdown');
    timer = setInterval(() => {
        if (countdownSeconds <= 0) {
            clearInterval(timer);
            countdownElement.style.display = 'none';
            showTimeoutMessage();
        } else {
            countdownSeconds--;
            const minutes = Math.floor(countdownSeconds / 60);
            const seconds = countdownSeconds % 60;
            countdownElement.textContent = `${minutes}분 ${seconds < 10 ? '0' : ''}${seconds}초`;
        }
    }, 1000);
}

function showTimeoutMessage() {
    const verificationInput = document.getElementById('verification-code');
    const errorMessage = document.createElement('div');
    errorMessage.textContent = '인증번호 입력 시간이 초과하였습니다. 재전송해주세요.';
    errorMessage.classList.add('red-text');
    errorMessage.id = 'timeout-message';
    verificationInput.classList.add('red-border');
    verificationInput.parentNode.insertBefore(errorMessage, verificationInput.nextSibling);
}

function verifyCode() {
    const verificationCode = document.getElementById('verification-code').value;
    if (verificationCode) {
        clearInterval(timer);
        document.getElementById('registration-modal').style.display = 'block';
    }
}

function completeRegistration() {
    const nickname = document.getElementById('nickname').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const phone = document.getElementById('phone').value;

    if (nickname && password && confirmPassword && phone && password === confirmPassword) {
        window.location.href = '../../../templates/views/Home.html';
    } else {
        alert('모든 입력 필드를 채우고 비밀번호를 확인해주세요.');
    }
}

function resetVerificationForm() {
    const verificationInput = document.getElementById('verification-code');
    const errorMessage = document.getElementById('timeout-message');
    countdownSeconds = 3;
    if (errorMessage) {
        errorMessage.remove();
    }
    verificationInput.classList.remove('red-border');
    document.getElementById('countdown').style.display = 'block';
    document.getElementById('countdown').textContent = '10분 00초';
}

// Add event listener to change border color back to normal on focus
document.getElementById('verification-code').addEventListener('focus', function() {
    this.classList.remove('red-border');
    this.style.borderColor = 'black';
});