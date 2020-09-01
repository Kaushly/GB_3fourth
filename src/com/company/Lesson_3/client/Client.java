package com.company.Lesson_3.client;

import com.company.Lesson_3.client.GUI.LoginChatFrame;
import com.company.Lesson_3.client.GUI.MainChatFrame;
import com.company.Lesson_3.server.Server;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Client implements AuthorizationChecker {
    private int port;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainChatFrame chatFrame;
    private JTextArea textArea;
    private ClientHistory clientHistory;

    public Client(int port) {
        this.port = port;
    }

    private void init(String login, String password) throws IOException {
        socket = new Socket("localhost", port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        LoginChatFrame loginFrame = new LoginChatFrame(this, login, password);
        System.out.println(loginFrame.isAuthorized());
        loginFrame.dispose();
        chatFrame = new MainChatFrame("Клиент чата " + login,
                new MessageListener() {
                    @Override
                    public void messagePerformed(String message) {
                        try {
                            out.writeUTF(message);
                        } catch (IOException ioException) {
                            throw new RuntimeException("Sending message error", ioException);
                        }
                    }
                });
    }


    public void start(String login, String password) throws IOException {

        init(login, password);
        clientHistory = new ClientHistory(login);
        chatFrame.getTextArea().append(clientHistory.getHistory());


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if(!message.isBlank()) {
                            if (message.equals(Server.END_MESSAGE)) {
                                System.out.println("Session closed. Cau!");
                                break;
                            }
                            clientHistory.writeFile(message + "\n");
                            if (message.startsWith(Server.PRIVATE_MESSAGE)) {
                                chatFrame.prepareMessage(Server.WHISP_MESSAGE + message.split(":")[0].split("\\s")[Server.PRIVATE_MESSAGE.split("\\s").length] + " ");
                            }
                            chatFrame.pushMessage(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean checkAuthorization(String login, String password) {
        try {
            out.writeUTF(Server.AUTH_MESSAGE + " " + login + " " + password);
            String message = in.readUTF();
            if (message.startsWith(Server.AUTH_DONE_MESSAGE)) {
                System.out.println("Authorized");
                return true;
            }
        } catch (IOException exception) {
            throw new RuntimeException("Ошибка авторизации", exception);
        }
        return false;
    }
}
