package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerSettingsRequestPacket implements Packet {


    public ServerSettingsRequestPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.SERVER_SETTINGS_REQUEST;
    }
}

