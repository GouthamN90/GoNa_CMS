function deleteComponentModal() {
	const modal = document.getElementById('removeComponentModal');
    if (modal) {
        modal.classList.add('open');
    }
}

function closeDeleteComponentModal() {
    const modal = document.getElementById('removeComponentModal');
    if (modal) {
        modal.classList.remove('open');
    }
}

function editComponentModal() {
	const modal = document.getElementById('editComponentModal');
    if (modal) {
        modal.classList.add('open');
    }
}

function closeEditComponentModal() {
    const modal = document.getElementById('editComponentModal');
    if (modal) {
        modal.classList.remove('open');
    }
}