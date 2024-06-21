document.addEventListener('DOMContentLoaded', function() {
    let currentImageIndex = 0;
    const images = document.querySelectorAll('.lodging-detail img');
    const totalImages = images.length;

    document.getElementById('nextButton').addEventListener('click', function() {
        images[currentImageIndex].classList.remove('active');
        currentImageIndex = (currentImageIndex + 1) % totalImages;
        images[currentImageIndex].classList.add('active');
    });

    document.getElementById('prevButton').addEventListener('click', function() {
        images[currentImageIndex].classList.remove('active');
        currentImageIndex = (currentImageIndex - 1 + totalImages) % totalImages;
        images[currentImageIndex].classList.add('active');
    });
});
