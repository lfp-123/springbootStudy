package com.newland.netty.study.springbootnettynio.server;

import sun.nio.ByteBuffered;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lfp
 * @Title: NioServer
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/11 11:32
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        //创建    ServerChannel -ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //得到一个Selector 对象
            Selector selector = Selector.open();
             //绑定地址
            serverSocketChannel.socket().bind(new InetSocketAddress(10086));
             //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //把serverSockerChannel 注册到selector 关心事件 为
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //循环监听等待客户端连接
            while (true){
                if(selector.select(1000) == 0){ //没有事件发生
                    System.out.println("server wait 1s ,no client connection");
                    continue;
                }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //遍历
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    //根据key发生的事件来处理对应
                    if(key.isAcceptable()){
                        //该客户端生成一个socketChannel
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        System.out.println("connection success "+socketChannel.hashCode());
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }
                    //发生读取事件
                    if(key.isReadable()){
                        //通过key反向获取对应的channel
                        SocketChannel channel = (SocketChannel)key.channel();
                        //获取该channel关联的buffer
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        channel.read(buffer);
                        System.out.println("from client "+new String(buffer.array()));

                    }
                    //手动删除
                    iterator.remove();
                }
            }

    }
}
