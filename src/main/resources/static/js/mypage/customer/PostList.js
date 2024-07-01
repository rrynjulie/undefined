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

function deleteOk() {
    alert('선택한 후기를 삭제하시겠습니까?')
}