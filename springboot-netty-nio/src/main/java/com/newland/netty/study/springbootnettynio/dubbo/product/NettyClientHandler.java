package com.newland.netty.study.springbootnettynio.dubbo.product;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author ${林锋鹏}
 * @Title: NettyClientHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/8 11:24
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter  implements Callable {

    private ChannelHandlerContext channelHandlerContext; //上下文
    private String result; //返回的结果
    private String para; //客户端调用方法时，传入的参数


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
    }

    @Override
    public synchronized void  channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify(); //唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

     @Override
    public synchronized Object call() throws Exception{
        channelHandlerContext.writeAndFlush(para);
        wait();
        return result;
    }

    public void setPara(String para){
        this.para = para;
    }

}
