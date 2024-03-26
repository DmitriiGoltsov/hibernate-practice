package org.goltsov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession()) {

            System.out.println("Ok. Session is " + session.getClass().getCanonicalName());
        }
    }
}
