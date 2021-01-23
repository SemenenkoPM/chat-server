package ru.itsjava;


import lombok.extern.flogger.Flogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itsjava.services.ServerImpl;

import java.sql.*;


public class MyApplication {
    private final static int PORT = 8080;
    private static final Logger logger = LogManager.getLogger("HelloWorld");


    public static void main(String[] args) {
        logger.info("Hello, World!");
        new ServerImpl().start();
//        UserDao userDao = new UserDaoImpl();
//        System.out.println(userDao.findByName("U1"));
//        System.out.println(userDao.findByName("U2"));

//        String DB_URL = "";
//        String DB_LOGIN = "";
//        String DB_PASSWORD = "";
//
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
//        Statement statement = connection.createStatement()) {
//
//            ResultSet resultSet = statement.executeQuery("SELECT name, password FROM my_schema_dec_2020.users");
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name") + ":" + resultSet.getString( "password"));
//            }
//
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


//        System.out.println("==SERVER STARTS==");
//        ServerSocket serverSocket = new ServerSocket(PORT);
//
//        while (true) {
//            Socket socket = serverSocket.accept();
//
//            if (socket != null){
//                System.out.println("Client connected");
//
//
////                System.out.println(socketReader.readLine());
//                ClientRunnable clientRunnable = new ClientRunnable(socket);
//                Thread clientThread = new Thread(clientRunnable);
//                clientThread.start();
//
//            }
//
//
//
//
//        }

    }
}
