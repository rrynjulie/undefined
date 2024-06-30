// script.js 파일 내용
document.addEventListener('DOMContentLoaded', function() {
    // DOM 요소를 가져오기
    const oneWayTab = document.getElementById('one-way-tab');
    const roundTripTab = document.getElementById('round-trip-tab');
    const returnDateInput = document.getElementById('return-date-input');

    // 초기 설정: 편도가 활성화된 상태에서는 돌아오는 날짜 입력란 숨김
    returnDateInput.style.display = 'none';

    // 왕복 클릭 시
    roundTripTab.addEventListener('click', function() {
        // 돌아오는 날짜 입력란을 보이도록 설정
        returnDateInput.style.display = 'block';

        // 탭 색상 변경
        oneWayTab.classList.remove('active');
        roundTripTab.classList.add('active');
    });

    // 편도 클릭 시
    oneWayTab.addEventListener('click', function() {
        // 돌아오는 날짜 입력란을 숨김
        returnDateInput.style.display = 'none';

        // 탭 색상 변경
        roundTripTab.classList.remove('active');
        oneWayTab.classList.add('active');
    });
    // 폼 제출 이벤트 리스너 추가
    ticketForm.addEventListener('submit', function(event) {
        // 각 필드를 확인
        const depTerminalId = document.getElementById('depTerminalId').value.trim();
        const arrTerminalId = document.getElementById('arrTerminalId').value.trim();
        const depPlandTime = document.getElementById('depPlandTime').value.trim();
        const busGradeId = document.getElementById('busGradeId').value.trim();
        const isRoundTrip = roundTripTab.classList.contains('active');
        const returnPlandTime = document.getElementById('returnPlandTime').value.trim();

        // 필수 필드가 비어 있는지 확인
        let emptyFields = [];

        if (!depTerminalId) {
            emptyFields.push('출발 터미널');
        }
        if (!arrTerminalId) {
            emptyFields.push('도착 터미널');
        }
        if (!depPlandTime) {
            emptyFields.push('출발일');
        }
        if (isRoundTrip && !returnPlandTime) {
            emptyFields.push('도착일');
        }
        if (!busGradeId) {
            emptyFields.push('버스 등급');
        }
        // 모든 필수 필드가 채워졌는지 확인
        if (emptyFields.length > 0) {
            event.preventDefault();  // 폼 제출을 막음
            alert(`다음 필드를 입력해주세요:\n${emptyFields.join(', ')}`);
        }
    });
});
