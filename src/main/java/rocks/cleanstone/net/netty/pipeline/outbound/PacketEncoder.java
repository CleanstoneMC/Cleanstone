package rocks.cleanstone.net.netty.pipeline.outbound;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.packet.OutboundPacket;

public class PacketEncoder extends MessageToMessageEncoder<OutboundPacket> {

    private final NettyNetworking nettyNetworking;

    public PacketEncoder(NettyNetworking nettyNetworking) {
        this.nettyNetworking = nettyNetworking;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, OutboundPacket in, List<Object> out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        nettyNetworking.callPacketListeners(in, connection);

        ctx.channel().attr(AttributeKey.<Integer>valueOf("outPacketId")).set(
                nettyNetworking.getProtocol().translateOutboundPacketId(
                        in.getType().getTypeId(), connection.getClientProtocolLayer()));

        ByteBuf data = nettyNetworking.getProtocol().getPacketCodec(in.getClass(), connection
                .getClientProtocolLayer())
                .encode(ctx.alloc().buffer(), in);
        out.add(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}