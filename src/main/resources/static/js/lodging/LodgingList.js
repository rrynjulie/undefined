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





// function filterLodging(type) {
//     const location = document.getElementById('location').value;
//     $.ajax({
//         url: '/LodgingList/filter',
//         type: 'POST',
//         data: {
//             location: location,
//             type: type
//         },
//         success: function (data) {
//             $('#item-list').html(data);
//         },
//         error: function (error) {
//             console.error('Error:', error);
//         }
//     });
// }
function filterLodging(type) {
    const location = document.getElementById('location').value;
    $.ajax({
        url: '/lodging/LodgingList/filter',
        type: 'POST',
        data: {
            location: location,
            type: type
        },
        success: function (data) {
            $('#item-list').html(data);  // 필터링된 결과로 item-list 부분 갱신
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}