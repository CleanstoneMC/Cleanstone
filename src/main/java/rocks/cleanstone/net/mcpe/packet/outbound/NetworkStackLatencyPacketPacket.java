package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class NetworkStackLatencyPacketPacket implements Packet {


    public NetworkStackLatencyPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.NETWORK_STACK_LATENCY_PACKET;
    }
}

