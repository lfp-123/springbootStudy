package com.newland.netty.study.springbootnettynio.dubbo.product.handler;


import com.newland.netty.study.springbootnettynio.dubbo.product.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ${林锋鹏}
 * @Title: NettyClient
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/15 15:00
 */
public class NettyClient {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static NettyClientHandler client ;

    private static void initClient() throws InterruptedException {
        client = new NettyClientHandler();
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(client);
                    }
                });
        bootstrap.connect("127.0.0.1",7000).sync();
    }

    public Object getBean( final  Class<?> tClass,final String priName){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[]{tClass},((proxy, method, args) -> {
            if(client == null){
                initClient();
            }
            client.setPara(priName+args[0]);
            return executorService.submit(client).get();
        }));
    }
}