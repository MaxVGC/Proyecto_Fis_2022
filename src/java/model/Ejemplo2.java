/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import datos.Cifrador;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

public class Ejemplo2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Querys q = new Querys();
        System.out.println(prueba( q,"pkmn3612","MaxVGC"));
    }

//    List res = q.Query("select u.id as id_user, c.id as id_ciudad from Usuarios u,Ciudades c where u.id=c.id");
//    Iterator it = res.iterator();
//    while (it.hasNext ()) {
//            Object[] object = (Object[]) it.next();
//        System.out.println("Department Number : " + object[0]
//                + " Salary : " + object[1]);
//    }
    public static boolean prueba(Querys q, String antigua, String user) {
        try {
            String s = "select a.password from Usuarios a where a.nickname='" + user + "'";
            List a = q.Query(s);
            Cifrador n = new Cifrador();
            Iterator it = a.iterator();
            String object = (String) it.next();
            if (object.equals(n.hash(antigua))) {
                return true;
            }else{
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private int Insert(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate.toString());
        System.out.println(theEvent.toString());
        session.save(theEvent);
        System.out.println(session);
        session.getTransaction().commit();
        System.out.println("Insertado: " + theEvent);
        return theEvent.getId();
    }

}

//Querys q = new Querys();
//        List a = q.QueryFavoritos(1);
//        Iterator it = a.iterator();

