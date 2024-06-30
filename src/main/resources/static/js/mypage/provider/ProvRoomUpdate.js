$(document).ready(function() {
    // 수정하기 버튼 클릭 시 처리
    $('.btn-update').on('click', function(event) {
        event.preventDefault(); // 폼 제출 방지

        if (confirm("수정을 하시겠습니까?")) {
            alert("객실 수정이 완료되었습니다");
            document.getElementById("room-update").submit(); // 폼 제출
        } else {
            alert("객실 수정이 취소되었습니다");
        }
    });

    // 객실 정보 로드 함수 호출
    loadRoomInfo();

    function loadRoomInfo() {
        // 각 입력 필드에 객실 정보 설정
        document.getElementById('roomId').value = room.roomId;
        document.getElementById('roomName').value = room.roomName;
        document.getElementById('roomNumber').value = room.roomNumber;
        document.getElementById('roomNormalPeople').value = room.roomNormalPeople;
        document.getElementById('roomMaxPeople').value = room.roomMaxPeople;
        document.getElementById('roomArea').value = room.roomArea;
        document.getElementById('roomPrice').value = room.roomPrice;
        document.getElementById('roomBedGrade').value = room.roomBedGrade;
        document.getElementById('roomBed').value = room.roomBed;
        document.getElementById('roomBathroom').value = room.roomBathroom;
        document.getElementById('roomPicture1').value = room.roomPicture1;

        if (room.roomSmoke === 'YES') {
            document.getElementById('roomSmoke1').checked = true;
        } else {
            document.getElementById('roomSmoke2').checked = true;
        }
    }
});
