package com.newland.netty.study.springbootnettynio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.server.ExportException;
import java.util.Arrays;

public class SpringbootNettyNioApplication {

    public static void main(String[] args)  {
        try (ServerSocketChannel open = ServerSocketChannel.open()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

            //绑定端口到Socket ，并启动
            open.socket().bind(inetSocketAddress);


            //创建buffer 数组
            ByteBuffer[] byteBuffers = new ByteBuffer[2];
            byteBuffers[0] = ByteBuffer.allocate(5);
            byteBuffers[1] = ByteBuffer.allocate(3);

            //等待客户端连接
            SocketChannel accept = open.accept();
            int messagelenght = 8;
            while (true){
                int buteRead =0;
                while (buteRead < messagelenght){
                    long l = accept.read(byteBuffers);
                    buteRead += l;
                    System.out.println("byteRead+"+buteRead);
                    Arrays.asList(byteBuffers).stream().map(buffer -> "position=" + buffer.position() +",limit="+ buffer.limit()).forEach(System.out::println
                    );
                }
                Arrays.asList(byteBuffers).forEach(buffer ->buffer.flip());

                long byteWrite =0;
                while (byteWrite <messagelenght){
                    long l = accept.write(byteBuffers);
                    byteWrite += 1;
                }
                //复位
                Arrays.asList(byteBuffers).forEach(buffer-> {
                    buffer.clear();
                });
                System.out.println("byteRead:="+ buteRead+" byteWrite:="+byteWrite+" message length:"+ messagelenght);
            }
            //将所有的buffer 进行flip

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
