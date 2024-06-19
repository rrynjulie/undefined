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
});
