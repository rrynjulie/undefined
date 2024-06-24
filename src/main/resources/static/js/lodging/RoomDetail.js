// let slideIndex = 1;
// showSlides(slideIndex);
//
// // 3초마다 페이지가
// setInterval(function() {
//     plusSlides(1);
// }, 3000);
//
// function plusSlides(n) {
//     showSlides(slideIndex += n);
// }
//
// function currentSlide(n) {
//     showSlides(slideIndex = n);
// }
//
// function showSlides(n) {
//     let i;
//     let slides = document.getElementsByClassName("mySlides");
//     let dots = document.getElementsByClassName("dot");
//     if (n > slides.length) {
//         slideIndex = 1
//     }
//     if (n < 1) {
//         slideIndex = slides.length
//     }
//     for (i = 0; i < slides.length; i++) {
//         slides[i].style.display = "none";
//     }
//     for (i = 0; i < dots.length; i++) {
//         dots[i].className = dots[i].className.replace(" active", "");
//     }
//     slides[slideIndex - 1].style.display = "block";
// }

document.addEventListener('DOMContentLoaded', function () {

    const selectedStartDate = document.getElementById('selected-start-date');
    const selectedEndDate = document.getElementById('selected-end-date');
    const selectedLineup = document.getElementById('selected-lineup');

    function showReservationInfo() {
        // session storage 에서 데이터 가져오기
        const storedStartDate = sessionStorage.getItem('startDate');
        const storedEndDate = sessionStorage.getItem('endDate');
        const storedAdultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
        const storedChildCount = parseInt(sessionStorage.getItem('childCount')) || 0;

        // 날짜 정보 표시
        if (storedStartDate && storedEndDate) {
            const startDate = new Date(storedStartDate);
            const endDate = new Date(storedEndDate);
            selectedStartDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()}`;
            selectedEndDate.innerHTML = `${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}`;
        }

        // 인원수 정보 표시
        selectedLineup.innerHTML = `성인 ${storedAdultCount}, 아동 ${storedChildCount}`;
    }

    showReservationInfo();
});