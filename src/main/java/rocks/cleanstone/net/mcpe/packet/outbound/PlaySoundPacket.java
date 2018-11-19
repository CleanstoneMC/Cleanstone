package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlaySoundPacket implements Packet {

    private final String name;
    private final BlockCoordinates coordinates;
    private final float volume;
    private final float pitch;

    public PlaySoundPacket(String name, BlockCoordinates coordinates, float volume, float pitch) {
        this.name = name;
        this.coordinates = coordinates;
        this.volume = volume;
        this.pitch = pitch;
    }

    public String getName() {
        return name;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.PLAY_SOUND;
    }
}

