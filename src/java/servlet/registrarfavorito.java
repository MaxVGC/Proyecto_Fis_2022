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
 *
 * @author carlo
 */
public class registrarfavorito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @return
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean isExist(String city, conexionJDBC conexion) throws SQLException {
        String sql = "select nombre from ciudades where nombre='" + city + "'";
        Statement stat = conexion.getConexion().createStatement();
        ResultSet query = stat.executeQuery(sql);
        if (query.next() != false) {
            return true;
        } else {
            return false;
        }
    }

    public int Id(Statement q) throws SQLException {
        String s = "select * from ciudades";
        ResultSet f = q.executeQuery(s);
        while (!f.isLast()) {
            f.next();
        }
        int id = f.getInt("id") + 1;
        return id;
    }

    public int UserId(Statement q, String user) throws SQLException {
        String s = "select id from usuarios where nickname='" + user + "'";
        ResultSet f = q.executeQuery(s);
        f.next();
        String aux = f.getString("id").replace(" ", "");
        return Integer.parseInt(aux);
    }

    public int CityId(Statement q, String name) throws SQLException {
        String s = "select id from ciudades where nombre='" + name + "'";
        ResultSet f = q.executeQuery(s);
        f.next();
        String aux = f.getString("id").replace(" ", "");
        return Integer.parseInt(aux);
    }

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

                int id = Id(q);
                int UserId = UserId(q, user);

                if (isExist(city, conexion)) {

                    int CityId = CityId(q, city);

                    String sql = "INSERT INTO favoritos (id_user,id_ciudad) VALUES (" + UserId + "," + CityId + ")";
                    PreparedStatement pst = conexion.getConexion().prepareStatement(sql);
                    pst.execute();

                } else {

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
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
