document.addEventListener('DOMContentLoaded', function() {
    const submitCommentButtons = document.querySelectorAll('.submit-comment');

    submitCommentButtons.forEach(button => {
        button.addEventListener('click', function() {
            const commentInputContainer = this.parentElement;
            const commentList = commentInputContainer.previousElementSibling;
            const input = commentInputContainer.querySelector('.comment-input');
            const comment = input.value.trim();

            if (comment !== '') {
                const newComment = document.createElement('p');
                newComment.textContent = comment;
                commentList.appendChild(newComment);
                input.value = '';

                // Hide input and button
                commentInputContainer.style.display = 'none';
            } else {
                alert('댓글을 작성하세요.');
            }
        });
    });
});