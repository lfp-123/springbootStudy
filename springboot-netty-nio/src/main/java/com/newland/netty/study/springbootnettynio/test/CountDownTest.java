package com.newland.netty.study.springbootnettynio.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author lfp
 * @Title: CountDownTest
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/31 14:17
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId()+ " 子线程打印");
                    countDownLatch.countDown();
                }
            }).start();
        }
        System.out.println("主线程完成-----");
        countDownLatch.await();
        System.out.println("子线程全部完成----");
    }
}
