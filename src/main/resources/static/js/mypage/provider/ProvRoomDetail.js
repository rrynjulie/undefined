let currentIndex = 0;
showSlides(currentIndex);

// 3초마다 페이지가
setInterval(function() {
    plusSlide(0);
}, 3000);

function plusSlide(idx) {
    showSlides(currentIndex += idx);
}

function currentSlide(idx) {
    showSlides(currentIndex = idx);
}

function showSlides(idx) {
    const slides = document.querySelectorAll('.my-slides');
    const dots = document.querySelectorAll('.dot');

    if (idx >= slides.length) {
        currentIndex = 0;
    } else if (idx < 0) {
        currentIndex = slides.length - 1;
    }

    slides.forEach((slide, i) => {
        slide.style.display = 'none';
        dots[i].classList.remove('active');
    });

    slides[currentIndex].style.display = 'block';
    dots[currentIndex].classList.add('active');
}
