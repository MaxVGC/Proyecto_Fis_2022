/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class Querys {

    public List<Ciudades> QueryCiudades(String opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Ciudades> result = (List<Ciudades>) session.createQuery("from Ciudades " + opc).list();
        session.getTransaction().commit();
        for (Ciudades ciudades : result) {
            System.out.println("Leido: " + ciudades);
        }
        return result;
    }

    public List<Roles> QueryRoles(String opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Roles> result = (List<Roles>) session.createQuery("from Roles " + opc).list();
        session.getTransaction().commit();
        for (Roles roles : result) {
            System.out.println("Leido: " + roles);
        }
        return result;
    }

    public List<Usuarios> QueryUsuarios(String opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Usuarios> result = (List<Usuarios>) session.createQuery("from Usuarios " + opc).list();
        session.getTransaction().commit();
        for (Usuarios usuarios : result) {
            System.out.println("Leido: " + usuarios);
        }
        return result;
    }

    public List<Usuarios_Google> QueryUsuarios_Google(String opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Usuarios_Google> result = (List<Usuarios_Google>) session.createQuery("from Usuarios_Google " + opc).list();
        session.getTransaction().commit();
        for (Usuarios_Google usuarios : result) {
            System.out.println("Leido: " + usuarios);
        }
        return result;
    }

    public List QueryFavoritos(int opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("select c.id_user , c.id_ciudad from Usuarios u,Favoritos c where u.id=c.id_user and u.id=" + opc).list();
        session.getTransaction().commit();
        return result;
    }

    public List Query(String opc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery(opc).list();
        session.getTransaction().commit();
        return result;
    }

    public String Query2(String opc) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(opc);
        int count = query.executeUpdate();
        transaction.commit();
        session.clear();
        return count+"";
    }

}
