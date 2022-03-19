/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import model.Event;

public class Ejemplo1 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Ejemplo1();
    }

    public Ejemplo1() {
        createAndStoreEvent("El Event", new Date());
        listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private int createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate.toString());
        System.out.println(theEvent.toString());
        session.save(theEvent);
        System.out.println(session);
        session.getTransaction().commit();
        System.out.println("Insertado: "+theEvent);
        return theEvent.getId();
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = (List<Event>)session.createQuery("from Event").list();
        session.getTransaction().commit();
        for (Event evento : result) {
            System.out.println("Leido: "+evento);
        }
        return result;
    }

}