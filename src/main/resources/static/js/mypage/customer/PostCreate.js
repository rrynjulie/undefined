document.addEventListener('DOMContentLoaded', function() {
    // sessionStorage 초기화
    sessionStorage.removeItem('alertShown');

    const stars = document.querySelectorAll('.star');
    const postGradeInput = document.getElementById('postGradeInput'); // 숨겨진 입력 필드

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const value = this.getAttribute('data-value'); // 'data-value' 값을 가져옴
            postGradeInput.value = value; // 숨겨진 입력 필드에 값 설정
            highlightStars(value);
        });

        star.addEventListener('mouseover', function() {
            const value = this.getAttribute('data-value');
            highlightStars(value);
        });

        star.addEventListener('mouseout', function() {
            const value = postGradeInput.value; // 숨겨진 입력 필드의 값을 가져옴
            highlightStars(value);
        });
    });

    function highlightStars(value) {
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= value) {
                star.classList.add('highlighted');
            } else {
                star.classList.remove('highlighted');
            }
        });
    }
});
