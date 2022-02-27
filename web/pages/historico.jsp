<%-- 
    Document   : home
    Created on : 7/11/2021, 02:09:16 PM
    Author     : carlo
--%>

<!DOCTYPE html>
<html lang="es" dir="ltr">

    <head>
        <title>Historico</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="Visualiza los datos historicos del clima en la ciudad deseada"/>
        <meta name="author" content="MaxDVC" />
        <meta name="copyright" content="MaxDVC" />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.js"></script>

        <script type="module" src="../js/historico.js" defer></script>
        <script type="module" src="../js/Buscador_hst.js" defer></script>
        <script type="module" src="../js/Buscador.js" defer></script>
        <script type="module" src="../js/Construir.js" defer></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>

        <link rel="stylesheet" href="../css/mdb.min.css" />
        <link rel="stylesheet" href="../css/navbar.css">
        <link rel="stylesheet" href="../css/historico.css">
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

        <section id="fondoi" class="home-section" >
            <div class="row">
                <div class="col-6 col-md-4">
                    <span id="title" style="font-size: 2rem;color: white;">Historico</span>
                </div>
                <div class="col-md-8">
                    <div id="div_input_hst" class="input-group rounded">
                        <div id="sugerencias_hst">
                        </div>
                        <input id="input_hst" type="search" autocomplete="off" class="form-control " placeholder="Buscar ciudad..." aria-label="Search" aria-describedby="search-addon" />
                        <span class="input-group-text border-0" id="search-addon">
                            <i class="fas fa-search"></i>
                        </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div id="card_hst" class="card bg-dark text-white" style="height:150px">
                        <img id="card_image_hst" src="https://mdbcdn.b-cdn.net/img/new/slides/017.webp" class="card-img"/>
                        <div class="card-img-overlay">
                            <h5 id="title_hst" class="card-title">Card title</h5>
                        </div>
                    </div>
                    <div id="list_hst" class="list-group">
                        <button  type="button" style="display: none" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                        <button  type="button" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                        <button  type="button" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                        <button  type="button" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                        <button  type="button" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                        <button  type="button" onclick = "desactivar(this)" class="ficha_hst list-group-item list-group-item-action"></button>
                    </div>
                </div>
                <div class="col-md-9 div_hst">
                    <div class="row">
                        <div class="col-md-6" style='display: flex;height: 276.5px;flex-direction: column;align-items: center;'>
                            <div style='width: 317.7px'>
                                <canvas id="myChart" width="230px" height="200px"></canvas>
                            </div>
                        </div>
                        <div class="col-md-6" style='display: flex;height: 276.5px;flex-direction: column;align-items: center;'>
                            <div style='width: 317.7px'>
                                <canvas id="myChart_pie" width="230px" height="200px"></canvas>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-4" style='display: flex;flex-direction: column;align-items: center;justify-content: center;height: 235.5px;'>
                            <span class='data_hst'></span>
                            <i class='bx bxs-cloud dat_hst' style='font-size: 80px; '></i>
                            <center><span class='dat_hst'>Porcentaje promedio de nubes</span></center>
                        </div>
                        <div class="col-md-4" style='display: flex;flex-direction: column;align-items: center;justify-content: center;height: 235.5px;'>
                            <span class='data_hst'></span>
                            <i class='bx bxs-sun dat_hst' style='font-size: 80px;'></i>
                            <center><span class='dat_hst'>Temperatura promedio</span></center>
                        </div>
                        <div class="col-md-4" style='display: flex;flex-direction: column;align-items: center;justify-content: center;height: 235.5px;'>
                            <span class='data_hst'></span>
                            <i class='dat_hst bx bxs-bullseye ' style='font-size: 80px'></i>
                            <center><span class='dat_hst'>Porcentaje promedio de humedad</span></center>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>

    <script>
        function desactivar(obj) {
            padre = obj.parentNode;
            for (i = 0; ele = padre.getElementsByTagName('button')[i]; i++) {
                if (ele.classList.contains('activa')) {
                    ele.classList.toggle('activa');
                }
            }
            obj.classList.toggle('activa');
        }
    </script>
</body>


</html>