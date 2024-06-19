document.addEventListener("DOMContentLoaded", function() {
    // Event listeners for buttons
    document.getElementById("manage-info-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/ManageAccount.html";
    });

    document.getElementById("reservation-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/BookingList.html";
    });

    document.getElementById("review-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/ReviewList.html";
    });

    document.getElementById("coupon-btn").addEventListener("click", function() {
        window.location.href = "../../../../templates/views/mypage/customer/CouponList.html";
    });
});
