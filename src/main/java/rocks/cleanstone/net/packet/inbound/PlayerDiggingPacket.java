package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.enums.DiggingStatus;

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

    public Position getPosition() {
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
