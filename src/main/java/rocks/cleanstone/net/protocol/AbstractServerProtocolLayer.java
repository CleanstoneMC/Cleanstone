package rocks.cleanstone.net.protocol;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nonnull;
import rocks.cleanstone.net.packet.Packet;

public abstract class AbstractServerProtocolLayer implements ServerProtocolLayer {
    private final Map<Class<? extends Packet>, InboundPacketCodec> inboundPacketClassCodecMap = Maps.newConcurrentMap();
    private final Map<Class<? extends Packet>, OutboundPacketCodec> outboundPacketClassCodecMap = Maps.newConcurrentMap();

    @Override
    public <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass) {
        if (codec instanceof InboundPacketCodec) {
            inboundPacketClassCodecMap.put(packetClass, (InboundPacketCodec) codec);
        }

        if (codec instanceof OutboundPacketCodec) {
            outboundPacketClassCodecMap.put(packetClass, (OutboundPacketCodec) codec);
        }
    }

    @Override
    public <T extends Packet> InboundPacketCodec<T> getInboundPacketCodec(Class<T> packetClass) {
        return (InboundPacketCodec<T>) inboundPacketClassCodecMap.get(packetClass);
    }

    @Override
    public <T extends Packet> OutboundPacketCodec<T> getOutboundPacketCodec(Class<T> packetClass) {
        return (OutboundPacketCodec<T>) outboundPacketClassCodecMap.get(packetClass);
    }

    @Override
    public Map<Class<? extends Packet>, InboundPacketCodec> getInboundPacketClassCodecMap() {
        return inboundPacketClassCodecMap;
    }

    @Override
    public int compareTo(@Nonnull ServerProtocolLayer serverProtocolLayer) {
        return Integer.compare(serverProtocolLayer.getOrderedID(), getOrderedID());
    }
}
