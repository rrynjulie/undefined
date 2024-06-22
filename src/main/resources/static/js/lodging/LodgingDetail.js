let slideIndex = 1;
showSlides(slideIndex);

// 3초마다 페이지가
setInterval(function () {
    plusSlides(1);
}, 3000);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
}
// 페이지가 로드되면 실행되는 이벤트
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