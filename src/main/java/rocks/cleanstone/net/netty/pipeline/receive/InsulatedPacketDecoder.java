package rocks.cleanstone.net.netty.pipeline.receive;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToMessageDecoder;
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
        PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();

        ClientProtocolLayer clientLayer;
        if (protocol.getClass() == SimpleMinecraftProtocol.class) {
            // TODO extract from Handshaking packet?
            clientLayer = MinecraftClientProtocolLayer.MINECRAFT_V1_12_2;
        } else clientLayer = CleanstoneClientProtocolLayer.LATEST;

        PacketType packetType = packetTypeRegistry.getPacketType(
                protocol.translateIncomingPacketId(in.getPacketID(), clientLayer));
        Packet packet = protocol.getPacketCodec(packetType.getPacketClass(), clientLayer).decode(in.getData());
        if (packet.getDirection() == PacketDirection.SEND) {
            throw new DecoderException("Received packet has invalid direction");
        }
        out.add(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}