package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
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

