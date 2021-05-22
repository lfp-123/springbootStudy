package com.newland.netty.study.springbootnettynio.heart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.IdentityHashMap;

/**
 * @author
 * @Title: HeartServerHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/7 18:40
 */
public class HeartServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *上下文
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String evectType = null;
            switch (event.state()){
                case READER_IDLE:
                    evectType = "读空闲";
                    break;
                case ALL_IDLE:
                    evectType = "读写空闲";break;
                case WRITER_IDLE:
                    evectType = "写空闲";
                    break;
                default: break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超时时间--"+ evectType);
            System.out.println("服务器做相应处理");
        }
    }
}
