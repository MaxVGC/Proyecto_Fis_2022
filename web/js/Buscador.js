//https://maps.googleapis.com/maps/api/place/autocomplete/json?input=villa&types=geocode&key=AIzaSyCSvCi-oJw7_VDj-zkrzkZCx4l1iGeH2Eg



import { API_KEY_GOOGLE } from '../js/keys.js';

let params = new URLSearchParams(location.search);

const input = document.querySelector('input');
const log = document.getElementById('valores');

var aux = 0;
var user = params.get('n');
var type = params.get('u');
var img = params.get('i');

let sidebar = document.querySelector(".sidebar");
let closeBtn = document.querySelector("#btn");
let searchBtn = document.querySelector(".bx-search");
var inp = document.getElementById('sugerencias');

input.addEventListener('input', updateValue);

function updateValue(e) {
    var p = e.srcElement.value;
    var div = document.getElementById('sugerencias');
    if (p.length >= 3) {
        if (aux == 0) {
            div.classList.toggle('open');
            aux = 1;
        }
        fetch("https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + e.srcElement.value + "&types=%28cities%29&key=" + API_KEY_GOOGLE)
                .then((res) => res.json()).then(data => {
            var aux = '';
            for (var i = 0; i <= (data.predictions.length - 1); i++) {
                if (data.predictions[i].terms.length == 2) {
                    aux = aux + concatenar1(data.predictions[i].terms[0].value, data.predictions[i].terms[1].value, data.predictions[i].place_id);
                } else {
                    aux = aux + concatenar2(data.predictions[i].terms[0].value, data.predictions[i].terms[1].value, data.predictions[i].terms[2].value, data.predictions[i].place_id);
                }
            }
            div.innerHTML = aux;
        });
    } else {
        if (aux == 1) {
            div.classList.toggle('open');
            aux = 0;
        }
    }
}

function concatenar1(nombre, pais, id) {
    var aux = "<a href='clima.jsp?id=" + id + "&n=" + user + "&u=" + type + "&i=" + img + "&aux=" + btoa("busqueda") + "'><div>" + nombre + ", " + pais + " </div></a>"
    return aux;
}

function concatenar2(nombre, estado, pais, id) {
    var aux = "<a href='clima.jsp?id=" + id + "&n=" + user + "&u=" + type + "&i=" + img + "&aux=" + btoa("busqueda") + "'><div>" + nombre + ", " + estado + ", " + pais + " </div></a>"
    return aux;
}

closeBtn.addEventListener("click", () => {
    sidebar.classList.toggle("open");
    menuBtnChange();
    b_sugerencias()
});

searchBtn.addEventListener("click", () => {
    sidebar.classList.toggle("open");
    menuBtnChange();
    b_sugerencias()
});

function menuBtnChange() {
    if (sidebar.classList.contains("open")) {
        closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");
    } else {
        closeBtn.classList.replace("bx-menu-alt-right", "bx-menu");
    }
}

function b_sugerencias() {
    if (inp.classList.contains("open")) {
        inp.classList.replace("open", "close_b");
    } else {
        inp.classList.replace("close_b", "open");
    }
}