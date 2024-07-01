document.addEventListener('DOMContentLoaded', () => {
    // 초기 상태 설정: 전체보기 버튼 클릭 상태로 설정
    filterItems('all');

    // 가격 형식화
    const prices = document.querySelectorAll('.item-price');
    prices.forEach(priceElement => {
        const price = priceElement.parentElement.parentElement.getAttribute('data-price');
        if (price) {
            priceElement.textContent = formatPrice(Number(price)) + '원~';
        }
    });
});

function logout() {
    fetch('/user/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'same-origin'
    })
        .then(response => {
            if (response.ok) {
                window.location.reload(); // 로그아웃 성공 시 페이지 리로드
            } else {
                alert('로그아웃에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('로그아웃 중 오류 발생:', error);
            alert('로그아웃 중 오류가 발생했습니다.');
        });
}

function filterItems(type) {
    const items = document.querySelectorAll('.item');
    items.forEach(item => {
        if (type === 'all' || item.getAttribute('data-type') === type) {
            item.style.display = 'flex';
        } else {
            item.style.display = 'none';
        }
        console.log("")
    });

    const buttons = document.querySelectorAll('.filter button');
    buttons.forEach(button => {
        if (button.textContent === type || (type === 'all' && button.textContent === '전체보기')) {
            button.classList.add('btn-active');
        } else {
            button.classList.remove('btn-active');
        }
    });


}

function goToLodgingDetail(element) {
    const lodgingId = element.getAttribute('data-id');
    window.location.href = `lodging/LodgingDetail/${lodgingId}`;
}

function formatPrice(price) {
    return price.toLocaleString();
}