package com.newland.netty.study.springbootnettynio.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import javax.jws.Oneway;

/**
 * @author lfp
 * @Title: GroupClientChatHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/7 14:00
 */
public class GroupClientChatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public  void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("消息："+byteBuf.toString(CharsetUtil.UTF_8));
    }

    //当通道就绪完成就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client "+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server: ", CharsetUtil.UTF_8));

    }
}
