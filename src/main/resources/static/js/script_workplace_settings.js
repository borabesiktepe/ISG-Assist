const formUpdate = document.getElementById("form-update");
const workplaceName = document.getElementById("name");
const workplaceDesc = document.getElementById("description");
const workplaceId = document.getElementById("workplaceId").value;

formUpdate.addEventListener('submit', function (e) {
    e.preventDefault();

    fetch('http://localhost:8080/api/workplaces/update/' + workplaceId, {
        method: 'PUT',
        body: JSON.stringify({
            name: workplaceName.value,
            description: workplaceDesc.value
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
});