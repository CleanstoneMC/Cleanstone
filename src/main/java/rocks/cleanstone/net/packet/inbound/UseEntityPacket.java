package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.net.packet.enums.InteractType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UseEntityPacket implements Packet {

    private final int target;
    private final InteractType interactType;
    private final float targetX;
    private final float targetY;
    private final float targetZ;
    private final Hand hand;

    public UseEntityPacket(int target, int interactType, float targetX, float targetY, float targetZ, int hand) {
        this.target = target;
        this.interactType = InteractType.fromTypeID(interactType);
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.hand = Hand.fromHandID(hand);
    }

    public UseEntityPacket(int target, InteractType interactType, float targetX, float targetY, float targetZ, Hand hand) {
        this.target = target;
        this.interactType = interactType;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.hand = hand;
    }

    public int getTarget() {
        return target;
    }

    public InteractType getInteractType() {
        return interactType;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public float getTargetZ() {
        return targetZ;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.USE_ENTITY;
    }
}
