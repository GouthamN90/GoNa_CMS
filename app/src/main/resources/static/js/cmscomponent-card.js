function openComponentModal(id) {
	const modal = document.getElementById('createComponentModal');
	document.getElementById('modalDefaultCompId').value = id; 
	document.getElementById("modalNewCompUid").value = ''; 
	document.getElementById("modalNewCompName").value = ''; 
    modal.classList.add('open');
}

function closeComponentModal() {
    const modal = document.getElementById('createComponentModal');
    if (modal) {
        modal.classList.remove('open');
    }
}

// Close modal if user clicks outside of the modal window frame content
window.addEventListener('click', function(event) {
    const modal = document.getElementById('createComponentModal');
    if (event.target === modal) {
        closeComponentModal();
    }
});