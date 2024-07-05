document.addEventListener('DOMContentLoaded', function () {

    // 회원가입 모달 열기
    document.querySelector('[data-bs-target="#termsModal"]').addEventListener('click', function () {
        var termsModal = new bootstrap.Modal(document.getElementById('termsModal'));
        termsModal.show();
    });

    document.getElementById('btn-Email-login').addEventListener('click', function () {
        var termsModal = new bootstrap.Modal(document.getElementById('termsModal'));
        termsModal.show();
    });

    // 모달 닫을 때 이벤트 추가 (약관 동의 모달)
    document.getElementById('termsModal').addEventListener('hidden.bs.modal', function () {
        document.body.classList.remove('modal-open');
        document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
    });

    // 전체 동의 체크박스
    const agreeAllCheckbox = document.getElementById('agreeAll');
    const agreeCheckboxes = document.querySelectorAll('.terms-list input[type="checkbox"]');

    // 전체 동의 클릭 시 모든 체크박스 체크/해제
    agreeAllCheckbox.addEventListener('change', function () {
        agreeCheckboxes.forEach(checkbox => {
            checkbox.checked = agreeAllCheckbox.checked;
        });
        updateAgreeButtonState();
    });

    // 개별 체크박스 클릭 시 전체 동의 체크박스 상태 업데이트
    agreeCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            if (!this.checked) {
                agreeAllCheckbox.checked = false;
            } else {
                const allChecked = Array.from(agreeCheckboxes).every(cb => cb.checked);
                agreeAllCheckbox.checked = allChecked;
            }
            updateAgreeButtonState();
        });
    });

    // 동의하고 계속하기 버튼
    const agreeButton = document.getElementById('agreeButton');

    // agree1 체크박스 상태에 따라 버튼 활성화/비활성화
    document.getElementById('agree1').addEventListener('change', function () {
        updateAgreeButtonState();
    });

    function updateAgreeButtonState() {
        const agree1Checked = document.getElementById('agree1').checked;
        if (agree1Checked) {
            agreeButton.style.backgroundColor = '#FC5185';
            agreeButton.style.pointerEvents = 'auto';
            agreeButton.classList.remove('disabled');
        } else {
            agreeButton.style.backgroundColor = 'gray';
            agreeButton.style.pointerEvents = 'none';
            agreeButton.classList.add('disabled');
        }
    }

    // 동의하고 계속하기 버튼 클릭 시
    agreeButton.addEventListener('click', function (e) {
        if (document.getElementById('agree1').checked) {
            window.location.href = '../user/register';
        } else {
            e.preventDefault();
        }
    });

    // 페이지 로드 시 초기 버튼 상태 설정
    updateAgreeButtonState();
});
