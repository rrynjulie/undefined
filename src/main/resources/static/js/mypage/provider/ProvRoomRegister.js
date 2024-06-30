document.addEventListener("DOMContentLoaded", function() {
    const registrationForm = document.getElementById('room-registration');

    registrationForm.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 제출 동작 막기

        const requiredFields = [
            { id: 'roomName', label: '객실명' },
            { id: 'roomNormalPeople', label: '객실 기준 인원수' },
            { id: 'roomMaxPeople', label: '객실 최대 인원수' },
            { id: 'roomPrice', label: '객실 가격' },
            { id: 'roomNumber', label: '객실 호수' },
            { id: 'roomArea', label: '객실 면적' },
            { id: 'roomBed', label: '객실 침대 개수' },
            { id: 'roomBedGrade', label: '객실 침대 유형' },
            { id: 'roomBathroom', label: '객실 욕실 개수' }
            // { id: 'smoke', label: '객실 흡연 여부' },
            // { id: 'notion', label: '숙소 공지사항 및 소개' }
        ];

        let allValid = true;
        let invalidFields = [];

        requiredFields.forEach(field => {
            const input = document.getElementById(field.id);
            if (!input || input.value.trim() === '') {
                allValid = false;
                invalidFields.push(field.label);
            } else if (['roomNormalPeople', 'roomMaxPeople', 'roomPrice', 'roomArea', 'roomBed'].includes(field.id)) {
                if (!isNumeric(input.value.trim())) {
                    allValid = false;
                    invalidFields.push(`${field.label}을(를) 숫자로 입력해 주세요.`);
                }
            }
        });

        // 모든 필수 필드가 입력되지 않은 경우 처리
        if (invalidFields.length > 0) {
            const errorMessage = `아래 필수 항목을 입력해 주세요:\n- ${invalidFields.join('\n- ')}`;
            alert(errorMessage);
            return;
        }

        // 사용자에게 객실 등록 여부를 물어보는 confirm 대화상자 표시
        const confirmed = confirm("객실을 등록하시겠습니까?");
        if (confirmed) {
            // 확인을 클릭한 경우 폼 제출
            registrationForm.submit();
        } else {
            alert("객실 등록이 취소 되었습니다.");
            return;
        }
    });

    function isNumeric(value) {
        return /^\d+$/.test(value);
    }
});
