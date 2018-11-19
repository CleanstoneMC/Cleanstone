package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TelemetryEventPacket implements Packet {

    private final long entityIDSelf;
    private final int unk1;
    private final byte unk2;

    public TelemetryEventPacket(long entityIDSelf, int unk1, byte unk2) {
        this.entityIDSelf = entityIDSelf;
        this.unk1 = unk1;
        this.unk2 = unk2;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public int getUnk1() {
        return unk1;
    }

    public byte getUnk2() {
        return unk2;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.TELEMETRY_EVENT;
    }
}

