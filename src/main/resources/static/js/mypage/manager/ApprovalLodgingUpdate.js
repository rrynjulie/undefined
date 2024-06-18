function btnReg(){
    if(confirm("숙소 등록을 승인하시겠습니까?")){
        alert("숙소 등록을 승인하셨습니다.")
    }else{
        alert("숙소 등록을 반려하셨습니다.")
    }
}

function btnDel() {
    if(confirm("숙소 삭제를 승인하시겠습니까?")){
        alert("숙소 삭제를 승인하셨습니다.")
    }else{
        alert("숙소 삭제를 반려하셨습니다.")
    }
}
document.addEventListener("DOMContentLoaded",function (){
    document.getElementById("lodging-update").addEventListener("click", function (){
        window.location.href = "../../../../templates/views/mypage/manager/ApprovalLodgingUpdate.html"
    });
    document.getElementById("member-management").addEventListener("click", function (){
        window.location.href = "../../../../templates/views/mypage/manager/MemberManagement.html"
    });
    document.getElementById("manage-info-btn").addEventListener("click", function (){
        window.location.href = "../../../../templates/views/mypage/ManageAccount.html"
    });
});