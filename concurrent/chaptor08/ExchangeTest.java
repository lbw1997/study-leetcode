package com.project.jvm.concurrent.chaptor08;


import java.util.concurrent.Exchanger;

/**
 * 在A、B线程中交换任务，A线程作为消费者，B线程作为生产者
 */
public class ExchangeTest {

    private static String msg;
    private static Exchanger<MachineComponent[]> exchanger = new Exchanger<>();
    public static MachineComponent[] car = new MachineComponent[10];

    /**
     * 卸货
     */
    static class A implements Runnable {

        @Override
        public void run() {
            while(true) {
                if (car.length==10) {
                    for (MachineComponent component : car) {
                        System.out.println(component);
                    }
                    car = new MachineComponent[10];
                    try {
                        exchanger.exchange(car);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 零件加工厂
     */
    static class B implements Runnable {

        @Override
        public void run() {
            while (true) {
                for (int i = 0;i<10;i++) {
                    car[i] = new MachineComponent("component"+i,i);
                }
                try {
                    Thread.sleep(2000);
                    System.out.println("component complete");
                    exchanger.exchange(car);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MachineComponent {
        private String name;
        private int num;

        public MachineComponent(String name, int num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public String toString() {
            return "MachineComponent{" +
                    "name='" + name + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        new Thread(new B()).start();
        new Thread(new A()).start();

    }

}
