import { API_KEY_OpenWeather } from '../js/keys.js';
import { API_KEY_GOOGLE } from '../js/keys.js';

let FavBtn = document.querySelector("#btn_fav");
let params = new URLSearchParams(location.search);

var user = params.get('n');
var type = params.get('u');
var id = params.get('id');
var lat;
var lng;
var namecity;
var divs = '';

ObtenerLatLngId(id);
name();

FavBtn.addEventListener("click", () => {
    if (document.querySelector("#btn_fav.bx.bx-heart")) {
        document.getElementById("btn_fav").className = "bx bxs-heart";
        $.get('../registrarfavorito?lat=' + lat + '&lng=' + lng + '&city=' + namecity + '&user=' + user, function (data) {
            alert(data);
        });
    } else if (document.querySelector("#btn_fav.bx.bxs-heart")) {
        document.getElementById("btn_fav").className = "bx bx-heart";
        $.get('../eliminarfavorito?&city=' + namecity + '&user=' + user, function (data) {
            alert(data);
        });
    }
});


function GuardarCoordenadas(lat1, lng1, cname) {
    lat = lat1;
    lng = lng1;
    namecity = cname;
}

function ValidarFavorito(name) {
    $.get('../validarfavorito?city=' + name + '&user=' + user, function (data) {
        if (data == 1) {
            document.getElementById("btn_fav").className = "bx bxs-heart";
        } else {
            document.getElementById("btn_fav").className = "bx bx-heart";
        }
    });
}

function ObtenerLatLngId() {
    fetch("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + id + "&key=" + API_KEY_GOOGLE)
            .then((res) => res.json()).then(data => {
        var lat = data.result.geometry.location.lat;
        var lng = data.result.geometry.location.lng;
        var name = data.result.address_components[0].long_name;
        var cname = data.result.formatted_address;

        ValidarFavorito(cname);
        ObtenerDatosClima(lat, lng, name);
        pronostico(lat, lng);
        ObtenerCalidadDelAire(lat, lng)
        GuardarCoordenadas(lat, lng, cname);
    });
}

function ObtenerDatosClima(lat, lng, name) {
    $.get("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
        document.title = "Buscar - " + name;
        document.getElementById('fav_link').href = "favoritos.jsp?n=" + user + "&u=" + type + "";
        document.getElementById('home_link').href = "home.jsp?n=" + user + "&u=" + type + "";
        document.getElementById('temp').innerHTML = Math.round(data.main.temp) + "°";
        document.getElementById('icon').style.backgroundImage = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + data.weather[0].icon + ".png')";
        document.getElementById('city').innerHTML = name + "";
        document.getElementById('fondoi').style.backgroundImage = "url('https://source.unsplash.com/1288x665/?" + (data.name) + "')"
        document.getElementById('country').innerHTML = data.sys.country + "";
        document.getElementById('inf').innerHTML = data.weather[0].description.charAt(0).toUpperCase() + data.weather[0].description.slice(1) + "";
        document.getElementById("BarraHome").style.width = (document.getElementById("BarraHome").clientWidth + 30) + "px";
        document.getElementById('perc1').innerHTML = data.clouds.all + "%";
        document.getElementById('perc2').innerHTML = data.main.humidity + "%";
        document.getElementById('presion').innerHTML = data.main.pressure + "hPa";
        document.getElementById('velv').innerHTML = data.wind.speed + " m/s";
        document.getElementById('st').innerHTML = Math.round(data.main.feels_like) + "°";

        Bar_perc(((data.clouds.all) * 180 / 100) + 180, 1);
        Bar_perc(((data.main.humidity) * 180 / 100) + 180, 2);
    });
}

function ObtenerCalidadDelAire(lat, lng) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            $.get("http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lng + "&lang=es&appid=" + API_KEY_OpenWeather, function (data) {
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
        });
    }
}

function name() {
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
}

function ObtenerProbabilidadDeLluvia(data) {
    document.getElementById('pdll').innerHTML = Math.round(data.daily[0].pop * 100) + "%";
}

function fecha(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    const humanDateFormat = dateObject.toLocaleString();
    let fecha = [dateObject.toLocaleString("es-MX", {weekday: "long"}), dateObject.toLocaleString("es-MX", {day: "numeric"}), dateObject.toLocaleString("es-MX", {month: "numeric"})]
    return fecha;
}

function ObtenerHora(dt) {
    const unixTimestamp = dt;
    const milliseconds = dt * 1000;
    const dateObject = new Date(milliseconds);
    const humanDateFormat = dateObject.toLocaleString();
    let fecha = dateObject.toLocaleString("en-US", {hour: "numeric"});
    return fecha;
}


function pronostico(lat, lng) {
    $.get("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lng + "&units=metric&lang=es&exclude=minutely&appid=" + API_KEY_OpenWeather, function (data) {
        ObtenerProbabilidadDeLluvia(data);
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
    });
}

function Bar_perc(perc, i) {
    var param = perc;
    var style = document.styleSheets[4];
    style.insertRule("@keyframes rotar" + i + "{100% {transform: rotate(" + perc + "deg);}}", 6);
}

function icons(i, icon) {
    var id = 'iconC' + i;
    document.getElementById(id).style.backgroundImage = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + icon + ".png')";
}

function concatenar(i, dia, dia2, mes, prons) {
    divs = divs + "<div id='Fpronostico'><span style='font-size: 1rem;color: white;'>" + dia + "</span><span style='font-size: 1rem;color: gray;'>" + dia2 + "/" + mes + "</span><div class='iconC' id='iconC" + i + "'></div><center><span style='font-size: 1rem;color: gray;margin-top:-10px'>" + prons + "</span></center></div>";
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
        },
        options: {
            scales: {

            }
        }
    });
}