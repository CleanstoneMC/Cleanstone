package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.packet.OutboundPacket;

public class OutboundPacketHandler extends ChannelOutboundHandlerAdapter {

    private final NettyNetworking nettyNetworking;

    public OutboundPacketHandler(NettyNetworking nettyNetworking) {
        this.nettyNetworking = nettyNetworking;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        OutboundPacket packet = (OutboundPacket) msg;
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        nettyNetworking.callPacketListeners(packet, connection);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}