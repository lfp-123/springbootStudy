package com.newland.netty.study.springbootnettynio.chatClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author lfp
 * @Title: GroupClient
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/24 10:00
 */
public class GroupClient {
    private final static String ip = "127.0.0.1";
    private final static int port =10086;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;


    public GroupClient(){
        try {
           selector = Selector.open();
           socketChannel =SocketChannel.open(new InetSocketAddress(ip,port));
           socketChannel.configureBlocking(false);
           socketChannel.register(selector, SelectionKey.OP_READ);
           socketChannel.configureBlocking(false);
           //得到userName
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("client"+username+" init over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //启动客户端
        GroupClient groupClient = new GroupClient();
        new Thread(() -> {
            while (true){
                groupClient.readServerInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String msg = scanner.nextLine();
            groupClient.sendServerInfo(msg);
        }
    }

    public void sendServerInfo(String msg){
        String info = username+" say : "+msg;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readServerInfo(){
        try {
            int select = selector.select();
            if(select >0){
                //有事件发生的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isReadable()){
                        SocketChannel channel =(SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg);
                    }
                    //需要移除当前key，防止重复操作。
                    iterator.remove();
                }
            }else {
                System.out.println("没有可用的通道============");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //发送数据给服务端

}
