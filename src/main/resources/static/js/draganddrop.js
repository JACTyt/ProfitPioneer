function allowDrop(ev) {
    ev.preventDefault();
    ev.currentTarget.classList.add('over');
}

function drag(ev) {
    ev.dataTransfer.setData("text/plain", ev.target.id);
    ev.currentTarget.classList.add('dragging');
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text/plain");
    var draggedElement = document.getElementById(data);
    ev.currentTarget.classList.remove('over');

    if (draggedElement && draggedElement.classList.contains('list-item')) {
        var workerId = data.split('-')[1]; // Extract worker ID
        var jobId = ev.currentTarget.id.split('-')[1]; // Extract job ID

        // Redirect to the assignment URL
        window.location.href = '/assign?worker=' + workerId + '&job=' + jobId;
    }
}

document.addEventListener('dragend', function(event) {
    event.target.classList.remove('dragging');
    document.querySelectorAll('.over').forEach(function(element) {
        element.classList.remove('over');
    });
});

function dropToTrashBin(ev){
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text/plain");
    var draggedElement = document.getElementById(data);
    ev.currentTarget.classList.remove('over');

    if (draggedElement && draggedElement.classList.contains('list-item')) {
        var workerId = data.split('-')[1]; // Extract worker ID

        // Redirect to the assignment URL
        window.location.href = '/remove?worker=' + workerId;
    }
}