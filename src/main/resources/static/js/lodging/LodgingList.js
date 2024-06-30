document.addEventListener('DOMContentLoaded', function () {
    const text = document.getElementById('lodging-text1')
    const searchWord = sessionStorage.getItem('searchWord')

    const selectedDate = document.getElementById("selectedDate");
    const startDate = new Date(sessionStorage.getItem('startDate'));
    const endDate = new Date(sessionStorage.getItem('endDate'));

    const totalDiv = document.getElementsByClassName('total');
    const adultCount = parseInt(sessionStorage.getItem('adultCount'));
    const childCount = parseInt(sessionStorage.getItem('childCount'));

    console.log('searchWord: ' + searchWord);
    console.log('startDate: ' + startDate);
    console.log('endDate: ' + endDate);
    console.log('adultCount: ' + adultCount);
    console.log('childCount: ' + childCount);


    text.innerHTML = `${searchWord}`

    selectedDate.innerHTML = `${startDate.getFullYear()}.${startDate.getMonth() + 1}.${startDate.getDate()} ~ ${endDate.getFullYear()}.${endDate.getMonth() + 1}.${endDate.getDate()}`

    for (let i = 0; i < totalDiv.length; i++) {
        totalDiv[i].innerHTML = `인원: 성인 ${adultCount}, 아동 ${childCount}`;
    }

});




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
            const resultCount = $(data).find('.item-list1').length;
            $('#result-filter').text(resultCount + '개의 검색 결과');
            $('#item-list').html(data);
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}

$(document).ready(function() {
    $('#priceFilter').change(function() {
        var selectedPriceOrder = $(this).val();
        var location = $('#location').val(); // Hidden input에서 location 값을 가져옴

        $.ajax({
            type: 'POST',
            url: '/lodging/LodgingList/price',
            data: {
                location: location,
                price: selectedPriceOrder
            },
            success: function(response) {
                $('#item-list').html(response);
            },
            error: function(error) {
                console.error('에러:', error);
            }
        });
    });
});