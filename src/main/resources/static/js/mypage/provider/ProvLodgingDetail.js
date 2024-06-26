// document.addEventListener('DOMContentLoaded', function() {
//     const slides = document.querySelectorAll('.my-slides img');
//     const totalSlides = slides.length;
//
//     // 모든 슬라이드 숨기고 첫 번째 슬라이드만 보이도록 설정
//     slides.forEach((slide, index) => {
//         if (index !== 0) {
//             slide.style.display = 'none';
//         }
//     });
//
//     function showSlide(index) {
//         // 모든 슬라이드 숨기기
//         slides.forEach(slide => slide.style.display = 'none');
//         // 클릭된 슬라이드 보이기
//         slides[index].style.display = 'block';
//     }
//
//     document.querySelector('.nav-button.prev').addEventListener('click', function() {
//         let currentIndex = getCurrentSlideIndex();
//         let newIndex = (currentIndex - 1 + totalSlides) % totalSlides;
//         showSlide(newIndex);
//     });
//
//     document.querySelector('.nav-button.next').addEventListener('click', function() {
//         let currentIndex = getCurrentSlideIndex();
//         let newIndex = (currentIndex + 1) % totalSlides;
//         showSlide(newIndex);
//     });
//
//     document.querySelectorAll('.dot').forEach((dot, index) => {
//         dot.addEventListener('click', function() {
//             showSlide(index);
//         });
//     });
//
//     // 현재 활성화된 슬라이드의 인덱스를 반환하는 함수
//     function getCurrentSlideIndex() {
//         let currentIndex = 0;
//         slides.forEach((slide, index) => {
//             if (slide.style.display === 'block') {
//                 currentIndex = index;
//             }
//         });
//         return currentIndex;
//     }
// });
function confirmDelete() {
    if (confirm("삭제하시겠습니까?")) {
        document.getElementById('deleteForm').submit();
    }
}