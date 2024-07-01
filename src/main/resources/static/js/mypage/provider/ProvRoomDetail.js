function confirmDelete() {
    if (confirm("객실을 삭제하시겠습니까?")) {
        document.getElementById('deleteForm').submit();
    }
}