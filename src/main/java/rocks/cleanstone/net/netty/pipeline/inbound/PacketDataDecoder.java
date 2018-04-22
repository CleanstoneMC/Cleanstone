package rocks.cleanstone.net.netty.pipeline.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.packet.protocol.cleanstone.CleanstoneClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.SimpleMinecraftProtocol;
import rocks.cleanstone.net.packet.protocol.minecraft.VanillaProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.util.List;

public class PacketDataDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Protocol protocol;

    public PacketDataDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            int packetID = ByteBufUtils.readVarInt(in);

            PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
            Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

            ClientProtocolLayer defaultClientLayer;
            if (protocol.getClass() == SimpleMinecraftProtocol.class) {
                defaultClientLayer = MinecraftClientProtocolLayer.MINECRAFT_V1_12_2;
                if (connection.getProtocolState() == null)
                    connection.setProtocolState(VanillaProtocolState.HANDSHAKE);
            } else defaultClientLayer = CleanstoneClientProtocolLayer.LATEST;

            if (connection.getClientProtocolLayer() == null)
                connection.setClientProtocolLayer(defaultClientLayer);

            PacketType packetType = packetTypeRegistry.getPacketType(
                    protocol.translateInboundPacketID(packetID, connection));
            Packet packet = protocol.getPacketCodec(packetType.getPacketClass(), defaultClientLayer).decode(in);

            out.add(packet);
        } finally {
            ReferenceCountUtil.release(in);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}