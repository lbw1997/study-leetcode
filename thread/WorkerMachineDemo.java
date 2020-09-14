package com.project.jvm.thread;

import java.util.concurrent.Semaphore;

public class WorkerMachineDemo {

    private static Semaphore semaphore = new Semaphore(3);
    private int workerNum = 8;

    public static void main(String[] args) {
        WorkerMachineDemo w =  new WorkerMachineDemo();
        for (int i = 0;i<w.workerNum;i++) {
            new Thread(new Work(semaphore),"工人"+i).start();
        }
    }
}

class Work implements Runnable{

    private Semaphore semaphore;

    Work(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            if (getMachine()) {
                Thread.sleep(1000);
                System.out.println(name + "工作完毕，准备释放机器");
                semaphore.release();            //只要拿到release()许可，才可以进行工作
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getMachine() {
        try {
            semaphore.acquire();
            String name = Thread.currentThread().getName();
            System.out.println(name + "获取到机器，开始工作");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}

