document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("filter-form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        const url = new URL(form.action);
        const params = new URLSearchParams(new FormData(form)).toString();
        window.location.href = `${url}?${params}`;
    });

    // 쿼리 매개변수를 확인하여 알림 표시
    const urlParams = new URLSearchParams(window.location.search);
    const alreadyPosted = urlParams.get('alreadyPosted');

    if (alreadyPosted === 'true' && !sessionStorage.getItem('alertShown')) {
        alert("이미 후기를 작성했습니다.");
        sessionStorage.setItem('alertShown', 'true');
    } else {
        sessionStorage.removeItem('alertShown');
    }
});


function confirmCancellation(event) {
    if (!confirm("정말 예약을 취소하시겠습니까?")) {
        event.preventDefault();
    return false;
}

    return true;
}



