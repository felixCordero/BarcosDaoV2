package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Amarre;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AmarreDaoImpl implements AmarreDao {
    private SessionFactory sessionFactory;

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
    public void saveorupdate(Amarre entity) {

    }

    @Override
    public Amarre obtener(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Amarre> findAll() {
        return List.of();
    }


}
