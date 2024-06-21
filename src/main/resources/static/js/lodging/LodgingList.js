$(document).ready(function() {
    $("#open-dialog").click(function() {
        $("#price-range").toggle();
        if ($("#price-range").is(":visible")) {
            $("#slider-range").slider({
                range: true,
                min: 1,
                max: 50,
                values: [1, 50],
                slide: function(event, ui) {
                    $("#price-text").text(ui.values[0] + "만원 ~ " + ui.values[1] + "만원 이상");
                }
            });
            $("#price-text").text($("#slider-range").slider("values", 0) + "만원 ~ " +
                $("#slider-range").slider("values", 1) + "만원 이상");
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const text = document.getElementById('lodging-text1')
    const searchWord = sessionStorage.getItem('searchWord')

    const selectedDate = document.getElementById("selectedDate");
    const startDate = new Date(sessionStorage.getItem('startDate'));
    const endDate = new Date(sessionStorage.getItem('endDate'));

    const totalDiv = document.getElementsByClassName('total');
    const adultCount = parseInt(sessionStorage.getItem('adultCount'));
    const childCount = parseInt(sessionStorage.getItem('childCount'));


    text.innerHTML = `${searchWord}`

    selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}`

    for (let i = 0; i < totalDiv.length; i++) {
        totalDiv[i].innerHTML = `인원: 성인 ${adultCount}, 아동 ${childCount}`;
    }

});

document.addEventListener('DOMContentLoaded', function () {
    const text1 = document.getElementById('result-filter');


})
console.log(sessionStorage);



