document.addEventListener('DOMContentLoaded', () => {
	capturePreviewContent();
			
});

async function capturePreviewContent() {
	
	const targetDiv = document.getElementById('captureSection');
	const doCapture = targetDiv.dataset.docapture;
	const compId = targetDiv.dataset.compid;
	const compUid = targetDiv.dataset.compuid;
	
	if (doCapture == 'true') {
		console.log("Step 3")
		try {
			const base64Image = await htmlToImage.toPng(document.getElementById('captureIframe'));
			//const base64Image = convertDivToBase64(targetDiv);
			
			const data = {
				id: compId,
				uid: compUid,
				screenshotString: base64Image
			}
	        
	        saveScreenshot(data);
		} catch (error) {
	        console.error("Failed to capture screenshot:", error);
	    }
	}
}

function convertDivToBase64(div) {
	const rawBase64 = '';
	html2canvas(div, {
       scale: 2,
       useCORS: true
    }).then(function(canvas) {
           // 1. Full Data URL (ready to use inside an <img src="..."> tag)
           const fullDataUrl = canvas.toDataURL("image/png");

           // 2. Raw Base64 string only (strips "data:image/png;base64,")
           rawBase64 = fullDataUrl.split(",")[1];
	});
	return rawBase64;
}

async function saveScreenshot(data) {
	try {
	    const response = await fetch("/save_previewimage", {
	      method: "POST",
	      headers: {
	        "Content-Type": "application/json",
	        // Include auth tokens if your API requires them:
	        // "Authorization": "Bearer YOUR_TOKEN"
	      },
	      body: JSON.stringify(data) // Convert JS object to JSON string
	    });

	    if (!response.ok) {
	      throw new Error(`HTTP error! Status: ${response.status}`);
	    }

	    const result = await response.json();
	    console.log("Saved successfully:", result);
	  } catch (error) {
	    console.error("Error saving object:", error);
	  }
}

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