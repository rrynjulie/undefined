$(document).ready(function () {
    $("#open-dialog").click(function () {
        $("#price-range").toggle();
        if ($("#price-range").is(":visible")) {
            $("#slider-range").slider({
                range: true,
                min: 1,
                max: 50,
                values: [1, 50],
                slide: function (event, ui) {
                    $("#price-text").text(ui.values[0] + "만원 ~ " + ui.values[1] + "만원 이상");
                }
            });
            $("#price-text").text($("#slider-range").slider("values", 0) + "만원 ~ " +
                $("#slider-range").slider("values", 1) + "만원 이상");
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {

    const lodgingText = document.getElementById('lodging-text1');
    const selectDate = document.getElementById('selectedDate');
    const people = document.getElementById('people');

    const searchHistory = JSON.parse(sessionStorage.getItem('searchHistory')) || [];
    console.log("searchHistory: ", searchHistory);

    searchHistory.forEach(i => {
        const word = i.word;
        const startDate = i.startDate;
        const endDate = i.endDate;
        const adults = i.adults;
        const children = i.children;

        // console.log(searchWord);
        console.log("searchWord: ", word);
        console.log("start Date: ", startDate);
        console.log("end Date: ", endDate);
        console.log("adults: ", adults);
        console.log("children: ", children);

        lodgingText.innerHTML = word;
        selectDate.innerHTML = `${new Date(startDate).getFullYear()}.${new Date(startDate).getMonth() + 1}.${new Date(startDate).getDate()}
        ~ ${new Date(endDate).getFullYear()}.${new Date(endDate).getMonth() + 1}.${new Date(endDate).getDate()}`;
        people.innerHTML = `성인 ${adults}, 아동 ${children}`;
    })
});


// 숙소 필터
document.addEventListener('DOMContentLoaded', function () {
    const text1 = document.getElementById('result-filter');
    console.log("Result Filter Text Element:", text1); // 추가된 콘솔 로그
});

console.log(sessionStorage);

function filterLodging(type) {
    const location = document.querySelector('input[name="location"]').value;
    fetch(`/lodging/filter?location=${encodeURIComponent(location)}&type=${encodeURIComponent(type)}`)
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.querySelector('.item-list');
            resultDiv.innerHTML = '';

            data.forEach(lodging => {
                resultDiv.innerHTML += `
                   <div style="display: flex; width: 48%; height: 100%; margin-bottom: 10px;">
                       <a class="item" href="/lodging/LodgingDetail/${lodging.lodgingId}">
                           <p class="item-img">
                               <img src="${lodging.lodgingPicture1}" alt="Lodging Picture"/>
                           </p>
                           <div class="item-details">
                               <p class="item-title">${lodging.lodgingName}</p>
                               <div class="item-type">${lodging.lodgingType}</div>
                               <div class="item-rating" style="display: flex; align-items: center;">
                                   <p id="star">&#9733;</p>
                                   <p class="star">${lodging.avgPostGrade}</p>
                               </div>
                               <p class="item-price">${lodging.roomPrice.toLocaleString()}원 ~</p>
                           </div>
                       </a>
                   </div>
               `;
            });
            document.getElementById('result-filter').innerHTML = `<span>${data.length}개의 검색 결과</span>`;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}


