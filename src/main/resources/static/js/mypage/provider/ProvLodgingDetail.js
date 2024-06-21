document.addEventListener("DOMContentLoaded", function() {
    // 숙소 사진들 배열
    var lodgingPictures = [
        /* ${lodging.lodgingPicture1}, ${lodging.lodgingPicture2}, ${lodging.lodgingPicture3} 등 */
        /* 숙소 사진 URL들을 순서대로 배열에 넣어주세요 */
        "${lodging.lodgingPicture1}",
        "${lodging.lodgingPicture2}",
        "${lodging.lodgingPicture3}"
    ];

    var currentImageIndex = 0; // 현재 보여지고 있는 사진의 인덱스

    // 초기화: 첫 번째 사진 보이기
    updateDisplayedImage();

    // 이전 버튼 클릭 시
    document.getElementById("prevButton").addEventListener("click", function() {
        currentImageIndex--;
        if (currentImageIndex < 0) {
            currentImageIndex = lodgingPictures.length - 1; // 마지막 사진으로 이동
        }
        updateDisplayedImage();
    });

    // 다음 버튼 클릭 시
    document.getElementById("nextButton").addEventListener("click", function() {
        currentImageIndex++;
        if (currentImageIndex >= lodgingPictures.length) {
            currentImageIndex = 0; // 첫 번째 사진으로 이동
        }
        updateDisplayedImage();
    });

    // 사진 업데이트 함수
    function updateDisplayedImage() {
        var currentImageUrl = lodgingPictures[currentImageIndex];
        var imageElement = document.getElementById("lodgingImage");
        imageElement.src = currentImageUrl;
    }
});
