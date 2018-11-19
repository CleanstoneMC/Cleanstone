package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.HURT_ARMOR;
    }
}

