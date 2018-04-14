package rocks.cleanstone.net.packet.protocol;

import rocks.cleanstone.net.packet.protocol.cleanstone.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.MinecraftProtocol_v1_12_2;

public enum StandardProtocolType implements ProtocolType {
    CLEANSTONE(0, SimpleCleanstoneProtocol.class), MINECRAFT_LATEST(1, MinecraftProtocol_v1_12_2.class);

    private final int typeId;
    private final Class<? extends Protocol> protocolClass;

    StandardProtocolType(int typeId, Class<? extends Protocol> protocolClass) {
        this.typeId = typeId;
        this.protocolClass = protocolClass;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public Class<? extends Protocol> getProtocolClass() {
        return protocolClass;
    }
}
