let barColors = [
    "#FFC40C",
    "#96BD33",
    "#34A761",
    "#008A7B",
    "#006978",
    "#2F4858"
];

fetch('https://64302442c26d69edc88bce05.mockapi.io/api/graph/enTekrarEdenTehlikeler')
    .then(response => response.json())
    .then(data => {
        console.log(data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].tehlikeAdi);
                yValues.push(data[i].olaySayisi);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
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
        }
    })

//En çok tehlike olan Yer/Ekipmanlar
fetch('https://64302442c26d69edc88bce05.mockapi.io/api/graph/enTehlikeliYerEkipman')
    .then(response => response.json())
    .then(data => {
        console.log(data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].tehlikeAdi);
                yValues.push(data[i].olaySayisi);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
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
        }
    })





var xValues = ["Italy", "France", "Spain", "USA", "Argentina", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K"];
var yValues = [55, 49, 44, 24, 15, 55, 49, 44, 24, 15, 55, 49, 44, 24, 15, 55, 49, 44, 24, 15];

for (i = 0; i < xValues.length; i++) {
    const R = Math.floor(Math.random() * 255);
    const G = Math.floor(Math.random() * 255);
    const B = Math.floor(Math.random() * 255);
    barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
}

new Chart("chart1", {
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