package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CommandOutputPacket implements Packet {


    public CommandOutputPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.COMMAND_OUTPUT;
    }
}

