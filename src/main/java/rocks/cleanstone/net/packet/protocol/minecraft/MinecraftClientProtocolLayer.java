package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

import javax.annotation.Nullable;

public enum MinecraftClientProtocolLayer implements ClientProtocolLayer {
    MINECRAFT_V1_12_2(340);

    private final int versionNumber;

    MinecraftClientProtocolLayer(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Override
    public int getOrderedVersionNumber() {
        return versionNumber;
    }

    @Nullable
    public static MinecraftClientProtocolLayer byVersionNumber(int versionNumber) {
        for (MinecraftClientProtocolLayer layer : values()) {
            if (layer.getOrderedVersionNumber() == versionNumber) return layer;
        }
        return null;
    }
}
