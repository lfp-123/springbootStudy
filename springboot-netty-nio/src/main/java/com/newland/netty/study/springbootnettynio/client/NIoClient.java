package com.newland.netty.study.springbootnettynio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author lfp
 * @Title: NIoClient
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/17 15:37
 */
public class NIoClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //连接服务端
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 10086);
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                Thread.sleep(1000);
                System.out.println("connect time client no blocking");
            }
        }
        String str="hello server -_-";
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer写入
        socketChannel.write(wrap);
        System.in.read();

    }
}
