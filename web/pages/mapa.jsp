<%-- 
    Document   : home
    Created on : 7/11/2021, 02:09:16 PM
    Author     : carlo
--%>

<!DOCTYPE html>
<html lang="es" dir="ltr">

    <head>
        <title>Mapa del sitio</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="Visualiza el mapa del sitio"/>
        <meta name="author" content="MaxDVC" />
        <meta name="copyright" content="MaxDVC" />

        <script type="module" src="../js/Buscador.js" defer></script>
        <script type="module" src="../js/Construir.js" defer></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>

        <link rel="stylesheet" href="../css/mdb.min.css" />
        <link rel="stylesheet" href="../css/navbar.css">
        <link rel="stylesheet" href="../css/mapa.css">
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

        <div class="home-section container-fluid" style="margin:0;background-color: #242424" id='fondoi'>
            <div class="row" style="padding-top: 10px;">
                <center style="color:white;"><span>Mapa del sitio</span></center>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <a class="home_link" href="#">
                            <img src="../img/inicio.png" class="card-img-top" alt="Screenshot de la pagina Inicio"/>
                        </a>
                        <div class="card-body">
                            <h5 class="card-title" style="display: inline">Home</h5>
                            <i class='bx bx-home guide'></i>
                            <p class="card-text">
                                Pagina de principal del sitio web, muestra el clima del sitio donde se encuentre,
                                se puede llegar a ella por todas las demas paginas a excepcion de Inicio y Registro, por el buscador y entrando 
                                a tu ciudad guardada en favoritos.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <a class="pf_link" href="#">                        
                            <img src="../img/perfil.png" class="card-img-top" alt="Screenshot de la pagina Perfil"/>                        
                        </a>
                        <div class="card-body">
                            <h5 class="card-title" style="display: inline">Perfil</h5>
                            <i class='bx bx-user guide'></i>
                            <p class="card-text">
                                Esta pagina muestra la informacion principal del usuario ademas de contar con la opcion de
                                poder cambiar la contraseña. Se puede llegar a ella a traves de las demas paginas a excepcion
                                de Inicio y Registro.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <a class="hst_link" href="#">  
                            <img src="../img/historico.png" class="card-img-top" alt="Screenshot de la pagina Historico"/>                       
                        </a>                      
                        <div class="card-body">
                            <h5 class="card-title" style="display: inline">Historico</h5>
                            <i class='bx bx-pie-chart-alt-2 guide'></i>
                            <p class="card-text">Muestra el historico del clima y graficas de la ciudad buscada. Se puede
                                llegar a ella a traves de las demas paginas a excepcion de Inicio y Registro.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <a class="fav_link" href="#">  
                            <img src="../img/favoritos.png" class="card-img-top" alt="Screenshot de la pagina Favoritos"/>                       
                        </a> 
                        <div class="card-body">
                            <h5 class="card-title" style="display: inline">Favoritos</h5>
                            <i class='bx bx-heart guide'></i>
                            <p class="card-text">
                                Muestra tus ciudades favoritas. Se puede
                                llegar a ella a traves de las demas paginas a excepcion de Inicio y Registro.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <img src="../img/login.png" class="card-img-top" alt="Screenshot de la pagina Iniciar sesion"/>                       
                        <div class="card-body">
                            <h5 class="card-title">Iniciar sesion</h5>
                            <p class="card-text">
                                Permite al usuario ingresar a la aplicacion, redirige a clima al iniciar sesion.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <img src="../img/registro.png" class="card-img-top" alt="Screenshot de la pagina Registro"/>                       
                        <div class="card-body">
                            <h5 class="card-title">Registro</h5>
                            <p class="card-text">Permite al usuario poder registrarse para ingresar a la aplicacion, se accede a ella solo por la pagina de Inicio</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <a class="map_link" href="#">  
                            <img src="../img/mapa.png" class="card-img-top" alt="Screenshot de la pagina Mapa del sitio"/>                       
                        </a> 
                        <div class="card-body">
                            <h5 class="card-title" style="display: inline">Mapa del sitio</h5>
                            <i class='bx bx-map-alt guide'></i>
                            <p class="card-text">
                                Muestra el mapa del sitio de la pagina.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>