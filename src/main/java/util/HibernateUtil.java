package util;

import converter.BirthdayConvertor;
import entity.User;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.experimental.UtilityClass;

import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(BirthdayConvertor.class, true);
        //configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
