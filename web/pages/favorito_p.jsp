<%-- 
    Document   : home
    Created on : 7/11/2021, 02:09:16 PM
    Author     : carlo
--%>

<!DOCTYPE html>
<html lang="es" dir="ltr">
    <head>
        <title>Inicio</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        
        <script type="module" src="../js/favorito_p.js" defer></script>
        <script type="module" src="../js/Buscador.js" defer></script>
        <script type="module" src="../js/Construir.js" defer></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.js"></script>
        
        <link rel="stylesheet" href="../css/mdb.min.css" />
        <link rel="stylesheet" href="../css/navbar.css">
        <link rel="stylesheet" href="../css/home.css">
        <link rel="stylesheet" href="../css/circle_progress.css">
        <link rel="icon" href="../img/icon.png" type="image/x-icon" />
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body style="background-color: black;">
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
                <li>
                    <a id="map_link" href="#">
                        <i class='bx bx-map-alt'></i>
                        <span class="links_name">Mapa del sitio</span>
                    </a>
                    <span class="tooltip">Mapa del sitio</span>
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
        <div class="col-md-12 big_container" >
            <div class="row" style="margin: 0;height: 65vh;flex-direction: row;align-items: flex-end;justify-content: flex-end;background-size: cover; background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg');" id='fondoi'>
                <div class="col-md-6 barra_container" style="padding:0;">
                    <div class="row" style="margin:0;">
                        <div class="col-md-6" style="padding:0;">
                            <div class="row" style="margin:0;height: 100%">
                                <div id="Dtemp" >
                                    <span id="temp" style="color: white;font-size: 4rem;"></span>
                                    <div id="icon" style="width: 100px;height:100px;background-size: contain;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding:0;">
                            <div class="row" style="margin:12px;">
                                <center>
                                    <div>
                                        <span id="city" style="font-size: 2rem;color: white;"></span>
                                        <span id="country" style="font-size: 1rem;color: gray;font-weight: bold;"></span>
                                    </div>
                                </center>
                                <center>
                                    <div>
                                        <span id="inf" style="font-size: 1rem;color: white;"></span>
                                        <i id="btn_fav" class='bx bx-heart' style="color:#ff4033"></i>
                                    </div>
                                </center>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin: 0;height: 35vh">
                <div class="col-md-6" style="padding:0;background-color: #242424;">
                    <div class="row" style="margin: 0;">
                        <div class="col-md-3" style="padding:0;">
                            <div class="row" style="margin: 0;height: 50%" >
                                <div class="col-md-12" style="padding: 0;" >
                                    <div class="row" style="margin: 20px;">
                                        <div style="display: inline-block;padding: 0;">
                                            <center><div id="cont">
                                                    <div id="ext">
                                                        <div id="rot1">
                                                            <div id="int1"></div>
                                                        </div>
                                                    </div>
                                                    <div id="shape" style="left: 0px">
                                                        <center><span id="perc1"></span></center>
                                                    </div>
                                                </div></center>
                                            <center><span style="color: white;font-size: 0.9rem;">Nubosidad</span></center>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin: 0;height: 50%" >
                                <div class="col-md-12" style="padding: 0;" >
                                    <div class="row" style="margin: 20px;">
                                        <div style="display: inline-block;padding: 0;">
                                            <center>
                                                <div id="cont">
                                                    <div id="ext">
                                                        <div id="rot2">
                                                            <div id="int2"></div>
                                                        </div>
                                                    </div>
                                                    <div id="shape" style="left: 0px">
                                                        <center><span id="perc2"></span></center>
                                                    </div>
                                                </div>
                                            </center>
                                            <center><span style="color: white;font-size: 0.9rem;">Humedad</span></center>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5" style="padding:0;">
                            <div class="row" style="margin: 0;height: 20%">
                                <div id="dcontain" style="display: flex;align-items: center;justify-content:center">
                                    <i id="icon_data" class='bx bxs-cloud-rain'></i>
                                    <span id="metricas">Prob. de precipitaciones:</span>
                                    <span id="pdll" style="margin-left: 5px;color: white;font-size: 0.8rem;"></span>
                                </div>
                            </div>
                            <div class="row" style="margin: 0;height: 20%">
                                <div id="dcontain" style="display: flex;align-items: center;justify-content:center" >
                                    <i id="icon_data" class='bx bxs-analyse'></i>
                                    <span id="metricas">Presi&oacuten:</span>
                                    <span id="presion" style="margin-left: 5px;color: white;font-size: 0.8rem;"></span>
                                </div>
                            </div>
                            <div class="row" style="margin: 0;height: 20%">
                                <div id="dcontain" style="display: flex;align-items: center;justify-content:center">
                                    <i id="icon_data" class='bx bx-wind'></i>
                                    <span id="metricas">Velocidad del viento:</span>
                                    <span id="velv" style="margin-left: 5px;color: white;font-size: 0.8rem;"></span>
                                </div>
                            </div>
                            <div class="row" style="margin: 0;height: 20%">
                                <div id="dcontain" style="display: flex;align-items: center;justify-content:center">
                                    <i id="icon_data" class='bx bxs-sun'></i>
                                    <span id="metricas">Sensaci&oacuten termica:</span>
                                    <span id="st" style="margin-left: 5px;color: white;font-size: 0.8rem;"></span>
                                </div>
                            </div>
                            <div class="row" style="margin: 0;height: 20%">
                                <div id="dcontain" style="display: flex;align-items: center;justify-content:center">
                                    <i id="icon_data" class='bx bxs-heart-circle'></i>
                                    <span id="metricas">Calidad del aire:</span>
                                    <span id="aqi" style="margin-left: 5px;color: white;font-size: 0.8rem;"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4" style="padding:0;">
                            <div class="row" style="margin: 0;height: 100%">
                                <div style="display: flex;align-items: center;justify-content:center;margin-top: 10px">
                                    <canvas id="myChart" width="230" height="200"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" style="padding:0;background-color: #242424;">
                    <div class="row" style="margin: 0;height: 10%">
                        <center><span style="color:gray">Pronostico</span></center>
                    </div>
                    <div class="row" style="margin: 0;height: 90%">
                        <div id="pronostico" style="background-color: #242424;"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>