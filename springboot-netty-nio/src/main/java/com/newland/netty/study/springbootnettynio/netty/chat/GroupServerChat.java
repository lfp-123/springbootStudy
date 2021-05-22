package com.newland.netty.study.springbootnettynio.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author lfp
 * @Title: GroupServerChat
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/7 10:41
 */
public class GroupServerChat {
    private int port;

    public GroupServerChat(int port){
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
    try {
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //获取到pipline
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //加入一个解码器
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringDecoder());
                        pipeline.addLast(new GroupServerChatHandler());
                    }
                });
        System.out.println("netty 服务器启动");
        ChannelFuture sync = serverBootstrap.bind(port).sync(); //同步
        sync.channel().closeFuture().sync();
    }finally {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    }

    public static void main(String[] args) throws InterruptedException {
        new GroupServerChat(7000).run();
    }
}
