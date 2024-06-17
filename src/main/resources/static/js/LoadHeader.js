function LoadHeader() {
    fetch('Header.html')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            document.getElementById("nav-bar").innerHTML = html;
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}
LoadHeader(); // 함수 실행