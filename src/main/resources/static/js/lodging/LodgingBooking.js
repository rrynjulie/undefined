document.addEventListener('DOMContentLoaded', function () {
    // HTML 에서 요소 가져오기
    const bookingForm = document.getElementById('bookingForm');
    const bookingDate = document.getElementById('bookingDate');
    const bookingLineUp = document.getElementById('bookingLineUp');

    // sessionStorage 에서 데이터 가져오기
    const storedStartDate = sessionStorage.getItem('startDate');
    const storedEndDate = sessionStorage.getItem('endDate');
    const storedAdultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
    const storedChildCount = parseInt(sessionStorage.getItem('childCount')) || 0;

    let nights;

    // 예약 폼에 날짜와 인원수 정보를 표시하는 함수
    function showBookingInfo() {

        // 몇 박인지 계산
        if (storedStartDate && storedEndDate)
            nights = Math.ceil((new Date(storedEndDate) - new Date(storedStartDate)) / (1000 * 60 * 60 * 24));
        const startDate = new Date(storedStartDate);
        const endDate = new Date(storedEndDate);

        // HTML 에 데이터 전송
        bookingDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()} (${nights}박)`;

        bookingLineUp.innerHTML = `성인 ${storedAdultCount}인, 아동 ${storedChildCount}인`;
    }

    // 페이지 로드 시 예약 정보를 표시
    showBookingInfo();

    bookingForm.addEventListener('submit', function (event) {
        // sessionStorage 에서 날짜 가져와서 폼에 추가
        //폼이 제출될 때 startDate, endDate, adultCount, childCount 값을 히든 필드로 추가
        if(storedStartDate && storedEndDate) {
            const startDate = new Date(storedStartDate);
            const endDate = new Date(storedEndDate);

            startDate.setDate(startDate.getDate() + 1);
            endDate.setDate(endDate.getDate() + 1);

            const hiddenStartDateField = document.createElement('input');
            hiddenStartDateField.type = 'hidden';
            hiddenStartDateField.name = 'bookingStartDate';
            hiddenStartDateField.value = startDate.toISOString().split('T')[0];
            bookingForm.appendChild(hiddenStartDateField);

            const hiddenEndDateField = document.createElement('input');
            hiddenEndDateField.type = 'hidden';
            hiddenEndDateField.name = 'bookingEndDate';
            hiddenEndDateField.value = endDate.toISOString().split('T')[0];
            bookingForm.appendChild(hiddenEndDateField);
        }

        const hiddenAdultCountField = document.createElement('input');
        hiddenAdultCountField.type = 'hidden';
        hiddenAdultCountField.name = 'bookingAdult';
        hiddenAdultCountField.value = storedAdultCount;
        bookingForm.appendChild(hiddenAdultCountField);

        const hiddenChildCountField = document.createElement('input');
        hiddenChildCountField.type = 'hidden';
        hiddenChildCountField.name = 'bookingChild';
        hiddenChildCountField.value = storedChildCount;
        bookingForm.appendChild(hiddenChildCountField);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var paymentItems = document.querySelectorAll('.payment-item');

    paymentItems.forEach(function(item) {
        item.addEventListener('click', function() {
            paymentItems.forEach(function(btn) {
                btn.classList.remove('active');
            });
            item.classList.add('active');
        });
    });
});
