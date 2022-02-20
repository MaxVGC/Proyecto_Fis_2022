import { API_KEY_OpenWeather } from '../js/keys.js';
var divs = '';
let params = new URLSearchParams(location.search);
var user = atob(params.get('n'));
var type = atob(params.get('u'));
var img = atob(params.get('i'));

Construir();
MostrarFavoritos();



function Construir() {
    document.getElementById('fav_link').href = "favoritos.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('home_link').href = "home.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('hst_link').href = "historico.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('pf_link').href = "perfil.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
    document.getElementById('img_perfil').src = img;
}

function MostrarFavoritos() {
    fetch('../mostrarfavoritos?user=' + user)
            .then((res) => res.json()).then(data => {
        ConstruirCards(data, data.favoritos.length);
    });
}

function ConstruirCards(data, lng) {
    var cont = Math.trunc(lng / 3) + 1;
    var aux = 0;
    var flag = 0;
    while (aux != cont) {
        ConstruirRow(aux + 1);
        aux++;
        while (flag != (aux * 3) && flag != (lng)) {
            ConstruirCard(aux, flag, data);
            flag++;
        }
    }
}

function ConstruirRow(id) {
    document.getElementById('fondoi').innerHTML = document.getElementById('fondoi').innerHTML + '<div id="row_' + id + '" class="row" style="padding:0;"></div>';
}

function ConstruirCard(row, id, data) {
    let city = data.favoritos[id].ciudad;
    const aux = city.split(",");
    document.getElementById('row_' + row).innerHTML = document.getElementById('row_' + row).innerHTML + '<div class="col-sm-4" style="margin-top:20px;"><a href="favorito_p.jsp?lat=' + data.favoritos[id].latitud + '&lng=' + data.favoritos[id].longitud + '&c=' + data.favoritos[id].ciudad + '&n=' + btoa(user) + '&u=' + btoa(type) + '&i=' + btoa(img) + '"><div class="card"><img id="img_card_' + id + '" src="https://mdbcdn.b-cdn.net/img/new/slides/041.webp" class="card-img-top" alt="Wild Landscape"/><div class="card-body"><h5 class="card-title">' + data.favoritos[id].ciudad + '</h5></div></div></a></div>';
    document.getElementById('img_card_' + id).src = "https://source.unsplash.com/1288x665/?" + (aux[0]) + "";
}
