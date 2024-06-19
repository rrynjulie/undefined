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

    // 모달 관련 스크립트
    var modal = document.getElementById("review-modal");
    var span = document.getElementsByClassName("close-btn")[0];

    document.querySelectorAll('.view-more-btn').forEach(button => {
        button.addEventListener('click', function() {
            var reviewId = this.getAttribute('data-review-id');
            // 여기서 reviewId를 사용하여 자세한 정보를 가져와 모달에 설정할 수 있습니다.
            document.getElementById("modal-hotel-name").textContent = "서울 호텔"; // 예시 데이터
            document.getElementById("modal-checkin-date").textContent = "2023-06-20"; // 예시 데이터
            document.getElementById("modal-checkout-date").textContent = "2023-06-25"; // 예시 데이터
            document.getElementById("modal-rating").textContent = "★★★★☆"; // 예시 데이터
            document.getElementById("modal-review-content").textContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."; // 예시 데이터
            document.getElementById("modal-image").src = "../../../static/image/hotel1.jpg"; // 예시 데이터
            modal.style.display = "block";
        });
    });

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});
