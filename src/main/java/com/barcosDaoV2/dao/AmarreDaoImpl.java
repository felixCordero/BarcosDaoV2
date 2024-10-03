package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Amarre;
import com.barcosDaoV2.model.Barco;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AmarreDaoImpl implements AmarreDao {
    private SessionFactory sessionFactory;
    private Transaction tx ;
    private Session session = null;
    public AmarreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Amarre amarre) {
        Transaction tx ;
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.merge(amarre);
            tx.commit();
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                System.out.println("Rolling back transaction Amarre");
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveorupdate(Amarre amarre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        amarre.setUbicacion("House");
        session.merge(amarre);
        transaction.commit();
        session.close();
    }

    @Override
    public Amarre obtener(Integer id) {
        Session session = sessionFactory.openSession();
        Amarre amarreObtenido = session.get(Amarre.class, id);
        session.close();
        return amarreObtenido;
    }

    @Override
    public void delete(Integer id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            Amarre amarreBorrar = obtener(id);
            if (amarreBorrar != null) {
                System.out.println("ELIMINANDO AMARRE");
                session.remove(amarreBorrar);
                session.flush();
                tx.commit();
            }else {
                System.out.println("EL AMARRE NO EXISTE EN LA BASE DE DATOS");
            }
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("ERROR AL BORRAR EL AMARRE"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Amarre> findAll() {
        Session session = sessionFactory.openSession();
        List<Amarre> amarres = session.createQuery("from Amarre ", Amarre.class).list();
        session.close();
        return amarres;
    }


}
