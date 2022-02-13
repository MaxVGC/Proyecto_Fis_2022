import { API_KEY_GOOGLE } from '../js/keys.js';
import { API_KEY_OpenWeather } from '../js/keys.js';

ciudad();
obtenerclima()
console.log("entre");

function Lugares() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            fetch("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + position.coords.latitude + "," + position.coords.longitude + "&language=es&key=" + API_KEY_GOOGLE)
                .then((res) => res.json()).then(data => {
                    console.log("Ciudad " + "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + position.coords.latitude + "," + position.coords.longitude + "&language=es&key=" + API_KEY_GOOGLE);
                });
        });
    }
}

function ciudad() {
    console.log("entre a ciudad")
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            fetch("https://api.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&appid=" + API_KEY_OpenWeather)
                .then((res) => res.json()).then(data => {                 
                    var x = data.name;
                    traernombreciudad(x.replaceAll(' ', '_'));
                });
        });
    }
}

function traernombreciudad(ciudad) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + position.coords.latitude + "," + position.coords.longitude + "&radius=5000&language=es&keyword=" + ciudad + "&key=" + API_KEY_GOOGLE)
                .then((res) => res.json()).then(data => {
                    traerphotoreference(data);
                });

        });
    }
}

function traerphotoreference(data) {
    var reference = data.results[0].photos[0].photo_reference;
    traerphoto(reference);
}

function traerphoto(reference) {

    const intro = document.getElementById('bg-image');
    const link = 'https://maps.googleapis.com/maps/api/place/photo?maxwidth=1920&maxheigh=1080&photoreference=' + reference + '&sensor=false&key=' + API_KEY_GOOGLE;
    intro.style.backgroundImage = "url('" + link + "')";

}

function pronostico() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            fetch("https://api.openweathermap.org/data/2.5/onecall?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&exclude=minutely,hourly&appid=" + API_KEY_OpenWeather)
                .then((res) => res.json()).then(data => {
                    console.log("Pronostico")
                    console.log(data);
                });
        });
    }
}

function obtenerclima() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            $.get("https://api.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function(data) {
                document.getElementById('temp').innerHTML = Math.round(data.main.temp) + "°";
                document.getElementById('icon').style.backgroundImage = "url('https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/" + data.weather[0].icon + ".png')";
                document.getElementById('city').innerHTML = data.name + "";
                document.getElementById('country').innerHTML = data.sys.country + "";
                document.getElementById('inf').innerHTML = data.weather[0].description.charAt(0).toUpperCase() + data.weather[0].description.slice(1) + "";
                document.getElementById("BarraLoginLateral").style.width = (document.getElementById("BarraLoginLateral").clientWidth + 20) + "px";
            })
        });
    }
}