<%-- 
    Document   : prueba2
    Created on : 5/11/2021, 08:35:12 PM
    Author     : carlo
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>


        <body>
            <% out.println("hpta"); %>
                <form action="controlador" method="POST">
                    <div class="form-outline mb-4">
                        <input type="text" id="id" name="id" class="form-control" />
                        <label class="form-label" for="form1Example1">Email address</label>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="text" id="user" name="user" class="form-control" />
                        <label class="form-label" for="form1Example2">Password</label>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Guardar</button>
                </form>
        </body>

        </html>