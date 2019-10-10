package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CommandBlockUpdatePacket implements Packet {


    public CommandBlockUpdatePacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.COMMAND_BLOCK_UPDATE;
    }
}

