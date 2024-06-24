document.addEventListener("DOMContentLoaded", function() {
    const registrationForm = document.getElementById('registrationForm');

    registrationForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const requiredFields = [
            { id: 'roomname', label: '객실 이름' },
            { id: 'normal', label: '객실 기준 인원수' },
            { id: 'max', label: '객실 최대 인원수' },
            { id: 'price', label: '객실 가격' },
            // { id: 'grade', label: '객실 등급' },
            { id: 'nm', label: '객실 호수' },
            { id: 'area', label: '객실 면적' },
            { id: 'bedcnt', label: '객실 침대 개수' },
            { id: 'roombedtype', label: '객실 침대 유형' },
            { id: 'bathcnt', label: '객실 욕실 개수' },
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
            } else if (['normal', 'max', 'price', 'area', 'bedcnt'].includes(field.id)) {
                if (!isNumeric(input.value.trim())) {
                    allValid = false;
                    invalidFields.push(`${field.label}을(를) 숫자로 입력해 주세요.`);
                }
            }
        });

        // 모든 필수 필드가 입력되지 않은 경우 처리
        if (invalidFields.length === requiredFields.length) {
            alert('모든 필수 항목을 입력(선택)해 주세요.');
            return;
        }

        // 일부 필수 필드가 입력되지 않은 경우 처리
        if (!allValid) {
            const errorMessage = `아래 필수 항목을 입력(선택)해 주세요:\n- ${invalidFields.join('\n- ')}`;
            alert(errorMessage);
            return;
        }

        // 모든 필수 필드가 입력된 경우
        alert('객실이 추가되었습니다.');
        registrationForm.submit();
    });

    function isNumeric(value) {
        return /^\d+$/.test(value);
    }
});



// function uploadRoomImages() {
//     var fileInput2 = document.getElementById('fileInput2');
//     var formData = new FormData();
//
//     // 모든 선택된 파일들을 FormData에 추가
//     for (var i = 0; i < fileInput2.files.length; i++) {
//         formData.append('files', fileInput2.files[i]);
//     }
//
//     // 객실 이미지를 업로드하는 AJAX 요청
//     $.ajax({
//         type: 'POST',
//         url: '/mypage/provider/uploadRoomImages',  // 객실 이미지 업로드 처리하는 경로에 맞게 수정
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function(response) {
//             console.log('객실 이미지 업로드 성공');
//             console.log(response);  // 확인용 로그
//
//             alert('객실 이미지가 성공적으로 업로드되었습니다.');
//         },
//         error: function(err) {
//             console.error('객실 이미지 업로드 에러');
//             alert('객실 이미지 업로드 중 오류가 발생했습니다.');
//         }
//     });
// }
