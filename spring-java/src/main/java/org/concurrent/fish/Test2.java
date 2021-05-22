package org.concurrent.fish;

/**
 * @author ${林锋鹏}
 * @Title: Test2
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/10 13:46
 */
public class Test2 {
    private int flag = 1;
    public static void main(String[] args) {
        Test2 print = new Test2();
        new Thread(() -> {for (int i = 0; i < 3; i++) { print.printStr("A",1,2); } }).start();
        new Thread(() -> {for (int i = 0; i < 3; i++) { print.printStr("B",2,3); } }).start();
        new Thread(() -> {for (int i = 0; i < 3; i++) { print.printStr("C",3,1); } }).start();

    }

        public void printStr(String str,int flag,int nextflag)  {
            synchronized (this){
                while (true) {
                    if (this.flag == flag) {
                        System.out.print(str);
                        this.flag = nextflag;
                        notifyAll();
                        break;
                    } else {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

}
