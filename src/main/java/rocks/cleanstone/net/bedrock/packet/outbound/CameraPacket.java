package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CameraPacket implements Packet {

    private final long unknown1;
    private final long unknown2;

    public CameraPacket(long unknown1, long unknown2) {
        this.unknown1 = unknown1;
        this.unknown2 = unknown2;
    }

    public long getUnknown1() {
        return unknown1;
    }

    public long getUnknown2() {
        return unknown2;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CAMERA;
    }
}

