package rocks.cleanstone.net.minecraft.protocol;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;

public enum MinecraftClientProtocolLayer implements ClientProtocolLayer {
    MINECRAFT_V1_12_2("1.12.2", 340),
    MINECRAFT_V1_13("1.13", 393);

    private final String name;
    private final int versionNumber;

    MinecraftClientProtocolLayer(String name, int versionNumber) {
        this.name = name;
        this.versionNumber = versionNumber;
    }

    @Nullable
    public static MinecraftClientProtocolLayer byVersionNumber(int versionNumber) {
        for (MinecraftClientProtocolLayer layer : values()) {
            if (layer.getOrderedVersionNumber() == versionNumber) return layer;
        }
        return null;
    }

    public static MinecraftClientProtocolLayer getLatest() {
        return Arrays.stream(values()).max(Comparator.comparingInt(a -> a.versionNumber)).orElse(null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrderedVersionNumber() {
        return versionNumber;
    }
}
