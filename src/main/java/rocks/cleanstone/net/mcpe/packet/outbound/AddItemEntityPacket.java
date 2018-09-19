package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.Item;
import rocks.cleanstone.net.mcpe.packet.data.MetadataDictionary;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AddItemEntityPacket implements Packet {

    private final long entityIDSelf;
    private final long runtimeEntityID;
    private final Item item;
    private final float x;
    private final float y;
    private final float z;
    private final float speedX;
    private final float speedY;
    private final float speedZ;
    private final MetadataDictionary metadata;
    private final boolean isFromFishing;

    public AddItemEntityPacket(long entityIDSelf, long runtimeEntityID, Item item, float x, float y, float z, float speedX, float speedY, float speedZ, MetadataDictionary metadata, boolean isFromFishing) {
        this.entityIDSelf =  entityIDSelf;
        this.runtimeEntityID =  runtimeEntityID;
        this.item =  item;
        this.x =  x;
        this.y =  y;
        this.z =  z;
        this.speedX =  speedX;
        this.speedY =  speedY;
        this.speedZ =  speedZ;
        this.metadata =  metadata;
        this.isFromFishing =  isFromFishing;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public Item getItem() {
        return item;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public float getSpeedZ() {
        return speedZ;
    }

    public MetadataDictionary getMetadata() {
        return metadata;
    }

    public boolean getIsFromFishing() {
        return isFromFishing;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.ADD_ITEM_ENTITY;
    }
}

