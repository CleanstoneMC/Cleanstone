package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.command.completion.node.CommandNode;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.List;

public class DeclareCommandsPacket implements Packet {

    private final List<CommandNode> commandNodes;

    public DeclareCommandsPacket(List<CommandNode> commandNodes) {
        this.commandNodes = commandNodes;
    }

    public List<CommandNode> getCommandNodes() {
        return commandNodes;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DECLARE_COMMANDS;
    }
}
