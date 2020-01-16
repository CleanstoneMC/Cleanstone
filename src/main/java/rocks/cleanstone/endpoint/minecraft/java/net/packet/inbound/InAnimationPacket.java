package rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InAnimationPacket implements Packet {

    private final Hand hand;

    public InAnimationPacket(int hand) {
        this.hand = Hand.fromHandID(hand);
    }

    public InAnimationPacket(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.ANIMATION;
    }
}
