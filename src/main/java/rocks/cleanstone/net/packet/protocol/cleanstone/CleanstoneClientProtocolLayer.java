package rocks.cleanstone.net.packet.protocol.cleanstone;

import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public enum CleanstoneClientProtocolLayer implements ClientProtocolLayer {
    LATEST(0);

    private final int orderedID;

    CleanstoneClientProtocolLayer(int orderedID) {
        this.orderedID = orderedID;
    }

    @Override
    public int getOrderedID() {
        return orderedID;
    }
}
