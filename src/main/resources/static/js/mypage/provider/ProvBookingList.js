// // 숙소를 선택했을 때 해당 숙소의 객실 정보를 객실 Select 요소의 Option 으로 불러오는 메소드
// function getRooms(lodgingId) {
//     let xmlHttpReq = new XMLHttpRequest();
//     xmlHttpReq.open('GET', '/mypage/provider/ProvBookingList/rooms?lodgingId=' + lodgingId, true);
//     xmlHttpReq.onload = function() {
//         if(xmlHttpReq.status == 200) {
//             let roomSelect = document.getElementById('room-select');  // 객실 select
//             let fragment = document.createElement('div');  // 객실 select 내 option
//             fragment.innerHTML = xmlHttpReq.responseText.trim();
//             roomSelect.innerHTML = fragment.querySelector('#roomList').innerHTML;
//         } else {
//             console.log('Request failed. Returned status of ' + xmlHttpReq.status);
//         }
//     };
//     xmlHttpReq.send();
// }
//
// function getBooks(roomId) {
//     let xmlHttpReq = new XMLHttpRequest();
//     xmlHttpReq.open('GET', '/mypage/provider/ProvBookingList/books?roomId=' + roomId, true);
//     xmlHttpReq.onload = function() {
//         if(xmlHttpReq.status == 200) {
//             let fragment = document.createElement('div');  // 객실 select 내 option
//         }
//     };
//     xmlHttpReq.send();
//     console.log('getRooms 왔다 갑니다 ~' + lodgingId);
// }

document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.btn-search').addEventListener('click', function(event) {
        event.preventDefault();
        const lodgingId = document.querySelector('#lodging-select').value;

        fetch(`/mypage/provider/ProvBookingList/books?lodgingId=${lodgingId}`)
            .then(response => response.text())
            .then(data => {
                document.querySelector('#booking-list').innerHTML = data;
            })
            .catch(error => console.error('Error:', error));
    });
});