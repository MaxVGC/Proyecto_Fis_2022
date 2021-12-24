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
public class eliminarfavorito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        conexionJDBC conexion = new conexionJDBC();

        try (PrintWriter out = response.getWriter()) {
            try {
                conexion.conectar();
                Statement q = conexion.getConexion().createStatement();

                int Cityid = CityId(q, city);
                int UserId = UserId(q, user);
                
                String sql = "delete from favoritos where id_user='"+UserId+"' and id_ciudad='"+Cityid+"'";
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
