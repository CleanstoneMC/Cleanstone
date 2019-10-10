package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AdventureSettingsPacket implements Packet {

    private final int flags;
    private final int commandPermission;
    private final int actionPermissions;
    private final int permissionLevel;
    private final int customStoredPermissions;
    private final long userId;

    public AdventureSettingsPacket(int flags, int commandPermission, int actionPermissions, int permissionLevel, int customStoredPermissions, long userId) {
        this.flags = flags;
        this.commandPermission = commandPermission;
        this.actionPermissions = actionPermissions;
        this.permissionLevel = permissionLevel;
        this.customStoredPermissions = customStoredPermissions;
        this.userId = userId;
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

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.ADVENTURE_SETTINGS;
    }
}

