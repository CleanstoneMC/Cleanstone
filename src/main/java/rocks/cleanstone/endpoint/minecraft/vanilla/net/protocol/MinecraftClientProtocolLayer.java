package rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol;

import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.Nullable;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

public enum MinecraftClientProtocolLayer implements ClientProtocolLayer {
    MINECRAFT_V1_12_2("1.12.2", 340),
    MINECRAFT_V1_13("1.13", 393),
    MINECRAFT_V1_13_1("1.13.1", 401),
    MINECRAFT_V1_13_2("1.13.2", 404),
    MINECRAFT_V1_14("1.14", 477),
    MINECRAFT_V1_14_1("1.14.1", 480),
    MINECRAFT_V1_14_2("1.14.2", 485),
    MINECRAFT_V1_14_3("1.14.3", 490),
    MINECRAFT_V1_14_4("1.14.4", 498);

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
