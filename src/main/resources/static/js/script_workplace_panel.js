console.log("Selam. Burası Workplace Paneli.");

const address = document.getElementById("address");
const mail = document.getElementById("mail");
const phone = document.getElementById("phone");
const contactPerson = document.getElementById("contactperson");
const workplaceId = document.getElementById("workplaceId").value;

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