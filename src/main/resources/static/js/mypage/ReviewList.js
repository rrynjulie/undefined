document.addEventListener("DOMContentLoaded", function() {
    // Event listeners for buttons
    document.getElementById("manage-info-btn").addEventListener("click", function() {
        window.location.href = "../../../templates/views/mypage/ManageAccount.html";
    });

    document.getElementById("reservation-btn").addEventListener("click", function() {
        window.location.href = "../../../templates/views/mypage/MyPage.html";
    });

    document.getElementById("review-btn").addEventListener("click", function() {
        window.location.href = "../../../templates/views/mypage/ReviewList.html";
    });

    document.getElementById("coupon-btn").addEventListener("click", function() {
        window.location.href = "../../../templates/views/mypage/CouponList.html";
    });
});
