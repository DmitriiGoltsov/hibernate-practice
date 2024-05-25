package org.goltsov;

import converter.BirthdayConvertor;
import entity.Birthday;
import entity.Company;
import entity.Role;
import entity.User;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class HibernateRunner {

    public static final Logger logger = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {

        Company company = Company.builder()
                .name("Gazprom")
                .build();

        User user = User.builder()
                .username("PussyDestroyer1811@gmail.com")
                .firsName("Gaylord")
                .surname("Fuckerer")
                .birthDate(LocalDate.of(1999, Month.DECEMBER, 15))
                .role(Role.ADMIN)
                .info("""
                            {
                                "name": "destroyer",
                                "id": 25
                            }
                            """)
                .company(company)
                .build();
        logger.info("User entity is in transient state, object: {}", user);

        try (SessionFactory factory = HibernateUtil.buildSessionFactory()) {
            Session session1 = factory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();

                User user1 = session1.get(User.class, 1L);
                Company company1 = user1.getCompany();
                String name = company1.getName();
//                session1.save(company);
//                session1.save(user);
                Object object = Hibernate.unproxy(company1);

                session1.getTransaction().commit();
            }
        }
    }
}
