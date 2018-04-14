package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.receive.*;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftReceivePacketType implements PacketType {
    HANDSHAKE(0, HandshakePacket.class, StandardProtocolType.MINECRAFT),
    CHAT_MESSAGE(1, HandshakePacket.class, StandardProtocolType.MINECRAFT),
    CLICK_WINDOW(2, ClickWindowPacket.class, StandardProtocolType.MINECRAFT),
    CLIENT_SETTINGS(3, ClientSettingsPacket.class, StandardProtocolType.MINECRAFT),
    CLIENT_STATUS(4, ClientStatusPacket.class, StandardProtocolType.MINECRAFT),
    CLOSE_WINDOW(5, CloseWindowPacket.class, StandardProtocolType.MINECRAFT),
    CONFIRM_TRANSACTION(6, ConfirmTransaction.class, StandardProtocolType.MINECRAFT),
    ENCHANT_ITEM(7, EnchantItemPacket.class, StandardProtocolType.MINECRAFT),
    KEEP_ALIVE(8, KeepAlivePacket.class, StandardProtocolType.MINECRAFT),
    TAB_COMPLETE(9, TabCompletePacket.class, StandardProtocolType.MINECRAFT),
    TELEPORT_CONFIRM(10, TeleportConfirmPacket.class, StandardProtocolType.MINECRAFT);

    private final int typeId;
    private final ProtocolType protocolType;
    private final Class<? extends Packet> packetClass;

    MinecraftReceivePacketType(int typeId, Class<? extends Packet> packetClass, ProtocolType protocolType) {
        this.typeId = typeId;
        this.packetClass = packetClass;
        this.protocolType = protocolType;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
