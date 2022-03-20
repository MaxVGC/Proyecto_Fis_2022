/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import datos.Cifrador;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import model.HibernateUtil;
import model.Querys;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Esta clase contiene los metodos para el cambio de contraseña.
 *
 * @author Andres Marlex
 */
public class cambiarcontrasena extends HttpServlet {

    /**
     * Este metodo hace una consulta a la base de datos para validar si la
     * contraseña que se ha ingresado concuerda con la del usuario.
     *
     * @param q Variable de la base de datos.
     * @param antigua Contraseña que se usara para comparar con la base de
     * datos.
     * @param user Usuario que se usara para la consulta de la base de datos.
     * @return (Boolean) Retorna falso si la contraseña no coincide con la de la
     * base de datos, Retorna verdadero en caso contrario.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public boolean ValidarContrasena(Querys q, Cifrador n, String antigua, String user) throws SQLException {
        try {
            String s = "select a.password from Usuarios a where a.nickname='" + user + "'";
            List a = q.Query(s);
            Iterator it = a.iterator();
            String object = (String) it.next();
            if (object.equals(n.hash(antigua))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
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
        response.setContentType("text/html;charset=UTF-8");

        String user = request.getParameter("user");
        String nueva = request.getParameter("nueva");
        String antigua = request.getParameter("antigua");

        try (PrintWriter out = response.getWriter()) {

            Cifrador n = new Cifrador();
            Querys q = new Querys();

            if (ValidarContrasena(q, n, antigua, user)) {
                
                String s = "update Usuarios a set a.password='" + n.hash(nueva) + "' where a.nickname='" + user + "'";
                q.Query2(s);
                out.println("1");
            } else {
                out.println("0");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
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
        return "Valida la contraseña";
    }// </editor-fold>

}
