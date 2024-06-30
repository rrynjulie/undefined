$(document).ready(function() {
    // 숙소 정보 로드 함수 호출
    loadLodgingInfo();

    // 상위 지역에 따라 하위 지역 옵션 업데이트
    const regionSelect = document.getElementById('region');
    const subRegionSelect = document.getElementById('sub-region');

    regionSelect.addEventListener('change', function() {
        const selectedRegion = this.value;
        updateSubRegionOptions(selectedRegion);
    });

    function updateSubRegionOptions(region) {
        subRegionSelect.innerHTML = ''; // 기존 옵션 초기화

        switch (region) {
            case '서울특별시':
                addOption('중구');
                addOption('강남구');
                addOption('동대문구');
                addOption('서초구');
                addOption('성북구');
                addOption('노원구');
                addOption('금천구');
                addOption('용산구');
                addOption('강동구');
                addOption('강북구');
                addOption('마포구');
                break;
            case '부산광역시':
                addOption('해운대구');
                break;
            case '인천광역시':
                addOption('강화군');
                addOption('중구');
                addOption('남동구');
                break;
            case '제주특별자치도':
                addOption('제주시');
                addOption('서귀포시');
                break;
            case '경기도':
                addOption('수원시');
                addOption('고양시');
                addOption('용인시');
                break;
            case '경상남도':
                addOption('창원시');
                addOption('김해시');
                break;
            case '경상북도':
                addOption('포항시');
                addOption('경주시');
                break;
            case '전라남도':
                addOption('여수시');
                addOption('순천시');
                break;
            case '충청남도':
                addOption('천안시');
                addOption('아산시');
                break;
            case '강원도':
                addOption('춘천시');
                addOption('강릉시');
                break;
            case '대전광역시':
                addOption('서구');
                break;
            case '대구광역시':
                addOption('북구');
                break;
            case '울산광역시':
                addOption('울주군');
                break;
            default:
                addOption('하위 지역을 선택해 주세요.');
        }
    }

    function addOption(subRegion) {
        const option = document.createElement('option');
        option.textContent = subRegion;
        option.value = subRegion;
        subRegionSelect.appendChild(option);
    }

    // 숙소 정보 로드 함수
    function loadLodgingInfo() {
        // 각 입력 필드에 숙소 정보 설정
        document.getElementById('lodgingId').value = lodging.lodgingId;
        document.getElementById('fileInput1').value = lodging.lodgingPicture1;
        document.getElementById('lodgingName').value = lodging.lodgingName;
        document.getElementById('lodgingType').value = lodging.lodgingType;
        document.getElementById('region').value = lodging.lodgingLocation1;

        // 하위 지역 선택 옵션 업데이트
        updateSubRegionOptions(lodging.lodgingLocation1);
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
