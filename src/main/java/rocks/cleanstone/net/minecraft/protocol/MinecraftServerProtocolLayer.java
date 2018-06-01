package rocks.cleanstone.net.minecraft.protocol;

import javax.annotation.Nullable;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.protocol.ServerProtocolLayer;

public abstract class MinecraftServerProtocolLayer extends ServerProtocolLayer {

    public int getProtocolPacketID(PacketType type) {
        return ((MinecraftPacketCodec) getPacketClassCodecMap().get(type.getPacketClass()))
                .getProtocolPacketID();
    }

    public ProtocolState getProtocolState(PacketType type) {
        return ((MinecraftPacketCodec) getPacketClassCodecMap().get(type.getPacketClass()))
                .getProtocolState();
    }

    @Nullable
    public PacketType getPacketType(int protocolPacketID, ProtocolState protocolState) {
        for (Class<? extends Packet> packetClass : getPacketClassCodecMap().keySet()) {
            MinecraftPacketCodec codec = (MinecraftPacketCodec) getPacketClassCodecMap().get(packetClass);
            if (codec.getProtocolPacketID() == protocolPacketID && codec.getProtocolState() == protocolState) {
                // matching codec might be outbound and therefore still incorrect
                PacketType type = MinecraftInboundPacketType.byPacketClass(packetClass);
                if (type != null) return type;
            }
        }
        return null;
    }
}
