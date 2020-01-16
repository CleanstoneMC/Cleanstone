package rocks.cleanstone.endpoint.minecraft.bedrock.net;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;

public enum BedrockClientProtocolLayer implements ClientProtocolLayer {
    BEDROCK_V1_4_2("1.2", 137);

    private final String name;
    private final int versionNumber;

    BedrockClientProtocolLayer(String name, int versionNumber) {
        this.name = name;
        this.versionNumber = versionNumber;
    }

    @Nullable
    public static BedrockClientProtocolLayer byVersionNumber(int versionNumber) {
        for (BedrockClientProtocolLayer layer : values()) {
            if (layer.getOrderedVersionNumber() == versionNumber) return layer;
        }
        return null;
    }

    public static BedrockClientProtocolLayer getLatest() {
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
