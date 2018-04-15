package rocks.cleanstone.net.netty.pipeline.receive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.packet.ReceivePacket;

public class PacketHandler extends ChannelInboundHandlerAdapter {

    private final NettyNetworking nettyNetworking;

    public PacketHandler(NettyNetworking nettyNetworking) {
        this.nettyNetworking = nettyNetworking;
    }

    @Override

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ReceivePacket packet = (ReceivePacket) msg;
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        nettyNetworking.callPacketListeners(packet, connection);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}