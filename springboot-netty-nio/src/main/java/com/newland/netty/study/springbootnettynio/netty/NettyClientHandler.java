package com.newland.netty.study.springbootnettynio.netty;

        import com.newland.netty.study.springbootnettynio.protobuf.StudentPojo;
        import io.netty.buffer.ByteBuf;
        import io.netty.buffer.Unpooled;
        import io.netty.channel.ChannelHandlerContext;
        import io.netty.channel.ChannelInboundHandlerAdapter;
        import io.netty.util.CharsetUtil;

/**
 * @author lfp
 * @Title: NettyClientHandler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/25 11:30
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    //当通道有读取事件时，会触发，基于事件驱动
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StudentPojo.Student student = StudentPojo.Student.newBuilder()
                .setId(1001)
                .setName("豹子头林冲")
                .build();
        ctx.writeAndFlush(student);
    }
}
