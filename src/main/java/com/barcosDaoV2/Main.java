package com.barcosDaoV2;

import com.barcosDaoV2.model.Amarre;
import com.barcosDaoV2.model.Barco;
import com.barcosDaoV2.model.Regata;
import com.barcosDaoV2.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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

        Regata regata = new Regata();
        regata.setNombre("Regata Ejemplo");
        regata.setLugar("Mar Mediterráneo");
        regata.setFecha(new Date());
        regata.setDistancia(100);


        barco1.setAmarre(amarre1);
        amarre1.setBarco(barco1);
        barco2.setAmarre(amarre2);
        amarre2.setBarco(barco2);

        // Agregar barcos a la lista de barcos en la regata

        regata.getBarcos().add(barco1);
        regata.getBarcos().add(barco2);

        // Agregar la regata a la lista de regatas en los barcos

        barco1.getRegatas().add(regata);
        barco2.getRegatas().add(regata);



        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(barco1);
            session.persist(amarre1);
            session.persist(barco2);
            session.persist(amarre2);
            session.persist(regata);
            transaction.commit();
        }
    }
}