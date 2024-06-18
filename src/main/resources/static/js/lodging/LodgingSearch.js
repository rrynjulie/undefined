document.addEventListener('DOMContentLoaded', function() {
    // 페이지 로딩이 완료되면 calendar-body 에 아래 코드 (월, 연도/
    const calendarBody = document.querySelector('#calendar-body');
    const monthYear = document.getElementById('month-year');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');

    let currentDate = new Date();   // 현재 날짜를 저장하는 변수 => 초기값은 현재


    function renderCalendar(date) {     // date 라는 매개변수 받는다
        const month = date.getMonth();  // date 객체에서 월 을 숫자형식으로 추출 / 자바 스크립트에서 월이 0부터 시작하기 때문에 0 = 1월 ~~~
        const year = date.getFullYear();    // FullYear 는 4자리 연도를 숫자로 반환
        // firstDay 는 요일을 저장
        const firstDate = new Date(year, month, 1).getDay(); // .getDay() 는 요일을 반환 ex) 월요일 (0), 화요일 (1) ~~~ 일요일 (6)
        const lastDate = new Date(year, month + 1, 0).getDate();
        // month + 1 은 다음 달을 가르키고 0 은 0일을 가르키지만 js 에서는 month + 1 전 달 마지막 날을 가르킨다.
        // ex) new Date(2024, 6, 0) 이면 2024년 5월 마지막 날인 31을 반환

        calendarBody.innerHTML = '';    // 현재 보고있던 달력을 초기화
        monthYear.textContent = `${year}년 ${month + 1}월`;


        let row = document.createElement('tr');

        // 빈 셀 추가
        for (let i = 0; i < firstDate; i++) {
            let cell = document.createElement('td');
            row.append(cell);
        }

        // 날짜 셀 추가
        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement('td');
            row.append(cell);
            cell.textContent = date;    // 새로 만들어진 빈 cell 에 date 를 입력
            if (row.children.length === 7 || date === lastDate) {
                // 셀,tr,td 이 7개 이거나 마지막 해당 달에 마지막 날이 나왔을 때
                calendarBody.append(row);
                // 새로운 행을 추가
                row = document.createElement('tr');
            }
        }
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


// 인원수 버튼
const decreaseBtn1 = document.getElementById('decrease-btn1');   // 성인 - 버튼
const increaseBtn1 = document.getElementById('increase-btn1');   // 성인 + 버튼
const adultCountDiv = document.getElementById('adult-count');    // 성인 수

const decreaseBtn2 = document.getElementById('decrease-btn2');   // 아동 - 버튼
const increaseBtn2 = document.getElementById('increase-btn2');   // 아동 + 버튼
const childCountDiv = document.getElementById('child-count');    // 아동 수


const totalDiv = document.getElementsByClassName('total'); // 총 성인 수,아동 수


let adultCount = 1;
let childCount = 0;

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
})

increaseBtn1.addEventListener('click', () => {
    adultCount++;
    updateCount();
});

increaseBtn2.addEventListener('click', () => {
    childCount++;
    updateCount();
});

function updateCount() {
    adultCountDiv.textContent = adultCount;
    childCountDiv.textContent = childCount;
    for (let i = 0; i < totalDiv.length; i++) {
        totalDiv[i].textContent = `성인 ${adultCount} 아동 ${childCount}`;
    }
}