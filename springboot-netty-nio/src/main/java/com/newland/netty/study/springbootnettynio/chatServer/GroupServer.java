package com.newland.netty.study.springbootnettynio.chatServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author lfp
 * @Title: GroupServer
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/24 9:17
 */
public class GroupServer {
    //定义属性
    private final static int port = 10086;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;


    public GroupServer(){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            //设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //注册到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GroupServer groupServer = new GroupServer();
        groupServer.listner();
    }
    //监听方法
    public void listner(){
        try {
            while (true) {
                int select = selector.select();
                if(select >0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isAcceptable()){
                            SocketChannel accept = serverSocketChannel.accept();
                            //需要设置为非阻塞
                            accept.configureBlocking(false);
                            //将该客户端 注册到selector
                            accept.register(selector,SelectionKey.OP_READ);
                            System.out.println(accept.getRemoteAddress()+" 上线！！！");
                        }
                        if(selectionKey.isReadable()){  //通道是可读状态
                            //处理读
                            read(selectionKey);
                        }
                        //当前的key 删除，防止重复
                        iterator.remove();
                    }
                }else {
                    System.out.println("wait ==========>");
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //读取消息
    public void read(SelectionKey selectionKey){
        //定义一个SocketChannel
        SocketChannel socketChannel=null;
        try {
            socketChannel =(SocketChannel) selectionKey.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取数据放到allocate
            int read = socketChannel.read(buffer);
            //处理
            if (read>0){
                byte[] array = buffer.array();
                String msg = new String(array);
                //输出
                System.out.println(" from client "+ msg);
                //向其他客户端转发
                sendInfoToOtherClient(msg,socketChannel);
            }
        } catch (Exception e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() +" outline....");
                //离线处理
                selectionKey.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        //取到关联的channel
    }

    //转发消息
    public void sendInfoToOtherClient(String msg,SocketChannel self) throws IOException {
        System.out.println("server msg rediect....");
        for (SelectionKey key : selector.keys()) {
            SelectableChannel channel = key.channel();
            if(self != channel && channel instanceof SocketChannel){
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                //将消息转发给其他客户端
                ((SocketChannel) channel).write(wrap);
            }
        }
    }
}
