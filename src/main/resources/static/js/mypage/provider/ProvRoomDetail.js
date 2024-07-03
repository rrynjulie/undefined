function confirmDelete() {
    alert("예약된 숙소가 모두 삭제 됩니다.")

    if (confirm("객실을 삭제하시겠습니까?")) {
        document.getElementById('deleteForm').submit();
    }
}