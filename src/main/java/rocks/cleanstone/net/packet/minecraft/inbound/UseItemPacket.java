package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Hand;

public class UseItemPacket {

    private final Hand hand;

    public UseItemPacket(int hand) {
        this.hand = Hand.fromHandID(hand);
    }

    public UseItemPacket(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.USE_ITEM;
    }
}
