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
    public BarcoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void crear(Barco barco) {
        Transaction tx ;
        Session session = null;
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
    public void saveorupdate(Barco entity) {

    }

    @Override
    public Barco obtener(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Barco> findAll() {
        return List.of();
    }
}
