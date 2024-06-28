function myFunction(y) {
    var x = document.getElementById("nav-menu");
    if (x.style.display === "flex") {
        x.style.display = "none";
    } else {
        x.style.display = "flex";
    }
    y.classList.toggle("change");
}

function fetchUserNickname() {
    fetch('/api/user/info') // Assuming this is the endpoint to get user info
        .then(response => response.json())
        .then(data => {
            document.getElementById('header-nickname').textContent = data.nickname + ' (' + data.username + ') 님 환영합니다';
            document.getElementById('header-nickname-mobile').textContent = data.nickname + ' (' + data.username + ') 님 환영합니다';
        })
        .catch(error => console.error('Error fetching user info:', error));
}

document.addEventListener('DOMContentLoaded', function() {
    fetchUserNickname();
});