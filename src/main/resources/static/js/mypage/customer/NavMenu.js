document.addEventListener('DOMContentLoaded', function() {
    const links = document.querySelectorAll('.customer-menu-pc a');

    function setActiveLink() {
        links.forEach(link => {
            if (link.href === window.location.href) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });
    }

    setActiveLink();

    links.forEach(link => {
        link.addEventListener('click', function() {
            links.forEach(link => link.classList.remove('active'));
            this.classList.add('active');
        });
    });
});

//
// document.getElementById('myLink').addEventListener('click', function(event) {
//     event.preventDefault(); // Prevent the default link behavior
//     document.getElementById('myImage').src = [[${/image/Booking.png}]]; // Change the image source
// });