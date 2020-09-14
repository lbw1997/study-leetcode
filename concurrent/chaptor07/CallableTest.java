package com.project.jvm.concurrent.chaptor07;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<MyCallable.Student> submit = executorService.submit(new MyCallable());
        try {
            MyCallable.Student stu = submit.get();
            System.out.println(stu);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }
    }
}

class MyCallable implements Callable {

    @Override
    public Student call() throws Exception {
        return new Student("abkm", 1);
    }
    static class Student{
        private String name;
        private int id;

        Student(String name, int id) throws Exception {
            this.name = name;
            this.id = id;
            if ("abc".equals(name)) {
                throw new Exception();
            }
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
