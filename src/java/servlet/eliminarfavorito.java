/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import model.HibernateUtil;
import model.Querys;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    public int UserId(Querys q, String user) throws SQLException {
        String s = "select a.id from Usuarios a where a.nickname='" + user + "'";
        List res = q.Query(s);
        Iterator it = res.iterator();
        int aux = 0;
        while (it.hasNext()) {
            int object = (int) it.next();
            aux = object;
        }
        return aux;
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
    public int CityId(Querys q, String name) throws SQLException {
        String s = "select a.id from Ciudades a where a.nombre='" + name + "'";
        List res = q.Query(s);
        Iterator it = res.iterator();
        int aux = 0;
        while (it.hasNext()) {
            int object = (int) it.next();
            aux = object;
        }
        return aux;
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

        try (PrintWriter out = response.getWriter()) {
            try {
                Querys q = new Querys();

                int Cityid = CityId(q, city);
                int UserId = UserId(q, user);

                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                String s = "delete from Favoritos a where a.id_user='" + UserId + "' and a.id_ciudad='" + Cityid + "'";
                Query query = session.createQuery(s);
                int count = query.executeUpdate();

            } catch (Exception e) {
                out.println(e);
                System.out.println(e);

            }
            out.println("Se ha eliminado la ciudad " + request.getParameter("city") + " de tus favoritos");
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
        return "Elimina una ciudad de favoritos del usuario";
    }// </editor-fold>

}
