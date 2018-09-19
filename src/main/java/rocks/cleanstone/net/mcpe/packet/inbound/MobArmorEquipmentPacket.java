package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.Item;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MobArmorEquipmentPacket implements Packet {

    private final long runtimeEntityID;
    private final Item helmet;
    private final Item chestplate;
    private final Item leggings;
    private final Item boots;

    public MobArmorEquipmentPacket(long runtimeEntityID, Item helmet, Item chestplate, Item leggings, Item boots) {
        this.runtimeEntityID =  runtimeEntityID;
        this.helmet =  helmet;
        this.chestplate =  chestplate;
        this.leggings =  leggings;
        this.boots =  boots;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public Item getHelmet() {
        return helmet;
    }

    public Item getChestplate() {
        return chestplate;
    }

    public Item getLeggings() {
        return leggings;
    }

    public Item getBoots() {
        return boots;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.MOB_ARMOR_EQUIPMENT;
    }
}

