package rocks.cleanstone.net.minecraft.protocol;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.packet.protocol.ServerProtocolLayer;

import javax.annotation.Nullable;

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
