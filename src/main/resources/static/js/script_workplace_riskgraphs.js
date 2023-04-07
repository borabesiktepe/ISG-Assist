fetch('https://64302442c26d69edc88bce05.mockapi.io/api/graph/enTekrarEdenTehlikeler')
    .then(response => response.json())
    .then(data => {
        console.log(data);

        xValues = [];
        yValues = [];
        let barColors = [
            "#b91d47",
            "#00aba9",
            "#2b5797",
            "#e8c3b9",
            "#1e7145"
        ];

        for (i = 0; i < data.length; i++) {
            xValues.push(data[i].tehlikeAdi);
            yValues.push(data[i].olaySayisi);
        }

        new Chart("chart-encok-tehlike-tekrar", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "En çok tekrar eden tehlikeler"
                }
            }
        });
    })

//En çok tehlike olan Yer/Ekipmanlar
fetch('https://64302442c26d69edc88bce05.mockapi.io/api/graph/enTehlikeliYerEkipman')
    .then(response => response.json())
    .then(data => {
        console.log(data);

        xValues = [];
        yValues = [];
        let barColors = [
            "#b91d47",
            "#00aba9",
            "#2b5797",
            "#e8c3b9",
            "#1e7145"
        ];

        for (i = 0; i < data.length; i++) {
            xValues.push(data[i].yerEkipman);
            yValues.push(data[i].olaySayisi);
        }

        new Chart("chart-en-tehlikeli-yerekipman", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "En Tehlikeli Yer/Ekipmanlar"
                }
            }
        });
    })
