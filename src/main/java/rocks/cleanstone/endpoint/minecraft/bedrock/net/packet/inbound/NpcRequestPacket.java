package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class NpcRequestPacket implements Packet {

    private final long runtimeEntityID;
    private final byte unknown0;
    private final String unknown1;
    private final byte unknown2;

    public NpcRequestPacket(long runtimeEntityID, byte unknown0, String unknown1, byte unknown2) {
        this.runtimeEntityID = runtimeEntityID;
        this.unknown0 = unknown0;
        this.unknown1 = unknown1;
        this.unknown2 = unknown2;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getUnknown0() {
        return unknown0;
    }

    public String getUnknown1() {
        return unknown1;
    }

    public byte getUnknown2() {
        return unknown2;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.NPC_REQUEST;
    }
}

