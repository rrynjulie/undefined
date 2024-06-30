
function addRoleProvider(userId) {
    fetch('/mypage/manager/addRoleProvider', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId: userId })
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Failed to add ROLE_PROVIDER');
        }
    });
}

function addRoleMaster(userId) {
    fetch('/mypage/manager/addRoleMaster', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId: userId })
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Failed to add ROLE_MASTER');
        }
    });
}

function confirmAction(name, action) {

    console.log("Name", name)
    console.log("Action", action)

    return confirm(action + " 하시겠습니까?");
}