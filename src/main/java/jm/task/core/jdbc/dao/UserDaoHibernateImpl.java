package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private final SessionFactory sessionFactory = Util.Hibernateutil.getSessionFactory();
    private Session currentSession;
    private Session session;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        currentSession.createSQLQuery("CREATE TABLE   IF NOT EXISTS Users " +
//                "(Id BIGINT PRIMARY KEY AUTO_INCREMENT,  FirstName TINYTEXT , " +
//                "LastName TINYTEXT ,  Age TINYINT)").executeUpdate();
//
//        currentSession.getTransaction().commit();
//        System.out.println("Hibernate Table has been created");


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE   IF NOT EXISTS Users " +
                "(Id BIGINT PRIMARY KEY AUTO_INCREMENT,  FirstName TINYTEXT , " +
                "LastName TINYTEXT ,  Age TINYINT)").executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        currentSession.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
//        currentSession.getTransaction().commit();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        tx.commit();
        session.close();

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        new UserDaoHibernateImpl().createUsersTable();

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        currentSession.save(user);
//        currentSession.getTransaction().commit();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();


    }


    @Override
    public void removeUserById(long id) {

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        User user = (User) currentSession.get(User.class, id);
//        currentSession.delete(user);
//        currentSession.getTransaction().commit();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        tx.commit();
        session.close();
    }


    @Override
    public List<User> getAllUsers() {

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        List<User> hibusers =
//                (ArrayList<User>) currentSession.createQuery("from User").list();
//        currentSession.getTransaction().commit();
//        return hibusers;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<User> hiberusers =
                session.createQuery("From User").list();
        tx.commit();
        session.close();
        return hiberusers;
    }

    @Override
    public void cleanUsersTable() {

//        currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
//        currentSession.createQuery("delete from User").executeUpdate();
//        currentSession.getTransaction().commit();
//        System.out.println("HibCleaning users table");

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        tx.commit();
        System.out.println("HibCleaning users table");
        session.close();

    }
}
