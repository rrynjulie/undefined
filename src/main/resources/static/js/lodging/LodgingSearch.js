document.addEventListener('DOMContentLoaded', function() {
    const calendarBody = document.querySelector('#calendar-body');
    const monthYear = document.getElementById('month-year');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const selectedDate = document.getElementById('selectedDate');
    let currentDate = new Date();
    let startDate = sessionStorage.getItem('startDate') ? new Date(sessionStorage.getItem('startDate')) : new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
    let endDate = sessionStorage.getItem('endDate') ? new Date(sessionStorage.getItem('endDate')) : new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() + 1);
    let today = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()); //현재날짜 저장
    // let startDate = sessionStorage.getItem('startDate') ? new Date(sessionStorage.getItem('startDate')) : new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
    // // 세션에 날짜가 저장되어 있으면 그 값을 startDate 로 세션에 저장되어 있는 값이 없으면 현재 날짜로
    // let endDate = sessionStorage.getItem('endDate') ? new Date(sessionStorage.getItem('endDate')) : new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() + 1);
    // 세션에 날짜가 저장되어 있으면 그 값을 startDate 로 세션에 저장되어 있는 값이 없으면 현재 날짜 + 1 로 저장
    selectedDate.innerHTML = startDate.getFullYear() + '.' + (startDate.getMonth() + 1) + '.' + startDate.getDate() + ' ~ ' + endDate.getFullYear() + '.' + (endDate.getMonth() + 1) + '.' + (endDate.getDate() + ' (1박)');
    if (!sessionStorage.getItem('startDate')) {
        sessionStorage.setItem('startDate', startDate.toISOString());
    }   // 날짜 선택을 안했다면 현재 날짜로
    if (!sessionStorage.getItem('endDate')) {
        sessionStorage.setItem('endDate', endDate.toISOString());
    }   // 날짜 선택을 안했다면 현재날 + 1 로
    let lastClickedCells = [];  // 선택한 날짜를 배열에 저장

    function renderCalendar(date) {
        const month = date.getMonth();  // getMonth, getFullYear, getDay, getDate 는 자바에서 지원해주는 객체
        const year = date.getFullYear();
        const firstDate = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();
        // date 0 은 0번째 날을 의미하므로 ex) 6월 0 이면 5월 마지막날이기 때문에 month 에 + 1

        calendarBody.innerHTML = '';    // 캘린더 초기화
        monthYear.textContent = `${year}년 ${month + 1}월`;   // 초기화 된 캘린더 monthYear 부분에 년, 월 출력

        let row = document.createElement('tr');

        for (let i = 0; i < firstDate; i++) {
            let cell = document.createElement('td');
            row.append(cell);   // 첫번째 날 전까지 td (빈셀) 생성
        }

        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement('td');
            row.append(cell);   // 빈셀 이후로 마지막 날 까지 td (빈셀) 생성
            cell.textContent = date;    // 빈 셀에 날짜 데이터 삽입

            let cellDate = new Date(year, month, date);

            // if (cellDate < today) {
            //     cell.style.color = 'lightgray'; //과거 날짜 회색표시
            //     cell.style.pointerEvents = 'none'; //선택 못하게
            // }

            if (startDate && endDate) {
                const start = new Date(startDate).getTime();
                const end = new Date(endDate).getTime();
                const cellTime = cellDate.getTime();
                if (cellTime > start && cellTime < end) {
                    cell.style.backgroundColor = '#FFEBEF'; // 입실일과 퇴실일 사이의 날짜 색상
                }
            }

            if (startDate && cellDate.toDateString() === new Date(startDate).toDateString()) {
                cell.style.backgroundColor = '#FC5185';
                lastClickedCells.push(cell);
            } else if (endDate && cellDate.toDateString() === new Date(endDate).toDateString()) {
                cell.style.backgroundColor = '#FC5185';
                lastClickedCells.push(cell);
            }

            cell.addEventListener('click', function() {
                if (!startDate) {
                    clearLastClickedCells();
                    startDate = new Date(year, month, date);
                    cell.style.backgroundColor = '#FC5185';
                    lastClickedCells.push(cell);
                    saveSelectedDate();
                } else if (!endDate) {
                    if (startDate < cellDate) {
                        endDate = new Date(year, month, date);
                        lastClickedCells.push(cell);
                        renderCalendar(currentDate);
                        saveSelectedDate();
                    }
                    updateSelectedDateText();
                    saveSelectedDate();
                } else {
                    clearLastClickedCells();
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

    function saveSelectedDate() {
        sessionStorage.setItem('startDate', startDate ? startDate.toISOString() : '');
        sessionStorage.setItem('endDate', endDate ? endDate.toISOString() : '');
    }

    function clearLastClickedCells() {
        lastClickedCells.forEach(function(cell) {
            cell.style.backgroundColor = 'white';
        });
        lastClickedCells = [];
    }

    function updateSelectedDateText() {
        const nights = Math.ceil((new Date(endDate) - new Date(startDate)) / (1000 * 60 * 60 * 24));
        selectedDate.textContent = `${new Date(startDate).getFullYear()}.${new Date(startDate).getMonth() + 1}.${new Date(startDate).getDate()} ~ ${new Date(endDate).getFullYear()}.${new Date(endDate).getMonth() + 1}.${new Date(endDate).getDate()} (${nights}박)`;
    }

    prevButton.addEventListener('click', function() {
        currentDate.setMonth(currentDate.getMonth() - 1);
        renderCalendar(currentDate);
    });

    nextButton.addEventListener('click', function() {
        currentDate.setMonth(currentDate.getMonth() + 1);
        renderCalendar(currentDate);
    });

    renderCalendar(currentDate);
});

    // 버튼
document.addEventListener('DOMContentLoaded', function() {
    const decreaseBtn1 = document.getElementById('decrease-btn1');  // 성인 수 감소 버튼
    const increaseBtn1 = document.getElementById('increase-btn1');  // 성인 수 증가 버튼
    const adultCountDiv = document.getElementById('adult-count');   // 성인 수 text
    const decreaseBtn2 = document.getElementById('decrease-btn2');  // 아동 수 감소 버튼
    const increaseBtn2 = document.getElementById('increase-btn2');  // 아동 수 증가 버튼
    const childCountDiv = document.getElementById('child-count');   // 아동 수 text
    let adultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
    // 성인 수가 세션에 없다면 1 로 저장 -> 세션은 String 형으로 저장되기 때문에 Int 로 파싱
    let childCount = parseInt(sessionStorage.getItem('childCount')) || 0;
    // 아동 수가 세션에 없다면 0 으로 저장
    let total = document.getElementById("total");

    function updateCount() {
        adultCountDiv.textContent = adultCount;
        childCountDiv.textContent = childCount;
        sessionStorage.setItem('adultCount', adultCount.toString());
        sessionStorage.setItem('childCount', childCount.toString());
        total.innerText = '성인 ' + adultCount + ', 아동 ' + childCount;
    }

    updateCount();

    decreaseBtn1.addEventListener('click', () => {
        if (adultCount > 1) {
            adultCount--;
            updateCount();
        }
    });

    decreaseBtn2.addEventListener('click', () => {
        if (childCount > 0) {
            childCount--;
            updateCount();
        }
    });

    increaseBtn1.addEventListener('click', () => {
        adultCount++;
        updateCount();
    });

    increaseBtn2.addEventListener('click', () => {
        childCount++;
        updateCount();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const inputText = document.getElementById("location");
    const form = document.getElementById("search-form");

    form.addEventListener("submit", function(event) {
        event.preventDefault();
        validateInput();
    });

    function validateInput() {
        if (inputText.value.trim() === "") {
            alert("숙소명 또는 지역명을 입력해주세요.");
        } else {
            sessionStorage.setItem('searchWord', inputText.value);
            saveSearchHistory(inputText.value);
            form.action = "/lodging/LodgingList";
            form.method = "POST";
            form.submit();
        }
    }

    function saveSearchHistory(searchWord) {
        let searchHistory = JSON.parse(sessionStorage.getItem('searchHistory')) || [];
        searchHistory.unshift({ // 새로운 객체를 배열에 맨 앞으로
            word: searchWord,
            startDate: sessionStorage.getItem('startDate') || new Date().toISOString(),
            endDate: sessionStorage.getItem('endDate') || new Date(new Date().getTime() + 24 * 60 * 60 * 1000).toISOString(),
            adults: parseInt(sessionStorage.getItem('adultCount')) || 1,
            children: parseInt(sessionStorage.getItem('childCount')) || 0
        });
        sessionStorage.setItem('searchHistory', JSON.stringify(searchHistory));
        // searchHistory 키 안에 위 세션들을 저장
    }


    // 최근검색어
    function updateRecentlySearches() {
        let searchHistory = JSON.parse(sessionStorage.getItem('searchHistory')) || [];
        let recentlySearchContainer = document.getElementById("recently-search-container");
        recentlySearchContainer.innerHTML = '';

        searchHistory.forEach(search => {
            let searchDiv = document.createElement('div');
            searchDiv.id = `recently-search`;
            searchDiv.style.backgroundColor = '#F5F5F5';
            searchDiv.style.boxShadow = '0 2px 16px rgba(0, 0, 0, 0.14)';
            searchDiv.innerHTML =
                `<div id="recentlyDiv1">
                    <div id="recentlyDiv2"><span>${search.word}</div> 
                    <div><span class="delete-icon"><img id="recentlyImg" src="/image/x.png"></span></div>
                </div>
                <br> <span>날짜: ${search.startDate ? new Date(search.startDate).toLocaleDateString() : ""} ~ ${search.endDate ? new Date(search.endDate).toLocaleDateString() : ""}</span>
                <br> <span>성인 ${search.adults}명, 아동 ${search.children}명</span>`;

            const deleteIcon = searchDiv.querySelector('.delete-icon');
            deleteIcon.addEventListener('click', function () {
                // 삭제할 요소를 DOM에서 제거
                recentlySearchContainer.removeChild(searchDiv);
                // 해당 검색 기록을 sessionStorage에서도 삭제
                const index = searchHistory.findIndex(item => item.word === search.word);   // searchHistory 배열에서 search.word 와 일치하는 검색 기록의 인덱스 순회
                if (index !== -1) {     // search.word 를 찾았다면?
                    searchHistory.splice(index, 1);     // 첫번째로 일치하는 search.word 에 해당하는 기록들을 삭제
                    sessionStorage.setItem('searchHistory', JSON.stringify(searchHistory));     // 수정된 배열을 sessionStorage 에 저장
                }
            });
            recentlySearchContainer.appendChild(searchDiv);
        });
    }

    updateRecentlySearches();


});