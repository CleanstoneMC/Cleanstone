package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import com.github.steveice10.opennbt.tag.builtin.Tag;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.UpdateBlockAction;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateBlockEntityPacket implements Packet {

    private final Position location;
    private final UpdateBlockAction action;
    private final Tag nbtData;

    public UpdateBlockEntityPacket(Position location, byte action, Tag nbtData) {
        this.location = location;
        this.action = UpdateBlockAction.fromActionID(action);
        this.nbtData = nbtData;
    }

    public UpdateBlockEntityPacket(Position location, UpdateBlockAction action, Tag nbtData) {
        this.location = location;
        this.action = action;
        this.nbtData = nbtData;
    }

    public Position getLocation() {
        return location;
    }

    public UpdateBlockAction getAction() {
        return action;
    }

    public Tag getNbtData() {
        return nbtData;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.UPDATE_BLOCK_ENTITY;
    }
}
