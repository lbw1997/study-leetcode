package com.project.jvm.concurrent.chaptor07;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService {

    private final ExecutorService exec;
    private final Set<Runnable> taskCancelledAtShutDown = Collections.synchronizedSet(new HashSet<>());

    TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void execute(Runnable command) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    if (isShutdown()&&Thread.currentThread().isInterrupted()) {
                        taskCancelledAtShutDown.add(command);
                    }
                }
            }
        });
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isShutdown()) {
            throw new IllegalStateException();
        }
        return new ArrayList<Runnable>(taskCancelledAtShutDown);
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }
}
