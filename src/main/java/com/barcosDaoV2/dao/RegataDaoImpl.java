package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Regata;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RegataDaoImpl implements RegataDao {
    private SessionFactory sessionFactory;

    public RegataDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Regata regata) {
        Transaction tx ;
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            session.persist(regata);
            tx.commit();
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                System.out.println("Rolling back transaction Regata");
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveorupdate(Regata entity) {

    }

    @Override
    public Regata obtener(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Regata> findAll() {
        return List.of();
    }

}
