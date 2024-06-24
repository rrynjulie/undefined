document.addEventListener('DOMContentLoaded', function() {
    const starRatings = document.querySelectorAll('.stars');

    starRatings.forEach(starDiv => {
        const count = starDiv.getAttribute('starCount');
        const stars = starDiv.querySelectorAll('.star');
        for (let i = 0; i < count; i++) {
            stars[i].classList.add('filled');
        }
    });
});