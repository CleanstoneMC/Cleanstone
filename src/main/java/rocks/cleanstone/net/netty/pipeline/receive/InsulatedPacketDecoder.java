package rocks.cleanstone.net.netty.pipeline.receive;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import rocks.cleanstone.net.netty.InsulatedPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class InsulatedPacketDecoder extends MessageToMessageDecoder<InsulatedPacket> {

    private final Protocol protocol;

    public InsulatedPacketDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, InsulatedPacket in, List<Object> out) throws Exception {
        PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
        PacketType packetType = packetTypeRegistry.getPacketType(
                protocol.translateIngoingPacketId(in.getPacketID()));
        out.add(null/*Packet*/);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}