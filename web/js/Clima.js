/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import { API_KEY_OpenWeather } from '../js/keys.js';
import { API_KEY_GOOGLE } from '../js/keys.js';
//variables generales
let params = new URLSearchParams(location.search);
let FavBtn = document.querySelector("#btn_fav");
var divs = '';
var user = atob(params.get('n'));
var aux = atob(params.get('aux'));
var lat,lng,city;

IdentificarProcedencia();

function IdentificarProcedencia() {
    if (aux == "inicio") {
        document.title = "Inicio";
        FavBtn.style.display = "none";
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                ObtenerDatosClima(position.coords.latitude, position.coords.longitude);
                ObtenerPronostico(position.coords.latitude, position.coords.longitude);
                ObtenerCalidadDelAire(position.coords.latitude, position.coords.longitude);
            });
        }
    } else if (aux == "busqueda") {
        var id = params.get('id');
        ObtenerLatLngId(id);
    } else if (aux == "favorita") {
        lat = params.get('lat');
        lng = params.get('lng');
        city = params.get('c');
        document.title = "Favorita - " + city.split(",")[0];
        ObtenerDatosClima(lat, lng);
        ObtenerPronostico(lat, lng);
        ObtenerCalidadDelAire(lat, lng);
        ValidarFavorito(params.get('c'));
    }
}



function ValidarFavorito(name) {
    console.log(name+"f1")
    $.get('../validarfavorito?city=' + name + '&user=' + user, function (data) {
        if (data == 1) {
            document.getElementById("btn_fav").className = "bx bxs-heart";
        } else {
            document.getElementById("btn_fav").className = "bx bx-heart";
        }
    });
}

function ObtenerLatLngId(id) {
    fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/details/json?place_id=" + id + "&key=" + API_KEY_GOOGLE)
            .then((res) => res.json()).then(data => {
        lat = data.result.geometry.location.lat;
        lng = data.result.geometry.location.lng;
        city = data.result.formatted_address;
        document.title = "Busqueda - " + city;
        ValidarFavorito(city);
        ObtenerDatosClima(lat, lng);
        ObtenerPronostico(lat, lng);
        ObtenerCalidadDelAire(lat, lng)
    });
}

function ObtenerCalidadDelAire(lat, lng) {
    $.get("https://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lng + "&lang=es&appid=" + API_KEY_OpenWeather, function (data) {
        switch (data.list[0].main.aqi) {
            case 1:
                document.getElementById('aqi').innerHTML = "Bueno";
                document.getElementById('aqi').style.color = "green";
                break;
            case 2:
                document.getElementById('aqi').innerHTML = "Aceptable";
                document.getElementById('aqi').style.color = "yellow";
                break;
            case 3:
                document.getElementById('aqi').innerHTML = "Moderado";
                document.getElementById('aqi').style.color = "orange";
                break;
            case 4:
                document.getElementById('aqi').innerHTML = "Malo";
                document.getElementById('aqi').style.color = "red";
                break;
            case 5:
                document.getElementById('aqi').innerHTML = "Muy Malo";
                document.getElementById('aqi').style.color = "brown";
                break;
        }
    })
}

function ObtenerDatosClima(lat, lng) {
    $.get("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
        document.getElementById('temp').innerHTML = Math.round(data.main.temp) + "°";
        document.getElementById('icon').style.backgroundImage = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + data.weather[0].icon + ".png')";
        if (aux !== "inicio") {
            document.getElementById('city').innerHTML = city.split(",")[0] + "";
        } else {
            document.getElementById('city').innerHTML = data.name + "";
        }
        document.getElementById('fondoi').style.backgroundImage = "url('https://source.unsplash.com/1288x665/?" + (data.name) + "')";
        document.getElementById('country').innerHTML = data.sys.country + "";
        document.getElementById('inf').innerHTML = data.weather[0].description.charAt(0).toUpperCase() + data.weather[0].description.slice(1) + "";
        document.getElementById('perc1').innerHTML = data.clouds.all + "%";
        document.getElementById('perc2').innerHTML = data.main.humidity + "%";
        document.getElementById('presion').innerHTML = data.main.pressure + "hPa";
        document.getElementById('velv').innerHTML = data.wind.speed + " m/s";
        document.getElementById('st').innerHTML = Math.round(data.main.feels_like) + "°";
        Bar_perc(((data.clouds.all) * 180 / 100) + 180, 1);
        Bar_perc(((data.main.humidity) * 180 / 100) + 180, 2);
    })
}

function ObtenerPronostico(lat, lng) {
    $.get("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lng + "&units=metric&lang=es&exclude=minutely&appid=" + API_KEY_OpenWeather, function (data) {
        document.getElementById('pdll').innerHTML = Math.round(data.daily[0].pop * 100) + "%";
        crear_grafico(data);
        for (var i = 1; i <= 7; i++) {
            let fechas = fecha(data.daily[i].dt);
            concatenar(i, fechas[0].charAt(0).toUpperCase() + fechas[0].slice(1), fechas[1], fechas[2], data.daily[i].weather[0].description.charAt(0).toUpperCase() + data.daily[i].weather[0].description.slice(1));
        }
        var test = document.getElementById('pronostico');
        test.innerHTML = divs;
        for (var i = 1; i <= 7; i++) {
            icons(i, data.daily[i].weather[0].icon);
        }
    })
}

function Bar_perc(perc, i) {
    var style = document.styleSheets[4];
    style.insertRule("@keyframes rotar" + i + "{100% {transform: rotate(" + perc + "deg);}}", 6);
}

function crear_grafico(data) {
    var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: [ObtenerHora(data.hourly[1].dt), ObtenerHora(data.hourly[2].dt), ObtenerHora(data.hourly[3].dt), ObtenerHora(data.hourly[4].dt), ObtenerHora(data.hourly[5].dt)],
            datasets: [{
                    label: 'Prob. de lluvia por hora (%)',
                    borderColor: 'rgb(0, 0, 252)',
                    backgroundColor: 'rgb(0,0,0)',
                    data: [Math.round(data.hourly[1].pop * 100), Math.round(data.hourly[2].pop * 100), Math.round(data.hourly[3].pop * 100), Math.round(data.hourly[4].pop * 100), Math.round(data.hourly[5].pop * 100)],
                }]
        }
    });
}

function ObtenerHora(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    const humanDateFormat = dateObject.toLocaleString();
    let fecha = dateObject.toLocaleString("en-US", {hour: "numeric"});
    return fecha;
}

function fecha(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    const humanDateFormat = dateObject.toLocaleString();
    let fecha = [dateObject.toLocaleString("es-MX", {weekday: "long"}), dateObject.toLocaleString("es-MX", {day: "numeric"}), dateObject.toLocaleString("es-MX", {month: "numeric"})]
    return fecha;
}

function concatenar(i, dia, dia2, mes, prons) {
    divs = divs + "<div id='Fpronostico'><span style='font-size: 1rem;color: white;'>" + dia + "</span><span style='font-size: 1rem;color: gray;'>" + dia2 + "/" + mes + "</span><div class='iconC' id='iconC" + i + "'></div><center><span style='font-size: 1rem;color: gray;margin-top:-10px'>" + prons + "</span></center></div>";
}

function icons(i, icon) {
    var id = 'iconC' + i;
    document.getElementById(id).style.backgroundImage = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + icon + ".png')";
}

FavBtn.addEventListener("click", () => {
    if (document.querySelector("#btn_fav.bx.bx-heart")) {
        document.getElementById("btn_fav").className = "bx bxs-heart";
        console.log(lat);
        $.get('../registrarfavorito?lat=' + lat + '&lng=' + lng + '&city=' + city + '&user=' + user, function (data) {
            alert(data);
        });
    } else if (document.querySelector("#btn_fav.bx.bxs-heart")) {
        document.getElementById("btn_fav").className = "bx bx-heart";
        $.get('../eliminarfavorito?&city=' + city + '&user=' + user, function (data) {
            alert(data);
        });
    }
});