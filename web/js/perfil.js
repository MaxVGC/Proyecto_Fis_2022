let params = new URLSearchParams(location.search);

var img = atob(params.get('i'));
var user = atob(params.get('n'));
let Btn_CambioContraseña = document.getElementById("btn_CambioContraseña");


MostrarDatosUsuario();
MostrarImgFavorito();

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
        document.getElementsByClassName("img_img")[0].src = img;
        document.getElementById('username-barra').innerHTML = user;
        document.getElementById('nombre-barra').innerHTML = data.datos[0].nombre + " " + data.datos[0].apellido;
    });
}

Btn_CambioContraseña.addEventListener("click", () => {
    document.getElementById("span_ValidacionContraseña").style.color = "red";
    let aux = document.getElementsByClassName("input_CambioContraseña");
    if (aux[0].value == '' || aux[1].value == '' || aux[2].value == '') {
        console.log("nyaa");
        document.getElementById("span_ValidacionContraseña").innerHTML = "Falta llenar algun campo en el formulario";
        document.getElementById("span_ValidacionContraseña").style.display = "block";
    } else if (aux[0].value.length < 8 || aux[1].value < 8) {
        document.getElementById("span_ValidacionContraseña").innerHTML = "La contraseña nueva debe de ser de mas de 8 caracteres";
        document.getElementById("span_ValidacionContraseña").style.display = "block";
    } else if (aux[0].value !== aux[1].value) {
        document.getElementById("span_ValidacionContraseña").innerHTML = "Las contraseñas no coinciden";
        document.getElementById("span_ValidacionContraseña").style.display = "block";
    } else {

        $.get('../cambiarcontrasena?user=' + user + '&antigua=' + aux[2].value + '&nueva=' + aux[0].value, function (data) {
            console.log(data);
            if (data == 0) {
                document.getElementById("span_ValidacionContraseña").innerHTML = "La contraseña antigua no es correcta";
                document.getElementById("span_ValidacionContraseña").style.display = "block";
            } else {
                document.getElementById("span_ValidacionContraseña").innerHTML = "Se ha cambiado la contraseña";
                document.getElementById("span_ValidacionContraseña").style.display = "block";
                document.getElementById("span_ValidacionContraseña").style.color = "green";
            }
        });
    }
});

