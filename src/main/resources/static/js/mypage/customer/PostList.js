document.addEventListener('DOMContentLoaded', function() {
    var starsContainers = document.querySelectorAll('.stars');
    starsContainers.forEach(function(container) {
        var starCount = container.getAttribute('data-star-count');
        var stars = container.querySelectorAll('.star');

        // Loop through stars and fill them based on data-star-count
        for (var i = 0; i < starCount; i++) {
            stars[4 - i].classList.add('filled'); // 오른쪽에서 왼쪽으로 색칠하도록 수정
        }
    });
});


function deleteOk(event) {
    event.preventDefault(); // 기본 동작(폼 제출) 방지

    var confirmDelete = confirm('선택한 후기를 삭제하시겠습니까?');
    if (confirmDelete) {
        event.target.closest('form').submit(); // 폼 제출
    }
}