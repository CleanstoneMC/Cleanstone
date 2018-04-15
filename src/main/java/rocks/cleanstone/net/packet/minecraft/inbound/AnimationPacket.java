package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Hand;

public class AnimationPacket extends InboundPacket {

    private final Hand hand;

    public AnimationPacket(int hand) {
        this.hand = Hand.fromHandID(hand);
    }

    public AnimationPacket(Hand hand) {
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
