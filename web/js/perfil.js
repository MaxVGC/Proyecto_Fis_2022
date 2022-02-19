import { API_KEY_OpenWeather } from '../js/keys.js';


let params = new URLSearchParams(location.search);

var divs = '';
var user = atob(params.get('n'));
var type = atob(params.get('u'));

Construir();
name();
MostrarDatosUsuario();
MostrarImgFavorito();

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

function MostrarImgFavorito() {
    fetch('../mostrarfavoritos?user=' + user)
            .then((res) => res.json()).then(data => {
        const random = Math.floor(Math.random() * (data.favoritos.length - 1)) + 0;
        const aux = data.favoritos[random].ciudad.split(",");
        document.getElementById('ubicacion_barra').innerHTML = data.favoritos[random].ciudad;
        document.getElementById('img_favorito').style.background = "url('https://source.unsplash.com/1288x665/?" + (aux[0]) + "') center center";
        document.getElementById('img_favorito').style.backgroundSize = "cover";

    });
}

function MostrarDatosUsuario() {
    fetch('../informacionusuario?user=' + user)
            .then((res) => res.json()).then(data => {
        console.log(data);
        document.getElementById('username-barra').innerHTML = user;
        document.getElementById('nombre-barra').innerHTML = data.datos[0].nombre + " " + data.datos[0].apellido;
    });
}

