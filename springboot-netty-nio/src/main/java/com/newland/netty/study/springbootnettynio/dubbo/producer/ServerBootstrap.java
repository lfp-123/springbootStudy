package com.newland.netty.study.springbootnettynio.dubbo.producer;


import com.newland.netty.study.springbootnettynio.dubbo.producer.handler.NettyServerHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author ${林锋鹏}
 * @Title: ServerBootstrap
 * @ProjectName Springboot
 * @Description: 启动服务端
 * @date 2021/4/8 10:57
 */
public class ServerBootstrap  {
    public static void main(String[] args) {
        startServer("127.0.0.1",7000);
    }
    public static void startServer(String hostName,int port){
        startServer0(hostName,port);
    }
    public static void startServer0(String hostName,int host){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            io.netty.bootstrap.ServerBootstrap serverBootstrap = new io.netty.bootstrap.ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture sync = serverBootstrap.bind(hostName, host).sync(); //异步阻塞
            System.out.println("=======================>服务提供方开始提供服务");
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
