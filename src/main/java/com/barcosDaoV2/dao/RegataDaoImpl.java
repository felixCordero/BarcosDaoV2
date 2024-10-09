package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Amarre;
import com.barcosDaoV2.model.Barco;
import com.barcosDaoV2.model.Regata;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RegataDaoImpl implements RegataDao {
    private SessionFactory sessionFactory;
    private Transaction tx ;
    private Session session = null;
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
    public void saveorupdate(Regata regata) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        regata.setLugar("Pacifico");
        session.merge(regata);
        transaction.commit();
        session.close();
    }

    @Override
    public Regata obtener(Integer id) {
        Session session = sessionFactory.openSession();
        Regata regataObtenida = session.get(Regata.class, id);
        session.close();
        return regataObtenida;
    }

    @Override
    public void delete(Integer id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx =session.beginTransaction();
            Regata regataBorrar = obtener(id);
            if (regataBorrar != null) {
                System.out.println("ELIMINANDO REFERENCIAS DE LA REGATA");
                for (Barco barco : regataBorrar.getBarcos()) {
                    barco.getRegatas().remove(regataBorrar);  // Eliminar la referencia desde el lado de Barco
                    session.update(barco); // Actualizar el barco en la base de datos
                }
                System.out.println("ELIMINANDO REGATA");
                session.remove(regataBorrar);
                session.flush();
                tx.commit();
            }else {
                System.out.println("LA REGATA NO EXISTE EN LA BASE DE DATOS");
            }
        }catch (HibernateException e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("ERROR AL BORRAR LA REGATA"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Regata> findAll() {
        return List.of();
    }

}
