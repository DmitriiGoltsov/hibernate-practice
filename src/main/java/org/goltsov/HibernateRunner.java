package org.goltsov;

import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.Month;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);

        try (SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession()) {

            Transaction transaction = session.beginTransaction();

            User user = User.builder()
                    .username("PussyDestroyer1811@gmail.com")
                    .name("Gaylord")
                    .surname("Fucker")
                    .birthday(LocalDate.of(1999, Month.DECEMBER, 15))
                    .age(25)
                    .role(Role.ADMIN)
                    .build();

            session.persist(user);

            transaction.commit();


        }
    }
}
