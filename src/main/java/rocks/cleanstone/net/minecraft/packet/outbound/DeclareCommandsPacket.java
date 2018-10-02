package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.command.completion.node.CommandNode;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class DeclareCommandsPacket implements Packet {

    private final CommandNode rootCommandNode;

    public DeclareCommandsPacket(CommandNode rootCommandNode) {
        this.rootCommandNode = rootCommandNode;
    }

    public CommandNode getCommandNodes() {
        return rootCommandNode;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DECLARE_COMMANDS;
    }
}
