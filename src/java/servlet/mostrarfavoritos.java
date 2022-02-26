/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import datos.conexionJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Esta clase contiene los metodos para hacer la consulta de las ciudades
 * guardadas por un usuario en la tabla favoritos.
 *
 * @author Andres Marlex
 */
public class mostrarfavoritos extends HttpServlet {

    /**
     * Este metodo se encarga de generar un JSON con los datos de la ciudades
     * obtenidos de la consulta.
     *
     * @param f Resultados de la consulta.
     * @return Retorna (String) que corresponde al JSON generado con los datos
     * del usuario.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public String GenerarJSON(ResultSet f) throws SQLException {
        String aux = "";
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        while (f.next()) {
            JSONObject innerObj = new JSONObject();
            innerObj.put("ciudad", f.getString("nombre"));
            innerObj.put("latitud", f.getString("latitud"));
            innerObj.put("longitud", f.getString("longitud"));
            list.add(innerObj);
        }
        obj.put("favoritos", list);
        return obj.toJSONString();
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
        response.setContentType("text/html;charset=UTF-8");

        String user = request.getParameter("user");

        conexionJDBC conexion = new conexionJDBC();

        try (PrintWriter out = response.getWriter()) {
            try {
                conexion.conectar();
                Statement q = conexion.getConexion().createStatement();

                String sql = "select ciudades.nombre,ciudades.latitud,ciudades.longitud from usuarios,favoritos,ciudades where usuarios.id=favoritos.id_user and favoritos.id_ciudad=ciudades.id and usuarios.nickname='" + user + "'";
                ResultSet f = q.executeQuery(sql);

                String json = GenerarJSON(f);
                out.println(json);
                conexion.getConexion().close();
            } catch (Exception e) {
                out.println(e);
                System.out.println(e);
                try {
                    conexion.getConexion().close();
                } catch (SQLException ex) {
                    Logger.getLogger(validarusuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //out.println(data);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Maneja el método HTTP GET.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws jakarta.servlet.ServletException Si se produce un error
     * específico del servlet.
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
     * @throws jakarta.servlet.ServletException Si se produce un error
     * específico del servlet.
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
        return "Obtiene las ciudades favoritas del usuario";
    }// </editor-fold>

}
