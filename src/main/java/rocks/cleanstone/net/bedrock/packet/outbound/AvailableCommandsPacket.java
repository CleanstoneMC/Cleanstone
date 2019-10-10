package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AvailableCommandsPacket implements Packet {


    public AvailableCommandsPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.AVAILABLE_COMMANDS;
    }
}

