package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Barco;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BarcoDaoImpl implements BarcoDao {

    private SessionFactory sessionFactory;
    private Transaction tx ;
    private Session session = null;
    public BarcoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void crear(Barco barco) {
       try {
           session = HibernateUtils.getSessionFactory().openSession();
           tx =session.beginTransaction();
           session.persist(barco);
           tx.commit();
       }catch (HibernateException e){
           if (session.getTransaction() != null) {
               session.getTransaction().rollback();
               System.out.println("Rolling back transaction Barco");
           }
       }finally {
           if (session != null) {
               session.close();
           }
       }
    }

    @Override
    public void saveorupdate(Barco barco) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Barco updatedBarco = new Barco();
        updatedBarco.setNombre("nuevoBribon");
        updatedBarco.setId(barco.getId());
        session.merge(updatedBarco);
        transaction.commit();
        session.close();
    }

    @Override
    public Barco obtener(Integer id) {
            Session session = sessionFactory.openSession();
            Barco barcoObtenido = session.get(Barco.class, id);
            return barcoObtenido;
    }

    @Override
    public void delete(Integer id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.remove(session.find(Barco.class, id));
            tx.commit();
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                System.out.println("Error al borrar el Barco");
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Barco> findAll() {
        return List.of();
    }
}
