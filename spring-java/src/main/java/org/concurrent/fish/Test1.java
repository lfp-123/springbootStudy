package org.concurrent.fish;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/10 10:54
 */
public class Test1 {
    private boolean pA = true;
    private boolean pB = false;
    private boolean pC = false;

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    test1.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    test1.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    test1.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public  void printB() throws InterruptedException {
        synchronized (this){
            while (!pB){
                this.wait();
            }
            System.out.print("B");
            this.pA =false;
            this.pC = true;
            this.pB = false;
            this.notifyAll();
        }
    }
    public  void printA() throws InterruptedException {
        synchronized (this){
            while (!pA){
                this.wait();
            }
            System.out.print("A");
            this.pA =false;
            this.pC = false;
            this.pB = true;
            this.notifyAll();
        }
    }
    public  void printC() throws InterruptedException {
        synchronized (this){
            while (!pC){
                this.wait();
            }
            System.out.print("C");
            this.pA =true;
            this.pC = false;
            this.pB = false;
            this.notifyAll();
        }
    }
}
