import { API_KEY_OpenWeather } from '../js/keys.js';
var divs = '';
let params = new URLSearchParams(location.search);
var user = params.get('n');
var type = params.get('u');

Construir();
name();
MostrarFavoritos();

function Construir() {
    document.getElementById('fav_link').href = "favoritos.jsp?n=" + user + "&u=" + type + "";
    document.getElementById('home_link').href = "home.jsp?n=" + user + "&u=" + type + "";
}
function MostrarFavoritos() {
    fetch('../mostrarfavoritos?user=' + user)
            .then((res) => res.json()).then(data => {
        ConstruirCards(data, data.favoritos.length);
    });
}

function name() {
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
}

function ConstruirCards(data, lng) {
    var cont = Math.trunc(lng / 3) + 1;
    var aux = 0;
    var flag = 0;
    console.log(lng);
    while (aux != cont) {
        ConstruirRow(aux + 1);
        aux++;
        while (flag != (aux * 3) && flag != (lng)) {
            console.log("flag " + flag + " aux " + aux)
            ConstruirCard(aux, flag, data);
            flag++;
        }
    }
}

function ConstruirRow(id) {
    document.getElementById('fondoi').innerHTML = document.getElementById('fondoi').innerHTML + '<div id="row_' + id + '" class="row"></div>';
}

function ConstruirCard(row, id, data) {
    let city = data.favoritos[id].ciudad;
    const aux = city.split(",");
    document.getElementById('row_' + row).innerHTML = document.getElementById('row_' + row).innerHTML + '<div class="col-sm-4"><div class="card"><img id="img_card_' + id + '" src="https://mdbcdn.b-cdn.net/img/new/slides/041.webp" class="card-img-top" alt="Wild Landscape"/><div class="card-body"><h5 class="card-title">' + data.favoritos[id].ciudad + '</h5><p class="card-text">With supporting text below as a natural lead-in to additional content.</p></div></div></div>';
    document.getElementById('img_card_'+id).src = "https://source.unsplash.com/1288x665/?" + (aux) + "";
}
