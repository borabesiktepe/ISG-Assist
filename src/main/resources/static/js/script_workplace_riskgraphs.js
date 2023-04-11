const workplaceId = document.getElementById("workplaceId").value;

let barColors = [
    "#FFC40C",
    "#96BD33",
    "#34A761",
    "#008A7B",
    "#006978",
    "#2F4858"
];

fetch('http://localhost:8080/api/riskassesments/getGenelTehlikeCount?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Risk Tehlike Count:", data);

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

            new Chart("chart-tehlike-sayilari", {
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
                        text: "Tüm Tehlike Sayıları"
                    }
                }
            });
        }
    })

//En çok tehlike olan Yer/Ekipmanlar
fetch('http://localhost:8080/api/riskassesments/getYerEkipmanTehlikeCount?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Risk Yer/Ekipman Count:", data);

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

            new Chart("chart-tehlike-sayilari-yerekipman", {
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
                        text: "Yer/Ekipmanlara Ait Tehlike Sayıları"
                    }
                }
            });
        }
    })

//Telike Adlarına Göre Toplam Riskleri
fetch('http://localhost:8080/api/riskassesments/getTehlikeAdiRiskSum?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Tehlike Adı Risk Sum:", data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].tehlikeAdi);
                yValues.push(data[i].toplamRisk);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
            }

            new Chart("chart-tehlike-adi-toplamRisk", {
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
                        text: "Tehlikelerin Sahip Olduğu Toplam Riskler"
                    }
                }
            });
        }
    })

//Telike Adlarına Göre Toplam Son Riskleri
fetch('http://localhost:8080/api/riskassesments/getTehlikeAdiSonRiskSum?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Tehlike Adı Son Risk Sum:", data);

        if (data.length > 0) {
            xValues = [];
            yValues = [];

            for (i = 0; i < data.length; i++) {
                xValues.push(data[i].tehlikeAdi);
                yValues.push(data[i].toplamSonRisk);
            }

            for (i = 0; i < xValues.length; i++) {
                const R = Math.floor(Math.random() * 255);
                const G = Math.floor(Math.random() * 255);
                const B = Math.floor(Math.random() * 255);
                barColors.push(`rgba(${R}, ${G}, ${B}, 1)`);
            }

            new Chart("chart-tehlike-adi-toplamSonRisk", {
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
                        text: "Tehlikelerin Sahip Olduğu Toplam Son Riskler"
                    }
                }
            });
        }
    })


// Yer/Ekipmanların Toplam Riskleri
fetch('http://localhost:8080/api/riskassesments/getYerEkipmanRiskSum?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Yer Ekipman Risk Sum:", data);

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

            new Chart("chart-yer-ekipman-toplamRisk", {
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

//Yer/Ekipmanların Toplam Son Riskleri
fetch('http://localhost:8080/api/riskassesments/getYerEkipmanSonRiskSum?workplaceId=' + workplaceId)
    .then(response => response.json())
    .then(data => {
        console.log("Yer/Ekipman Son Risk Sum:", data);

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