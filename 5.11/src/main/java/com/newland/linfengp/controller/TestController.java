package com.newland.linfengp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author ${林锋鹏}
 * @Title: TestController
 * @ProjectName springboot_foz
 * @Description: TODO
 * @date 2020/5/11 15:43
 */

public class TestController {

    public static void main(String[] args) throws InterruptedException {
        TicketWindow ticketWindow = new TicketWindow(2000);
        ArrayList<Thread> list = new ArrayList<Thread>();
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                int sell = ticketWindow.sell(getRandom());
                sellCount.add(sell);
        }));
        }
        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            thread.join();
        }
        System.out.println("剩余票数： "+ticketWindow.getCount());
        System.out.println("卖出总票数： "+sellCount.stream().mapToInt(c -> c).sum());
    }
    //static Random random = new Random();
    public static int getRandom(){
        Random random = new Random();
        int i = random.nextInt(5)+1;
        return i;
    }
}
