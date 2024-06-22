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




function filterLodging(type) {
    const location = document.querySelector('input[name="location"]').value;
    fetch(`/lodging/filter?location=` + encodeURIComponent(location) + `&type=` + encodeURIComponent(type))
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.querySelector('.item-list');
            resultDiv.innerHTML = '';

            data.forEach(lodging => {
                resultDiv.innerHTML += `
                            <div style="display: flex; width: 48%; height: 100%; margin-bottom: 10px;">
                                <a class="item" href="/lodging/LodgingDetail/${lodging.lodgingId}">
                                    <p class="item-img"><img src="${lodging.lodgingPicture1}" alt="Lodging Picture"></p>
                                    <div class="item-details">
                                        <p class="item-title">${lodging.lodgingName}</p>
                                        <p class="item-rating">⭐ 4.6(512)</p>
                                        <p class="item-type">${lodging.lodgingType}</p>
                                        <p class="item-price">${lodging.roomPrice}원 ~</p>
                                    </div>
                                </a>
                            </div>
                        `;
            });

            // 검색 결과 개수 업데이트
            document.getElementById('result-filter').innerHTML = `<span>${data.length}개의 검색 결과</span>`;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}