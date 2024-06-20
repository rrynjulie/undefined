// document.addEventListener("DOMContentLoaded", function() {
//     // Event listeners for buttons
//     document.getElementById("manage-info-btn").addEventListener("click", function() {
//         window.location.href = "../../../../templates/views/mypage/customer/ManageAccount.html";
//     });
//
//     document.getElementById("reservation-btn").addEventListener("click", function() {
//         window.location.href = "../../../../templates/views/mypage/customer/BookingList.html";
//     });
//
//     document.getElementById("review-btn").addEventListener("click", function() {
//         window.location.href = "../../../../templates/views/mypage/customer/ReviewList.html";
//     });
//
//     document.getElementById("coupon-btn").addEventListener("click", function() {
//         window.location.href = "../../../../templates/views/mypage/customer/CouponList.html";
//     });
// });
//
//

// 리뷰 작성 모달 열기
function openReviewModal(hotelName) {
    var modal = document.getElementById('reviewModal');
    var modalTitle = document.getElementById('modalTitle');
    var hotelNameInput = document.getElementById('hotelName');

    modal.style.display = 'block';
    modalTitle.textContent = '리뷰 작성하기 - ' + hotelName;
    hotelNameInput.value = hotelName;
}

// 리뷰 작성 모달 닫기
function closeReviewModal() {
    var modal = document.getElementById('reviewModal');
    modal.style.display = 'none';
}

// 모달 외부를 클릭하여 닫을 수 있도록 설정
window.onclick = function(event) {
    var modal = document.getElementById('reviewModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};

