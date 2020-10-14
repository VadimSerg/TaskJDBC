package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
        // UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Konstantin", "Habensky", (byte) 34);
//        userDaoJDBC.saveUser("James", "Gossling", (byte) 78);
//        userDaoJDBC.saveUser("Vasiliy", "Pupkin", (byte) 57);
//        System.out.println(userDaoJDBC.getAllUsers());
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.saveUser("Vlad", "Vlasov", (byte) 22);
//        userDaoJDBC.saveUser("Frank", "Darabont", (byte) 45);
//        System.out.println(userDaoJDBC.getAllUsers());
//        userDaoJDBC.saveUser("Rich", "Sarbon", (byte) 36);
//        userDaoJDBC.removeUserById(3);
//        userDaoJDBC.removeUserById(7);

        ///Hibernate

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Rich", "Sarbon", (byte) 36);
        userDaoHibernate.saveUser("Konstantin", "Habensky", (byte) 34);
        userDaoHibernate.saveUser("Vasiliy", "Pupkin", (byte) 57);
        userDaoHibernate.saveUser("James", "Gossling", (byte) 78);
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.removeUserById(1);
//        userDaoHibernate.removeUserById(3);
        System.out.println(userDaoHibernate.getAllUsers());
        //   userDaoHibernate.cleanUsersTable();
        // userDaoHibernate.dropUsersTable();

    }
}
