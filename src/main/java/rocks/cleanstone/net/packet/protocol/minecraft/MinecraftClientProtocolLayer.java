package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public enum MinecraftClientProtocolLayer implements ClientProtocolLayer {
    MINECRAFT_V1_12_2(0);

    private final int orderedID;

    MinecraftClientProtocolLayer(int orderedID) {
        this.orderedID = orderedID;
    }

    @Override
    public int getOrderedID() {
        return orderedID;
    }
}
