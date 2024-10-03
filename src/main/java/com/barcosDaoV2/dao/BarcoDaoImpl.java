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
        barco.setNombre("nuevoBribon");
        session.merge(barco);
        transaction.commit();
        session.close();
    }

    @Override
    public Barco obtener(Integer id) {
            Session session = sessionFactory.openSession();
            Barco barcoObtenido = session.get(Barco.class, id);
            session.close();
            return barcoObtenido;
    }

    @Override
    public void delete(Integer id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            Barco barcoBorrar = obtener(id);
            if (barcoBorrar != null) {
                System.out.println("eliminando barco");
                session.remove(barcoBorrar);
                session.flush();
                tx.commit();
            }else {
                System.out.println("El barco no existe en la base de datos");
            }
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error al borrar el Barco"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Barco> findAll() {
        Session session = sessionFactory.openSession();
        List<Barco> barcos = session.createQuery("from Barco", Barco.class).list();
        session.close();
        return barcos;
    }
}
