package com.newland.netty.study.springbootnettynio.dubbo.producer;

import com.newland.netty.study.springbootnettynio.dubbo.service.HelloService;

/**
 * @author ${林锋鹏}
 * @Title: HelloServiceImpl
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/8 10:54
 */
public class HelloServiceImpl implements HelloService {


    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息 = "+msg);
        if(msg!=null){
            return "你好客户端， 我已经收到你的消息："+msg;
        }else {
            return "你好客户端，我已收到你的消息";
        }
    }
}
