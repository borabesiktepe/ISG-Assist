fetch('https://64302442c26d69edc88bce05.mockapi.io/api/graph/enTekrarEdenTehlikeler')
    .then(response => response.json())
    .then(data => {
        console.log(data);

        xValues = [];
        yValues = [];
        let barColors = [
            "#FFC40C",
            "#96BD33",
            "#34A761",
            "#008A7B",
            "#006978",
            "#2F4858"
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
            "#FFC40C",
            "#96BD33",
            "#34A761",
            "#008A7B",
            "#006978",
            "#2F4858"
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

var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
var yValues = [55, 49, 44, 24, 15];
let barColors = [
    "#FFC40C",
    "#96BD33",
    "#34A761",
    "#008A7B",
    "#006978",
    "#2F4858"
];

new Chart("chart1", {
    type: "pie",
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
            text: "World Wide Wine Production 2018"
        }
    }
});

new Chart("chart2", {
    type: "pie",
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
            text: "World Wide Wine Production 2018"
        }
    }
});