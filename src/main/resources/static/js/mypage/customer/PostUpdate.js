document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('.star');
    const starsInput = document.getElementById('stars');

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const value = this.getAttribute('data-value');  // 'date-value' 값을 가져와서 value 에 저장
            starsInput.value = value;  // 선택된 value 에 따라 표시
            highlightStars(value);  // highlightStars 에 저장
        });

        star.addEventListener('mouseover', function() { // 마우스를 star 에 올렸을 때
            const value = this.getAttribute('data-value');
            highlightStars(value);
        });

        star.addEventListener('mouseout', function() {  // 마우스를 star 밖으로 댈 때
            const value = starsInput.value;    // starsInput 의 value 를 value 에 저장
            highlightStars(value);
        });
    });

    function highlightStars(value) {
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= value) {   // 'date-vale' 값이 value 보다 작거나 같을 때 실행
                star.classList.add('highlighted');  // highlighted 클래스 추가
            } else {    // 클 때
                star.classList.remove('highlighted');   // highlighted 클래스 제거
            }
        });
    }
});


document.addEventListener('DOMContentLoaded', function() {
    var starsContainers = document.querySelectorAll('.stars');
    starsContainers.forEach(function(container) {
        var starCount = container.getAttribute('data-star-count');
        var stars = container.querySelectorAll('.star');
        for (var i = 0; i < starCount; i++) {
            stars[i].classList.add('filled');
        }
    });
});