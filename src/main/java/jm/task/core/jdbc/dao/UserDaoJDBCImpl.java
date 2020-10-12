package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Util util = new Util();
    private final Connection connection = util.getSession();


    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE   IF NOT EXISTS users " +
                    "(Id BIGINT PRIMARY KEY AUTO_INCREMENT,  FirstName TINYTEXT , " +
                    "LastName TINYTEXT ,  Age TINYINT)");

            System.out.println("Table has been created successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS Users ");
            System.out.println("Table has been removed successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT  Users (FirstName,LastName,Age) VALUE(?,?,?) ")) {
            new UserDaoJDBCImpl().createUsersTable();
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM Users  WHERE Id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User has been removed successfully");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT *FROM  users");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getByte("Age")));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM Users");
            System.out.println("The table has been cleaned");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }
}

