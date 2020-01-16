package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.EntityAttributes;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Links;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.MetadataDictionary;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AddEntityPacket implements Packet {

    private final long entityIDSelf;
    private final long runtimeEntityID;
    private final int entityType;
    private final float x;
    private final float y;
    private final float z;
    private final float speedX;
    private final float speedY;
    private final float speedZ;
    private final float pitch;
    private final float yaw;
    private final float headYaw;
    private final EntityAttributes attributes;
    private final MetadataDictionary metadata;
    private final Links links;

    public AddEntityPacket(long entityIDSelf, long runtimeEntityID, int entityType, float x, float y, float z, float speedX, float speedY, float speedZ, float pitch, float yaw, float headYaw, EntityAttributes attributes, MetadataDictionary metadata, Links links) {
        this.entityIDSelf = entityIDSelf;
        this.runtimeEntityID = runtimeEntityID;
        this.entityType = entityType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.attributes = attributes;
        this.metadata = metadata;
        this.links = links;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public int getEntityType() {
        return entityType;
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

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getHeadYaw() {
        return headYaw;
    }

    public EntityAttributes getAttributes() {
        return attributes;
    }

    public MetadataDictionary getMetadata() {
        return metadata;
    }

    public Links getLinks() {
        return links;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.ADD_ENTITY;
    }
}

