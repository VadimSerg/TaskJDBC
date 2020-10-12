package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String url = "jdbc:mysql://localhost/vadim_5?serverTimezone=Asia/Vladivostok&useSSL=false";
    private static final String password = "IwannaBeAdeveloper2312&";
    private static final String username = "Vadim";
    private Connection session;

    public Connection getSession() {
        try {
            session = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return session;
    }

    // for Hibernate
    public static final class Hibernateutil {
        private static SessionFactory sessionFactory;
        private static Session session;

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, url);
                    settings.put(Environment.USER, username);
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                    settings.put(Environment.SHOW_SQL, "true");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                    settings.put(Environment.PASS, "IwannaBeAdeveloper2312&");
                    settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(User.class);
                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                            applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
            return sessionFactory;
        }
    }
}

