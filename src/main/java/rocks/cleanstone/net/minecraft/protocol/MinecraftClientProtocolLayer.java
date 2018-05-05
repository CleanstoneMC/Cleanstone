package rocks.cleanstone.net.minecraft.protocol;

import javax.annotation.Nullable;

import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public enum MinecraftClientProtocolLayer implements ClientProtocolLayer {
    MINECRAFT_V1_12_2(340);

    private final int versionNumber;

    MinecraftClientProtocolLayer(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Nullable
    public static MinecraftClientProtocolLayer byVersionNumber(int versionNumber) {
        for (MinecraftClientProtocolLayer layer : values()) {
            if (layer.getOrderedVersionNumber() == versionNumber) return layer;
        }
        return null;
    }

    @Override
    public int getOrderedVersionNumber() {
        return versionNumber;
    }
}
