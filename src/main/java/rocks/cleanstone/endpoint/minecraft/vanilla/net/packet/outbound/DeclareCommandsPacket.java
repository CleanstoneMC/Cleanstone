package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.Collection;

public class DeclareCommandsPacket implements Packet {

    private Collection<Command> commands;

    public DeclareCommandsPacket(Collection<Command> commands) {
        this.commands = commands;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DECLARE_COMMANDS;
    }

    public Collection<Command> getCommands() {
        return commands;
    }

    public void setCommands(Collection<Command> commands) {
        this.commands = commands;
    }
}
