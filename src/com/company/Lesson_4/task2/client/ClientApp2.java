package com.company.Lesson_4.task2.client;

import com.company.Lesson_4.task2.server.Server;

import java.io.IOException;

public class ClientApp2 {
    public static void main(String[] args) {
        try {
            new Client(Server.PORT).start("l2","p2");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}