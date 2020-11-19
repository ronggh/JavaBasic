package cn.alan.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThreadTest {
    public static void main(String[] args) {
        // 方式一
        MyThread myThread = new MyThread();
        myThread.start();

        // 方式二
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        // 方式三
        // 创建MyCallable对象
        Callable<Integer> myCallable = new MyCallable();
        // 使用FutureTask来包装MyCallable对象
        FutureTask<Integer> ft = new FutureTask<>(myCallable);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                // FutureTask对象作为Thread对象的target创建新的线程
                Thread subThread = new Thread(ft);
                subThread.start();
            }
        }

        System.out.println("主线程for循环执行完毕...");

        try {
            // 取得新创建的新线程中的call()方法返回的结果
            int sum = ft.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
