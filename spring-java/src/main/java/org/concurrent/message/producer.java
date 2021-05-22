package org.concurrent.message;


/**
 * @author ${林锋鹏}
 * @Title: producer
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/9 10:22
 */
public class producer {

    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id =i;
           new Thread(() -> messageQueue.putMessage(new Message(id,"值 "+id) {
           }),"生产者"+i).start();
        }

        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = messageQueue.getMessage();
                System.out.println(message);
            }
        },"消费者 ").start();
    }


}
