package ru.itsjava.dao;

import ru.itsjava.domain.User;

import java.sql.*;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/my_schema_dec_2020?serverTimezone=UTC";
    private final static String DB_LOGIN = "root";
    private final static String DB_PASSWORD = "";


    @Override
    public User findByName(String name) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT name, password FROM my_schema_dec_2020.users where name = '" + name + "'");

            resultSet.next();
            return new User(resultSet.getString("name"), resultSet.getString("password"));


//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name") + ":" + resultSet.getString( "password"));
//            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new NoSuchElementException("User not found");
    }

    @Override
    public void createNewUser(String login, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO my_schema_dec_2020.users (name, password) VALUES ('" + login + "', '" + password + "')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

