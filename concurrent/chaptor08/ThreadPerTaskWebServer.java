package com.project.jvm.concurrent.chaptor08;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true) {
            final Socket connection = serverSocket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handlerRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }

    private static void handlerRequest(Socket connection) {

    }
}
