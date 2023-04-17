const trainingList = document.querySelector("ul");
const trainingButton = document.querySelector(".training-bottom button");
const selectedItem = document.querySelector(".selected-file");
const pdfFrame = document.querySelector("iframe");
const message = document.getElementById("message");

const workplaceId = document.getElementById("workplaceId").value;

pdfFrame.style.display = "none";
message.style.display = "none";

fetch(`http://localhost:8080/api/documents/getall?workplaceId=${workplaceId}`)
    .then(function (response) {
        return response.json();
    })
    .then(data => {
        if (data.length > 0) {
            data.forEach(documents => {
                var radioButton = document.createElement('input');
                radioButton.type = 'radio';
                radioButton.name = 'file';
                radioButton.value = documents.documentName;

                var trainingItem = document.createElement('li');
                trainingItem.insertAdjacentHTML("beforeend", documents.documentName);
                trainingItem.appendChild(radioButton);
                trainingList.appendChild(trainingItem);
            });
        }
        else {
            trainingList.innerHTML = "<li>Döküman bulunamadı.</li>"
        }
    })
    .catch(error => console.log(error));

//Iframe'de listeden seçili dosyayı görüntüle
trainingButton.addEventListener("click", () => {
    pdfFrame.style.display = "";
    message.style.display = "";

    //Iframe'e Scroll
    document.querySelector("iframe").scrollIntoView({
            behavior: "smooth",
            block: "start",
            inline: "nearest"
    });

    var element = document.getElementsByName('file');

    for (i = 0; i < element.length; i++) {
        if (element[i].checked) {
            selectedItem.innerHTML = "Seçilen döküman: " + element[i].value;
            pdfFrame.src = `/files/${element[i].value}`;
            document.getElementById("document-link").href = `/files/${element[i].value}`;
        }
    }
})