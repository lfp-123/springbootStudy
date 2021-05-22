package com.newland.netty.study.springbootnettynio.netty;

import com.newland.netty.study.springbootnettynio.protobuf.StudentPojo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;

/**
 * @author lfp
 * @Title: NettyServerHandler
 * @ProjectName Springboot
 * @Description: 自定义个handle，需要继承netty 规定好某个handlerAdapter
 * @date 2020/12/25 10:57
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static HashMap<String,Channel> maps = new HashMap<>();
    private static HashMap<User,Channel> userMaps = new HashMap<>();


  // channelHandlerContext 上下文对象，含有管道和通道，可以读取客户端发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        StudentPojo.Student student = (StudentPojo.Student) msg;

        //将msg转成byte
        ByteBuf buf = (ByteBuf) msg;
        String message = buf.toString(CharsetUtil.UTF_8);
        for (Channel channel : channels) {
            if(channel!=ctx.channel()){
                channel.write(Unpooled.copiedBuffer(student.getId()+" ,"+student.getName(),CharsetUtil.UTF_8));
                channel.write(Unpooled.copiedBuffer("[来自客户端：]"+ctx.channel().remoteAddress()+message,CharsetUtil.UTF_8));
                channel.flush();
            }else {
                channel.write(Unpooled.copiedBuffer("[来自自己：]"+ctx.channel().remoteAddress()+message,CharsetUtil.UTF_8));
                channel.flush();
            }

        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[客户端] "+ctx.channel().remoteAddress()+" 下线了~");
        System.out.println("当前用户在线数量："+channels.size());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        for (Channel channel1 : channels) {
            channel1.write(Unpooled.copiedBuffer("[客户端]" + ctx.channel().remoteAddress() + " 加入聊天 ", CharsetUtil.UTF_8));
            channel1.flush();
        }
        channels.add(ctx.channel());
    }

    //当通道就绪时就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("[客户端] "+ctx.channel().remoteAddress()+" 上线了~");
    }
}

