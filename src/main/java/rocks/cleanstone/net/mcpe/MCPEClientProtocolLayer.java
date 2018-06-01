package rocks.cleanstone.net.mcpe;

import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.Nullable;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

public enum MCPEClientProtocolLayer implements ClientProtocolLayer {
    BEDROCK_V1_4_2("1.2", 137);

    private final String name;
    private final int versionNumber;

    MCPEClientProtocolLayer(String name, int versionNumber) {
        this.name = name;
        this.versionNumber = versionNumber;
    }

    @Nullable
    public static MCPEClientProtocolLayer byVersionNumber(int versionNumber) {
        for (MCPEClientProtocolLayer layer : values()) {
            if (layer.getOrderedVersionNumber() == versionNumber) return layer;
        }
        return null;
    }

    public static MCPEClientProtocolLayer getLatest() {
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
