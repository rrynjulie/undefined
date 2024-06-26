function myFunction(y) {
    var x = document.getElementById("nav-menu");
    if (x.style.display === "flex") {
        x.style.display = "none";
    } else {
        x.style.display = "flex";
    }
    y.classList.toggle("change");
}


function logout() {
    fetch('/user/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'same-origin'
    })
        .then(response => {
            if (response.ok) {
                window.location.reload(); // 로그아웃 성공 시 페이지 리로드
            } else {
                alert('로그아웃에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('로그아웃 중 오류 발생:', error);
            alert('로그아웃 중 오류가 발생했습니다.');
        });
}