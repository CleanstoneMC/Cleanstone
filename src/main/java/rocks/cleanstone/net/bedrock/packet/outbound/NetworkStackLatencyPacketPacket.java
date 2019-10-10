package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
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

