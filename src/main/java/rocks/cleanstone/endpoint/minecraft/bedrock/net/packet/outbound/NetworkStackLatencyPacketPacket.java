package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class NetworkStackLatencyPacketPacket implements Packet {


    public NetworkStackLatencyPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.NETWORK_STACK_LATENCY_PACKET;
    }
}

