package com.project.jvm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args!=null && args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order to server succeed.");
            String resp = in.readLine();
            System.out.println("Now is :"+resp);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in!=null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null) {
                out.close();
                out = null;
            }
            if (socket!=null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
