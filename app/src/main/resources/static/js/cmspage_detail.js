function deletePageModal() {
	const modal = document.getElementById('removePageModal');
    if (modal) {
        modal.classList.add('open');
    }
}

function closeDeletePageModal() {
    const modal = document.getElementById('removePageModal');
    if (modal) {
        modal.classList.remove('open');
    }
}