package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class PlayerSkinPacket implements Packet {

    private final UUID uUID;
    private final String skinID;
    private final String skinName;
    private final String oldSkinName;
    private final byte[] skinData;
    private final byte[] capeData;
    private final String geometryModel;
    private final String geometryData;

    public PlayerSkinPacket(UUID uUID, String skinID, String skinName, String oldSkinName, byte[] skinData, byte[] capeData, String geometryModel, String geometryData) {
        this.uUID = uUID;
        this.skinID = skinID;
        this.skinName = skinName;
        this.oldSkinName = oldSkinName;
        this.skinData = skinData;
        this.capeData = capeData;
        this.geometryModel = geometryModel;
        this.geometryData = geometryData;
    }

    public UUID getUUID() {
        return uUID;
    }

    public String getSkinID() {
        return skinID;
    }

    public String getSkinName() {
        return skinName;
    }

    public String getOldSkinName() {
        return oldSkinName;
    }

    public byte[] getSkinData() {
        return skinData;
    }

    public byte[] getCapeData() {
        return capeData;
    }

    public String getGeometryModel() {
        return geometryModel;
    }

    public String getGeometryData() {
        return geometryData;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.PLAYER_SKIN;
    }
}

