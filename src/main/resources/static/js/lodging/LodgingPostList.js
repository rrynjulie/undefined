document.addEventListener('DOMContentLoaded', function() {
    const starRatings = document.querySelectorAll('.stars');

    starRatings.forEach(starDiv => {
        const count = starDiv.getAttribute('starCount');
        const stars = starDiv.querySelectorAll('.star');
        for (let i = 0; i < count; i++) {
            stars[i].classList.add('filled');
        }
    });
});


$(function (){
    // 현재 글의 id 를 불러온다.
    const id = $("input[name='id']").val().trim();

    // 현재 글의 댓글 불러오기
    loadComment(id);

    // 댓글 작성 버튼 누르면 댓글 등록 하기.
    // 1. 어느글에 대한 댓글인지? --> 위에 id 변수에 담겨있다
    // 2. 어느 사용자가 작성한 댓글인지? --> logged_id 값
    // 3. 댓글 내용은 무엇인지?  --> 아래 content
    $("#btn_comment").click(function (){
        const content = $("#input_comment").val().trim();

        //검증
        if(!content){
            alert("댓글 입력을 하세요");
            $("#input_comment").focus();
            return;
        }

        //전달할 parameter 준비 POST
        const data  = {
            "post_id" :id,
            "user_id" : logged_id,
            "content" : content,
        };

        $.ajax({
            url:"/comment/write",
            type: "POST",
            data: data,
            cache: false,
            success: function (data,status) {
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return
                    }
                    loadComment(id);
                    $("#input_comment").val('');
                }
            }
        })
    })
})


// 특정 글(post_id) 의 댓글 목록 읽어오기
function loadComment(post_id) {
    $.ajax({
        url: "/comment/list/" + post_id,
        type: "GET",
        cache: false,
        success: function(data, status){
            if(status == "success"){
                // 서버쪽 에러 메세지 있는경우
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                buildComment(data);  // 댓글 화면 렌더링

                // ★댓글목록을 불러오고 난뒤에 삭제에 대한 이벤트 리스너를 등록해야 한다
                // addDelete();
            }
        },
    });
}

