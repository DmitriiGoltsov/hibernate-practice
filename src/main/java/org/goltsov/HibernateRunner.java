package org.goltsov;

import converter.BirthdayConvertor;
import entity.Birthday;
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
        configuration.addAttributeConverter(BirthdayConvertor.class, true);

        try (SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession()) {

            Transaction transaction = session.beginTransaction();

            User user = User.builder()
                    .username("PussyDestroyer1811@gmail.com")
                    .name("Gaylord")
                    .surname("Fucker")
                    .birthDate(new Birthday(LocalDate.of(1999, Month.DECEMBER, 15)))
                    .role(Role.ADMIN)
                    .info("""
                            {
                                "name": "destroyer",
                                "id": 25
                            }
                            """)
                    .build();

            session.persist(user);

            transaction.commit();
        }
    }
}
