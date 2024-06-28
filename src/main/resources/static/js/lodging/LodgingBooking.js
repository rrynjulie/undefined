// document.addEventListener('DOMContentLoaded', function () {
//     const bookingForm = document.getElementById('bookingForm'); // 예약 폼 요소 가져오기
//     const selectedDate = document.getElementById('selectedDate');
//     const total = document.getElementById('total');
//     const booking = document.getElementById('booking'); // 예약 폼 요소 가져오기
//
//     // 예약 폼에 날짜와 인원수 정보를 표시하는 함수
//     function showBookingInfo() {
//         // sessionStorage에서 데이터 가져오기
//         const storedStartDate = sessionStorage.getItem('startDate');
//         const storedEndDate = sessionStorage.getItem('endDate');
//         const storedAdultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
//         const storedChildCount = parseInt(sessionStorage.getItem('childCount')) || 0;
//
//         // 날짜 정보 표시
//         if (storedStartDate && storedEndDate) {
//             const startDate = new Date(storedStartDate);
//             const endDate = new Date(storedEndDate);
//             const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
//             selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}, ${nights}박`;
//         }
//
//         // 인원수 정보 표시
//         total.innerHTML = `성인 ${storedAdultCount}명, 아동 ${storedChildCount}명`;
//     }
//
//     // 페이지 로드 시 예약 정보를 표시
//     showBookingInfo();
//
//     //예약 폼 제출 시 처리
//     booking.addEventListener('submit', function (event) {
//     });
// });

document.addEventListener('DOMContentLoaded', function () {
    const bookingForm = document.getElementById('bookingForm'); // 예약 폼 요소 가져오기
    const selectedDate = document.getElementById('selectedDate');
    const total = document.getElementById('total');

    // 예약 폼에 날짜와 인원수 정보를 표시하는 함수
    function showBookingInfo() {
        // sessionStorage에서 데이터 가져오기
        const storedStartDate = sessionStorage.getItem('startDate');
        const storedEndDate = sessionStorage.getItem('endDate');
        const storedAdultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
        const storedChildCount = parseInt(sessionStorage.getItem('childCount')) || 0;

        // 날짜 정보 표시
        if (storedStartDate && storedEndDate) {
            const startDate = new Date(storedStartDate);
            const endDate = new Date(storedEndDate);
            const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
            selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}, ${nights}박`;
        }

        // 인원수 정보 표시
        total.innerHTML = `성인 ${storedAdultCount}명, 아동 ${storedChildCount}명`;

        // hidden input 필드로 인원 정보 추가
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
    }

    // 페이지 로드 시 예약 정보를 표시
    showBookingInfo();

    // 예약 폼 제출 시 처리
    bookingForm.addEventListener('submit', function (event) {
        // sessionStorage에서 날짜 가져와서 폼에 추가
        const storedStartDate = sessionStorage.getItem('startDate').split('T')[0]; // 시간 정보 제거
        const storedEndDate = sessionStorage.getItem('endDate').split('T')[0]; // 시간 정보 제거

        // 폼에 날짜 정보 추가
        const hiddenStartDateField = document.createElement('input');
        hiddenStartDateField.type = 'hidden';
        hiddenStartDateField.name = 'bookingStartDate';
        hiddenStartDateField.value = storedStartDate;
        bookingForm.appendChild(hiddenStartDateField);

        const hiddenEndDateField = document.createElement('input');
        hiddenEndDateField.type = 'hidden';
        hiddenEndDateField.name = 'bookingEndDate';
        hiddenEndDateField.value = storedEndDate;
        bookingForm.appendChild(hiddenEndDateField);
    });

    var visitorNameSpan = document.getElementById("visitorName");
    var visitorPhoneNumSpan = document.getElementById("visitorPhoneNum");
    var roomNameSpan = document.getElementById("roomName");
    var bookingPaySpan = document.getElementById("bookingPay");
    visitorNameSpan.value = visitorNameSpan.textContent;
    visitorPhoneNumSpan.value = visitorPhoneNumSpan.textContent;
    roomNameSpan.value = roomNameSpan.textContent;
    bookingPaySpan.value = bookingPaySpan.textContent;
});

function setBooking() {
    const visitorNameSpan = document.getElementById("visitorName");
    const visitorPhoneNumSpan = document.getElementById("visitorPhoneNum");
    const roomNameSpan = document.getElementById("roomName");
    const bookingPaySpan = document.getElementById("bookingPay");
    visitorNameSpan.value = visitorNameSpan.textContent;
    visitorPhoneNumSpan.value = visitorPhoneNumSpan.textContent;
    roomNameSpan.value = roomNameSpan.textContent;
    bookingPaySpan.value = bookingPaySpan.textContent;
}