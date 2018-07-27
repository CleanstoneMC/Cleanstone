package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.collect.Maps;
import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.protocol.ServerProtocolLayer;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class MinecraftServerProtocolLayer extends ServerProtocolLayer {

    private final Map<Class<? extends Packet>, Integer> packetClassProtocolPacketIDMap = Maps.newConcurrentMap();
    private final Map<Class<? extends Packet>, ProtocolState> packetClassProtocolStateMap = Maps.newConcurrentMap();

    public int getProtocolPacketID(PacketType type) {
        return packetClassProtocolPacketIDMap.get(type.getPacketClass());
    }

    public <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass, ProtocolState state,
                                                       int protocolPacketID) {
        super.registerPacketCodec(codec, packetClass);
        packetClassProtocolPacketIDMap.put(packetClass, protocolPacketID);
        packetClassProtocolStateMap.put(packetClass, state);
    }

    @Nullable
    public PacketType getPacketType(int protocolPacketID, ProtocolState protocolState) {
        for (Class<? extends Packet> packetClass : getPacketClassCodecMap().keySet()) {
            if (packetClassProtocolPacketIDMap.get(packetClass) == protocolPacketID
                    && packetClassProtocolStateMap.get(packetClass) == protocolState) {
                // matching codec might be outbound and therefore still incorrect
                PacketType packetType = MinecraftInboundPacketType.byPacketClass(packetClass);
                if (packetType != null) return packetType;
            }
        }
        return null;
    }
}
