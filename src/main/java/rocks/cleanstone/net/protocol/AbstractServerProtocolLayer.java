package rocks.cleanstone.net.protocol;

import com.google.common.collect.Maps;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class AbstractServerProtocolLayer implements ServerProtocolLayer {
    private final Map<Class<? extends Packet>, PacketCodec> packetClassCodecMap = Maps.newConcurrentMap();

    @Override
    public <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass) {
        packetClassCodecMap.put(packetClass, codec);
    }

    @Override
    public PacketCodec getPacketCodec(Class<? extends Packet> packetClass) {
        return packetClassCodecMap.get(packetClass);
    }

    @Override
    public Map<Class<? extends Packet>, PacketCodec> getPacketClassCodecMap() {
        return packetClassCodecMap;
    }

    @Override
    public int compareTo(@Nonnull ServerProtocolLayer serverProtocolLayer) {
        return Integer.compare(serverProtocolLayer.getOrderedID(), getOrderedID());
    }
}
