package rocks.cleanstone.net.netty.pipeline.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.List;

public class PacketDataDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Protocol protocol;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketDataDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int packetID = ByteBufUtils.readVarInt(in);
        PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        PacketType packetType = packetTypeRegistry.getPacketType(
                protocol.translateInboundPacketID(packetID, connection));
        PacketCodec codec = protocol.getPacketCodec(packetType.getPacketClass(),
                connection.getClientProtocolLayer());
        Preconditions.checkNotNull(codec, "Cannot find codec for packetType " + packetType
                + " and clientLayer " + connection.getClientProtocolLayer());
        Packet packet;
        try {
            packet = codec.decode(in);
        } catch (Exception e) {
            logger.warn("Failed to decode packet " + packetType + " for clientLayer "
                    + connection.getClientProtocolLayer());
            throw e;
        }
        out.add(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (e.getMessage() != null && e.getMessage().contains("Cannot find ")) {
            logger.warn(e.getMessage());
        } else if (e.getCause() instanceof IllegalArgumentException) {
            logger.warn("Client sent illegal packet: " + e.getMessage());
        } else if (e.getCause() instanceof IOException || e.getCause() instanceof IndexOutOfBoundsException) {
            logger.warn("Client sent malformed packet: " + e.getMessage());
            ctx.close();
        } else {
            logger.error("Error occurred while decoding packet data", e);
        }
    }
}