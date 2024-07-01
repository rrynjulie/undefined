document.addEventListener('DOMContentLoaded', function() {
    var currentDateElement = document.getElementById('currentDate');
    var currentDate = new Date();
    currentDateElement.textContent = '현재 날짜 및 시간: ' + currentDate.toLocaleString();
});

function goBack() {
    window.history.back();
}