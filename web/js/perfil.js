import { API_KEY_OpenWeather } from '../js/keys.js';


let params = new URLSearchParams(location.search);

var divs = '';
var user = atob(params.get('n'));
var type = atob(params.get('u'));

Construir();
name();

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



