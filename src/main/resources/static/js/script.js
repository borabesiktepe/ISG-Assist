console.log("Selam");

const workplaceList = document.querySelector(".workplace-management ul");
const workplaceSelect = document.querySelector("select");

fetch(`http://localhost:8080/api/workplaces/getworkplaces`)
.then(function(response){
    return response.json();
})
.then(data => {
    data.forEach(workplace => {
        const markupLi = `<li>${workplace.name}</li>`;
        const markupOption = `<option value="${workplace.name}">${workplace.name}</option>`;

        workplaceList.insertAdjacentHTML('beforeend', markupLi);
        workplaceSelect.insertAdjacentHTML('beforeend', markupOption);

        console.log("Çalışma alanları listelendi.");
    });
})
.catch(error => console.log(error));

