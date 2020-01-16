package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
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

