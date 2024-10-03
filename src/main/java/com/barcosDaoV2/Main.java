package com.barcosDaoV2;

import com.barcosDaoV2.dao.*;
import com.barcosDaoV2.model.Amarre;
import com.barcosDaoV2.model.Barco;
import com.barcosDaoV2.model.Regata;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Configurar el hibernate no se si hace falta realmente
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // En Openwebinar lo hace de este modo
        //Session session = HibernateUtils.getSessionFactory().openSession();
        //session.beginTransaction();

        Barco barco1 = new Barco();
        barco1.setNombre("Bribon");
        barco1.setTipo("Velero");
        barco1.setEslora(10);
        barco1.setManga(3);
        barco1.setCapacidad(6);

        Barco barco2 = new Barco();
        barco2.setNombre("Guebon");
        barco2.setTipo("Velero");
        barco2.setEslora(10);
        barco2.setManga(3);
        barco2.setCapacidad(6);

        Barco barco3 = new Barco();
        barco3.setNombre("Guebon2");
        barco3.setTipo("Velero/motor");
        barco3.setEslora(10);
        barco3.setManga(4);
        barco3.setCapacidad(5);

        Amarre amarre1 = new Amarre();
        amarre1.setUbicacion("A-1");
        amarre1.setPrecio(1500.0);
        amarre1.setProfundidad(5);
        amarre1.setLongitud(12);
        amarre1.setElectricidad(true);

        Amarre amarre2 = new Amarre();
        amarre2.setUbicacion("A-1");
        amarre2.setPrecio(1500.0);
        amarre2.setProfundidad(5);
        amarre2.setLongitud(12);
        amarre2.setElectricidad(true);

        Regata regata1 = new Regata();
        regata1.setNombre("Regata Ejemplo");
        regata1.setLugar("Mar Mediterráneo");
        regata1.setFecha(new Date());
        regata1.setDistancia(100);


        barco1.setAmarre(amarre1);
        amarre1.setBarco(barco1);
        barco2.setAmarre(amarre2);
        amarre2.setBarco(barco2);

        // Agregar barcos a la lista de barcos en la regata

        regata1.getBarcos().add(barco1);
        regata1.getBarcos().add(barco2);

        // Agregar la regata a la lista de regatas en los barcos

        barco1.getRegatas().add(regata1);
        barco2.getRegatas().add(regata1);


        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            BarcoDao barcoDao = new BarcoDaoImpl(sessionFactory);
            RegataDao regataDao = new RegataDaoImpl(sessionFactory);
            AmarreDao amarreDao = new AmarreDaoImpl(sessionFactory);
//            regataDao.crear(regata1);
//            barcoDao.crear(barco1);
//            amarreDao.crear(amarre1);
//            barcoDao.crear(barco2);
//            amarreDao.crear(amarre2);
//            barcoDao.crear(barco3);
//            Barco barcoObtenido = barcoDao.obtener(3);
//            String mensaje = (barcoObtenido == null)
//                    ? "Ese barco no está en la base de datos"
//                    : "Nombre: "+barcoObtenido.getNombre()+" Id: "+ barcoObtenido.getId();
//            System.out.println(mensaje);
//            barcoDao.delete(1);
//            barcoDao.saveorupdate(barco1);
//            List<Barco> barcos = barcoDao.findAll();
//            for (Barco barco : barcos) {
//                System.out.println(barco.getNombre());
//            }
            Amarre amarreObtenido = amarreDao.obtener(2);
            amarreDao.saveorupdate(amarreObtenido);
            String mensaje = (amarreObtenido == null)
                    ? "Ese barco no está en la base de datos"
                    : "Nombre: " + amarreObtenido.getUbicacion() + " Id: " + amarreObtenido.getId();
            System.out.println(mensaje);
            amarreDao.delete(12);
            List<Amarre> amarres = amarreDao.findAll();
            for (Amarre amarre : amarres) {
                System.out.println(amarre.getUbicacion());
            }

        }
        sessionFactory.close();
    }
}