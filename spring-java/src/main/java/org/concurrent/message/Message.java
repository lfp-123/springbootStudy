package org.concurrent.message;

/**
 * @author ${林锋鹏}
 * @Title: Message
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/9 10:45
 */
public class Message {

    private int id;
    private String message;

    public Message(int id,String message){
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
