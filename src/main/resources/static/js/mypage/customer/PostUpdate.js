document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('.star');
    const starsInput = document.getElementById('stars');
    const initialRating = starsInput.value;

    function highlightStars(value) {
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= value) {
                star.classList.add('highlighted');
            } else {
                star.classList.remove('highlighted');
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