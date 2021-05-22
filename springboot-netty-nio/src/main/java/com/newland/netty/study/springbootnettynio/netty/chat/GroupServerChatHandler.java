package com.newland.netty.study.springbootnettynio.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lfp
 * @Title: GroupServerChatHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/7 11:03
 */
public class GroupServerChatHandler extends ChannelInboundHandlerAdapter {


   //定义一个channel 组管理
    //全局事件执行器，是一个单例
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf=   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 将当前的channel 加入
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //在加入之前，将该推送给其他的客户端
        //channelGroup 底层会自动将所有管理的channel遍历，并发送消息
        channels.writeAndFlush(sdf.format(new Date())+" [客户端] "+channel.remoteAddress() +"加入聊天\n");
        channels.add(channel);
    }

    /**
     * 表示channel处于活动状态，提示上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(sdf.format(new Date())+ctx.channel().remoteAddress() + " 上线了~ ");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(sdf.format(new Date())+ctx.channel().remoteAddress()+" 离线了~");
    }

    /**
     * 表示断开连接,将当前客户端离线的信息推送给当前在线的其他客户端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush(sdf.format(new Date())+"[客户端]" + channel.remoteAddress() + " 下线了~ ");
        System.out.println(sdf.format(new Date())+"当前在线客户端数量："+channels.size());
    }

    //客户端发送消息，转发给其他客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx ="+ctx.read());
        //将msg转成byte
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
        Channel channel = ctx.channel();
        for (Channel channel1 : channels) {
            if(channel1!=channel){ //排除自己
                channel1.writeAndFlush(sdf.format(new Date())+"[客户]"+channel.remoteAddress() +" 发送了消息"+msg+"\n");
            }else {
                channel.writeAndFlush(sdf.format(new Date())+"[自己]"+channel.remoteAddress() +" 发送了消息"+msg+"\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
