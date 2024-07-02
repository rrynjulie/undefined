// // 리뷰 작성 모달 열기
// function openReviewModal(hotelName) {
//     var modal = document.getElementById('reviewModal');
//     var modalTitle = document.getElementById('modalTitle');
//     var hotelNameInput = document.getElementById('hotelName');
//
//     modal.style.display = 'block';
//     modalTitle.textContent = '리뷰 작성하기 - ' + hotelName;
//     hotelNameInput.value = hotelName;
// }
//
// // 리뷰 작성 모달 닫기
// function closeReviewModal() {
//     var modal = document.getElementById('reviewModal');
//     modal.style.display = 'none';
// }
//
// // 모달 외부를 클릭하여 닫을 수 있도록 설정
// window.onclick = function(event) {
//     var modal = document.getElementById('reviewModal');
//     if (event.target === modal) {
//         modal.style.display = 'none';
//     }
// };

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("filter-form");
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        const url = new URL(form.action);
        const params = new URLSearchParams(new FormData(form)).toString();
        window.location.href = `${url}?${params}`;
    });

    // 쿼리 매개변수를 확인하여 알림 표시
    const urlParams = new URLSearchParams(window.location.search);
    const alreadyPosted = urlParams.get('alreadyPosted');

    if (alreadyPosted === 'true' && !sessionStorage.getItem('alertShown')) {
        alert("이미 후기를 작성했습니다.");
        sessionStorage.setItem('alertShown', 'true');
    } else {
        sessionStorage.removeItem('alertShown');
    }
});

function confirmDelete() {
    if (confirm("예약을 취소하시겠습니까?")) {
        document.getElementById("deleteForm").submit();
    }
}
