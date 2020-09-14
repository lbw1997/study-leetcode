package com.project.jvm.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


/**
 * 管道流PipedInputStream和PipedOutputStream
 * 支持在线程之间进行通信，
 * PipedOutputStream可以进行写操作，
 * PipedInputStream支持读写操作，
 * 每次读取只支持1024个字节
 */
public class PipedStreamTest {

    public static void main(String[] args) {

        Receiver receiver = new Receiver();
        Sender sender = new Sender();
        PipedInputStream in = receiver.getIn();
        PipedOutputStream os = sender.getOs();

        new Thread(receiver).start();
        new Thread(sender).start();
        try {
            os.connect(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Receiver implements Runnable {

    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getIn() {
        return in;
    }

    @Override
    public void run() {
        getMessageContinue();
//        getMessageOnce();
    }

    private void getMessageContinue() {

        byte[] bytes = new byte[1024];
        int total = 0;
        try {
            while(true) {
                int read = in.read(bytes);
                total+= read;
                System.out.println(new String(bytes,0,read));
                if (total>1024) {
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessageOnce() {
        byte[] bytes = new byte[2048];
        try {
            int read = in.read(bytes);
            System.out.println(new String(bytes,0,read));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
class Sender implements Runnable {

    private PipedOutputStream os = new PipedOutputStream();

    @Override
    public void run() {
        sendMessageOnce();
//        sendShortMessage();
    }

    public PipedOutputStream getOs() {
        return os;
    }

    private void sendMessageOnce() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<102;i++) {
            sb.append("0123456789");
        }
        sb.append("abcdefghijklmnopqrstuvwxyz");
        String str = sb.toString();
        try {
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendShortMessage() {
        String str = "this is a message";
        try {
            os.write(str.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}