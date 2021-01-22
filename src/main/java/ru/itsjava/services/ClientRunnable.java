package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientRunnable implements Runnable, Observer {
    private final Socket socket;
    private final Observable server;
    private final UserDao userDao;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String socketInputMessage;
        String numMenu = socketReader.readLine();

            if (numMenu.equals("1")) {
                if (autorization(socketReader)) {
                    server.addObserver(this);


                    while ((socketInputMessage = socketReader.readLine()) != null) {
                        System.out.println(socketInputMessage);
                        server.notifyObservers(user.getName() + ":" + socketInputMessage);
                    }
                }
            }
            if (numMenu.equals("2")) {
                String socketInputLogin = socketReader.readLine();
                String socketInputPassword = socketReader.readLine();

                userDao.createNewUser(socketInputLogin,socketInputPassword);

            }
        }
    }

    @SneakyThrows
    private boolean autorization(BufferedReader socketReader) {
        String socketInputMessage = socketReader.readLine();
        //auth!login:password
        var loginAndPass = socketInputMessage.substring(6).split(":");

        user = userDao.findByName(loginAndPass[0]);
        if (user.getPassword().equals(loginAndPass[1])) {
            notify("Вы успешно авторизовались");
            return true;
        }
        notify("Неправильно введен пароль");
        return false;
    }



    @SneakyThrows
    @Override
    public void notify(String message) {
        PrintWriter socketWriter = new PrintWriter(socket.getOutputStream());
        socketWriter.println(message);
        socketWriter.flush();

    }

}
