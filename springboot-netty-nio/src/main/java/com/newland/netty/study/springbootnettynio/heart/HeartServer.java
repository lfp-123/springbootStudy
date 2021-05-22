package com.newland.netty.study.springbootnettynio.heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @Title: HeartServer
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/7 16:49
 */
public class HeartServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossEvent = new NioEventLoopGroup();
        NioEventLoopGroup workEvent = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEvent,workEvent)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //Netty提供的日志处理器
                        //提供处理空闲状态的处理器
                        //提示多长时间没有读，就会发送心跳检测包
                        //long readerIdleTime 多长时间没有读, long writerIdleTime多长时间没有写, long allIdleTime多长时间没有读写, TimeUnit unit
                        //当channel没有执行
                        pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                        pipeline.addLast(new HeartServerHandler());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
        bossEvent.shutdownGracefully();
        workEvent.shutdownGracefully();
    }


}
