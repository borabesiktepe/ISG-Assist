const formContact = document.getElementById("form-contact");
const address = document.getElementById("address");
const city = document.getElementById("cities");
const mail = document.getElementById("mail");
const phone = document.getElementById("phone");
const contactPerson = document.getElementById("contactperson");
const contactPersonPhone = document.getElementById("contactpersonPhone");

const workplaceId = document.getElementById("workplaceId").value;

//Şehirleri Drop Down'a Listele
fetch(`https://turkiyeapi.cyclic.app/api/v1/provinces`)
    .then(res => {
        return res.json();
    })
    .then(json => {
        json.data.forEach(cities => {
            const markupOption = `<option value=${cities.name}>${cities.name}</option>`;
            city.insertAdjacentHTML('beforeend', markupOption);
        });

        console.log("Şehirler select'e listelendi.");
    })
    .catch(error => console.log(error));

//İletişim Bilgileri (Companies Tablosundan)
//Get
const selectDefaultValue = document.getElementById("defaultValue");

fetch(`http://localhost:8080/api/companies/getall?workplaceId=${workplaceId}`)
    .then(response => response.json())
    .then(data => {

        if (data.length == 0) {
            document.getElementById("update").style.display = "none";
            document.getElementById("add").style.display = "";

            console.log("İletişim bilgileri bulunamadı.");
        } else {
            address.value = data[0].address;
            selectDefaultValue.value = data[0].city;
            selectDefaultValue.innerHTML = data[0].city;
            mail.value = data[0].mail;
            phone.value = data[0].phone;
            contactPerson.value = data[0].contactPerson;
            contactPersonPhone.value = data[0].contactPersonPhone;

            document.getElementById("update").style.display = "";
            document.getElementById("add").style.display = "none";

            console.log("İletişim bilgileri input'lara eklendi.");
        }
    })
    .catch((error) => {
        console.log(error)
    });

//Companies Tablosuna Veri Ekle/Düzelt
//Post-Put
formContact.addEventListener('submit', function (e) {
    e.preventDefault();

    console.log(address.value, mail.value, phone.value, contactPerson.value, workplaceId);

    fetch('http://localhost:8080/api/companies/add', {
        method: 'POST',
        body: JSON.stringify({
            address: address.value,
            city: city.value,
            mail: mail.value,
            phone: phone.value,
            contactPerson: contactPerson.value,
            contactPersonPhone: contactPersonPhone.value,
            workplaceId: workplaceId
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));

        location.reload();
});

const updateCompanyBtn = document.getElementById("update");

updateCompanyBtn.addEventListener("click", (e) => {
    e.preventDefault();

    console.log(address.value, mail.value, phone.value, contactPerson.value, workplaceId);

    fetch('http://localhost:8080/api/companies/update/' + workplaceId, {
        method: 'PUT',
        body: JSON.stringify({
            address: address.value,
            city: city.value,
            mail: mail.value,
            phone: phone.value,
            contactPerson: contactPerson.value,
            contactPersonPhone: contactPersonPhone.value,
            workplaceId: workplaceId
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));

        location.reload();
})

//Workplace Bilgilerini Güncelle (WorkplaceName ve WorkplaceDescription)
//Put
const formUpdate = document.getElementById("form-update");
const workplaceName = document.getElementById("name");
const workplaceDesc = document.getElementById("description");

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

        window.location.reload();
});

//Risk Değerlendirme Özeti
//Workplace'e Ait Son Risk Değerlendirme Tarihini Getir
const riskOzet = document.getElementById("risk-infos");
fetch(`http://localhost:8080/api/riskassesments/getall?workplaceId=${workplaceId}`)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        riskOzet.innerHTML += `<span style="font-weight:bold;">Son Risk Değerlendirilme Tarihi:</span> ${data[data.length - 1].degerlendirmeTarihi}`;
    })
    .catch((error) => {
        console.log(error)
    });

//Workplace'e Ait Risk Değerlendirme tablosunda ki risk adedini sınıflarına göre say.
fetch(`http://localhost:8080/api/riskassesments/getall?workplaceId=${workplaceId}`)
  .then(response => response.json())
  .then(data => {
    console.log(data);

    if (data.length > 0 ) {
      let countAzTehlikeli = 0;
      let countTehlikeli = 0;
      let countCokTehlikeli = 0;

      data.forEach(function (item) {
        if (item.risk >= 1 && item.risk <= 6) {
          countAzTehlikeli++;
        } else if (item.risk >= 8 && item.risk <= 12) {
          countTehlikeli++;
        } else if (item.risk >= 15 && item.risk <= 25) {
          countCokTehlikeli++;
        }
      });
      console.log(countAzTehlikeli, countTehlikeli, countCokTehlikeli);
      riskOzet.innerHTML += `
          </br> <span style="color:#92d050; font-weight:bold;">Az Tehlikeli Risk: </span> ${countAzTehlikeli}
          </br> <span style="color:#d9ce00; font-weight:bold;">Tehlikeli Risk: </span> ${countTehlikeli}
          </br> <span style="color:#ff0000; font-weight:bold;">Çok Tehlikeli Risk: </span> ${countCokTehlikeli}
      `;
    } else {
      riskOzet.innerHTML += "Risk değerlendirmesi yapılmamış.";
    }
  })
  .catch(error => console.error(error));


