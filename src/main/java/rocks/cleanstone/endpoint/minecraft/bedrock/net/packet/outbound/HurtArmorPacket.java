package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class HurtArmorPacket implements Packet {

    private final int health;

    public HurtArmorPacket(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.HURT_ARMOR;
    }
}

