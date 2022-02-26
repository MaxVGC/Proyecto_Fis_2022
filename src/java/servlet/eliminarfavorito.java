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

/**
 * Esta clase contiene los metodos para hacer la eliminacion de la una ciudad
 * guardada en la tabla favoritos de la base de datos.
 *
 * @author Andres Marlex
 */
public class eliminarfavorito extends HttpServlet {

    /**
     * Este metodo hace una consulta a la base de datos para obtener el id del
     * usuario.
     *
     * @param q Variable de la base de datos.
     * @param user Usuario que se usara para la consulta de la base de datos.
     * @return Retorna (Integer) que corresponde al id del usuario
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public int UserId(Statement q, String user) throws SQLException {
        String s = "select id from usuarios where nickname='" + user + "'";
        ResultSet f = q.executeQuery(s);
        f.next();
        String aux = f.getString("id").replace(" ", "");
        return Integer.parseInt(aux);
    }

    /**
     * Este metodo hace una consulta a la base de datos para obtener el id de la
     * ciudad.
     *
     * @param q Variable de la base de datos.
     * @param name Nombre de la ciudad que se usara para la consulta de la base
     * de datos.
     * @return Retorna (Integer) que corresponde al id de la ciudad.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public int CityId(Statement q, String name) throws SQLException {
        String s = "select id from ciudades where nombre='" + name + "'";
        ResultSet f = q.executeQuery(s);
        f.next();
        String aux = f.getString("id").replace(" ", "");
        return Integer.parseInt(aux);
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
        String city = request.getParameter("city");

        conexionJDBC conexion = new conexionJDBC();

        try (PrintWriter out = response.getWriter()) {
            try {
                conexion.conectar();
                Statement q = conexion.getConexion().createStatement();

                int Cityid = CityId(q, city);
                int UserId = UserId(q, user);

                String sql = "delete from favoritos where id_user='" + UserId + "' and id_ciudad='" + Cityid + "'";
                PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
                pst.execute();

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
            String data = "Se ha eliminado la ciudad " + request.getParameter("city") + " de tus favoritos";
            out.println(data);
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
        return "Elimina una ciudad de favoritos del usuario";
    }// </editor-fold>

}
