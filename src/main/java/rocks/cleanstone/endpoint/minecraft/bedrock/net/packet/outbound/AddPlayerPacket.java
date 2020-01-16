package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Item;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Links;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.MetadataDictionary;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class AddPlayerPacket implements Packet {

    private final UUID uUID;
    private final String username;
    private final String thirdPartyName;
    private final int platform;
    private final long entityIDSelf;
    private final long runtimeEntityID;
    private final String platformChatID;
    private final float x;
    private final float y;
    private final float z;
    private final float speedX;
    private final float speedY;
    private final float speedZ;
    private final float pitch;
    private final float yaw;
    private final float headYaw;
    private final Item item;
    private final MetadataDictionary metadata;
    private final int flags;
    private final int commandPermission;
    private final int actionPermissions;
    private final int permissionLevel;
    private final int customStoredPermissions;
    private final long userId;
    private final Links links;
    private final String deviceID;

    public AddPlayerPacket(UUID uUID, String username, String thirdPartyName, int platform, long entityIDSelf, long runtimeEntityID, String platformChatID, float x, float y, float z, float speedX, float speedY, float speedZ, float pitch, float yaw, float headYaw, Item item, MetadataDictionary metadata, int flags, int commandPermission, int actionPermissions, int permissionLevel, int customStoredPermissions, long userId, Links links, String deviceID) {
        this.uUID = uUID;
        this.username = username;
        this.thirdPartyName = thirdPartyName;
        this.platform = platform;
        this.entityIDSelf = entityIDSelf;
        this.runtimeEntityID = runtimeEntityID;
        this.platformChatID = platformChatID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.item = item;
        this.metadata = metadata;
        this.flags = flags;
        this.commandPermission = commandPermission;
        this.actionPermissions = actionPermissions;
        this.permissionLevel = permissionLevel;
        this.customStoredPermissions = customStoredPermissions;
        this.userId = userId;
        this.links = links;
        this.deviceID = deviceID;
    }

    public UUID getUUID() {
        return uUID;
    }

    public String getUsername() {
        return username;
    }

    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public int getPlatform() {
        return platform;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public String getPlatformChatID() {
        return platformChatID;
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

    public Item getItem() {
        return item;
    }

    public MetadataDictionary getMetadata() {
        return metadata;
    }

    public int getFlags() {
        return flags;
    }

    public int getCommandPermission() {
        return commandPermission;
    }

    public int getActionPermissions() {
        return actionPermissions;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public int getCustomStoredPermissions() {
        return customStoredPermissions;
    }

    public long getUserId() {
        return userId;
    }

    public Links getLinks() {
        return links;
    }

    public String getDeviceID() {
        return deviceID;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.ADD_PLAYER;
    }
}

