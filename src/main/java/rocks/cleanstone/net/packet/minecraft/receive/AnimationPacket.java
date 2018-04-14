package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Hand;

public class AnimationPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.ANIMATION;
    }
}
