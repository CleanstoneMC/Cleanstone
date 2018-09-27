package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PacketEncoder extends MessageToMessageEncoder<Packet> {

    private final Protocol protocol;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketEncoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet in, List<Object> out) {
        try {
            Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

            int packetID = protocol.translateOutboundPacketID(in.getType(), connection);
            OutboundPacketCodec outboundPacketCodec = protocol.getOutboundPacketCodec(in.getClass(), connection.getClientProtocolLayer());
            ByteBuf data = ctx.alloc().buffer();
            ByteBufUtils.writeVarInt(data, packetID);
            data = outboundPacketCodec.encode(data, in);
            if (connection.isCompressionEnabled())
                ctx.channel().attr(AttributeKey.<Integer>valueOf("uncompressedPacketLength"))
                        .set(data.readableBytes());
            out.add(data);
        } catch (Exception e) {
            logger.error("Error occurred while encoding packet data", e);
        }
    }
}