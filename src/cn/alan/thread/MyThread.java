package cn.alan.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("ThreadName === " + Thread.currentThread().getName());
    }
}
