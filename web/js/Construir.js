/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

let params = new URLSearchParams(location.search);
var user = atob(params.get('n'));
var type = atob(params.get('u'));
var img = atob(params.get('i'));

Construir();

function Construir() {
    document.getElementById('fav_link').href = "favoritos.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('home_link').href = "home.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('hst_link').href = "historico.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('pf_link').href = "perfil.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('map_link').href = "mapa.jsp?n=" + btoa(user) + "&u=" + btoa(type) + "&i=" + btoa(img);
    document.getElementById('username').innerHTML = user;
    document.getElementById('role').innerHTML = type;

    if (params.get('i').toString() !== "null") {
        document.getElementById('img_perfil').src = img;
    }
}