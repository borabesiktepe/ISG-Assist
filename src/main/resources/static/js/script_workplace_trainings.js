const trainingList = document.querySelector("ul");
const trainingButton = document.querySelector(".training-bottom button");
const selectedItem = document.querySelector(".selected-file");
const pdfFrame = document.querySelector("iframe");

const workplaceId = document.getElementById("workplaceId").value;

pdfFrame.style.display = "none";

    fetch(`http://localhost:8080/api/documents/getall?workplaceId=${workplaceId}`)
        .then(function (response) {
            return response.json();
        })
        .then(data => {
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
        })
        .catch(error => console.log(error));

//Iframe'de listeden seçili dosyayı görüntüle
trainingButton.addEventListener("click", () => {
    pdfFrame.style.display = "";

    //Iframe'e Scroll
    document.querySelector("iframe").scrollIntoView({
            behavior: "smooth",
            block: "start",
            inline: "nearest"
    });

    var element = document.getElementsByName('file');

    for (i = 0; i < element.length; i++) {
        if (element[i].checked) {
            selectedItem.innerHTML = "Seçilen dosya: " + element[i].value;
            pdfFrame.src = `/files/${element[i].value}`;
        }
    }
})