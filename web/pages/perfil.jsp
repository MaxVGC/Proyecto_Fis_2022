<%-- 
    Document   : home
    Created on : 7/11/2021, 02:09:16 PM
    Author     : carlo
--%>

<!DOCTYPE html>
<html lang="es" dir="ltr">

    <head>
        <title>Perfil</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />

        <script type="module" src="../js/Buscador.js" defer></script>
        <script type="module" src="../js/perfil.js" defer></script>
        <script type="module" src="../js/Construir.js" defer></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.js"></script>
        
        <link rel="stylesheet" href="../css/mdb.min.css" />
        <link rel="stylesheet" href="../css/navbar.css">
        <link rel="stylesheet" href="../css/perfil.css">
        <link rel="icon" href="../img/icon.png" type="image/x-icon" />
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>

    </head>

    <body style="background-color: #242424;">
        <div id="prueba" class="sidebar">
            <div class="logo-details">
                <img class='icon' src="../img/icon.png" style="width: 15%;margin-right: 5%;margin-left:3%;">
                <div class="logo_name">Heart Cloud</div>
                <i class='bx bx-menu' id="btn"></i>
            </div>
            <ul class="nav-list" style="padding-left: 0;">
                <li>
                    <i class='bx bx-search'></i>
                    <div id="sugerencias">
                    </div>
                    <input autocomplete="off" id="inputs" type="text" placeholder="Buscar ciudad...">
                    <span class="tooltip">Buscar</span>
                </li>
                <li>
                    <a id="home_link" href="#">
                        <i class='bx bx-home'></i>
                        <span class="links_name">Inicio</span>
                    </a>
                    <span class="tooltip">Inicio</span>
                </li>
                <li>
                    <a id="pf_link" href="#">
                        <i class='bx bx-user'></i>
                        <span class="links_name">Perfil</span>
                    </a>
                    <span class="tooltip">Perfil</span>
                </li>
                <li>
                    <a id="hst_link" href="#">
                        <i class='bx bx-pie-chart-alt-2'></i>
                        <span class="links_name">Historico</span>
                    </a>
                    <span class="tooltip">Historico</span>
                </li>
                <li>
                    <a id="fav_link" href="#">
                        <i class='bx bx-heart'></i>
                        <span class="links_name">Favoritos</span>
                    </a>
                    <span class="tooltip">Favoritos</span>
                </li>
                <li class="profile">
                    <div class="profile-details">
                        <img id="img_perfil" src="../img/prueba.png" alt="profileImg">
                        <div class="name_job">
                            <div id="username" class="name"></div>
                            <div id="role" class="job">Web designer</div>
                        </div>
                    </div>
                    <a href="../index.html">
                        <i class='bx bx-log-out' id="log_out"></i>
                    </a>
                </li>
            </ul>
        </div>

        <div class="home-section container-fluid" style="margin:0;background-color: #242424" id='fondoi'>
            <div id="img_favorito" class="row">
                <div class="col-md-6" id="perfil-barra">
                    <div id="img_perfil_barra">
                        <img id="img_perfil" class="img_img" src="../img/prueba.png" alt="profileImg">
                    </div>
                    <div id="datos_perfil">
                        <center><span id="username-barra" style="color: white;font-size: 27px;"></span></center>
                        <center><span id="nombre-barra" style="color:gray"></span></center>
                    </div>
                </div>
                <div class="col-md-6" id="datos_ubicacion">
                    <div id="ubicacion_img">
                        <i class='bx bx-camera'></i>
                        <center><span id="ubicacion_barra" style="color:white; margin-left: 10px;"></span></center>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="row" style="padding-top: 10px">
                        <div class='span_CambiarContraseña'>
                            <span style="color:white;">Cambiar contraseña</span>
                        </div>                     
                    </div>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-md-6" >
                            <div style="width:100%;display: flex;justify-content: center">
                                <input type="password" autocomplete="off"  class="input-custom input_CambioContraseña" placeholder="Nueva contraseña" required/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div style="width:100%;display: flex;justify-content: center">
                                <input type="password" autocomplete="off"  class="input-custom input_CambioContraseña" placeholder="Confirmar contraseña" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-md-12" >
                            <div style="width:100%;display: flex;justify-content: center">
                                <input style="margin-top: 0" type="password" autocomplete="off"  class="input-custom input_CambioContraseña" placeholder="Antigua contraseña" required/>
                            </div>
                            <center style="margin-top: 10px"><span style="color:red;font-size: 10px;" id="span_ValidacionContraseña"></span></center>
                        </div> 
                    </div>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-md-12" >
                            <button id="btn_CambioContraseña" class="btn btn-primary btn-grad" >Aceptar</button>
                        </div> 
                    </div>
                </div>
                <div class="col-md-6">

                </div>
            </div>
        </div>
    </body>
</html>