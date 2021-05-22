package com.newland.netty.study.springbootnettynio.dubbo.customer;

import com.newland.netty.study.springbootnettynio.dubbo.product.handler.NettyClient;
import com.newland.netty.study.springbootnettynio.dubbo.service.HelloService;

/**
 * @author ${林锋鹏}
 * @Title: ClientBootstrap
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/15 15:17
 */
public class ClientBootstrap {
    public static final String providerName = "buildYourDream#";

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        //创建代理对象
        HelloService bean =(HelloService)nettyClient.getBean(HelloService.class, providerName);
        String res = bean.hello("你好 dubbo");
        System.out.println(res);
    }
}