package com.project.jvm.concurrent.chaptor07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorCompletionTest {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        ExecutorCompletionTest ect = new ExecutorCompletionTest();
        ExecutorCompletionService completionService = new ExecutorCompletionService(ect.executorService);
//        List<MyTask> list = new ArrayList<>();
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0;i<100;i++) {
            //list.add(new MyTask());
            Future<Integer> future = completionService.submit(new MyTask());
            list.add(future);
        }
        /*try {
            List<Future<Integer>> futures = ect.executorService.invokeAll(list);
            for (Future<Integer> future:futures) {
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    }catch (ExecutionException e) {
                        System.out.println(e.getCause()+"!"+e.getMessage());
                    }
                }
                if (future.isCancelled()) {
                    System.out.println("被取消了");
                }
            }
            ect.executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        list.forEach(future -> {
            Integer integer = null;
            if (future.isDone()) {
                try {
                    integer = future.get();
                    if (integer>30) {
                        System.out.println(integer);
                    }
                    future.cancel(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    System.out.println(e.getCause());
                }
            }
        });
        System.out.println("=================");

        list.forEach(integerFuture -> {
            try {
                if (!integerFuture.isCancelled())
                System.out.println(integerFuture.get());
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            } catch (ExecutionException e) {
                System.out.println(e.getCause());
            }
        });
        ect.executorService.shutdown();
    }

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static class MyTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int i = atomicInteger.incrementAndGet();
            if (i>90) {
                throw new Exception("不得大于90！");
            }
            return i;
        }
    }
}