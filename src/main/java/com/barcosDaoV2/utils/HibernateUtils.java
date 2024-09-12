package com.barcosDaoV2.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static SessionFactory buildSessionFactory() throws RuntimeException {
        try {
            StandardServiceRegistry standardRegistry = new
                    StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new
                    MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la sesión de Hibernate.");
        }
    }
    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }
}
