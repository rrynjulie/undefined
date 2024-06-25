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
            { id: 'phone', label: '숙소 전화번호' },
            { id: 'url', label: '숙소 URL' },
            { id: 'checkin', label: '숙소 입실시간' },
            { id: 'checkout', label: '숙소 퇴실시간' },
            { id: 'description', label: '숙소 소개' },
            { id: 'facilities', label: '숙소 시설 및 서비스' },
            { id: 'usinginfo', label: '숙소 이용 안내' },
            { id: 'notice', label: '숙소 예약 공지' },
            { id: 'ownername', label: '담당자 이름' },
            { id: 'ownerphone', label: '담당자 전화번호' },
            { id: 'owneremail', label: '담당자 이메일' },
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
            alert('모든 필수 항목을 입력(선택)해 주세요.');
            return;
        }

        if (!allValid) {
            const errorMessage = `아래 필수 항목을 입력(선택)해 주세요:\n- ${invalidFields.join('\n- ')}`;
            alert(errorMessage);
            return;
        }

        alert('숙소가 추가되었습니다.');
        registrationForm.submit();
    });
});

