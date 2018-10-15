package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Slf4j
public class PacketEncoder extends MessageToMessageEncoder<Packet> {
    private final Protocol protocol;

    public PacketEncoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet in, List<Object> out) {
        try {
            final Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

            final int packetID = protocol.translateOutboundPacketID(in.getType(), connection);
            final OutboundPacketCodec outboundPacketCodec = protocol.getOutboundPacketCodec(in.getClass(), connection.getClientProtocolLayer());
            ByteBuf data = ctx.alloc().buffer();
            ByteBufUtils.writeVarInt(data, packetID);
            data = outboundPacketCodec.encode(data, in);
            if (connection.isCompressionEnabled()) {
                ctx.channel().attr(AttributeKey.<Integer>valueOf("uncompressedPacketLength"))
                        .set(data.readableBytes());
            }
            out.add(data);
        } catch (Exception e) {
            log.error("Error occurred while encoding packet data", e);
        }
    }
}