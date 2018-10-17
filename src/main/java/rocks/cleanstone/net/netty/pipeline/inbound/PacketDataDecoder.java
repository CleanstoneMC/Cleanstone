package rocks.cleanstone.net.netty.pipeline.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
public class PacketDataDecoder extends MessageToMessageDecoder<ByteBuf> {
    private final Protocol protocol;

    public PacketDataDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int packetID = ByteBufUtils.readVarInt(in);
        PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        PacketType packetType = protocol.translateInboundPacketID(packetID, connection);
        InboundPacketCodec codec = protocol.getInboundPacketCodec(packetType.getPacketClass(),
                connection.getClientProtocolLayer());
        Preconditions.checkNotNull(codec, "Cannot find codec for packetType " + packetType
                + " and clientLayer " + connection.getClientProtocolLayer());
        Packet packet;
        try {
            packet = codec.decode(in);
        } catch (Exception e) {
            log.warn("Failed to decode packet " + packetType + " for clientLayer "
                    + connection.getClientProtocolLayer());
            throw e;
        }
        out.add(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (e.getMessage() != null && e.getMessage().contains("Cannot find ")) {
            log.warn(e.getMessage());
        } else if (e.getCause() instanceof IllegalArgumentException) {
            log.warn("Client sent illegal packet: " + e.getMessage());
        } else if (e.getCause() instanceof IOException || e.getCause() instanceof IndexOutOfBoundsException) {
            log.warn("Client sent malformed packet: " + e.getMessage());
            ctx.close();
        } else {
            log.error("Error occurred while decoding packet data", e);
        }
    }
}