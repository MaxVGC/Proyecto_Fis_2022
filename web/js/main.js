import { API_KEY_GOOGLE } from '../js/keys.js';
import { API_KEY_OpenWeather } from '../js/keys.js';

posicion();
obtenerclima()


function posicion() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            fetch("https://api.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&appid=" + API_KEY_OpenWeather)
                    .then((res) => res.json()).then(data => {
                var x = data.name;
                TraerFotoCiudad(x.replaceAll(' ', '_'));
            });
        });
    }
}

function TraerFotoCiudad(ciudad) {
    console.log(ciudad);
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + position.coords.latitude + "," + position.coords.longitude + "&radius=5000&language=es&keyword=" + ciudad + "&key=" + API_KEY_GOOGLE)
                    .then((res) => res.json()).then(data => {
                var reference = data.results[0].photos[0].photo_reference;
                const intro = document.getElementById('bg-image');
                const link = 'https://maps.googleapis.com/maps/api/place/photo?maxwidth=1920&maxheigh=1080&photoreference=' + reference + '&sensor=false&key=' + API_KEY_GOOGLE;
                intro.style.backgroundImage = "url('" + link + "')";
            });
        });
    }
}

function obtenerclima() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            $.get("https://api.openweathermap.org/data/2.5/weather?lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&lang=es&units=metric&appid=" + API_KEY_OpenWeather, function (data) {
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