package com.newland.netty.study.springbootnettynio.dubbo.producer.handler;

import com.newland.netty.study.springbootnettynio.dubbo.producer.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ${林锋鹏}
 * @Title: NettyServerHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/8 11:10
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息，并返回消息
        System.out.println("msg: "+msg);
        //客户端在调用服务器的时候必须满足服务端的协议，才能远程调用
        if(msg.toString().startsWith("buildYourDream#")){
            String hello = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(hello);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
