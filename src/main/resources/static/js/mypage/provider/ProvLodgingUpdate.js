// ProvLodgingUpdate.js

// 페이지 로드 후 실행되는 함수
document.addEventListener('DOMContentLoaded', function() {
    // 초기화 로직 등
    loadLodgingInfo(); // 숙소 정보 로드 함수 호출
});

// 숙소 정보 로드 함수
function loadLodgingInfo() {

    // 각 입력 필드에 숙소 정보 설정
    document.getElementById('lodgingId').value = lodging.lodgingId;
    document.getElementById('lodgingPicture1').value = lodging.lodgingPicture1;
    document.getElementById('lodgingName').value = lodging.lodgingName;
    document.getElementById('lodgingType').value = lodging.lodgingType;
    document.getElementById('lodgingLocation1').value = lodging.lodgingLocation1;
    document.getElementById('lodgingLocation2').value = lodging.lodgingLocation2;
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
