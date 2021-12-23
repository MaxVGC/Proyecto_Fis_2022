<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

<head>
    <title>Registro</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <link rel="icon" href="../img/icon.png" type="image/x-icon" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" />
    <link rel="stylesheet" href="../css/mdb.min.css" />
    <link rel="stylesheet" href="../css/main.css" />
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
    <script type="text/javascript" src="../js/mdb.min.js"></script>
</head>

<body>
    <div class="container-fluid" style="padding: 0%;">
        <div id="bg-image" class="bg-image" style="height: 100vh;">
            <div class="row">
                <div id="formulario" style="background-color: #333333;padding: 0%;" class="col-md-8">
                    <div style="width: 70%;height: 100%;display: inline-block;float: left;">
                        <div style="width: 100%;height: 100%;display: flex;align-content: center;flex-direction: column;align-items: center;justify-content: center;">
                            <form action="../registrarusuario" method="POST" style="display: contents;">
                                <div class="form-outline form-white" style="width: 70%;margin-top: 10%;">
                                    <input type="text" autocomplete="off" name="user" id="inputs" class="form-control" required/>
                                    <label class="form-label" for="formWhite">Usuario</label>
                                </div>
                                <div class="form-outline form-white" style="width: 70%;margin-top: 10%;">
                                    <input type="password" name="pass" id="inputs" class="form-control" required/>
                                    <label class="form-label" for="formWhite">Contraseña</label>
                                </div>
                                <div class="form-outline form-white" style="width: 70%;margin-top: 10%;">
                                    <input type="text" name="name" id="inputs" class="form-control" required/>
                                    <label class="form-label" for="formWhite">Nombre</label>
                                </div>
                                <div class="form-outline form-white" style="width: 70%;margin-top: 10%;">
                                    <input type="text" id="inputs" name="apell" class="form-control" required/>
                                    <label class="form-label" for="formWhite">Apellido</label>
                                </div>
                                <div class="form-outline form-white" style="width: 70%;margin-top: 10%;">
                                    <input type="text" name="email" id="inputs" class="form-control" required/>
                                    <label class="form-label" for="formWhite">Correo</label>
                                </div>
                                <button style="margin-top: 10%;" id="iniciar" type="submit" class="btn btn-primary">Registrarse</button>
                            </form>
                        </div>
                    </div>
                    <div style="width: 29%;height: 100%; display: inline-block;background-image:url(../img/info_register.png)"></div>
                </div>
                <div id="lateralimage" style="padding: 0%;" class="col-md-4">
                    <div style=" background-image: url('https://source.unsplash.com/464x665/?mountain');height: 100%;">
                        <div style="position: relative;height: 10%;left: 63%;">
                            <img src="../img/icon.png" style="max-width: 10%;max-height: 66%;">
                            <span style="font-size: 1rem;color: black;font-weight: bold;margin-left: 3%;">Heart Cloud</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var height = window.innerHeight;

        function redimensionar() {
            var height = window.innerHeight;
            document.getElementById("lateralimage").style.height = height + "px";
            document.getElementById("formulario").style.height = height + "px";
        }
        document.getElementById("lateralimage").style.height = height + "px";
        document.getElementById("formulario").style.height = height + "px";

        $(document).ready(() => {
            $(window).on('resize', _.debounce(redimensionar, 100));
        });
    </script>
</body>

</html>