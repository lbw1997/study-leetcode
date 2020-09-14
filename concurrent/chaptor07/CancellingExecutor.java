package com.project.jvm.concurrent.chaptor07;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

public class CancellingExecutor extends ThreadPoolExecutor {
    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {

        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        }else {
            return super.newTaskFor(callable);
        }
    }
}
interface CancellableTask<T> extends Callable<T> {

    void cancel();
    RunnableFuture<T> newTask();
}
class SocketUsingTask<T> implements CancellableTask<T>{

    private Socket socket;

    SocketUsingTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void cancel() {
        if (socket!=null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
          public boolean cancel(boolean mayInterruptIfRunning) {
              try {
                  SocketUsingTask.this.cancel();
              } finally {
                  return super.cancel(mayInterruptIfRunning);
              }
          }
        };
    }

    @Override
    public T call() throws Exception {
        return null;
    }
}