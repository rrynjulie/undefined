document.addEventListener('DOMContentLoaded', function () {
    const text = document.getElementById('lodging-text');
    const searchWord = sessionStorage.getItem('searchWord') || '';

    const selectedDate = document.getElementById("selectedDate");
    const startDate = new Date(sessionStorage.getItem('startDate'));
    const endDate = new Date(sessionStorage.getItem('endDate'));

    const totalDiv = document.getElementsByClassName('total');
    const adultCount = parseInt(sessionStorage.getItem('adultCount')) || 0;
    const childCount = parseInt(sessionStorage.getItem('childCount')) || 0;

    console.log('searchWord: ' + searchWord);
    console.log('startDate: ' + startDate);
    console.log('endDate: ' + endDate);
    console.log('adultCount: ' + adultCount);
    console.log('childCount: ' + childCount);

    text.value = searchWord;

    selectedDate.textContent = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}`;

    for (let i = 0; i < totalDiv.length; i++) {
        totalDiv[i].textContent = `인원: 성인 ${adultCount}, 아동 ${childCount}`;
    }

    // 초기로드시 전체 버튼에 스타일 적용
    document.querySelector('.btn1.ALL').style.backgroundColor = '#FC5185';
    document.querySelector('.btn1.ALL').style.color = 'white';

    // 검색 버튼 클릭 시
    document.getElementById('search').addEventListener('click', function() {
        const location = text.value.trim();
        sessionStorage.setItem('searchWord', location);
        filterLodging();
    });

    // 엔터 키 입력 처리
    text.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            const location = text.value.trim();
            sessionStorage.setItem('searchWord', location);
            filterLodging();
        }
    });

    // 숙소 타입 버튼 클릭 시
    $('.btn1').click(function() {
        var type = $(this).text();
        $('.btn1').removeClass('active');   // class = btn1 을 누르면 active  value 값을 가진 것을 삭제
        $(this).addClass('active');
        sessionStorage.setItem('selectedType', type); // 선택한 타입을 sessionStorage에 저장
        filterLodging(type, this); // 버튼의 타입과 자신을 전달
    });

    // 가격 필터링 변경 시
    $('#priceFilter').change(function() {
        filterLodging();
    });

    // 숙소 필터링 함수
    function filterLodging(type, btn1) {
        // 모든 버튼의 배경색을 초기화
        var buttons = document.querySelectorAll('.btn1');
        buttons.forEach(function(button) {
            button.style.backgroundColor = ''; // 기본 값으로 설정
            button.style.color = 'black';
        });

        // 선택한 버튼의 배경색을 변경
        if (btn1) {     // if (btn1) => btn1 이 null, false, nan 등 이 아닌 존재하는 요소면 if 문 진행
            btn1.style.backgroundColor = '#FC5185';
            btn1.style.color = 'white';
        }

        // sessionStorage에서 선택한 타입 가져오기
        var selectedType = sessionStorage.getItem('selectedType');
        if (selectedType) {     // 위 if 문과 같이 selectedType 이 존재하는 요소라면 if 문 진행
            // 선택한 타입 버튼을 다시 스타일링
            var activeButton = document.querySelector('.btn1.active');
            if (activeButton) {
                activeButton.style.backgroundColor = '#FC5185';
                activeButton.style.color = 'white';
            }
        }

        // 필터링 로직 추가 (필요한 경우)
        console.log(type);

        var price = $('#priceFilter').val();    // #priceFilter 는 select 요소이고 여기서 .val() 은 select 요소 안에 있는 option 을 말함
        var location = sessionStorage.getItem('searchWord');
        var selectedType = $('.btn1.active').text(); // 현재 선택된 타입 가져오기

        $.ajax({
            type: 'POST',
            url: '/lodging/LodgingList/price',
            data: {
                location: location,
                price: price,
                type: selectedType // 현재 선택된 타입 전달
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

    // 초기 필터링 적용
    filterLodging();

    // 결과 개수 업데이트 함수
    function updateResultCount() {
        const resultCount = $('.item-list1').length;
        $('#result-filter').text(resultCount + '개의 검색 결과');
    }
});