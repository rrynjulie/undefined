$(document).ready(function() {

    // 수정하기 버튼 클릭 시 처리
    $('.btn2').on('click', function(event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        if (confirm("수정을 하시겠습니까?")) {
            alert("숙소 수정이 완료되었습니다.");
            document.getElementById("updateForm").submit(); // 폼 제출
        } else {
            alert("숙소 수정이 취소되었습니다.");
        }
    });

    // 숙소 정보 로드 함수 호출
    loadLodgingInfo();


    // 숙소 정보 로드 함수
    function loadLodgingInfo() {
        // 각 입력 필드에 숙소 정보 설정
        document.getElementById('lodgingId').value = lodging.lodgingId;
        document.getElementById('fileInput1').value = lodging.lodgingPicture1;
        document.getElementById('lodgingName').value = lodging.lodgingName;
        document.getElementById('lodgingType').value = lodging.lodgingType;
        document.getElementById('region').value = lodging.lodgingLocation1;


        document.getElementById('sub-region').value = lodging.lodgingLocation2;

        document.getElementById('lodgingAddress').value = lodging.lodgingAddress;
        document.getElementById('lodgingNumber').value = lodging.lodgingNumber;
        document.getElementById('lodgingOpen').value = lodging.lodgingOpen;
        document.getElementById('lodgingClose').value = lodging.lodgingClose;
        document.getElementById('lodgingUrl').value = lodging.lodgingUrl;
        document.getElementById('lodgingIntroduce').value = lodging.lodgingIntroduce;
        document.getElementById('lodgingService').value = lodging.lodgingService;
        document.getElementById('lodgingUsingInfo').value = lodging.lodgingUsingInfo;
        document.getElementById('lodgingNotice').value = lodging.lodgingNotice;
        document.getElementById('lodgingOwnerName').value = lodging.lodgingOwnerName;
        document.getElementById('lodgingOwnerNumber').value = lodging.lodgingOwnerNumber;
        document.getElementById('lodgingOwnerEmail').value = lodging.lodgingOwnerEmail;
        document.getElementById('lodgingOwnerId').value = lodging.lodgingOwnerId;
        document.getElementById('userId').value = lodging.userId;
    }
});
