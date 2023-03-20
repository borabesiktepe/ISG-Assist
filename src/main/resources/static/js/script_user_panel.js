console.log("Selam");

const workplaceList = document.querySelector(".workplace-management ul");
const noteArea = document.querySelector("textarea");
const todoList = document.querySelector(".todolist-management ul");
const todoAddButton = document.querySelector(".todolist-buttons .add");
const todoInput = document.querySelector(".todo-input");

//API VERİ YÜKLEME
//ÇALIŞMA ALANLARI
fetch(`http://localhost:8080/api/workplaces/getall`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        data.forEach(workplace => {
            const markupLi = `<li><a href="">${workplace.name}</a></li>`;
            workplaceList.insertAdjacentHTML('beforeend', markupLi);
        });

        console.log("Çalışma alanları listelendi.");
    })
    .catch(error => console.log(error));

//YAPILACAKLAR LİSTESİ
fetch(`http://localhost:8080/api/todos/getall`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        data.forEach(todo => {
            const markupLi = `<li>${todo.todoItem}</li>`;
            todoList.insertAdjacentHTML('beforeend', markupLi);
        });

        console.log("Yapılacaklar listesi yüklendi.");
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
