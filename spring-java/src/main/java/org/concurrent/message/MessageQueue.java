package org.concurrent.message;



import java.util.LinkedList;

/**
 * @author ${林锋鹏}
 * @Title: MessageQueue
 * @ProjectName Springboot
 * @Description: 消息队列类
 * @date 2021/3/9 10:27
 */
public class MessageQueue {
    private LinkedList<Message> list = new LinkedList<>();
    private int capcity;

    public MessageQueue(int capcity){
        this.capcity = capcity;

    }

    public Message getMessage(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    System.out.println("队列是空，等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        Message message = list.removeFirst();
        System.out.println(Thread.currentThread().getId()+" " +Thread.currentThread().getName()+" 已消费信息 { }"+message.toString());
        list.notifyAll();
            return message;
        }

    }


    public void putMessage(Message message){
        synchronized (list){
            while (list.size() == capcity){
                try {
                    System.out.println(Thread.currentThread().getId()+" 队列已满，请等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            System.out.println(Thread.currentThread().getId()+" " +Thread.currentThread().getName()+" 已生产信息 { }"+message.toString());
            list.notifyAll();
        }
    }
}
