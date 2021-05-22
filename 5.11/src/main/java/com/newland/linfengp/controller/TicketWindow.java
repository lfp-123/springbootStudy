package com.newland.linfengp.controller;

/**
 * @author ${林锋鹏}
 * @Title: TicketWindow
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/2/23 10:55
 */
public class TicketWindow  {
    private int  count;

    public TicketWindow(int count){
        this.count = count;
    }

    /**
     * 卖票数量
     */
    public synchronized int sell(int ammout){
       if(this.count>=ammout){
           this.count -= ammout;
           return ammout;
       }else {
           return 0;
       }
    }

    public int getCount() {
        return count;
    }
}
