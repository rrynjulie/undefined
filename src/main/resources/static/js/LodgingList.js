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