package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Hand;

public class UseItemPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.USE_ITEM;
    }
}
