import { API_KEY_OpenWeather } from '../js/keys.js';


let params = new URLSearchParams(location.search);

var divs = '';
var user = atob(params.get('n'));
var type = atob(params.get('u'));
var myChart = null;
var myChart_2 = null;

const lst = document.getElementById('list_hst');
var data_hst = document.getElementsByClassName('data_hst');

Construir();
name();

lst.addEventListener("click", ObtenerBtn);

function Construir() {
    document.getElementById('fav_link').href = "favoritos.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "";
    document.getElementById('home_link').href = "home.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "";
    document.getElementById('hst_link').href = "historico.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "";
    document.getElementById('pf_link').href = "perfil.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "";
}

function name() {
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
}


function ObtenerBtn(e) {
    var aux = e.target.parentNode;
    var child = aux.childNodes;
    var lat;
    var lng;
    MostrarDiv();
    try {
        if (child[0].id == 'icon_hst_g') {
            lat = aux.classList[1];
            lng = aux.classList[2];
        } else {
            var aux2 = child[0].childNodes;
            lat = aux2[0].classList[1];
            lng = aux2[0].classList[2];
            var dia = aux2[0].classList[3];
            if ((myChart != null) && (myChart_2 != null)) {
                myChart.destroy();
                myChart_2.destroy();
            }
            estadisticas1(lat, lng, dia);
        }
    } catch (e) {
        if (child[0].id == 'icon_hst_g') {
            lat = aux.classList[1];
            lng = aux.classList[2];
        } else {
            lat = child[0].classList[1];
            lng = child[0].classList[2];
            var dia = child[0].classList[3];
            if ((myChart != null) && (myChart_2 != null)) {
                myChart.destroy();
                myChart_2.destroy();
            }
            estadisticas1(lat, lng, dia);
        }
    }
}

function MostrarData() {
    var aux = document.getElementsByClassName('dat_hst');
    for (var i = 0; i < aux.length; i++) {
        if (!(aux[i].classList.contains('open'))) {
            aux[i].classList.toggle('open');
        }
    }
}

function MostrarDiv() {
    var aux = document.getElementsByClassName('div_hst');
    if (!(aux[0].classList.contains('open'))) {
        aux[0].classList.toggle('open');
    }
}

function estadisticas1(lat, lng, dia) {
    const fecha = new Date();
    const añoActual = fecha.getFullYear();
    const hoy = fecha.getDate();
    const mesActual = fecha.getMonth()
    var newDate = new Date(Date.UTC(añoActual, mesActual, hoy - (1 * dia), 0, 0, 0));
    $.get("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat + "&lon=" + lng + "&dt=" + newDate.getTime().toString().substr(0, 10) + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
        estadisticas2(data);
    });

}

function estadisticas2(data) {
    crear_grafico_linea(data);
    var promedionubes = 0;
    var promediotemp = 0;
    var promediohumedad = 0;
    var climas = new Array();
    var repetidos = new Array();

    for (var i = 0; i < data.hourly.length; i++) {
        promedionubes = promedionubes + (data.hourly[i].clouds) / data.hourly.length;
        promediotemp = promediotemp + (data.hourly[i].temp) / data.hourly.length;
        promediohumedad = promediohumedad + (data.hourly[i].humidity) / data.hourly.length;
        climas[i] = data.hourly[i].weather[0].description;

    }

    data_hst[0].innerHTML = promedionubes.toFixed(1) + "%";
    data_hst[1].innerHTML = promediotemp.toFixed(1) + "°C";
    data_hst[2].innerHTML = promediohumedad.toFixed(1) + "%";
    MostrarData()

    const dataArr = new Set(climas);

    var result = [...dataArr];
    for (var i = 0; i < result.length; i++) {
        repetidos[i] = 0;
        for (var x = 0; x < climas.length; x++) {
            if (result[i] == climas[x]) {
                repetidos[i] = repetidos[i] + 1;
            }
        }
    }

    for (var i = 0; i < result.length; i++) {
        var aux = (repetidos[i] * 100 / 24);
        result[i] = result[i] + ' (' + aux.toFixed(1) + '%)';
    }

    crear_grafico_pie(repetidos, result);
}

function ObtenerHora(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    let fecha = dateObject.toUTCString();
    return fecha.charAt(17) + fecha.charAt(18) + ":00";
}

function crear_grafico_linea(data) {
    var ctx = document.getElementById("myChart").getContext("2d");
    var lab = new Array();
    var tmp = new Array();
    for (var i = 0; i < data.hourly.length; i++) {
        lab[i] = ObtenerHora(data.hourly[i].dt) + "\n(" + data.hourly[i].weather[0].description.charAt(0).toUpperCase() + data.hourly[i].weather[0].description.slice(1) + ")";
        tmp[i] = data.hourly[i].temp;
    }
    myChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: lab,
            datasets: [{
                    label: 'Temperatura (°C)',
                    borderColor: 'rgb(0, 0, 252)',
                    backgroundColor: 'rgb(0,0,0)',
                    data: tmp,
                }],
            options: {
                maintainAspectRatio: false,
                responsive: true,
            }
        }
    });
}

function crear_grafico_pie(data, desc) {
    var coloR = [];

    var dynamicColors = function () {
        var r = Math.floor(Math.random() * 255);
        var g = Math.floor(Math.random() * 255);
        var b = Math.floor(Math.random() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
    };

    for (var i = 0; i < data.length; i++) {
        coloR.push(dynamicColors());
    }

    var ctx = document.getElementById("myChart_pie").getContext("2d");
    myChart_2 = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: desc,
            datasets: [
                {
                    label: 'Dataset 1',
                    data: data,
                    backgroundColor: coloR,
                    borderColor: '#242424',
                    borderWidth: 0
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: false,
                }
            }
        },
    });
}

