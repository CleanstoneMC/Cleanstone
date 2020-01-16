package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateTradePacket implements Packet {

    private final byte windowID;
    private final byte windowType;
    private final int unknown0;
    private final int unknown1;
    private final boolean isWilling;
    private final long traderEntityID;
    private final long playerEntityID;
    private final String displayName;
    private final NamedBinaryTag namedTag;

    public UpdateTradePacket(byte windowID, byte windowType, int unknown0, int unknown1, boolean isWilling, long traderEntityID, long playerEntityID, String displayName, NamedBinaryTag namedTag) {
        this.windowID = windowID;
        this.windowType = windowType;
        this.unknown0 = unknown0;
        this.unknown1 = unknown1;
        this.isWilling = isWilling;
        this.traderEntityID = traderEntityID;
        this.playerEntityID = playerEntityID;
        this.displayName = displayName;
        this.namedTag = namedTag;
    }

    public byte getWindowID() {
        return windowID;
    }

    public byte getWindowType() {
        return windowType;
    }

    public int getUnknown0() {
        return unknown0;
    }

    public int getUnknown1() {
        return unknown1;
    }

    public boolean getIsWilling() {
        return isWilling;
    }

    public long getTraderEntityID() {
        return traderEntityID;
    }

    public long getPlayerEntityID() {
        return playerEntityID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public NamedBinaryTag getNamedTag() {
        return namedTag;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.UPDATE_TRADE;
    }
}

