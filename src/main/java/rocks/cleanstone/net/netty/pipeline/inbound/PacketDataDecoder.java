package rocks.cleanstone.net.netty.pipeline.inbound;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

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
        Packet packet = codec.decode(in);
        out.add(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause.getMessage() != null && cause.getMessage().contains("Cannot find packetType by")) {
            logger.warn(cause.getMessage());
        } else if (cause.getCause() != null && (cause.getCause() instanceof IllegalArgumentException)) {
            logger.warn("Client sent illegal packet: " + cause.getMessage());
        } else if (cause.getCause() != null && (cause.getCause() instanceof IOException)) {
            logger.warn("Client sent malformed packet: " + cause.getMessage());
            ctx.close();
        } else {
            logger.error("Error occurred while decoding packet data", cause);
        }
    }
}