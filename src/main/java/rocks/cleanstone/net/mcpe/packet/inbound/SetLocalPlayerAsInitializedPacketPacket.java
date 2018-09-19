package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetLocalPlayerAsInitializedPacketPacket implements Packet {


    public SetLocalPlayerAsInitializedPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET;
    }
}

