console.log("Selam");

const workplaceSelect = document.querySelector("select");

//API VERİ YÜKLEME
fetch(`http://localhost:8080/api/workplaces/getall`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        data.forEach(workplace => {
            const markupOption = `<option value="${workplace.name}">${workplace.name}</option>`;
            workplaceSelect.insertAdjacentHTML('beforeend', markupOption);

            console.log("Çalışma alanları listelendi.");
        });
    })
    .catch(error => console.log(error));