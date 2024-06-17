function LoadFooter() {
    fetch('Footer.html')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            document.getElementById("footer").innerHTML = html;
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

LoadFooter(); // 함수 실행