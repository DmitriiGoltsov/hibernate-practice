package org.goltsov;

import converter.BirthdayConvertor;
import entity.Birthday;
import entity.Role;
import entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.time.Month;

public class HibernateRunner {

    public static final Logger logger = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        User user = User.builder()
                .username("PussyDestroyer1811@gmail.com")
                .name("Gaylord")
                .surname("Fuckerer")
                .birthDate(new Birthday(LocalDate.of(1999, Month.DECEMBER, 15)))
                .role(Role.ADMIN)
                .info("""
                            {
                                "name": "destroyer",
                                "id": 25
                            }
                            """)
                .build();
        logger.info("User entity is in transient state, object: {}", user);


        try (SessionFactory factory = HibernateUtil.buildSessionFactory()) {
            Session session1 = factory.openSession();

            try (session1) {
                Transaction transaction = session1.beginTransaction();
                logger.trace("Transaction is created, {}", transaction);

                session1.persist(user);
                logger.trace("User is in persistent state: {}, session {}", user, session1);

                session1.getTransaction().commit();
            }
            logger.warn("User is in detached state: {}, session is closed {}", user, session1);
        } catch (Exception exception) {
            logger.error("Exception has occurred!", exception);
            throw exception;
        }
    }
}
