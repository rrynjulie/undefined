document.addEventListener('DOMContentLoaded', function() {
    const calendarBody = document.querySelector('#calendar-body');
    const monthYear = document.getElementById('month-year');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const selectedDate = document.getElementById('selectedDate');
    let currentDate = new Date();
    let startDate = null;
    let endDate = null;
    let lastClickedCells = [];

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
                    // 입실일과 퇴실일 사이의 날짜 색상 처리
                    if (startDate < cellDate) {
                        endDate = new Date(year, month, date);
                        lastClickedCells.push(cell);
                        renderCalendar(currentDate); // 달력을 다시 렌더링하여 색상을 업데이트합니다.
                        saveSelectedDate();
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

    function saveSelectedDate() {
        sessionStorage.setItem('startDate', startDate ? startDate.toString() : '');
        sessionStorage.setItem('endDate', endDate ? endDate.toString() : '');
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
document.addEventListener('DOMContentLoaded', function() {
    const decreaseBtn1 = document.getElementById('decrease-btn1');
    const increaseBtn1 = document.getElementById('increase-btn1');
    const adultCountDiv = document.getElementById('adult-count');

    const decreaseBtn2 = document.getElementById('decrease-btn2');
    const increaseBtn2 = document.getElementById('increase-btn2');
    const childCountDiv = document.getElementById('child-count');

    const totalDiv = document.getElementsByClassName('total')[0];

    let adultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
    let childCount = parseInt(sessionStorage.getItem('childCount')) || 0;

    function updateCount() {
        adultCountDiv.textContent = adultCount;
        childCountDiv.textContent = childCount;
        totalDiv.textContent = `성인 ${adultCount}, 아동 ${childCount}`;
        sessionStorage.setItem('adultCount', adultCount);
        sessionStorage.setItem('childCount', childCount);
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
        let searchDate = new Date();
        let startDate = sessionStorage.getItem('startDate') || "";
        let endDate = sessionStorage.getItem('endDate') || "";
        let adultCount = parseInt(sessionStorage.getItem('adultCount')) || 1;
        let childCount = parseInt(sessionStorage.getItem('childCount')) || 0;

        searchHistory.unshift({
            word: searchWord,
            startDate: startDate,
            endDate: endDate,
            adults: adultCount,
            children: childCount
        });

        sessionStorage.setItem('searchHistory', JSON.stringify(searchHistory));
        updateRecentlySearches();
    }

    function updateRecentlySearches() {
        let searchHistory = JSON.parse(sessionStorage.getItem('searchHistory')) || [];
        let recentlySearchContainer = document.getElementById("recently-search-container");
        recentlySearchContainer.innerHTML = '';

        searchHistory.forEach(search => {
            let searchDiv = document.createElement('div');
            searchDiv.id = `recently-search`;
            let startDateStr = search.startDate ? new Date(search.startDate).toLocaleDateString() : "";
            let endDateStr = search.endDate ? new Date(search.endDate).toLocaleDateString() : "";
            searchDiv.innerHTML =
                `<div style="display: flex; justify-content: space-between">
                    <div><span>${search.word}</div> 
                    <div><span class="delete-icon"><img style="height: 20px" src="/image/x.png"></span></div>
                </div>
                <br> <span>날짜: ${search.startDate ? new Date(search.startDate).toLocaleDateString() : ""} ~ ${search.endDate ? new Date(search.endDate).toLocaleDateString() : ""}</span>
                <br> <span>성인: ${search.adults}명, 아동: ${search.children}명</span>`;

            const deleteIcon = searchDiv.querySelector('.delete-icon');
            deleteIcon.addEventListener('click', function() {
                // 삭제할 요소를 DOM에서 제거
                recentlySearchContainer.removeChild(searchDiv);
                // 해당 검색 기록을 sessionStorage에서도 삭제
                searchHistory.splice(index, 1);
                sessionStorage.setItem('searchHistory', JSON.stringify(searchHistory));
            });
            recentlySearchContainer.appendChild(searchDiv);
        });
    }

    updateRecentlySearches();
});
