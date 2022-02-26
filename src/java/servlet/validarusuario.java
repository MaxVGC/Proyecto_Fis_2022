/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import datos.Cifrador;
import datos.conexionJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author carlo
 */
public class validarusuario extends HttpServlet {

    /**
     * Este metodo se encarga de generar un JSON con los datos del usuario
     * ingresado con google obtenidos de la consulta.
     *
     * @param f Resultados de la consulta.
     * @return Retorna (String) que corresponde al JSON generado con los datos
     * del usuario, si el usuario no existe retorna un JSON vacio.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public String GenerarJSON(ResultSet f) throws SQLException {
        Cifrador n = new Cifrador();
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject innerObj = new JSONObject();

        while (f.next()) {
            innerObj.put("nickname", n.codificarB64(f.getString("nickname")));
            innerObj.put("rol", n.codificarB64(f.getString("rol")));
            innerObj.put("image", n.codificarB64(f.getString("image")));
            list.add(innerObj);
        }

        obj.put("datos", list);
        return obj.toJSONString();
    }

    /**
     * Este metodo se encarga de generar un JSON con los datos del usuario
     * ingresado con google obtenidos de la consulta.
     *
     * @param q Variable de la base de datos.
     * @param id_google Id del usuario de google entregado por la api de google.
     * @return Retorna (String) que corresponde al JSON generado con los datos
     * del usuario, si el usuario no existe retorna un JSON vacio.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public String existeUsuarioGoogle(Statement q, String id_google) throws SQLException {
        String s = "select usuarios.nickname, roles.rol,usuarios_google.image from usuarios,roles,usuarios_google where usuarios_google.id=usuarios.id and usuarios.rol=roles.id and usuarios_google.id_google='" + id_google + "'";
        ResultSet f = q.executeQuery(s);
        String json = GenerarJSON(f);
        return json;
    }

    /**
     * Este metodo se encarga obtener el url de la imagen de perfil desde la
     * base de datos del usuario ingresado por google.
     *
     * @param q Variable de la base de datos.
     * @param user Usuario que se usara para la consulta de la base de datos.
     * @return Retorna (String) que corresponde a la url de la imagen de perfil
     * del usuario.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public String TraerFoto(Statement q, String user) throws SQLException {
        String s = "select image from usuarios, usuarios_google where usuarios.id=usuarios_google.id and usuarios.nickname='" + user + "'";
        ResultSet f = q.executeQuery(s);
        f.next();
        return f.getString("image");
    }

    /**
     * Este metodo se encarga de verificar si el usuario existe en la base de
     * datos.
     *
     * @param q Variable de la base de datos.
     * @param user Usuario que se usara para la consulta de la base de datos.
     * @return Retorna (String[]) que corresponde a la contraseña y rol del
     * usuario, en dado caso que no exista este retornara null del usuario.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public String[] existeUsuario(Statement q, String user) throws SQLException {
        String s = "select usuarios.password, roles.rol from usuarios,roles where usuarios.rol=roles.id and nickname='" + user + "'";
        ResultSet f = q.executeQuery(s);
        String[] aux = {"a", "a"};
        ResultSet aux2 = f;
        if (aux2.next()) {
            aux[0] = f.getString("password");
            aux[1] = f.getString("Rol");
            return aux;
        } else {
            return null;
        }
    }

    /**
     * Este se encarga de procesar la peticion que se le hace al servlet.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws jakarta.servlet.ServletException Si se produce un error
     * específico del servlet.
     * @throws java.io.IOException Si un error de I/O ocurre.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            response.setContentType("text/html;charset=UTF-8");

            conexionJDBC conexion = new conexionJDBC();
            Cifrador n = new Cifrador();

            String aux = request.getParameter("aux");

            conexion.conectar();
            Statement q = conexion.getConexion().createStatement();

            if (aux.equals("1")) {
                String user = request.getParameter("user");
                String pass = n.hash(request.getParameter("pass"));
                String[] f = existeUsuario(q, user);
                if (f != null) {
                    if (f[0].equals(pass)) {
                        if (f[1].equals("Usuario - N")) {
                            response.sendRedirect("pages/clima.jsp?n=" + n.codificarB64(user) + "&u=" + n.codificarB64(f[1]) + "&i=null" + "&aux=" + n.codificarB64("inicio"));
                        } else {
                            response.sendRedirect("pages/clima.jsp?n=" + n.codificarB64(user) + "&u=" + n.codificarB64(f[1]) + "&i=" + n.codificarB64(TraerFoto(q, user)) + "&aux=" + n.codificarB64("inicio"));
                        }
                    } else {
                        response.sendRedirect("index.html?alert=1");
                    }
                } else {
                    response.sendRedirect("index.html?alert=0");
                }
            } else {
                PrintWriter out = response.getWriter();
                String id_google = request.getParameter("id_google");
                out.write(existeUsuarioGoogle(q, id_google));;
            }
            conexion.getConexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(validarusuario.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(validarusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Maneja el método HTTP GET.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws jakarta.servlet.ServletException Si se produce un error específico del servlet.
     * @throws java.io.IOException Si un error de I/O ocurre.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Maneja el método HTTP POST.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws jakarta.servlet.ServletException Si se produce un error específico del servlet.
     * @throws java.io.IOException Si un error de I/O ocurre.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Retorna una breve descripcion de la funcion del servlet.
     *
     * @return Un String con la descripcion del servlet.
     */
    @Override
    public String getServletInfo() {
        return "Valida si el usuario existe en la base de datos";
    }// </editor-fold>

}
//response.sendRedirect("pages/home.jsp?n=" + n.codificarB64(request.getParameter("user")) + "&u="+n.codificarB64("Usuario"));
