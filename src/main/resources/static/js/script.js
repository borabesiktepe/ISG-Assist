console.log("Selam");

const workplaceList = document.querySelector(".workplace-management ul");
const workplaceSelect = document.querySelector("select");

fetch(`http://localhost:8080/api/workplaces/getworkplaces`)
.then(function(response){
    return response.json();
})
.then(data => {
    data.forEach(workplace => {
        const markup = `<li>${workplace.name}</li>`;
        const markup2 = `<option value="${workplace.name}">${workplace.name}</option>`;

        workplaceList.insertAdjacentHTML('beforeend', markup);
        workplaceSelect.insertAdjacentHTML('beforeend', markup2);

        console.log("Çalışma alanları listelendi.");
    });
})
.catch(error => console.log(error));