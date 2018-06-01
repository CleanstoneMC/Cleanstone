package rocks.cleanstone.net.protocol;

import com.google.common.collect.Maps;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class ServerProtocolLayer implements Comparable<ServerProtocolLayer> {
    private final Map<Class<? extends Packet>, PacketCodec> packetClassCodecMap = Maps.newConcurrentMap();

    public abstract MinecraftClientProtocolLayer getCorrespondingClientLayer();

    public <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass) {
        packetClassCodecMap.put(packetClass, codec);
    }

    public PacketCodec getPacketCodec(Class<? extends Packet> packetClass) {
        return packetClassCodecMap.get(packetClass);
    }

    public Map<Class<? extends Packet>, PacketCodec> getPacketClassCodecMap() {
        return packetClassCodecMap;
    }

    public abstract int getOrderedID();

    @Override
    public int compareTo(@Nonnull ServerProtocolLayer serverProtocolLayer) {
        return Integer.compare(serverProtocolLayer.getOrderedID(), getOrderedID());
    }
}
