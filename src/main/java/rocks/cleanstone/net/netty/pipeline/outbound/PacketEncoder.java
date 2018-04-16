package rocks.cleanstone.net.netty.pipeline.outbound;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PacketEncoder extends MessageToMessageEncoder<OutboundPacket> {

    private final Protocol protocol;

    public PacketEncoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, OutboundPacket in, List<Object> out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

        int packetID = protocol.translateOutboundPacketID(
                in.getType().getTypeId(), connection.getClientProtocolLayer());

        ByteBuf data = protocol.getPacketCodec(in.getClass(), connection
                .getClientProtocolLayer())
                .encode(ctx.alloc().buffer(), in);

        ByteBufUtils.writeVarInt(data, packetID);

        if (connection.isCompressionEnabled())
            ctx.channel().attr(AttributeKey.<Integer>valueOf("uncompressedPacketLength"))
                    .set(data.readableBytes());

        out.add(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}