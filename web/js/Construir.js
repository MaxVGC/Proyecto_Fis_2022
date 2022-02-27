/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

let params = new URLSearchParams(location.search);
var user = atob(params.get('n'));
var type = atob(params.get('u'));
var img = atob(params.get('i'));
var aux = atob(params.get('aux'));
var pathname = window.location.pathname;


Construir();

if (pathname == "/WheaterApp/pages/mapa.jsp") {
    links()
}

function Construir() {
    document.getElementById('fav_link').href = "favoritos.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('home_link').href = "clima.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img) + "&aux=" + btoa("inicio");
    document.getElementById('hst_link').href = "historico.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('pf_link').href = "perfil.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('map_link').href = "mapa.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;
    console.log(img);
    if (img !== atob("null")) {
        document.getElementById('img_perfil').src = img;
    }
}


function links() {
    document.getElementsByClassName('fav_link')[0].href = "favoritos.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementsByClassName('home_link')[0].href = "clima.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img) + "&aux=" + btoa("inicio");
    document.getElementsByClassName('hst_link')[0].href = "historico.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementsByClassName('pf_link')[0].href = "perfil.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementsByClassName('map_link').href = "mapa.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);

}
