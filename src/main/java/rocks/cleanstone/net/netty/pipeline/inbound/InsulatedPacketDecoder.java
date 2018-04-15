package rocks.cleanstone.net.netty.pipeline.inbound;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.netty.InsulatedPacket;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.packet.protocol.cleanstone.CleanstoneClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.SimpleMinecraftProtocol;

public class InsulatedPacketDecoder extends MessageToMessageDecoder<InsulatedPacket> {

    private final Protocol protocol;

    public InsulatedPacketDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, InsulatedPacket in, List<Object> out) throws Exception {
        try {
            PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
            Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

            ClientProtocolLayer defaultClientLayer;
            if (protocol.getClass() == SimpleMinecraftProtocol.class) {
                defaultClientLayer = MinecraftClientProtocolLayer.MINECRAFT_V1_12_2;
            } else defaultClientLayer = CleanstoneClientProtocolLayer.LATEST;

            connection.setClientProtocolLayer(defaultClientLayer);

            PacketType packetType = packetTypeRegistry.getPacketType(
                    protocol.translateInboundPacketId(in.getPacketID(), defaultClientLayer));
            Packet packet = protocol.getPacketCodec(packetType.getPacketClass(), defaultClientLayer).decode(in.getData());

            if (packet.getDirection() == PacketDirection.OUTBOUND) {
                throw new DecoderException("Received packet has invalid direction");
            }
            out.add(packet);
        } finally {
            ReferenceCountUtil.release(in.getData());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}