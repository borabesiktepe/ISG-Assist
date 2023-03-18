console.log("Selam");

const workplaceList = document.querySelector(".workplace-management ul");
const noteArea = document.querySelector("textarea");

//API VERİ YÜKLEME
//ÇALIŞMA ALANLARI
fetch(`http://localhost:8080/api/workplaces/getall`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        data.forEach(workplace => {
            const markupLi = `<li>${workplace.name}</li>`;
            workplaceList.insertAdjacentHTML('beforeend', markupLi);
        });

        console.log("Çalışma alanları listelendi.");
    })
    .catch(error => console.log(error));

//NOTLAR
fetch(`http://localhost:8080/api/notes/getall`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        data.forEach(workplace => {
            noteArea.innerHTML = workplace.note;
        });
        console.log("Not yüklendi.");
    })
    .catch(error => console.log(error));