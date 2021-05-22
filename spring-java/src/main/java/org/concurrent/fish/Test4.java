package org.concurrent.fish;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ${林锋鹏}
 * @Title: Test4
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/10 14:39
 */
public class Test4 {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        Test4 a = new Test4();
       t1 =   new Thread(() ->{ for (int i = 0; i < 3; i++) { a.print("A",t2); }});
       t2 =  new Thread(() ->{for (int i = 0; i < 3; i++) { a.print("B",t3); } });
       t3 =   new Thread(() ->{for (int i = 0; i < 3; i++) {a.print("C",t1); } });

       t1.start();
       t2.start();
       t3.start();

       LockSupport.unpark(t1);


    }

    public void print(String str,Thread next){
        LockSupport.park();
        System.out.print(str);
        LockSupport.unpark(next);
    }
}
