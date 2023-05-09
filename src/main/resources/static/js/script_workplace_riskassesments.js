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

    const dateOptions = {
      timeZone: 'Europe/Istanbul',
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    };

    const date = new Date().toLocaleDateString('tr-TR', dateOptions);

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
    e.preventDefault();

    let risk = siddet.value * olasilik.value;
    let sRisk = sSiddet.value * sOlasilik.value;

    const dateOptions = {
      timeZone: 'Europe/Istanbul',
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    };

    const date = new Date().toLocaleDateString('tr-TR', dateOptions);

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

//Tabloda seçilen satırı iptal etme
const cancelSelection = document.getElementById("cancel");

cancelSelection.addEventListener("click", () => {
        tehlikeAdi.value = "";
        yerEkipman.value = "";
        mevcutTehlikeler.value = "";
        olusacakRiskler.value = "";
        mevcutOnlemler.value = "";
        maruzKalanlar.value = "";
        siddet.value = "";
        olasilik.value = "";
        alinacakTedbirler.value = "";
        sSiddet.value = "";
        sOlasilik.value = "";
        selectedRiskId = "";

    document.getElementById("table-buttons").style.display = "none";
    document.getElementById("message").style.display = "none";
    document.getElementById("add").style.display = "";
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

    document.getElementById("message").style.display = "";
    document.getElementById("message").innerHTML = "Seçilen sıra numarası: " + tds[0].innerHTML.trim();

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

//SheetJS ile görüntülenen tabloyu EXCEL formatında indirme
function exportToExcel(type, fn, dl) {
    let table = document.getElementById('risk-table');

    let wb = XLSX.utils.table_to_book(table, { sheet: "sheet1" });
    return dl ?
        XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }) :
        XLSX.writeFile(wb, fn || ('RiskDegerlendirmeTablosu_' + workplaceId + '.' + (type || 'xlsx')));
}

//Kullanıcı sayfayı aşağı kaydırınca çıkan Scroll aksiyonlu butonlar
const scrollButtons = document.getElementById("scroll-buttons");
const scrollToTop = document.getElementById("scrollToTop");
const scrollToInpust = document.getElementById("scrollToInputs");

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 300 || document.documentElement.scrollTop > 300) {
    scrollButtons.style.display = "flex";
  } else {
    scrollButtons.style.display = "none";
  }
}

scrollToTop.addEventListener("click", () => {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
})

scrollToInpust.addEventListener("click", () => {
        document.querySelector(".table-inputs").scrollIntoView({
                behavior: "smooth",
                block: "start",
                inline: "nearest"
        });
})

//Tehlike adı filtresi için tabloda ki verilere göre Select'e dizme
const tehlikeSelect = document.getElementById("tehlike-select");
fetch("http://localhost:8080/api/riskassesments/getall?workplaceId=" + workplaceId)
    .then((data) => {
        return data.json();
    })
    .then((objectData) => {
        const uniqueData = [...new Set(objectData.map((values) => values.tehlikeAdi))];
        let tehlikeSelectOptions = "";

        uniqueData.forEach((tehlikeAdi) => {
            tehlikeSelectOptions += `
                <option value='${tehlikeAdi}'>${tehlikeAdi}</option>
            `;
        });

        tehlikeSelect.innerHTML += tehlikeSelectOptions;
    })

tehlikeSelect.addEventListener("change", () => {
  const selectedValue = tehlikeSelect.options[tehlikeSelect.selectedIndex].value;
  const tableRows = tableBody.getElementsByTagName("tr");

  if (selectedValue === "all") {
    for (let i = 0; i < tableRows.length; i++) {
      tableRows[i].style.display = "";
    }
    return;
  }

  for (let i = 0; i < tableRows.length; i++) {
    const tableData = tableRows[i].getElementsByTagName("td");
    let shouldShow = false;
    for (let j = 0; j < tableData.length; j++) {
      if (tableData[j].textContent === selectedValue) {
        shouldShow = true;
        break;
      }
    }
    if (shouldShow) {
      tableRows[i].style.display = "";
    } else {
      tableRows[i].style.display = "none";
    }
  }
});

//Yer/Ekipman filtresi için tabloda ki verilere göre Select'e dizme
const yerEkipmanSelect = document.getElementById("yer-ekipman-select");
fetch("http://localhost:8080/api/riskassesments/getall?workplaceId=" + workplaceId)
    .then((data) => {
        return data.json();
    })
    .then((objectData) => {
        const uniqueData = [...new Set(objectData.map((values) => values.yerEkipman))];
        let yerEkipmanSelectOptions = "";

        uniqueData.forEach((yerEkipman) => {
            yerEkipmanSelectOptions += `
                <option value='${yerEkipman}'>${yerEkipman}</option>
            `;
        });

        yerEkipmanSelect.innerHTML += yerEkipmanSelectOptions;
    })

yerEkipmanSelect.addEventListener("change", () => {
  const selectedValue = yerEkipmanSelect.options[yerEkipmanSelect.selectedIndex].value;
  const tableRows = tableBody.getElementsByTagName("tr");

  if (selectedValue === "all") {
    for (let i = 0; i < tableRows.length; i++) {
      tableRows[i].style.display = "";
    }
    return;
  }

  for (let i = 0; i < tableRows.length; i++) {
    const tableData = tableRows[i].getElementsByTagName("td");
    let shouldShow = false;
    for (let j = 0; j < tableData.length; j++) {
      if (tableData[j].textContent === selectedValue) {
        shouldShow = true;
        break;
      }
    }
    if (shouldShow) {
      tableRows[i].style.display = "";
    } else {
      tableRows[i].style.display = "none";
    }
  }
});

//Maruz Kalanlar filtresi için tabloda ki verilere göre Select'e dizme
const maruzKalanlarSelect = document.getElementById("maruz-kalanlar-select");
fetch("http://localhost:8080/api/riskassesments/getall?workplaceId=" + workplaceId)
    .then((data) => {
        return data.json();
    })
    .then((objectData) => {
        const uniqueData = [...new Set(objectData.map((values) => values.maruzKalanlar))];
        let maruzKalanlarSelectOptions = "";

        uniqueData.forEach((maruzKalanlar) => {
            maruzKalanlarSelectOptions += `
                <option value='${maruzKalanlar}'>${maruzKalanlar}</option>
            `;
        });

        maruzKalanlarSelect.innerHTML += maruzKalanlarSelectOptions;
    })

maruzKalanlarSelect.addEventListener("change", () => {
  const selectedValue = maruzKalanlarSelect.options[maruzKalanlarSelect.selectedIndex].value;
  const tableRows = tableBody.getElementsByTagName("tr");

  if (selectedValue === "all") {
    for (let i = 0; i < tableRows.length; i++) {
      tableRows[i].style.display = "";
    }
    return;
  }

  for (let i = 0; i < tableRows.length; i++) {
    const tableData = tableRows[i].getElementsByTagName("td");
    let shouldShow = false;
    for (let j = 0; j < tableData.length; j++) {
      if (tableData[j].textContent === selectedValue) {
        shouldShow = true;
        break;
      }
    }
    if (shouldShow) {
      tableRows[i].style.display = "";
    } else {
      tableRows[i].style.display = "none";
    }
  }
});

//Risk Değerlendirme tablosunda "Risk" kolonuna göre filtreleme yapma
const riskSelect = document.getElementById('risk-select');
const tableBody = document.getElementById('table_body');

function filterRisk(min, max) {
    for (let i = 0; i < tableBody.children.length; i++) {
        const riskElement = tableBody.children[i].children[9];
        const riskValue = parseInt(riskElement.innerText);
        if (riskValue >= min && riskValue <= max) {
            tableBody.children[i].style.display = '';
        } else {
            tableBody.children[i].style.display = 'none';
        }
    }
}

riskSelect.addEventListener('change', function () {
    const selectedValue = this.value;
    switch (selectedValue) {
        case '0-6':
            filterRisk(0, 6);
            break;
        case '8-12':
            filterRisk(8, 12);
            break;
        case '15-25':
            filterRisk(15, 25);
            break;
        default:
            for (let i = 0; i < tableBody.children.length; i++) {
                tableBody.children[i].style.display = '';
            }
    }
});

//Risk Değerlendirme tablosunda "Risk ve Son Risk" kolonlarına göre filtreleme yapma
const sonRiskSelect = document.getElementById('son-risk-select');

function filterSonRisk(min, max) {
    for (let i = 0; i < tableBody.children.length; i++) {
        const riskElement = tableBody.children[i].children[13];
        const riskValue = parseInt(riskElement.innerText);
        if (riskValue >= min && riskValue <= max) {
            tableBody.children[i].style.display = '';
        } else {
            tableBody.children[i].style.display = 'none';
        }
    }
}

sonRiskSelect.addEventListener('change', function () {
    const selectedValue = this.value;
    switch (selectedValue) {
        case '0-6':
            filterSonRisk(0, 6);
            break;
        case '8-12':
            filterSonRisk(8, 12);
            break;
        case '15-25':
            filterSonRisk(15, 25);
            break;
        default:
            for (let i = 0; i < tableBody.children.length; i++) {
                tableBody.children[i].style.display = '';
            }
    }
});