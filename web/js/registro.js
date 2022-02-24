/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

Construir();

function Construir() {
    let params = new URLSearchParams(location.search);
    let aux = params.get('rol')
    let inputs = document.getElementsByClassName("input-custom");
    if (aux == "2") {
        inputs[1].value = params.get('rol');
        inputs[1].setAttribute("readonly", "");
        inputs[2].value = params.get('image');
        inputs[2].setAttribute("readonly", "");
        inputs[3].value = params.get('nombre');
        inputs[3].setAttribute("readonly", "");
        inputs[4].value = params.get('apellido');
        inputs[4].setAttribute("readonly", "");
        inputs[5].value = params.get('email');
        inputs[5].setAttribute("readonly", "");
        inputs[7].value = window.location;
        inputs[7].setAttribute("readonly", "");
        inputs[8].value = params.get('id_google');
        inputs[8].setAttribute("readonly", "");
    }

}

$(document).ready(() => {
    let params = new URLSearchParams(location.search);
    var contract = params.get('alert');
    if (contract == 0) {
        alert('El usuario ingresado ya se encuentra registrado');
    }
});
