package com.newland.configure.center.springboottestapp.test;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @author lfp
 * @Title: TestSystemApp
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/29 15:27
 */
public class TestSystemApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("path: "+System.getProperty("user.dir"));
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("子线程执行");
                        sleep(1000);
                        countDownLatch.countDown();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        long count = countDownLatch.getCount();
        System.out.println(count);
        countDownLatch.await();
        System.out.println("主线程完成");
        countDownLatch.countDown();
        long count1 = countDownLatch.getCount();
        System.out.println(count1);
    }
    public void testA(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.setDaemon(true);

    }
}
