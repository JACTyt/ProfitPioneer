function handleFileAction(action) {
    event.preventDefault();
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.name = 'file';
    fileInput.accept = 'application/json';
    fileInput.style.display = 'none';
    document.body.appendChild(fileInput);

    fileInput.addEventListener('change', function() {
        const form = document.getElementById(action + 'Form');
        form.appendChild(fileInput);
        form.submit();
    });

    fileInput.click();
}