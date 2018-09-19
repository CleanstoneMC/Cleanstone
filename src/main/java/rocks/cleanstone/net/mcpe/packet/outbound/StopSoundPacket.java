package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class StopSoundPacket implements Packet {

    private final String name;
    private final boolean stopAll;

    public StopSoundPacket(String name, boolean stopAll) {
        this.name =  name;
        this.stopAll =  stopAll;
    }

    public String getName() {
        return name;
    }

    public boolean getStopAll() {
        return stopAll;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.STOP_SOUND;
    }
}

