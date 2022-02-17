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
        <link rel="stylesheet" href="../css/registro.css" />
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <script async src="https://www.google.com/recaptcha/api.js"></script>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row width-100">
                <div class="col-md-4 register-div">
                    <form action="../registrarusuario" method="POST" style="display: contents;">
                        <div class="row">
                            <div class="col-md-12">
                                <a href="../" style="color:#b0b0b0">
                                   <i style="font-size: 30px;"class='bx bx-left-arrow-alt'></i>
                                </a>                               
                                <h1 style="color:white;display: inline">Registrate!</h1>
                            </div>     
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-outline form-white" style="width: 100%;margin-top: 6%;">
                                    <span style="color:white;">Usuario</span>
                                    <div style="width:100%;display: flex;justify-content: center">
                                        <input  type="text" autocomplete="off" name="user" class="input-custom" required/>
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-outline form-white" style="width: 100%;margin-top: 6%;">
                                    <span style="color:white;">Nombre</span><center></center>
                                    <div style="width:100%;display: flex;justify-content: center">
                                        <input  type="text" autocomplete="off" name="name" class="input-custom" required/>
                                    </div>
                                </div>
                            </div>   
                            <div class="col-md-6">
                                <div class="form-outline form-white" style="width: 100%;margin-top: 6%;">
                                    <span style="color:white;">Apellido</span><center></center>
                                    <div style="width:100%;display: flex;justify-content: center">
                                        <input  type="text" autocomplete="off" name="apell" class="input-custom" required/>
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-outline form-white" style="width: 100%;margin-top: 4%;">
                                    <span style="color:white;">Correo</span>
                                    <div style="width:100%;display: flex;justify-content: center">
                                        <input  type="email" autocomplete="off" name="email" class="input-custom" required/>
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-outline form-white" style="width: 100%;margin-top: 4%;">
                                    <span style="color:white;">Contraseña</span><center></center>
                                    <div style="width:100%;display: flex;justify-content: center">
                                        <input type="password" minlength="8" autocomplete="off" name="pass" class="input-custom" required/>
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <div class="row">
                            <div class="col-md-12" style="display: flex;justify-content: center">
                                <div style="display: block;margin-top: 10%;" class="g-recaptcha" data-callback="recaptcha_callback" data-sitekey="6LeYVoMeAAAAAFbmkLD42RpfyjPDHMTQZr48VANS" data-theme="dark"></div>
                            </div> 
                        </div>
                        <div class="row">
                            <div class="col-md-12" style="display: flex;justify-content: center">
                                <div style="width:100%;display: flex;justify-content: center">
                                    <button style="margin-top: 10%;" id="btn_submit" type="submit" class="btn btn-primary" disabled>Registrarse</button>
                                </div>
                            </div> 
                        </div>
                    </form>
                </div>
                <div class="col-md-8 mockup">
                </div>
            </div>
        </div>
        <script>
            function recaptcha_callback() {
                var axx = grecaptcha.getResponse();
                if (axx.length !== 0) {
                    var aux = document.querySelector('#btn_submit');
                    aux.removeAttribute('disabled');
                }
            }
        </script>
    </body>

</html>