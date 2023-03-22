console.log("Selam");

const workplaceList = document.querySelector(".workplace-management ul");
const noteArea = document.querySelector("textarea");
const todoList = document.querySelector(".todolist-management ul");
const todoAddButton = document.querySelector(".todolist-buttons .add");
const todoInput = document.querySelector(".todo-input");

//API GET İŞLEMLERİ
//ÇALIŞMA ALANLARI
function listWorkplaces() {
    fetch(`http://localhost:8080/api/workplaces/getall`)
        .then(function (response) {
            return response.json();
        })
        .then(data => {
            data.forEach(workplace => {
                const markupLi = `<li><a href="/workplace_panel/${workplace.id}">${workplace.name}</a></li>`;
                workplaceList.insertAdjacentHTML('beforeend', markupLi);
            });

            console.log("Çalışma alanları listelendi.");
        })
        .catch(error => console.log(error));
}

//YAPILACAKLAR LİSTESİ
function listToDos() {
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
}

//NOTLAR
function listNotes() {
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
}

listWorkplaces();
listToDos();
listNotes();

//API POST İŞLEMLERİ
const formTodo = document.getElementById("todo-form");

formTodo.addEventListener('submit', function (e) {
    e.preventDefault();

    let inputTodoItem = document.getElementById('todo-input').value;
    let inputUserId = document.getElementById('userId').value;

    console.log(inputTodoItem + " " + inputUserId);

    fetch('http://localhost:8080/api/todos/add', {
        method: 'POST',
        body: JSON.stringify({
            todoItem: inputTodoItem,
            userId: inputUserId
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(function (response) {
            return response.json()
        })
        .then(function (data) {
            console.log(data)
        }).catch(error => console.error('Error:', error));

    location.reload();
});

const formNotes = document.getElementById("notes-form");

formNotes.addEventListener('submit', function (e) {
        e.preventDefault();

        let inputNote = document.querySelector("textarea").value;
        let inputUserId = document.getElementById('userId').value;

        console.log(inputNote + " " + inputUserId);

        fetch('http://localhost:8080/api/notes/update', {
            method: 'PUT',
            body: JSON.stringify({
                note: inputNote,
                userId: inputUserId
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                return response.json()
            })
            .then(function (data) {
                console.log(data)
            }).catch(error => console.error('Error:', error));

        location.reload();
})

