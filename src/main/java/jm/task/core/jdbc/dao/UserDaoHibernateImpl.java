package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private final SessionFactory sessionFactory = Util.Hibernateutil.getSessionFactory();
    private Session currentSession;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery("CREATE TABLE   IF NOT EXISTS Users " +
                "(Id BIGINT PRIMARY KEY AUTO_INCREMENT,  FirstName TINYTEXT , " +
                "LastName TINYTEXT ,  Age TINYINT)").executeUpdate();

        currentSession.getTransaction().commit();
        System.out.println("Hibernate Table has been created");


    }

    @Override
    public void dropUsersTable() {

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        currentSession.getTransaction().commit();


    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        new UserDaoHibernateImpl().createUsersTable();
        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.save(user);
        currentSession.getTransaction().commit();
    }


    @Override
    public void removeUserById(long id) {

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        User user = (User) currentSession.get(User.class, id);
        currentSession.delete(user);
        currentSession.getTransaction().commit();


    }


    @Override
    public List<User> getAllUsers() {

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        List<User> hibusers =
                (ArrayList<User>) currentSession.createQuery("from User").list();
        currentSession.getTransaction().commit();
        return hibusers;
    }

    @Override
    public void cleanUsersTable() {

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from User").executeUpdate();
        currentSession.getTransaction().commit();
        System.out.println("HibCleaning users table");
    }
}
