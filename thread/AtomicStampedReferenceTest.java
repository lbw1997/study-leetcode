package com.project.jvm.thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    private static int initialStamp = 1;

    public static void main(String[] args) {
        AtomicStampedReferenceTest at = new AtomicStampedReferenceTest();
        User user = at.getUser("abkm",initialStamp);
        AtomicStampedReference<Object> asr = new AtomicStampedReference<>(user,initialStamp);

        new Thread(()-> {
            user.setID(2);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newStamp = asr.getStamp();
            if (asr.weakCompareAndSet(user, user, newStamp, newStamp++)) {
                System.out.println("----------");
                asr.set(user,newStamp);
                user.setID(newStamp);
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e)    {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    public User getUser(String name, int ID) {
        return new User(name,ID);
    }

    class User {
        User(String name, int ID) {
            this.ID = ID;
            this.name = name;
        }
        private String name;
        private int ID;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getID() {
            return ID;
        }
        public void setID(int ID) {
            this.ID = ID;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", ID=" + ID +
                    '}';
        }
    }
}

