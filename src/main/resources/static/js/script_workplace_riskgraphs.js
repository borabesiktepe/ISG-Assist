let barColors = [
    "#FFC40C",
    "#96BD33",
    "#34A761",
    "#008A7B",
    "#006978",
    "#2F4858"
];

fetch('http://localhost:8080/api/riskassesments/getRiskTehlikeCount?workplaceId=' + 2)
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
fetch('http://localhost:8080/api/riskassesments/getRiskYerEkipmanCount?workplaceId=' + 2)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].yerEkipman);
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

// Yer/Ekipmanların Toplam Riskleri
fetch('http://localhost:8080/api/riskassesments/getYerEkipmanRiskSum?workplaceId=' + 2)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].yerEkipman);
                yValues.push(data[i].toplamRisk);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
            }

            new Chart("chart-yer-ekipman-toplamrisk", {
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
                        text: "Yer/Ekipmanların Sahip Olduğu Toplam Riskler"
                    }
                }
            });
        }
    })

// Yer/Ekipmanların Toplam Son Riskleri
fetch('http://localhost:8080/api/riskassesments/getYerEkipmanSonRiskSum?workplaceId=' + 2)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].yerEkipman);
                yValues.push(data[i].toplamRisk);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
            }

            new Chart("chart-yer-ekipman-toplamSonRisk", {
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
                        text: "Yer/Ekipmanların Sahip Olduğu Toplam Son Riskler"
                    }
                }
            });
        }
    })