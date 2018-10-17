package rocks.cleanstone.net.minecraft.protocol;

import rocks.cleanstone.net.protocol.ProtocolState;

import javax.annotation.Nullable;

public enum VanillaProtocolState implements ProtocolState {
    HANDSHAKE(0), STATUS(1), LOGIN(2), PLAY(3);

    private final int stateID;

    VanillaProtocolState(int stateID) {
        this.stateID = stateID;
    }

    @Nullable
    public static VanillaProtocolState byStateID(int stateID) {
        for (VanillaProtocolState state : values()) {
            if (state.getStateID() == stateID) return state;
        }
        return null;
    }

    @Override
    public int getStateID() {
        return stateID;
    }
}
