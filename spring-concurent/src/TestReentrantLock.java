import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @author ${林锋鹏}
 * @Title: TestReentrantLock
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/2/8 9:52
 */
public class TestReentrantLock {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    static volatile boolean hasCigrette = false;
    static volatile boolean hasBreakfast = false;
    static Condition waitCigaretteQueue = reentrantLock.newCondition();
    static Condition waitBreakfastQueue = reentrantLock.newCondition();
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        TestReentrantLock testReentrantLock = new TestReentrantLock();
        testReentrantLock.call();

    }
    public static void sendBreakfast(){
        reentrantLock.lock();
        try {
        System.out.println("送烟来了");
        hasCigrette = true;
        waitCigaretteQueue.signal();
    } finally {
        reentrantLock.unlock();
    }
}
    public static void sendCigarette(){
        reentrantLock.lock();
        try {
            System.out.println("送早餐来了");
            hasBreakfast = true;
            waitBreakfastQueue.signal();
        } finally {
            reentrantLock.unlock();
        }
    }
    public void call() throws ExecutionException, InterruptedException {
       ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(() -> "ok");
        String s = future.get();
        System.out.println(s);
        executorService.shutdown();
    }

    public void test() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    while (!hasCigrette){
                        try {
                            waitCigaretteQueue.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally {
                    reentrantLock.unlock();
                }
                System.out.println(Thread.currentThread().getId()+"等到了他的烟");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    while (!hasBreakfast){
                        try {
                            waitBreakfastQueue.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally {
                    reentrantLock.unlock();
                }
                System.out.println(Thread.currentThread().getId()+"等到了他的早餐");
            }
        }).start();

        sleep(1);
        sendBreakfast();
        sleep(1);
        sendCigarette();
    }
}
