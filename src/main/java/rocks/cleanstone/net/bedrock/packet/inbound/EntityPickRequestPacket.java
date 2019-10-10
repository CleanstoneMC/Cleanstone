package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityPickRequestPacket implements Packet {

    private final long runtimeEntityID;
    private final byte selectedSlot;

    public EntityPickRequestPacket(long runtimeEntityID, byte selectedSlot) {
        this.runtimeEntityID = runtimeEntityID;
        this.selectedSlot = selectedSlot;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getSelectedSlot() {
        return selectedSlot;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.ENTITY_PICK_REQUEST;
    }
}

