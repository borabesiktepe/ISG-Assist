console.log("Selam. Burası Workplace Paneli.");

const formContact = document.getElementById("form-contact");
const address = document.getElementById("address");
const mail = document.getElementById("mail");
const phone = document.getElementById("phone");
const contactPerson = document.getElementById("contactperson");

const workplaceId = document.getElementById("workplaceId").value;

//API GET İŞLEMLERİ
//İLETİŞİM BİLGİLERİ ALANI
fetch(`http://localhost:8080/api/companies/getall?workplaceId=${workplaceId}`)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        address.value = data[0].address;
        mail.value = data[0].mail;
        phone.value = data[0].phone;
        contactPerson.value = data[0].contactPerson;
    })
    .catch((error) => {
        console.log(error)
});

//API POST İŞLEMLERİ
//İLETİŞİM BİLGİLERİ ALANI
formContact.addEventListener('submit', function (e) {
    e.preventDefault();

    console.log(address.value, mail.value, phone.value, contactPerson.value, workplaceId);

    fetch('http://localhost:8080/api/companies/add', {
        method: 'POST',
        body: JSON.stringify({
            address: address.value,
            mail: mail.value,
            phone: phone.value,
            contactPerson: contactPerson.value,
            workplaceId: workplaceId.value
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
});