function toggleDropdown(event) {
    event.stopPropagation();
    const menu = document.getElementById('userMenu');
    const trigger = event.currentTarget;
    const isCurrentlyOpen = menu.classList.contains('open');
    
    menu.classList.toggle('open');
    trigger.setAttribute('aria-expanded', !isCurrentlyOpen);
}

document.addEventListener('click', function(event) {
    const menu = document.getElementById('userMenu');
    if (menu && menu.classList.contains('open')) {
        const isClickInside = menu.contains(event.target);
        if (!isClickInside) {
            menu.classList.remove('open');
            const trigger = menu.querySelector('.menu-trigger');
            if (trigger) {
                trigger.setAttribute('aria-expanded', 'false');
            }
        }
    }
});