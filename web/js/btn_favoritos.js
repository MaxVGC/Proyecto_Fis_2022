/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let FavBtn = document.querySelector("#btn_fav");
var cd="kaka";

FavBtn.addEventListener("click", () => {
    if (document.querySelector("#btn_fav.bx.bx-heart")) {
        document.getElementById("btn_fav").className = "bx bxs-heart";
        $.get('../eliminarfavorito?u='+cd, function (data) {
            alert(data);
            console.log(data);
        });
    } else if (document.querySelector("#btn_fav.bx.bxs-heart")) {
        document.getElementById("btn_fav").className = "bx bx-heart";
        $.get('../eliminarfavorito?u='+cd, function (data) {
            alert(data);
            console.log(data);
        });
    }
});