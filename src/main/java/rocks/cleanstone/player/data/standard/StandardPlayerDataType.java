package rocks.cleanstone.player.data.standard;

import rocks.cleanstone.player.data.PlayerDataType;

public enum StandardPlayerDataType implements PlayerDataType {
    ENTITY_DATA(0), NAME(1);

    private final int typeID;

    StandardPlayerDataType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
