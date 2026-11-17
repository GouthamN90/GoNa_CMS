const queryKey = document.getElementById('searchInput')
queryKey.addEventListener('keydown', function(event) {
	if (event.key === 'Enter') {
		event.preventDefault();
		fetchComponents();
	}
});

function fetchComponents() {
	
	const searchQuery = document.getElementById('searchInput').value.toLowerCase();
	const gridContainer = document.getElementById('componentsGrid');
	const pageId = gridContainer.dataset.pageid;
	fetch('/assign_search_components?searchQuery=' + searchQuery + '&pageId=' + pageId)
	.then(response => {
		gridContainer.innerHTML = ''; // Clear loader
        if (!response.ok) {
			gridContainer.innerHTML = '<div class="col-span-full text-center text-red-500 py-8">Failed to load components.</div>';
		}
        return response.text();
    })
	.then(data => {
		gridContainer.innerHTML = data;
	})
	.catch(error => {
		console.error('Failed to fetch components:', error);
		gridContainer.innerHTML = '<div class="col-span-full text-center text-red-500 py-8">Failed to load components.</div>';
    });
	
}

document.addEventListener("DOMContentLoaded", () => {
    const container = document.getElementById("page-components-container");
    const pageId = container.dataset.pageid;
    let draggedItem = null;

    container.querySelectorAll(".page-component-card").forEach(attachCardEvents);

    function attachCardEvents(card) {
        card.addEventListener("dragstart", (e) => {
            draggedItem = card;
            e.dataTransfer.effectAllowed = "move";
            card.classList.add("dragging");
        });

        card.addEventListener("dragend", () => {
            card.classList.remove("dragging");
            draggedItem = null;
        });

        card.addEventListener("dragover", (e) => {
            e.preventDefault();
            e.dataTransfer.dropEffect = "move";
        });

        card.addEventListener("drop", async (e) => {
            e.preventDefault();
            const targetCard = card;

            if (!draggedItem || draggedItem === targetCard) return;

            const fromIndex = parseInt(draggedItem.dataset.index, 10);
            const toIndex = parseInt(targetCard.dataset.index, 10);

            // 1. Move element in the DOM
            if (fromIndex < toIndex) {
                targetCard.after(draggedItem);
            } else {
                targetCard.before(draggedItem);
            }

            // 2. Re-assign data-index to reflect new positions
            updateIndices();

            // 3. Notify Spring MVC Backend
            try {
                await sendReorderRequest(pageId, fromIndex, toIndex);
            } catch (err) {
                console.error("Reorder failed, reloading page...", err);
                window.location.reload(); // Revert on failure
            }
        });
    }

    function updateIndices() {
        const cards = container.querySelectorAll(".page-component-card");
        cards.forEach((card, idx) => {
            card.dataset.index = idx;
        });
    }

    async function sendReorderRequest(pageId, from, to) {
        const url = `/reorder_components/${pageId}?from=${from}&to=${to}`;
        const response = await fetch(url, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
                // Include CSRF token if Spring Security is active:
                // "X-CSRF-TOKEN": document.querySelector('meta[name="_csrf"]')?.content
            }
        });

        if (!response.ok) {
            throw new Error(`Server returned ${response.status}`);
        }
    }
});