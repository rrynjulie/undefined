document.addEventListener('DOMContentLoaded', function () {
    const selectedDate = document.getElementById('selectedDate');   // 입실일 ~ 퇴실일 n박 div 의 id
    const total = document.getElementById('total');     // 성인 아동 수 div 의 id

    const calendarBody = document.querySelector('#calendar-body');
    const dateDialog = document.getElementById('dateDialog');       // 달력 dialog 의 id
    const saveDateBtn = document.getElementById('saveDateBtn');     // 달력 저장 버튼
    const monthYear = document.getElementById('month-year');        // 달력 dialog 안에 년 월
    const prevButton = document.getElementById('prev');     // 달력 이전 버튼
    const nextButton = document.getElementById('next');     // 달력 다음 버튼
    const peopleDialog = document.getElementById('peopleDialog');   // 인원수 변경 dialog 의 id
    const savePeopleBtn = document.getElementById('savePeopleBtn'); // 인원수 저장 버튼
    const decreaseBtn1 = document.getElementById('decrease-btn1');  // 성인 수 감소 버튼
    const increaseBtn1 = document.getElementById('increase-btn1');  // 성인 수 증가 버튼
    const adultCountDiv = document.getElementById('adult-count');   // 성인 text 의 id
    const decreaseBtn2 = document.getElementById('decrease-btn2');  // 아동 수 감소 버튼
    const increaseBtn2 = document.getElementById('increase-btn2');  // 아동 수 증가 버튼
    const childCountDiv = document.getElementById('child-count');   // 아동 text 의 id

    let currentDate = new Date();   // 현재 시간을 currentDate 변수에 저장
    // let startDate = null;
    let endDate = null;
    let today = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()); //현재날짜 저장
    let startDate = sessionStorage.getItem('startDate') ? new Date(sessionStorage.getItem('startDate')) : new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
    let lastClickedCells = [];

    let adultCount = 1;
    let childCount = 0;

    // 세션 스토리지에서 데이터 가져오기
    function loadData() {
        const storedStartDate = sessionStorage.getItem('startDate') || new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
        const storedEndDate = sessionStorage.getItem('endDate') || new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() + 1);
        if (storedStartDate && storedEndDate) {
            startDate = new Date(storedStartDate);
            endDate = new Date(storedEndDate);
            const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));    // 숙박일 수를 nights 에 저장
            selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}, ${nights}박`;
        }
        adultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
        childCount = parseInt(sessionStorage.getItem('childCount')) || 0;
        total.innerHTML = `성인 ${adultCount}, 아동 ${childCount}`;
        adultCountDiv.textContent = adultCount;
        childCountDiv.textContent = childCount;
    }

    // 캘린더 렌더링 함수
    function renderCalendar(date) {
        const month = date.getMonth();
        const year = date.getFullYear();
        // 매월 첫 시작일이 언제인지 .getDay() 를 사용해 몇번째 요일에 시작하는지 알기위해서 .getDay() 사용  => 요일은 0 ~ 6 [ 0 = 일요일, 5 = 금요일 ]
        const firstDate = new Date(year, month, 1).getDay();
        // 매월 마지막 날이 언제인지 .getDate() 를 사용해 몇번째 날짜(date) 에 끝나는지 알기위해서 .getDate() 사용 => date(일) 은 0 이 전 달의 마지막 날을 가르킴
        const lastDate = new Date(year, month + 1, 0).getDate();

        // month 와 day 는 배열이 아닌 script 에서 단순히 0부터 저장시키는 것.

        calendarBody.innerHTML = '';    // 달력을 열거나 다음달로 넘길 때 마다 기존 달력을 초기화한 후 로딩
        monthYear.textContent = `${year}년 ${month + 1}월`;

        let row = document.createElement('tr');

        for (let i = 0; i < firstDate; i++) {
            let cell = document.createElement('td');
            row.append(cell);
        }

        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement('td');
            row.append(cell);
            cell.textContent = date;

            let cellDate = new Date(year, month, date);

            if (cellDate < today) {
                cell.style.color = 'lightgray'; //과거 날짜 회색표시
                cell.style.pointerEvents = 'none'; //선택 못하게
            }

            // 입실일과 퇴실일 사이의 날짜 색상 처리
            if (startDate && endDate) {     // 입실일과 퇴실일이 선택되었을 때
                const start = startDate.getTime();
                const end = endDate.getTime();
                const cellTime = cellDate.getTime();
                if (cellTime > start && cellTime < end) {
                    cell.style.backgroundColor = '#FFEBEF'; // 입실일과 퇴실일 사이의 날짜 색상
                }
            }

            // toDateString() 을 이용하는 이유? -> toDateString() 을 사용하지 않고 Date 객체끼리 비교를 하게 되면 시,분,초 를 포함해서 계산해야하지만
            // toDateString() 메소드를 사용하게 되면 시,분,초 를 계산하지 않아도 될 상황에는 간결하게 코드를 짤 수 있기 때문
            if (startDate && cellDate.toDateString() === startDate.toDateString()) {
                cell.style.backgroundColor = '#FC5185';
                lastClickedCells.push(cell);
            } else if (endDate && cellDate.toDateString() === endDate.toDateString()) {
                cell.style.backgroundColor = '#FC5185';
                lastClickedCells.push(cell);
            }

            cell.addEventListener('click', function() {
                if (!startDate) {
                    lastClickedCells.forEach(function(cell) {
                        cell.style.backgroundColor = 'white';   // 입실일로 선택하지 않은 cell 은 흰색으로
                    });
                    lastClickedCells = [];
                    startDate = new Date(year, month, date);
                    cell.style.backgroundColor = '#FC5185';
                    lastClickedCells.push(cell);
                    saveSelectedDate();
                } else if (!endDate) {
                    if (startDate < cellDate) {
                        endDate = new Date(year, month, date);
                        lastClickedCells.push(cell);
                        renderCalendar(currentDate); // 달력을 다시 렌더링하여 색 업데이트
                        saveSelectedDate()
                    }
                    const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
                    selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}, ${nights}박`;
                    saveSelectedDate();

                } else {
                    lastClickedCells.forEach(function(cell) {
                        cell.style.backgroundColor = 'white';
                    });
                    lastClickedCells = [];
                    startDate = new Date(year, month, date);
                    endDate = null;
                    cell.style.backgroundColor = '#FC5185';
                    lastClickedCells.push(cell);
                    saveSelectedDate();
                }
            });

            if (row.children.length === 7 || date === lastDate) {
                calendarBody.append(row);
                row = document.createElement('tr');
            }
        }
    }

    // 선택한 날짜를 저장
    function saveSelectedDate() {
        sessionStorage.setItem('startDate', startDate ? startDate.toISOString() : '');
        sessionStorage.setItem('endDate', endDate ? endDate.toISOString() : '');
    }

    // 인원수 업데이트 함수
    function updateCount() {
        adultCountDiv.textContent = `성인: ${adultCount}명`;
        childCountDiv.textContent = `아동: ${childCount}명`;
        total.innerHTML = `성인 ${adultCount}, 아동 ${childCount}`;
        sessionStorage.setItem('adultCount', adultCount);
        sessionStorage.setItem('childCount', childCount);
    }

    // 성인 인원수 증가/감소 버튼 이벤트 리스너 추가
    decreaseBtn1.addEventListener('click', () => {
        if (adultCount > 1) {
            adultCount--;
            updateCount();
        }
    });

    increaseBtn1.addEventListener('click', () => {
        adultCount++;
        updateCount();
    });

    // 아동 인원수 증가/감소 버튼 이벤트 리스너 추가
    decreaseBtn2.addEventListener('click', () => {
        if (childCount > 0) {
            childCount--;
            updateCount();
        }
    });

    increaseBtn2.addEventListener('click', () => {
        childCount++;
        updateCount();
    });

    // 이전 달로 이동
    prevButton.addEventListener('click', function() {
        currentDate.setMonth(currentDate.getMonth() - 1);
        renderCalendar(currentDate);
    });

    // 다음 달로 이동
    nextButton.addEventListener('click', function() {
        currentDate.setMonth(currentDate.getMonth() + 1);
        renderCalendar(currentDate);
    });

    // 날짜 다이얼로그 저장하고 닫기
    saveDateBtn.addEventListener('click', function() {
        if (startDate && endDate) {
            dateDialog.style.display = 'none';
            saveSelectedDate();
        } else {
            alert('퇴실일을 선택하세요.');
        }
    });

    // 인원수 다이얼로그 저장하고 닫기
    savePeopleBtn.addEventListener('click', function() {
        peopleDialog.style.display = 'none';
        updateCount();
    });

    // 날짜 다이얼로그 열기
    function openDateDialog() {
        dateDialog.style.display = 'block';
        renderCalendar(currentDate);
    }
    // 닫기 버튼 클릭 시 모달 숨기기
    var closeBtn1 = dateDialog.querySelector("#close1");
    closeBtn1.onclick = function() {
        dateDialog.style.display = "none";
    }
    var closeBtn2 = peopleDialog.querySelector("#close2");
    closeBtn2.onclick = function() {
        peopleDialog.style.display = "none";
    }

    // 인원수 다이얼로그 열기
    function openPeopleDialog() {
        let adultCount = parseInt(sessionStorage.getItem('adultCount'));
        let childCount = parseInt(sessionStorage.getItem('childCount'));
        peopleDialog.style.display = 'block';
        adultCountDiv.textContent = `성인: ${adultCount}명`;
        childCountDiv.textContent = `아동: ${childCount}명`;
    }

    // 날짜 정보를 클릭하면 날짜 다이얼로그 열기
    selectedDate.addEventListener('click', openDateDialog);

    // 인원수 정보를 클릭하면 인원수 다이얼로그 열기
    total.addEventListener('click', openPeopleDialog);

    // 페이지 로드 시 데이터 불러오기
    loadData();
});




const apiKey = 'AIzaSyAes70WE_fWcC9y72SFcpItg17PDMU5Prg';

function initMap() {
    const lodgingAddress = document.getElementById('address').innerText.trim();
    const geocoder = new google.maps.Geocoder();    // 좌표 변환하는 변수

    geocoder.geocode({ address: lodgingAddress }, function(results) {   // address 주소를 geocoder 좌표로 변환

        const location = results[0].geometry.location;
        const maps = new google.maps.Map(document.getElementById('address'), {   // 페이지를 열 때 마다 maps 객체를 초기화하여 새로운 값 적용
            zoom: 15,
            center: location    // 지도 중심 위치
        });
        new google.maps.Marker({
            position: location, // 마커 위치
            map: maps    // 마커 추가할 지도 객체
        });
    });
}
const script = document.createElement('script');    // google API 는 외부 스크립트라서 동적으로 사용하기 위해 새로운 script 를 만듦
script.src = `https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap`;  // script 가 실행될 때 마다 initMap 함수를 호출하여 지도 초기화
document.body.append(script);   // script 를 html body 에 선언


document.addEventListener('DOMContentLoaded', function() {
    var starsContainers = document.querySelectorAll('.stars');
    starsContainers.forEach(function(container) {
        var starCount = container.getAttribute('data-star-count');
        var stars = container.querySelectorAll('.star');
        for (var i = 0; i < starCount; i++) {
            stars[i].classList.add('filled');
        }
    });
});


// 좋아요 버튼 JS

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.like-button').forEach(function(button) {
        button.addEventListener('click', function() {
            var userId = this.getAttribute('data-user-id');
            var lodgingId = this.getAttribute('data-lodging-id');
            var isLiked = this.getAttribute('data-is-liked') === 'true';

            var actionUrl = isLiked ? '/lodging/removeLove' : '/lodging/addLove';

            fetch(actionUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: `userId=${userId}&lodgingId=${lodgingId}`
            })
                .then(response => {
                    if (response.ok) {
                        this.setAttribute('data-is-liked', !isLiked);
                        this.classList.toggle('liked');
                        alert(!isLiked ? '좋아요가 추가되었습니다.' : '좋아요가 취소되었습니다.');
                    } else {
                        alert('오류가 발생했습니다. 다시 시도해주세요.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('오류가 발생했습니다. 다시 시도해주세요.');
                });
        });
    });
});