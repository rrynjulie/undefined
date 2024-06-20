document.addEventListener('DOMContentLoaded', function() {
    const couponRadio = document.querySelector('input[name="discount"][value="coupon"]');
    const noneRadio = document.querySelector('input[name="discount"][value="none"]');
    const couponInfo = document.querySelector('.info.coupon');
    const maxDiscountCheckbox = document.getElementById('maxDiscountCheckbox');
    const modal = document.getElementById('couponModal');
    const closeModal = modal.querySelector('.close');
    const applyCouponButton = document.getElementById('applyCoupon');
    const couponSelect = document.getElementById('couponSelect');

    // 쿠폰 선택 라디오 버튼 클릭 시 모달 열기
    couponRadio.addEventListener('change', function() {
        if (this.checked) {
            modal.style.display = 'block';
        }
    });

    // 모달 닫기 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    // 적용 버튼 클릭 시 선택한 쿠폰 정보 적용
    applyCouponButton.addEventListener('click', function() {
        const selectedCoupon = couponSelect.value;
        couponInfo.style.display = 'block';
        couponInfo.querySelector('#coupon').textContent = selectedCoupon;
        modal.style.display = 'none';
    });

    // 선택 안 함 라디오 버튼 클릭 시 쿠폰 정보 숨기기 및 최대할인적용 체크 해제
    noneRadio.addEventListener('change', function() {
        if (this.checked) {
            couponInfo.style.display = 'none';
            maxDiscountCheckbox.checked = false; // 최대할인적용 체크 해제
        }
    });

    // 최대할인적용 체크박스 처리
    maxDiscountCheckbox.addEventListener('change', function() {
        if (this.checked) {
            couponRadio.checked = true;
            modal.style.display = 'block';
        } else {
            noneRadio.checked = true;
            couponInfo.style.display = 'none';
        }
    });

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});
