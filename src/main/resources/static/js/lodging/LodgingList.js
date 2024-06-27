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
function filterLodging(location, type) {
    // Ajax 요청을 통해 서버에 필터링된 숙소 정보 요청
    $.ajax({
        type: "GET",
        url: "/lodging/filter",  // 서버에서 필터링된 숙소 정보를 반환하는 엔드포인트
        data: {
            location: location,
            type: type  // 클릭된 버튼의 타입을 전달
        },
        success: function(response) {
            // 성공적으로 데이터를 받았을 때 처리할 내용
            updateLodgingList(response);  // 숙소 리스트 업데이트 함수 호출
        }
    });
}

// 숙소 리스트를 업데이트하는 함수
function updateLodgingList(data) {
    // 받은 데이터(data)를 기반으로 숙소 목록을 업데이트
    var lodgingListDiv = document.getElementById("item-list");
    lodgingListDiv.innerHTML = "";  // 기존 목록 초기화

    data.forEach(function(lodging) {
        // 각 숙소 정보를 HTML로 구성하여 숙소 목록에 추가
        var lodgingItem = `
            <div style="display: flex; width: 48%; height: 100%; margin-bottom: 10px;">
                <a class="item" href="/lodging/LodgingDetail/${lodging.lodgingId}">
                    <p class="item-img">
                        <img src="${lodging.lodgingPicture1}" alt="Lodging Picture"/>
                        <div class="item-details">
                            <div class="item-title">${lodging.lodgingName}</div>
                            <div class="item-type">${lodging.lodgingType}</div>
              
                        </div>
                    </p>
                </a>
            </div>
        `;
        lodgingListDiv.innerHTML += lodgingItem;
    });
}