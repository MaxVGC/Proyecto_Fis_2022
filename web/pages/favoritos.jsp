<%-- 
    Document   : home
    Created on : 7/11/2021, 02:09:16 PM
    Author     : carlo
--%>

<!DOCTYPE html>
<html lang="es" dir="ltr">

    <head>
        <title>Favoritos</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <script type="module" src="../js/Buscador.js" defer></script>
        <script type="module" src="../js/favoritos.js" defer></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.js"></script>
        <script src="../js/jquery-3.4.1.min.js"></script>

        <link rel="stylesheet" href="../css/mdb.min.css" />
        <link rel="stylesheet" href="../css/navbar.css">
        <link rel="stylesheet" href="../css/favoritos.css">
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
                    <a href="#">
                        <i class='bx bx-user'></i>
                        <span class="links_name">User</span>
                    </a>
                    <span class="tooltip">User</span>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bx-pie-chart-alt-2'></i>
                        <span class="links_name">Analytics</span>
                    </a>
                    <span class="tooltip">Analytics</span>
                </li>
                <li>
                    <a id="fav_link" href="#">
                        <i class='bx bx-heart'></i>
                        <span class="links_name">Favoritos</span>
                    </a>
                    <span class="tooltip">Favoritos</span>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bx-cog'></i>
                        <span class="links_name">Setting</span>
                    </a>
                    <span class="tooltip">Setting</span>
                </li>
                <li class="profile">
                    <div class="profile-details">
                        <!--<img src="profile.jpg" alt="profileImg">-->
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
            
            
        </section>

        <script>
            let sidebar = document.querySelector(".sidebar");
            let closeBtn = document.querySelector("#btn");
            let searchBtn = document.querySelector(".bx-search");
            var inp = document.getElementById('inputs');

            closeBtn.addEventListener("click", () => {
                sidebar.classList.toggle("open");
                menuBtnChange();
                // b_sugerencias();
            });

            searchBtn.addEventListener("click", () => {
                sidebar.classList.toggle("open");
                menuBtnChange();
                // b_sugerencias();
            });

            function menuBtnChange() {
                if (sidebar.classList.contains("open")) {
                    closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");
                } else {
                    closeBtn.classList.replace("bx-menu-alt-right", "bx-menu");
                }
            }

            function b_sugerencias() {

                if (document.querySelector("#sugerencias.open")) {
                    document.getElementById("sugerencias").className = "";
                }
            }
        </script>
    </body>


</html>