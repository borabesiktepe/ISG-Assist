const tehlikeAdi = document.getElementById("tehlikeAdi");
const yerEkipman = document.getElementById("yerEkipman");
const mevcutTehlikeler = document.getElementById("mevcutTehlikeler");
const olusacakRiskler = document.getElementById("olusacakRiskler");
const mevcutOnlemler = document.getElementById("mevcutOnlemler");
const maruzKalanlar = document.getElementById("maruzKalanlar");
const siddet = document.getElementById("siddet");
const olasilik = document.getElementById("olasilik");
const alinacakTedbirler = document.getElementById("alinacakTedbirler");
const sSiddet = document.getElementById("sSiddet");
const sOlasilik = document.getElementById("sOlasilik");

const workplaceId = document.getElementById("workplaceId").value;

let selectedRiskId;

document.getElementById("table-buttons").style.display = "none";
document.getElementById("add").style.display = "";

//Workplace'e ait Risk Değerlendirme Verilerini Tabloya Doldur (Risk Assesment Tablosundan)
fetch("http://localhost:8080/api/riskassesments/getall?workplaceId=" + workplaceId)
    .then((data) => {
        return data.json();
    })
    .then((objectData) => {
        console.log(objectData[0]);
        let tableData = "";
        let siraNo = 1;
        objectData.map((values) => {
            tableData += `
                <tr onclick="printToInputs(this)">
                    <td>${siraNo}</td>
                    <td>${values.tehlikeAdi}</td>
                    <td>${values.yerEkipman}</td>
                    <td>${values.mevcutTehlikeler}</td>
                    <td>${values.olusacakRiskler}</td>
                    <td>${values.mevcutOnlemler}</td>
                    <td>${values.maruzKalanlar}</td>
                    <td>${values.siddet}</td>
                    <td>${values.olasilik}</td>
                    <td class="colored">${values.risk}</td>
                    <td>${values.alinacakTedbirler}</td>
                    <td>${values.sonSiddet}</td>
                    <td>${values.sonOlasilik}</td>
                    <td class="colored">${values.sonRisk}</td>
                    <td style="display:none;">${values.id}</td>
                </tr>
            `;
            siraNo++;
        })
        document.getElementById("table_body").innerHTML = tableData
    })

//Risk Assesment Tablosuna Veri Ekle
const formRisk = document.getElementById("risk-assesment");
formRisk.addEventListener('submit', function (e) {
    e.preventDefault();

    let risk = siddet.value * olasilik.value;
    let sRisk = sSiddet.value * sOlasilik.value;
    let today = new Date();
    let date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();

    console.log("Risk:", risk, "Son Risk:", sRisk);
    console.log(tehlikeAdi.value, yerEkipman.value, mevcutTehlikeler.value, olusacakRiskler.value, mevcutOnlemler.value, maruzKalanlar.value, siddet.value, olasilik.value, risk, alinacakTedbirler.value, sSiddet.value, sOlasilik.value, sRisk, date, workplaceId);

    fetch('http://localhost:8080/api/riskassesments/add', {
        method: 'POST',
        body: JSON.stringify({
            tehlikeAdi: tehlikeAdi.value,
            yerEkipman: yerEkipman.value,
            mevcutTehlikeler: mevcutTehlikeler.value,
            olusacakRiskler: olusacakRiskler.value,
            mevcutOnlemler: mevcutOnlemler.value,
            maruzKalanlar: maruzKalanlar.value,
            siddet: siddet.value,
            olasilik: olasilik.value,
            risk: risk,
            alinacakTedbirler: alinacakTedbirler.value,
            sonSiddet: sSiddet.value,
            sonOlasilik: sOlasilik.value,
            sonRisk: sRisk,
            degerlendirmeTarihi: date,
            workplaceId: workplaceId
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .then(window.location.reload())
        .catch(error => console.error('Error:', error));
});

//Tabloda seçilen satırın verilerini Risk Assesment tablosunda güncelle
const updateRisks = document.getElementById("update");

updateRisks.addEventListener("click", () => {
    let risk = siddet.value * olasilik.value;
    let sRisk = sSiddet.value * sOlasilik.value;
    let today = new Date();
    let date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();

    fetch('http://localhost:8080/api/riskassesments/update/' + selectedRiskId, {
        method: 'PUT',
        body: JSON.stringify({
            tehlikeAdi: tehlikeAdi.value,
            yerEkipman: yerEkipman.value,
            mevcutTehlikeler: mevcutTehlikeler.value,
            olusacakRiskler: olusacakRiskler.value,
            mevcutOnlemler: mevcutOnlemler.value,
            maruzKalanlar: maruzKalanlar.value,
            siddet: siddet.value,
            olasilik: olasilik.value,
            risk: risk,
            alinacakTedbirler: alinacakTedbirler.value,
            sonSiddet: sSiddet.value,
            sonOlasilik: sOlasilik.value,
            sonRisk: sRisk,
            degerlendirmeTarihi: date,
            workplaceId: workplaceId
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .then(window.location.reload())
        .catch(error => console.error('Error:', error));
});

//Tabloda seçilen satırı veritabanından silme
const deleteRisks = document.getElementById("delete");

deleteRisks.addEventListener("click", () => {
        fetch('http://localhost:8080/api/riskassesments/delete/' + selectedRiskId, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => console.log(data))

        location.reload();
})


//Görüntülenen tabloda tıklanan satırın verilerini input'lara dizme
function printToInputs(e) {
    document.getElementById("table-buttons").style.display = "";
    document.getElementById("add").style.display = "none";

    var tds = e.getElementsByTagName('td');
    tehlikeAdi.value = tds[1].innerHTML.trim();
    yerEkipman.value = tds[2].innerHTML.trim();
    mevcutTehlikeler.value = tds[3].innerHTML.trim();
    olusacakRiskler.value = tds[4].innerHTML.trim();
    mevcutOnlemler.value = tds[5].innerHTML.trim();
    maruzKalanlar.value = tds[6].innerHTML.trim();
    siddet.value = tds[7].innerHTML.trim();
    olasilik.value = tds[8].innerHTML.trim();
    alinacakTedbirler.value = tds[10].innerHTML.trim();
    sSiddet.value = tds[11].innerHTML.trim();
    sOlasilik.value = tds[12].innerHTML.trim();
    selectedRiskId = tds[14].innerHTML.trim();

    document.getElementById("message").innerHTML = "Seçilen sıra no: " + tds[0].innerHTML.trim();

    console.log("Seçili Risk ID: " + selectedRiskId);
}

//Görüntülenen Tabloda Risk ve Son Risk kolonlarına ait verilerin içinde ki değere göre renklendirilmesi
setInterval(function colorizeCells() {
    let tableColumn = document.querySelectorAll(".colored");

    tableColumn.forEach((colored) => {
        if (colored.innerHTML > 0) {
            colored.style.backgroundColor = "#92d050";
        } if (colored.innerHTML > 6) {
            colored.style.backgroundColor = "#ffff00";
        } if (colored.innerHTML > 12) {
            colored.style.backgroundColor = "#ff0000";
        }
    });
}, 100);

//Görüntülenen tabloyu EXCEL formatında indirme
function exportToExcel(type, fn, dl) {
    let table = document.getElementById('risk-table');

    let wb = XLSX.utils.table_to_book(table, { sheet: "sheet1" });
    return dl ?
        XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }) :
        XLSX.writeFile(wb, fn || ('RiskDegerlendirmeTablosu_' + workplaceId + '.' + (type || 'xlsx')));
}