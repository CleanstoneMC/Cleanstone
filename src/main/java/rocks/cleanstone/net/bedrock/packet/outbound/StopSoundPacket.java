package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class StopSoundPacket implements Packet {

    private final String name;
    private final boolean stopAll;

    public StopSoundPacket(String name, boolean stopAll) {
        this.name = name;
        this.stopAll = stopAll;
    }

    public String getName() {
        return name;
    }

    public boolean getStopAll() {
        return stopAll;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.STOP_SOUND;
    }
}

