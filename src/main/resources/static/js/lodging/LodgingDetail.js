document.addEventListener('DOMContentLoaded', function () {
    const selectedDate = document.getElementById('selectedDate');
    const total = document.getElementById('total');
    const dateDialog = document.getElementById('dateDialog');
    const peopleDialog = document.getElementById('peopleDialog');
    const saveDateBtn = document.getElementById('saveDateBtn');
    const savePeopleBtn = document.getElementById('savePeopleBtn');
    const calendarBody = document.querySelector('#calendar-body');
    const monthYear = document.getElementById('month-year');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const decreaseBtn1 = document.getElementById('decrease-btn1');
    const increaseBtn1 = document.getElementById('increase-btn1');
    const adultCountDiv = document.getElementById('adult-count');
    const decreaseBtn2 = document.getElementById('decrease-btn2');
    const increaseBtn2 = document.getElementById('increase-btn2');
    const childCountDiv = document.getElementById('child-count');

    let currentDate = new Date();
    let startDate = null;
    let endDate = null;
    let lastClickedCells = [];
    let adultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
    let childCount = parseInt(sessionStorage.getItem('childCount')) || 0;

    // 세션 스토리지에서 데이터 가져오기
    function loadData() {
        const storedStartDate = sessionStorage.getItem('startDate');
        const storedEndDate = sessionStorage.getItem('endDate');
        if (storedStartDate && storedEndDate) {
            startDate = new Date(storedStartDate);
            endDate = new Date(storedEndDate);
            const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
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
        const firstDate = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        calendarBody.innerHTML = '';
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

            // 입실일과 퇴실일 사이의 날짜 색상 처리
            if (startDate && endDate) {
                const start = startDate.getTime();
                const end = endDate.getTime();
                const cellTime = cellDate.getTime();
                if (cellTime > start && cellTime < end) {
                    cell.style.backgroundColor = '#ff86ab'; // 입실일과 퇴실일 사이의 날짜 색상
                }
            }

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
                        cell.style.backgroundColor = 'white';
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
                        renderCalendar(currentDate); // 달력을 다시 렌더링하여 색상을 업데이트합니다.
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
        sessionStorage.setItem('startDate', startDate ? startDate.toString() : '');
        sessionStorage.setItem('endDate', endDate ? endDate.toString() : '');
    }

    // 인원수 업데이트 함수
    function updateCount() {
        adultCountDiv.textContent = `성인: ${adultCount}명`;
        childCountDiv.textContent = `아동: ${childCount}명`;
        total.innerHTML = `성인 ${adultCount}명, 아동 ${childCount}명`;
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


document.addEventListener('DOMContentLoaded', function () {
    const bookingForm = document.getElementById('bookingForm');
    const bookingSDate = document.getElementById('bookingStartDate');
    const bookingEDate = document.getElementById('bookingEndDate');

    // booking table 에 저장된 객실 예약 날짜 불러오기
    let bookingStartDateElements = document.querySelectorAll('.dbStartDate');
    let bookingEndDateElements = document.querySelectorAll('.dbEndDate');
    let bookingPeopleElements = document.querySelectorAll('.dbBookingPeople');
    let bookingList = Array.from(bookingStartDateElements).map((startDateElem, index) => {
        return {
            bookingStartDate: startDateElem.textContent,
            bookingEndDate: bookingEndDateElements[index].textContent
        };
    });
    // let peopleList = [];
    // bookingPeopleElements.forEach(tmp => {
    //     peopleList.add(tmp.textContent);
    // });

    // 세션에 있는 정보 불러오기
    const storedStartDate = sessionStorage.getItem('startDate').split('T')[0];
    const storedEndDate = sessionStorage.getItem('endDate').split('T')[0];
    const storedAdultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
    const storedChildCount = parseInt(sessionStorage.getItem('childCount')) || 0;



    function setBookingInfo() {
        if (storedStartDate) bookingStartDate.value = storedStartDate;
        if (storedEndDate) bookingEndDate.value = storedEndDate;

        // bookingForm.action = `lodging/LodgingDetail/${lodgingId}`;
    }

    // isBookingAvailable();
    setBookingInfo();

    const goBookingBtn = document.getElementById('reserve-btn');

    goBookingBtn.addEventListener('submit', function(event) {
        event.preventDefault();
        console.log(document.getElementById('bookingAvailable').textContent);
        if(document.getElementById('bookingAvailable').textContent == 0)
            window.location.href = `/lodging/LodgingBooking(lodgingId=${lodge.lodgingId}, roomId=${lodge.roomId}`;
        else
            alert('예약이 마감된 객실입니다.');
    });
});
