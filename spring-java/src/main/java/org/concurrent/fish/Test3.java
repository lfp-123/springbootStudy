package org.concurrent.fish;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/10 14:21
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(() -> {awaitSignal.print("A",a,b);}).start();
        new Thread(() -> {awaitSignal.print("B",b,c);}).start();
        new Thread(() -> {awaitSignal.print("C",c,a);}).start();

        //由主线程发起开始顺序的命令
        System.out.println("3s后主线程开始唤醒A");
        Thread.sleep(3000);
        awaitSignal.lock();
        System.out.println("开始");
        a.signal();
        awaitSignal.unlock();

    }
    static class AwaitSignal extends ReentrantLock{
        private int loopNumber;

        public AwaitSignal(int loopNumber){
            this.loopNumber = loopNumber;
        }

        public void print (String str,Condition me,Condition next){
            for (int i = 0; i < loopNumber; i++) {
                lock();
                try {
                    me.await();
                    System.out.print(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    unlock();
                }
            }
        }
    }
}
