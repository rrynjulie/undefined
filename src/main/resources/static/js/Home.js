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

function filterItems(type, section) {
    const sectionElement = document.getElementById(`${section}-section`);
    const items = sectionElement.querySelectorAll('.item');
    items.forEach(item => {
        if (type === 'all' || item.getAttribute('data-type') === type) {
            item.style.display = 'flex';
        } else {
            item.style.display = 'none';
        }
    });

    const buttons = sectionElement.querySelectorAll('.filter button');
    buttons.forEach(button => {
        if (button.textContent === type || (type === 'all' && button.textContent === '전체보기')) {
            button.classList.add('btn-active');
        } else {
            button.classList.remove('btn-active');
        }
    });
    // 필터링 후 가격 형식 적용
    formatPrices(`#${section}-section .item-price`);
}

// 초기 로드 시 전체보기 필터 적용
document.addEventListener('DOMContentLoaded', () => {
    filterItems('all', 'post');
    filterItems('all', 'booking');
    filterItems('all', 'love');
});
//
// function filterItemsBooking(type) {
//     const items = document.querySelectorAll('.item-booking');
//     items.forEach(item => {
//         if (type === 'all' || item.getAttribute('data-type') === type) {
//             item.style.display = 'flex';
//         } else {
//             item.style.display = 'none';
//         }
//     });
//
//     const buttons = document.querySelectorAll('.filter button');
//     buttons.forEach(button => {
//         if (button.textContent === type || (type === 'all' && button.textContent === '전체보기')) {
//             button.classList.add('btn-active');
//         } else {
//             button.classList.remove('btn-active');
//         }
//     });
// }
//
// function filterItemsLove(type) {
//     const items = document.querySelectorAll('.item-love');
//     items.forEach(item => {
//         if (type === 'all' || item.getAttribute('data-type') === type) {
//             item.style.display = 'flex';
//         } else {
//             item.style.display = 'none';
//         }
//     });
//
//     const buttons = document.querySelectorAll('.filter button');
//     buttons.forEach(button => {
//         if (button.textContent === type || (type === 'all' && button.textContent === '전체보기')) {
//             button.classList.add('btn-active');
//         } else {
//             button.classList.remove('btn-active');
//         }
//     });
// }

function goToLodgingDetail(element) {
    const lodgingId = element.getAttribute('data-id');
    window.location.href = `lodging/LodgingDetail/${lodgingId}`;
}

function formatPrice(price) {
    return price.toLocaleString();
}

function formatPrices(selector) {
    const prices = document.querySelectorAll(selector);
    prices.forEach(priceElement => {
        const price = priceElement.closest('.item').getAttribute('data-price');
        if (price) {
            priceElement.textContent = formatPrice(Number(price)) + '원~';
        }
    });
}