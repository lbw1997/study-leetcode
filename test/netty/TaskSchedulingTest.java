package com.project.jvm.test.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskSchedulingTest {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void main(String[] args) {

    }

    public void schedule() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        service.schedule(
                () -> System.out.println("20 seconds later"), 20, TimeUnit.SECONDS
        );
        service.shutdown();
    }

    public void scheduleViaEventLoop() {
        CHANNEL_FROM_SOMEWHERE.eventLoop().schedule(
                () -> System.out.println("10 seconds later"),10,TimeUnit.SECONDS);
    }

    public void scheduleFixedViaEventLoop() {
        CHANNEL_FROM_SOMEWHERE.eventLoop().scheduleAtFixedRate(
                () -> System.out.println("Run every 10 seconds"),10,10,TimeUnit.SECONDS);
    }
}
