package com.project.jvm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基础IO服务器端模型
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args!=null && args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:"+port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandle(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server !=null) {
                System.out.println("The time server is close");
                server.close();
                server = null;
            }
        }
    }
}
class TimeServerHandle implements Runnable {

    private Socket socket;

    TimeServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);

            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("The time server receive order:"+body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
                        new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (out!=null) {
                out.close();
                out = null;
            }
            if (this.socket!=null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.socket = null;
            }
            e.printStackTrace();
        }
    }
}
