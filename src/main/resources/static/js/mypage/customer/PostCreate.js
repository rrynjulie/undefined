document.addEventListener('DOMContentLoaded', function () {
    const stars = document.querySelectorAll('.star');
    const starsInput = document.getElementById('postGradeInput');
    const initialRating = starsInput.value;

    function highlightStars(rating) {
        stars.forEach(star => {
            if (parseInt(star.getAttribute('data-value')) <= rating) {
                star.classList.add('selected');
            } else {
                star.classList.remove('selected');
            }
        });
    }

    // 초기 로드 시 초기 평점을 표시
    if (initialRating) {
        highlightStars(initialRating);
    }

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const value = this.getAttribute('data-value');
            starsInput.value = value;
            highlightStars(value);
        });
    });
});



function validateForm() {
    // 평점 선택 확인
    const rating = document.getElementById('postGradeInput').value;
    if (!rating) {
        alert('평점을 선택해주세요.');
        return false;
    }

    // 후기글 입력 확인
    const postText = document.getElementById('textInput').value.trim();
    if (!postText) {
        alert('후기글을 입력해주세요.');
        return false;
    }

    // 모든 조건이 충족되면 true 반환하여 제출 진행
    return true;
}