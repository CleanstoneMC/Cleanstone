package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Face;
import rocks.cleanstone.net.packet.minecraft.enums.Hand;

public class PlayerBlockPlacementPacket {

    private final Position location;
    private final Face face;
    private final Hand hand;
    private final float cursorPositionX;
    private final float cursorPositionY;
    private final float cursorPositionZ;

    public PlayerBlockPlacementPacket(Position location, int face, int hand, float cursorPositionX, float cursorPositionY, float cursorPositionZ) {
        this.location = location;
        this.face = Face.fromFaceID(face);
        this.hand = Hand.fromHandID(hand);
        this.cursorPositionX = cursorPositionX;
        this.cursorPositionY = cursorPositionY;
        this.cursorPositionZ = cursorPositionZ;
    }

    public PlayerBlockPlacementPacket(Position location, Face face, Hand hand, float cursorPositionX, float cursorPositionY, float cursorPositionZ) {
        this.location = location;
        this.face = face;
        this.hand = hand;
        this.cursorPositionX = cursorPositionX;
        this.cursorPositionY = cursorPositionY;
        this.cursorPositionZ = cursorPositionZ;
    }

    public Position getLocation() {
        return location;
    }

    public Face getFace() {
        return face;
    }

    public Hand getHand() {
        return hand;
    }

    public float getCursorPositionX() {
        return cursorPositionX;
    }

    public float getCursorPositionY() {
        return cursorPositionY;
    }

    public float getCursorPositionZ() {
        return cursorPositionZ;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLAYER_BLOCK_PLACEMENT;
    }
}
