document.addEventListener('DOMContentLoaded', function () {
    const text = document.getElementById('lodging-text1')
    const searchWord = sessionStorage.getItem('searchWord')

    const selectedDate = document.getElementById("selectedDate");
    const startDate = new Date(sessionStorage.getItem('startDate'));
    const endDate = new Date(sessionStorage.getItem('endDate'));

    const totalDiv = document.getElementsByClassName('total');
    const adultCount = parseInt(sessionStorage.getItem('adultCount'));
    const childCount = parseInt(sessionStorage.getItem('childCount'));

    console.log('searchWord: ' + searchWord);
    console.log('startDate: ' + startDate);
    console.log('endDate: ' + endDate);
    console.log('adultCount: ' + adultCount);
    console.log('childCount: ' + childCount);


    text.innerHTML = `${searchWord}`

    selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}`

    for (let i = 0; i < totalDiv.length; i++) {
        totalDiv[i].innerHTML = `인원: 성인 ${adultCount}, 아동 ${childCount}`;
    }

});


function filterLodging(type, btn1) {
    // 모든 버튼의 배경색을 초기화
    var buttons = document.querySelectorAll('.btn1');
    buttons.forEach(function(button) {
        button.style.backgroundColor = ''; // 기본 값으로 설정
        button.style.color = 'black';
    });

    // 선택한 버튼의 배경색을 변경
    btn1.style.backgroundColor = '#FC5185';
    btn1.style.color = 'white';
    // 필터링 로직 추가 (필요한 경우)
    console.log(type);
}

$(document).ready(function() {
    var currentType = ''; // 초기값은 빈 문자열로 설정

    // 숙소 타입 버튼 클릭 시
    $('.btn1').click(function() {
        var type = $(this).text();
        currentType = type; // 현재 선택된 타입 업데이트
        filterLodging();
    });

    // 가격 필터링 변경 시
    $('#priceFilter').change(function() {
        filterLodging();
    });

    // 숙소 필터링 함수
    function filterLodging() {
        var price = $('#priceFilter').val();
        var location = $('#location').val();

        $.ajax({
            type: 'POST',
            url: '/lodging/LodgingList/price',
            data: {
                location: location,
                price: price,
                type: currentType // 현재 선택된 타입 전달
            },
            success: function(response) {
                $('#item-list').html(response);
                updateResultCount();
            },
            error: function(error) {
                console.error('에러:', error);
            }
        });
    }

    // 결과 개수 업데이트 함수
    function updateResultCount() {
        const resultCount = $('.item-list1').length;
        $('#result-filter').text(resultCount + '개의 검색 결과');
    }
});