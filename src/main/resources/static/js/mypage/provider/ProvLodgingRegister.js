document.addEventListener("DOMContentLoaded", function() {
    const registrationForm = document.getElementById('registrationForm1');

    registrationForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const requiredFields = [
            { id: 'fileInput1', label: '숙소 이미지' },
            { id: 'name', label: '숙소 이름' },
            { id: 'type', label: '숙소 타입' },
            { id: 'region', label: '지역' },
            { id: 'sub-region', label: '하위 지역' },
            { id: 'address', label: '숙소 주소' },
            // { id: 'phone', label: '숙소 전화번호' },
            // { id: 'url', label: '숙소 URL' },
            { id: 'checkin', label: '숙소 입실시간' },
            { id: 'checkout', label: '숙소 퇴실시간' },
            // { id: 'description', label: '숙소 소개' },
            // { id: 'facilities', label: '숙소 시설 및 서비스' },
            // { id: 'usinginfo', label: '숙소 이용 안내' },
            // { id: 'notice', label: '숙소 예약 공지' },
            { id: 'ownername', label: '담당자 이름' },
            { id: 'ownerphone', label: '담당자 전화번호' },
            // { id: 'owneremail', label: '담당자 이메일' },
            { id: 'ownerid', label: '사업자등록번호' },
        ];

        let allValid = true;
        let invalidFields = [];

        const phoneRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        requiredFields.forEach(field => {
            const input = document.getElementById(field.id);
            if (!input || input.value.trim() === '') {
                allValid = false;
                invalidFields.push(field.label);
            } else {
                if (field.id === 'phone' && !phoneRegex.test(input.value.trim())) {
                    allValid = false;
                    invalidFields.push(`${field.label}의 형식이 올바르지 않습니다.`);
                }
                if (field.id === 'owneremail' && !emailRegex.test(input.value.trim())) {
                    allValid = false;
                    invalidFields.push(`${field.label}의 형식이 올바르지 않습니다.`);
                }
            }
        });

        if (invalidFields.length === requiredFields.length) {
            alert('필수 항목을 입력(선택)해 주세요.');
            return;
        }

        if (!allValid) {
            const errorMessage = `아래 필수 항목을 입력(선택)해 주세요:\n- ${invalidFields.join('\n- ')}`;
            alert(errorMessage);
            return;
        }

        // 사용자에게 등록 여부를 확인 받음
        if (confirm("숙소를 등록 하시겠습니까?")) {
            // 숙소 등록 처리
            alert('숙소가 등록되었습니다.');
            submitForm(); // 숙소 등록 처리
        } else {
            alert("숙소 등록이 취소되었습니다."); // 경고창 표시
            // 여기서 추가로 할 일이 없기 때문에 폼 제출을 막고 경고창만 표시
        }
    });

    function submitForm() {
        // 숙소 등록 처리 로직 구현
        // 예를 들어, Ajax 요청을 통해 서버로 데이터를 전송하거나, 폼을 서버로 제출할 수 있음
        registrationForm.submit(); // 기본 폼 제출
    }

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
        subRegionSelect.appendChild(option);
    }
});
