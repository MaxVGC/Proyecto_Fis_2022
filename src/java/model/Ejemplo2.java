/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Ejemplo2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(prueba(1));
    }

//    List res = q.Query("select u.id as id_user, c.id as id_ciudad from Usuarios u,Ciudades c where u.id=c.id");
//    Iterator it = res.iterator();
//    while (it.hasNext ()) {
//            Object[] object = (Object[]) it.next();
//        System.out.println("Department Number : " + object[0]
//                + " Salary : " + object[1]);
//    }
    public static int prueba(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        String qryString = "delete from Event s where s.id=" + id;
        Query query = session.createQuery(qryString);
        int count = query.executeUpdate();

        return count;
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
//        while (it.hasNext()) {
//            Object[] object = (Object[]) it.next();
//            System.out.println("Department Number : " + object[0]
//                    + " Salary : " + object[1]);
//        }
