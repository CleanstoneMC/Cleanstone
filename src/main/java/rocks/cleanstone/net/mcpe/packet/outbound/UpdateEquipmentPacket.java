package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateEquipmentPacket implements Packet {

    private final byte windowID;
    private final byte windowType;
    private final byte unknown;
    private final long entityID;
    private final NamedBinaryTag namedTag;

    public UpdateEquipmentPacket(byte windowID, byte windowType, byte unknown, long entityID, NamedBinaryTag namedTag) {
        this.windowID =  windowID;
        this.windowType =  windowType;
        this.unknown =  unknown;
        this.entityID =  entityID;
        this.namedTag =  namedTag;
    }

    public byte getWindowID() {
        return windowID;
    }

    public byte getWindowType() {
        return windowType;
    }

    public byte getUnknown() {
        return unknown;
    }

    public long getEntityID() {
        return entityID;
    }

    public NamedBinaryTag getNamedTag() {
        return namedTag;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.UPDATE_EQUIPMENT;
    }
}

