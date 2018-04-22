package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.protocol.ProtocolState;

public enum VanillaProtocolState implements ProtocolState {
    HANDSHAKE(0), STATUS(1), LOGIN(2), PLAY(3);

    private final int stateID;

    VanillaProtocolState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getStateID() {
        return stateID;
    }
}
