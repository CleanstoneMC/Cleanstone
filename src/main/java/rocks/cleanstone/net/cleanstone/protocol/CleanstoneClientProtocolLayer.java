package rocks.cleanstone.net.cleanstone.protocol;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

public enum CleanstoneClientProtocolLayer implements ClientProtocolLayer {
    LATEST("Latest", 0);

    private final int orderedID;
    private final String name;

    CleanstoneClientProtocolLayer(String name, int orderedID) {
        this.name = name;
        this.orderedID = orderedID;
    }

    @Override
    public int getOrderedVersionNumber() {
        return orderedID;
    }

    @Override
    public String getName() {
        return name;
    }
}
