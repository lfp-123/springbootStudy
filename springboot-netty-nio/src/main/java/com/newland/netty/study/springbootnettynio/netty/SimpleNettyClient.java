package com.newland.netty.study.springbootnettynio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.util.CharsetUtil;

import java.util.Scanner;


/**
 * @author lfp
 * @Title: SimpleNettyClient
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/25 11:18
 */
public class SimpleNettyClient  {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        //创建启动对象
        Bootstrap bootstrap = new Bootstrap();

        //设置相关参数
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast("encoder",new ProtobufEncoder());
                        channel.pipeline().addLast(new NettyClientHandler());     //加入自己的处理器
                    }
                });
        //启动客户端连接服务器端
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1234).sync();
        //给关闭通道增加连接进行监听
        System.out.println("========"+channelFuture.channel().localAddress()+"==========");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            channelFuture.channel().write(Unpooled.copiedBuffer(s, CharsetUtil.UTF_8)); //写到缓存区，
            channelFuture.channel().flush(); //刷新至管道
        }
        channelFuture.channel().closeFuture().sync(); //非阻塞
        eventExecutors.shutdownGracefully();
    }
}
