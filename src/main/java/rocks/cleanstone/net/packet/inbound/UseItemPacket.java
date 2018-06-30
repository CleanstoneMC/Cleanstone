package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UseItemPacket implements Packet {

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
