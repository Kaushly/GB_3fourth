package com.company.Lesson_3.server;

import com.company.Lesson_3.client.ClientHistory;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Server {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8889/CHATS";
    static final String DB_USER = "root";
    static final String DB_PASS = "root";

    public static final int PORT = 8082;

    public static final String AUTH_MESSAGE = "/auth";
    public static final String AUTH_DONE_MESSAGE = "/authok";
    public static final String WHISP_MESSAGE = "/w ";
    public static final String END_MESSAGE = "/end";
    public static final String PRIVATE_MESSAGE = "Private message from";

    private AuthService authService;
    private Set<ClientHandler> clientHandlers;

    private Connection dataBaseConnection = null;

    public Server() {

        this(PORT);
    }

    public Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            initSQLServer();

            authService = new DataBaseAuthService(dataBaseConnection);
            System.out.println("Auth is started up");

            clientHandlers = new HashSet<>();

            while (true) {
                System.out.println("Waiting for a connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);
                new ClientHandler(this, socket);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (dataBaseConnection != null) dataBaseConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isOccupied(AuthService.Record record) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getRecord().equals(record)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler ch) {
        clientHandlers.add(ch);
    }

    public synchronized void unsubscribe(ClientHandler ch) {
        clientHandlers.remove(ch);
    }

    public synchronized void sendMessage(String name, String message) {

        if (message.toLowerCase().startsWith(WHISP_MESSAGE)) {
            sendPrivateMessage(name, message);
        } else {
            broadcastMessage(name, message);
        }
    }

    private void broadcastMessage(String name, String message) {
        for (ClientHandler ch : clientHandlers) {
            ch.sendMessage(String.format("%s: %s", name, message));
        }
    }

    private void sendPrivateMessage(String name, String message) {
        ClientHandler sender = getClientHandlerByName(name);

        String[] split = message.split("\\s");

        if (split.length == 1) {
            sender.sendMessage(String.format("Whisp command format: %sname message", Server.WHISP_MESSAGE));
            return;
        }

        String recipientName = split.length > 1 ? split[1] : "";
        String privateMessage = message.substring(split[0].length() + split[1].length() + 1);

        if (privateMessage.isBlank()) {
            sender.sendMessage("You should type message before sending");
            return;
        }

        ClientHandler recipient = getClientHandlerByName(recipientName);
        if (recipient == null) {
            sender.sendMessage(String.format("Unknown username: %s", recipientName));
            return;
        }

        if (name.equalsIgnoreCase(recipientName)) {
            sender.sendMessage("You sent message to yourself: " + privateMessage);
            return;
        }

        sender.sendMessage(String.format("You sent private message to %s: %s", recipientName, privateMessage));
        recipient.sendMessage(String.format("%s %s: %s", PRIVATE_MESSAGE, name, privateMessage));
    }

    private ClientHandler getClientHandlerByName(String name) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getRecord().getName().toLowerCase().equals(name.toLowerCase())) {
                return ch;
            }
        }
        return null;
    }

    public void initSQLServer() throws SQLException, ClassNotFoundException {
        Class.forName(Server.JDBC_DRIVER);
        dataBaseConnection = DriverManager.getConnection(Server.DB_URL, Server.DB_USER, Server.DB_PASS);
    }
}