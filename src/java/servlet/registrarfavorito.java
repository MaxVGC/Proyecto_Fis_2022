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
 * Esta clase contiene los metodos para hacer el registro de la ciudad 
 * por un usuario en la tabla favoritos.
 *
 * @author Andres Marlex
 */
public class registrarfavorito extends HttpServlet {

    /**
     * Este metodo hace una consulta a la base de datos para comprobar si la
     * ciudad ya existe en la tabla ciudades.
     *
     * @param q Variable de la base de datos.
     * @param city Nombre de la ciudad que se usara para la consulta de la base
     * de datos.
     * @return Retorna (Boolean), true si la ciudad existe y false en caso
     * contrario.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public boolean isExist(Statement q, String city) throws SQLException {
        String sql = "select nombre from ciudades where nombre='" + city + "'";
        ResultSet query = q.executeQuery(sql);
        if (query.next() != false) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este metodo hace una consulta a la base de datos para obtener un id
     * siguiente al ultimo de la tabla ciudades.
     *
     * @param q Variable de la base de datos.
     * @return Retorna (Integer) que corresponde al id de la ciudad.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public int Id(Statement q) throws SQLException {
        String s = "select * from ciudades";
        ResultSet f = q.executeQuery(s);
        while (!f.isLast()) {
            f.next();
        }
        int id = f.getInt("id") + 1;
        return id;
    }

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
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");

        conexionJDBC conexion = new conexionJDBC();

        try (PrintWriter out = response.getWriter()) {
            try {
                conexion.conectar();
                Statement q = conexion.getConexion().createStatement();

                int UserId = UserId(q, user);

                if (isExist(q, city)) {

                    int CityId = CityId(q, city);

                    String sql = "INSERT INTO favoritos (id_user,id_ciudad) VALUES (" + UserId + "," + CityId + ")";
                    PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
                    pst.execute();

                } else {
                    int id = Id(q);
                    String sql = "INSERT INTO ciudades (id,nombre,latitud,longitud) VALUES (" + id + ",'" + city + "','" + lat + "','" + lng + "')";
                    PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
                    pst.execute();
                    sql = "INSERT INTO favoritos (id_user,id_ciudad) VALUES (" + UserId + "," + id + ")";
                    pst = conexion.getConexion().prepareStatement(sql);
                    pst.execute();

                }
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
            String data = "Se ha agregado la ciudad " + request.getParameter("city") + " a tus favoritos";
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
        return "Guarda la ciudad enviada por el usuario en favoritos";
    }// </editor-fold>

}
