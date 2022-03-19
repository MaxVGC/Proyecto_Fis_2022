/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import datos.Cifrador;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.HibernateUtil;
import model.Querys;
import model.Usuarios;
import model.Usuarios_Google;
import org.hibernate.Session;

/**
 *
 * @author carlo
 */
public class registrarusuario extends HttpServlet {

    /**
     * Este metodo se encarga de verificar si el usuario existe en la base de
     * datos.
     *
     * @param q Variable de la base de datos.
     * @param user Usuario que se usara para la consulta de la base de datos.
     * @return Retorna (Boolean)
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public boolean existeUsuario(Querys q, String user) throws SQLException {
        String s = "select a.nickname from Usuarios a where a.nickname='" + user + "'";
        List res = q.Query(s);
        Iterator it = res.iterator();
        if (it.hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este metodo se encarga de obtener un id para el registro del usuario.
     *
     * @param q Variable de la base de datos.
     * @return Retorna (Integer) que corresponde al id libre.
     * @throws java.sql.SQLException Si un error de SQL ocurre.
     */
    public int ObtenerIdLibre(Querys q) throws SQLException {
        String s = "select a.id from Usuarios a order by a.id desc ";
        List res = q.Query(s);
        Iterator it = res.iterator();
        int aux = 0;
        if (it.hasNext()) {
            int object = (int) it.next();
            aux = object;
        }
        return aux + 1;
    }

    /**
     * Este metodo se encarga de transformar en mayusculas la primera letra de
     * cada inicio de palabra o nombre.
     *
     * @param str Palabra a la cual se le aplicara el metodo.
     * @return Retorna (Integer) que corresponde al id libre.
     */
    public String Mayusculas(String str) {
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
        while (match.find()) {
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        return (match.appendTail(strbf).toString());
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
            Cifrador n = new Cifrador();
            response.setContentType("text/html;charset=UTF-8");
            Querys q = new Querys();

            String user = request.getParameter("user");
            String rol = request.getParameter("rol");
            String url = request.getParameter("URL");
            String b[] = url.split("registro.jsp");

            if (!existeUsuario(q, user)) {
                int id = ObtenerIdLibre(q);
                String name = Mayusculas(request.getParameter("name"));
                String pass = n.hash(request.getParameter("pass"));
                String apell = Mayusculas(request.getParameter("apell"));
                if (apell.equals("Undefined")) {
                    apell = " ";
                }
                String email = request.getParameter("email");
                if (rol.equals("1")) {
                    Usuarios u = new Usuarios(id, user, pass, name, apell, email, Integer.parseInt(rol));
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    session.save(u);
                    session.getTransaction().commit();
                    session.close();
                } else {
                    String id_google = request.getParameter("id_google");
                    String image = request.getParameter("image");
                    Usuarios u = new Usuarios(id, user, pass, name, apell, email, Integer.parseInt(rol));
                    Usuarios_Google u2 = new Usuarios_Google(id, id_google, image);
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    session.save(u);
                    session.save(u2);
                    session.getTransaction().commit();
                    session.close();
                }
                response.sendRedirect("index.html?alert=2");
            } else {
                if (rol.equals("1")) {
                    response.sendRedirect("pages/registro.jsp?alert=0");
                } else {
                    response.sendRedirect("pages/registro.jsp" + b[1] + "&alert=0");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(registrarusuario.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Registra el usuario en la base de datos";
    }// </editor-fold>

}
