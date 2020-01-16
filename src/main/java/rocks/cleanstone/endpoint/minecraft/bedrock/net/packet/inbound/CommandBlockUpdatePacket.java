package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
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

