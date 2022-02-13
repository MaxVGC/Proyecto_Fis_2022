//https://maps.googleapis.com/maps/api/place/autocomplete/json?input=villa&types=geocode&key=AIzaSyCSvCi-oJw7_VDj-zkrzkZCx4l1iGeH2Eg



import { API_KEY_GOOGLE } from '../js/keys.js';
import { API_KEY_OpenWeather } from '../js/keys.js';

let params = new URLSearchParams(location.search);

const input = document.getElementById('input_hst');
const log = document.getElementById('valores');

var aux = 0;
var user = params.get('n');
var type = params.get('u');
var historial = new Array();

input.addEventListener('input', updateValue);

function updateValue(e) {
    var p = e.srcElement.value;
    var div = document.getElementById('sugerencias_hst');
    if (p.length >= 3) {
        if (aux == 0) {
            div.classList.toggle('open');
            aux = 1;
        }
        fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + e.srcElement.value + "&types=%28cities%29&key=" + API_KEY_GOOGLE)
                .then((res) => res.json()).then(data => {
            var aux = '';
            for (var i = 0; i <= (data.predictions.length - 1); i++) {
                if (data.predictions[i].terms.length == 2) {
                    aux = aux + concatenar1(data.predictions[i].terms[0].value, data.predictions[i].terms[1].value, data.predictions[i].place_id, i);
                } else {
                    aux = aux + concatenar2(data.predictions[i].terms[0].value, data.predictions[i].terms[1].value, data.predictions[i].terms[2].value, data.predictions[i].place_id, i);
                }
            }
            div.innerHTML = aux;
            click();
        });
    } else {
        if (aux == 1) {
            div.classList.toggle('open');
            aux = 0;
        }
    }
}

function concatenar1(nombre, pais, id, i) {
    var aux = "<div id='ds_" + i + "' class='" + id + "'>" + nombre + ", " + pais + "</div>"
    return aux;
}

function concatenar2(nombre, estado, pais, id, i) {
    var aux = "<div id='ds_" + i + "' class='" + id + "'>" + nombre + ", " + estado + ", " + pais + "</div>"
    return aux;
}

function click() {
    var div = document.getElementById('sugerencias_hst');
    var div2 = document.getElementById('card_hst');
    $("#ds_0").click(function (event) {
        GenerarLista($(this), div, div2);
    });
    $("#ds_1").click(function (event) {
        GenerarLista($(this), div, div2);
    });
    $("#ds_2").click(function (event) {
        GenerarLista($(this), div, div2);
    });
    $("#ds_3").click(function (event) {
        GenerarLista($(this), div, div2);
    });
    $("#ds_4").click(function (event) {
        GenerarLista($(this), div, div2);
    });
}

function OcultarDiv() {
    var aux = document.getElementsByClassName('div_hst');
    if ((aux[0].classList.contains('open'))) {
        aux[0].classList.toggle('open');
    }
}
function GenerarLista(ele, div, div2) {
    OcultarDiv()
    input.value = "";
    document.getElementById('title_hst').innerHTML = ele.text();
    ObtenerLatLngId(ele[0].className);
    div.classList.toggle('open');
    if (!(document.getElementById("list_hst").classList.contains('open') && div2.classList.contains('open'))) {
        div2.classList.toggle('open');
        document.getElementById("list_hst").classList.toggle('open');
    }
    document.getElementById('card_image_hst').src = "https://source.unsplash.com/1288x665/?" + ele.text() + "";
    document.getElementById('fondoi').classList.toggle('listo');
}

function ObtenerLatLngId(id) {
    fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/details/json?place_id=" + id + "&key=" + API_KEY_GOOGLE)
            .then((res) => res.json()).then(data => {
        var lat = data.result.geometry.location.lat;
        var lng = data.result.geometry.location.lng;
        var dt = data;
        const fecha = new Date();
        const añoActual = fecha.getFullYear();
        const hoy = fecha.getDate();
        const mesActual = fecha.getMonth()
        var newDate = new Date(Date.UTC(añoActual, mesActual, hoy - 1, 0, 0, 0));
        BuscarHistorial(lat, lng, newDate.getTime().toString().substr(0, 10));

    });
}

function BuscarHistorial(lat, lng, dt) {
    for (var i = 0; i < 5; i++) {
        HistorialPorDias(lat, lng, (dt - 86400 * i), i + 1);
    }
}

function HistorialPorDias(lat, lng, dt, i) {
    $.get("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat + "&lon=" + lng + "&dt=" + dt + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
        CrearFichasHistorial(data, i, dt, lat, lng)
    });
}



function CrearFichasHistorial(data, i, dt, lat, lng) {
    var aux = document.getElementsByClassName('ficha_hst');
    let fechas = fecha(data.current.dt);
    var x = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + data.current.weather[0].icon + ".png')";
    aux[i].innerHTML = '<div id="contain_hst"><div id="' + dt + '" class="image_hst ' + lat + ' ' + lng + ' ' + i + '" style="background-image:' + x + '"></div><span id="span_hst" >' + fechas[0].charAt(0).toUpperCase() + fechas[0].slice(1) + '  ' + fechas[1] + '/' + fechas[2] + '</span></div>';
}

function fecha(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    const humanDateFormat = dateObject.toLocaleString();
    let fecha = [dateObject.toLocaleString("es-MX", {weekday: "long"}), dateObject.toLocaleString("es-MX", {day: "numeric"}), dateObject.toLocaleString("es-MX", {month: "numeric"})]
    return fecha;
}

