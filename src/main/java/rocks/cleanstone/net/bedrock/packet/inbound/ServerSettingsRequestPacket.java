package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerSettingsRequestPacket implements Packet {


    public ServerSettingsRequestPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.SERVER_SETTINGS_REQUEST;
    }
}

