package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.DiggingStatus;
import rocks.cleanstone.net.packet.minecraft.enums.Face;

public class PlayerDiggingPacket implements Packet {

    private final DiggingStatus diggingStatus;
    private final Position location;
    private final Face face;

    public PlayerDiggingPacket(int diggingStatus, Position location, byte face) {
        this.diggingStatus = DiggingStatus.fromStatusID(diggingStatus);
        this.location = location;
        this.face = Face.fromFaceID(face);
    }

    public PlayerDiggingPacket(DiggingStatus diggingStatus, Position location, Face face) {
        this.diggingStatus = diggingStatus;
        this.location = location;
        this.face = face;
    }

    public DiggingStatus getDiggingStatus() {
        return diggingStatus;
    }

    public Position getLocation() {
        return location;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLAYER_DIGGING;
    }
}
