package com.newland.netty.study.springbootnettynio.netty;


import com.newland.netty.study.springbootnettynio.protobuf.StudentPojo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @author lfp
 * @Title: SimpleNetty
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/25 10:30
 */
public class SimpleNettyServer {
    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组，boss负责连接请求，真正的和客户端业务处理，会交给workerGroup完成。
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        //创建启动配置，配置参数
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) //使用 作为服务端的通道
                    .option(ChannelOption.SO_BACKLOG,128)  //设置线程队列得到的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder",new ProtobufDecoder(StudentPojo.Student.getDefaultInstance())); //指定对哪种对象进行解码
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给我们的workerGroup 的EventLoop 对应的管道设置处理器
            System.out.println("服务器准备就绪-----------------------");

            //绑定一个端口并且同步，生成一个channelFuture对象
            ChannelFuture sync = serverBootstrap.bind(1234).sync();
            //对关闭通道进行监听
            sync.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
