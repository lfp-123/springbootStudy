package com.newland.boss.springbootactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * @author ${linfengpeng}
 * @Title: Producer
 * @ProjectName hdfs0609
 * @Description: TODO
 * @date 2020/9/14 16:38
 */
@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue myqueue;

    public void producer(String queue){

        jmsTemplate.convertAndSend(queue,"a test springboot message");
    }

    public String consumer(String queuue){

      //  TextMessage receive =(TextMessage) jmsTemplate.receive(queuue);\
        jmsTemplate.setReceiveTimeout(1000*3);
        String text = (String)jmsTemplate.receiveAndConvert(queuue);
       return text;
    }
}
