package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateSoftEnumPacketPacket implements Packet {


    public UpdateSoftEnumPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.UPDATE_SOFT_ENUM_PACKET;
    }
}

