// document.addEventListener('DOMContentLoaded', function() {
//     const calendarBody = document.querySelector('#calendar-body');   // 달력의 본체
//     const monthYear = document.getElementById('month-year');    // 달력의 헤더부분 => 년도와 월 표시
//     const prevButton = document.getElementById('prev');         // 이전 달 버튼
//     const nextButton = document.getElementById('next');         // 다음 달 버튼
//     const selectedDate = document.getElementById('selectedDate');   // 입실일 퇴실일 선택 한 날짜
//     let currentDate = new Date();   // 현재 날짜와 시간
//     let startDate = new Date(sessionStorage.getItem('startDate')); // 입실일 선택 (첫번째로 선택한 것)
//     let endDate = new Date(sessionStorage.getItem('endDate'));  // 퇴실일 선택 (두번째로 선택한 것)
//     let lastClickedCells = []; // 입실일과 퇴실일을 선택한 후 또 새로운 입실일을 선택할 때 전에 선택했던 값을 초기화하기 위한 빈 배열
//
//     function renderCalendar(date) { // 현재 달력 정보가 있는 date 를 매개변수로 받아 달력을 렌더링
//         const month = date.getMonth();  // date 객체에서 월 을 가져온다 ( 0 ~ 11 로 0은 1월, 11은 12월 )
//         const year = date.getFullYear();    // date 객체에서 연도를 가져온다   => 연도는 배열 아님
//         const firstDate = new Date(year, month, 1).getDay();    // 해당 월의 1일이 무슨 요일인지 가져와서 firstDate 에 저장
//         const lastDate = new Date(year, month + 1, 0).getDate();    // 해당 월의 마지막 날이 몇 일인지 가져와 lastDate 에 저장
//
//         calendarBody.innerHTML = '';    // 달력의 몸체 부분을 비움 -> 기존에 표시된 모든 날짜 제거
//         monthYear.textContent = `${year}년 ${month + 1}월`;   // 월 과 연도를 표시하는 부분에 텍스트로 설정, 월은 배열형이라 1을 더해준 것
//
//         let row = document.createElement('tr'); // 새로운 행을 추가 (한 주)
//
//         for (let i = 0; i < firstDate; i++) {   // 첫 번째 날부터 요일에 1일이 들어가야함으로 firstDate 까지 for 문 돌림
//             let cell = document.createElement('td');    // 첫 번째 날 전까지 빈셀 (td) 추가
//             row.append(cell);   // cell = td -> 빈 셀
//         }
//
//         for (let date = 1; date <= lastDate; date++) {  // 위 for 문에서 첫째날 전까지 빈 셀 추가했으므로 1부터 마지막 날까지
//             let cell = document.createElement('td');    // 셀 추가
//             row.append(cell);   // 셀을 만들어진 행에 추가
//             cell.textContent = date;    // 만들어진 셀에 텍스트로 date = 1++ ~~ lastDate 까지 추가
//
//             let cellDate = new Date(year, month, date); // 현재 셀 날짜를 Date 객체로 추가
//             if (startDate && cellDate.toDateString() === startDate.toDateString()) {
//                 cell.style.backgroundColor = 'lightblue';
//                 lastClickedCells.push(cell);
//             } else if (endDate && cellDate.toDateString() === endDate.toDateString()) {
//                 cell.style.backgroundColor = 'lightblue';
//                 lastClickedCells.push(cell);
//             }
//
//             cell.addEventListener('click', function() { // 각 셀에 클릭 이벤트 추가
//                 if (!startDate) {   // startDate(입실일) 선택 안되있을 때
//                     lastClickedCells.forEach(function(cell) {   // cell 이란 매개변수로 lastClickedCells 로 순회한다
//                         cell.style.backgroundColor = 'white';   // cell 이란 매개변수의 background 색상을 흰색으로 바꾼다
//                     });
//                     lastClickedCells = [];  // lastClickedCells 변수 초기화
//                     startDate = new Date(year, month, date);    // 입실일을 Date 라는 새로운 객체로 추가
//                     cell.style.backgroundColor = 'lightblue';   // 입실일을 lightblue 색상으로
//                     lastClickedCells.push(cell);    // 위 셀을 lasClickedCells 에 저장
//                     saveSelectedDate(); // saveSelectedDate 함수를 호출하여 입실일을 저장
//                 } else if (!endDate) {  // endDate(퇴실일) 선택 안되었을 때
//                     cell.style.backgroundColor = 'lightblue';   // 퇴실일을 lightblue 색상으로
//                     endDate = new Date(year, month, date);  // 퇴실일을 Date 라는 새로운 객체로 추가
//                     lastClickedCells.push(cell);    // 퇴실일을 lasClickedCells 에 추가
//                     if (endDate < startDate) {
//                         let a = startDate;
//                         startDate = endDate;
//                         endDate = a;
//                     }
//                     const nights = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
//                     selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}, ${nights}박`;
//                     saveSelectedDate(); // 선택된 날짜를 저장
//                 } else {    // 입실일, 퇴실일 다 선택되었을 때
//                     lastClickedCells.forEach(function(cell) {
//                         cell.style.backgroundColor = 'white';   // lastClickedCells 에 있던 기존 셀들을 모두 흰색으로 초기화
//                     });
//                     lastClickedCells = [];  // lastClickedCells 변수 배열 초기화
//                     startDate = new Date(year, month, date);
//                     endDate = null;
//                     cell.style.backgroundColor = 'lightblue';
//                     lastClickedCells.push(cell);
//                     saveSelectedDate();     // saveSelectedDate (세션 스토리지) 에 저장
//                 }
//             });
//
//             if (row.children.length === 7 || date === lastDate) {
//                 calendarBody.append(row);
//                 row = document.createElement('tr');
//             }
//         }
//     }
//
//     // 현재 날짜를 기본 형식의 문자열로 가져오기
//     // 현재 날짜를 'YYYY.MM.DD' 형식으로 변환하는 함수
//     function getCurrentDateFormatted() {
//         const date = new Date();
//         const year = date.getFullYear();
//         const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
//         const day = String(date.getDate()).padStart(2, '0'); // 일자는 1부터 시작
//         return `${year}.${month}.${day}`;
//     }
//
//     // 현재 날짜를 포맷된 문자열로 가져오기
//     const currentDateStr = getCurrentDateFormatted();
//
//     // 'selectedDate' div 요소에 현재 날짜 설정
//     document.getElementById('selectedDate').textContent = currentDateStr;
//
//
//     function saveSelectedDate() {   // 세션 스토리지에 입실일과 퇴실일을 저장하는 함수
//         sessionStorage.setItem('startDate', startDate ? startDate.toString() : '');
//         sessionStorage.setItem('endDate', endDate ? endDate.toString() : '');
//         console.log(sessionStorage);
//     }
//
//     function resetSelection() {
//         lastClickedCells.forEach(function(cell) {
//             cell.style.backgroundColor = 'white';
//         });
//         startDate = null;
//         endDate = null;
//         lastClickedCells = [];
//         saveSelectedDate();
//     }
//
//     prevButton.addEventListener('click', function() {   // 이전 달 버튼 클릭 이벤트
//         currentDate.setMonth(currentDate.getMonth() - 1);
//         renderCalendar(currentDate);    // 위에 호출했던 달력 함수 호출
//     });
//
//     nextButton.addEventListener('click', function() {   // 다음 달 버튼 클릭 이벤트
//         currentDate.setMonth(currentDate.getMonth() + 1);
//         renderCalendar(currentDate);
//     });
//
//     // 새로고침 시 sessionStorage 초기화 및 선택된 셀의 배경색 초기화
//     startDate = null;
//     endDate = null;
//     lastClickedCells = [];
//
//     renderCalendar(currentDate);
// });
//
//
//
//
//
//
// document.addEventListener('DOMContentLoaded', function() {
//
// // 인원수 버튼
//     const decreaseBtn1 = document.getElementById('decrease-btn1');   // 성인 - 버튼
//     const increaseBtn1 = document.getElementById('increase-btn1');   // 성인 + 버튼
//     const adultCountDiv = document.getElementById('adult-count');    // 성인 수
//
//     const decreaseBtn2 = document.getElementById('decrease-btn2');   // 아동 - 버튼
//     const increaseBtn2 = document.getElementById('increase-btn2');   // 아동 + 버튼
//     const childCountDiv = document.getElementById('child-count');    // 아동 수
//
//     const totalDiv = document.getElementsByClassName('total'); // 총 성인 수,아동 수
//
//
// let adultCount = sessionStorage.getItem('adultCount') || 1;
// let childCount = sessionStorage.getItem('childCount') || 0;
//
//
//
//     function updateCount() {
//         adultCountDiv.textContent = adultCount;
//         childCountDiv.textContent = childCount;
//         for (let i = 0; i < totalDiv.length; i++) {
//             totalDiv[i].textContent = `성인 ${adultCount}, 아동 ${childCount}`;
//         }
//         sessionStorage.setItem('adultCount', adultCount);   // 'adultCount' => 세션 스토리지 키, adultCount => adultCount 값을 저장하기 위해 코드에서 사용되었던 변수명
//         sessionStorage.setItem('childCount', childCount);   // 'childCount' => 세션 스토리지 키, childCount => childCount 값을 저장하기 위해 코드에서 사용되었던 변수명
//         console.log(sessionStorage);
//     }
//
//     updateCount();
//
//     decreaseBtn1.addEventListener('click', () => {
//         if (adultCount > 1) {
//             adultCount--;
//             updateCount();
//         }
//     });
//     decreaseBtn2.addEventListener('click', () => {
//         if (childCount > 0) {
//             childCount--;
//             updateCount();
//         }
//     })
//
//     increaseBtn1.addEventListener('click', () => {
//         adultCount++;
//         updateCount();
//     });
//
//     increaseBtn2.addEventListener('click', () => {
//         childCount++;
//         updateCount();
//     });
//     updateCount();
// });
//
//
//
// document.addEventListener("DOMContentLoaded", function() {
//     const inputText = document.getElementById("location");
//     const form = document.getElementById("search-form");
//
//     form.addEventListener("submit", function (event) {
//         event.preventDefault(); // 폼 제출을 막음
//         validateInput();
//     });
//
//     function validateInput() {
//         if (inputText.value.trim() === "") {
//             alert("숙소명 또는 지역명을 입력해주세요.");
//         } else {
//             sessionStorage.setItem('searchWord', inputText.value);
//             form.action = "/lodging/LodgingList"; // 폼의 action 설정
//             form.method = "POST"; // 폼의 method 설정
//             form.submit(); // 유효한 경우 폼 제출
//         }
//     }
// });
//
//
// document.addEventListener("DOMContentLoaded",function () {
//
//     let recentlySearch1 = document.getElementById("recently-search1");
//     let recentlySearch2 = document.getElementById("recently-search2");
//     let recentlySearch3 = document.getElementById("recently-search3");
//     function getCurrentDateFormatted(date) {
//         const year = date.getFullYear();
//         const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
//         const day = String(date.getDate()).padStart(2, '0'); // 일자는 1부터 시작
//         return `${year}.${month}.${day}`;
//     }
//
//     const startDateStr = sessionStorage.getItem("startDate");
//     const endDateStr = sessionStorage.getItem("endDate");
//
//     let formattedStartDate = "";
//     let formattedEndDate = "";
//
//     if (startDateStr) {
//         const startDate = new Date(startDateStr);
//         formattedStartDate = getCurrentDateFormatted(startDate);
//     }
//
//     if (endDateStr) {
//         const endDate = new Date(endDateStr);
//         formattedEndDate = getCurrentDateFormatted(endDate);
//     }
//     recentlySearch1.innerHTML = sessionStorage.getItem("searchWord");
//     recentlySearch2.innerHTML = formattedStartDate + " ~ " + formattedEndDate;
//     recentlySearch3.innerHTML = sessionStorage.getItem("adultCount") + "명  " + sessionStorage.getItem("childCount") + "명";
// })
//
//
//
//
//
//
//
//
//

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
            if (startDate && cellDate.toDateString() === startDate.toDateString()) {
                cell.style.backgroundColor = 'lightblue';
                lastClickedCells.push(cell);
            } else if (endDate && cellDate.toDateString() === endDate.toDateString()) {
                cell.style.backgroundColor = 'lightblue';
                lastClickedCells.push(cell);
            }

            cell.addEventListener('click', function() {
                if (!startDate) {
                    lastClickedCells.forEach(function(cell) {
                        cell.style.backgroundColor = 'white';
                    });
                    lastClickedCells = [];
                    startDate = new Date(year, month, date);
                    cell.style.backgroundColor = 'lightblue';
                    lastClickedCells.push(cell);
                    saveSelectedDate();
                } else if (!endDate) {
                    cell.style.backgroundColor = 'lightblue';
                    endDate = new Date(year, month, date);
                    lastClickedCells.push(cell);
                    if (endDate < startDate) {
                        let a = startDate;
                        startDate = endDate;
                        endDate = a;
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
                    cell.style.backgroundColor = 'lightblue';
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
