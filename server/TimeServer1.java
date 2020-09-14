package com.project.jvm.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TimeServer1 {

    public static void main(String[] args) {
        int port = 8080;
        if (args!=null && args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("THE TIME SERVER IS START:"+port);
            TimeServerHandleExecutePool singleExecutor = new TimeServerHandleExecutePool(50,10000);
            while (true) {
                socket = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandle(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null) {
                System.out.println("THE TIME SERVER IS CLOSE");
                try {
                    serverSocket.close();
                    serverSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class TimeServerHandleExecutePool {

    private ExecutorService executorService;

    TimeServerHandleExecutePool(int maxPoolSize, int queueSize) {

        executorService = new ThreadPoolExecutor(Runtime.getRuntime()
                        .availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(queueSize));

    }
    public void execute(java.lang.Runnable task) {
        executorService.execute(task);
    }
}
