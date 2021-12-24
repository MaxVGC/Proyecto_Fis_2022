import { API_KEY_OpenWeather } from '../js/keys.js';
var divs = '';
let params = new URLSearchParams(location.search);
var user = params.get('n');
var type = params.get('u');

Construir();
name();

function Construir() {
    $.get("https://api.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
        document.getElementById('fav_link').href = "favoritos.jsp?n=" + user + "&u=" + type + "";
        document.getElementById('home_link').href = "home.jsp?n=" + user + "&u=" + type + "";
    });
}


function name() {
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
}

