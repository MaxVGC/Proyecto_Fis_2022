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

/**
 *
 * @author carlo
 */
public class validarusuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        conexionJDBC conexion = new conexionJDBC();
        try {
            conexion.conectar();
            Cifrador n = new Cifrador();
            String sql = "select nickname,password from usuarios where nickname='" + request.getParameter("user") + "'";
            Statement stat = conexion.getConexion().createStatement();
            ResultSet query = stat.executeQuery(sql);
            if (query.next() != false) {
                conexion.getConexion().close();
                if (query.getString("nickname").replace(" ", "").equals(request.getParameter("user")) && query.getString("password").replace(" ", "").equals(n.hash(request.getParameter("pass")))) {
                    response.sendRedirect("pages/home.jsp?n=" + n.codificarB64(request.getParameter("user")) + "&u="+n.codificarB64("Usuario"));
                } else {
                    response.sendRedirect("index.html?alert=1");
                }
            } else {
                conexion.getConexion().close();
                response.sendRedirect("index.html?alert=0");
            }

        } catch (Exception e) {
            out.println(e);
            try {
                conexion.getConexion().close();
            } catch (SQLException ex) {
                Logger.getLogger(validarusuario.class.getName()).log(Level.SEVERE, null, ex);
            }
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
