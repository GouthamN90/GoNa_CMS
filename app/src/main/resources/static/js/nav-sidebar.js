document.addEventListener('DOMContentLoaded', function () {
    const navLinks = document.querySelectorAll('.naah-sidebar .nav-link');
    navLinks.forEach(link => {
        link.addEventListener('keydown', function (e) {
            if (e.key === ' ' || e.key === 'Enter') {
                link.click();
            }
        });
    });
});