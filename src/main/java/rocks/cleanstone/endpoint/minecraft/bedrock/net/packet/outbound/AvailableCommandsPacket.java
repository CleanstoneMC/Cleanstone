package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
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

