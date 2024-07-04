document.addEventListener('DOMContentLoaded', (event) => {
    fetch('/mypage/customer/getProvider')
        .then(response => response.text())
        .then(provider => {
            const passwordContainer = document.getElementById('password-container');
            const nameGroup = document.getElementById('name-group');

            if (provider === 'kakao' || provider === 'google' || provider === 'naver') {
                if (passwordContainer) {
                    passwordContainer.style.display = 'none'; // 비밀번호 관련 필드 숨김
                }
                if (nameGroup) {
                    nameGroup.style.display = 'block';
                }
                document.getElementById('password').disabled = true;
                document.getElementById('email').disabled = true;
                document.getElementById('phone').disabled = false; // 전화번호는 수정 가능
                document.getElementById('nickname').disabled = false; // 닉네임 수정 가능
                document.getElementById('save-btn').disabled = false; // 저장 버튼 활성화
            } else {
                if (passwordContainer) {
                    passwordContainer.style.display = 'block'; // 비밀번호 관련 필드 보임
                }
                if (nameGroup){
                    nameGroup.style.display = 'none';
                }
                document.getElementById('password').disabled = false;
                document.getElementById('email').disabled = true;
                document.getElementById('phone').disabled = false;
                document.getElementById('nickname').disabled = false;
                document.getElementById('save-btn').disabled = false;
            }
        })
        .catch(error => console.error('Error fetching provider:', error));
});