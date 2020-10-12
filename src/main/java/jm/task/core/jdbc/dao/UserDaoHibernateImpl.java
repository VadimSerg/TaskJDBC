package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private final SessionFactory sessionFactory = Util.Hibernateutil.getSessionFactory();
    private final Session currentSession = sessionFactory.getCurrentSession();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery("CREATE TABLE   IF NOT EXISTS Users " +
                "(Id BIGINT PRIMARY KEY AUTO_INCREMENT,  FirstName TINYTEXT , " +
                "LastName TINYTEXT ,  Age TINYINT)").executeUpdate();

        currentSession.getTransaction().commit();
        System.out.println("Hibernate Table has been created");


    }

    @Override
    public void dropUsersTable() {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        currentSession.getTransaction().commit();


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        new UserDaoHibernateImpl().createUsersTable();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();

        Session current = sessionFactory.getCurrentSession();
        current.beginTransaction();
        current.save(user);
        current.getTransaction().commit();


    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> hibusers = (ArrayList<User>) session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return hibusers;
    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        System.out.println("HibCleaning users table");
    }
}
