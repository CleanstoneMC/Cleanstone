package rocks.cleanstone.net.protocol;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

public abstract class LayeredProtocol implements Protocol {

    private final List<ServerProtocolLayer> protocolLayers = Lists.newLinkedList();

    public void registerProtocolLayer(ServerProtocolLayer protocolLayer) {
        protocolLayers.add(protocolLayer);
        protocolLayers.sort(Comparator.naturalOrder());
    }

    public Collection<ServerProtocolLayer> getProtocolLayers() {
        return protocolLayers;
    }

    @Nullable
    public ServerProtocolLayer getServerLayerFromClientLayer(ClientProtocolLayer clientLayer) {
        return protocolLayers.stream().filter(
                serverLayer -> serverLayer.getCorrespondingClientLayer() == clientLayer
        ).findFirst().orElse(null);
    }

    @Override
    public <T extends Packet> InboundPacketCodec<T> getInboundPacketCodec(Class<T> packetClass, ClientProtocolLayer clientLayer) {
        return new LayeredPacketCodec<>(packetClass, clientLayer);
    }

    @Override
    public <T extends Packet> OutboundPacketCodec<T> getOutboundPacketCodec(Class<T> packetClass, ClientProtocolLayer clientLayer) {
        return new LayeredPacketCodec<>(packetClass, clientLayer);
    }

    private class LayeredPacketCodec<T extends Packet> implements InOutPacketCodec<T> {

        private final Class<T> packetClass;
        private final ClientProtocolLayer clientLayer;

        LayeredPacketCodec(Class<T> packetClass, ClientProtocolLayer clientLayer) {
            this.packetClass = packetClass;
            this.clientLayer = clientLayer;
        }

        @Override
        public T decode(ByteBuf byteBuf) throws IOException {
            return getServerLayerFromClientLayer(clientLayer)
                    .getInboundPacketCodec(packetClass).decode(byteBuf);
        }

        @Override
        public ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException {
            return getServerLayerFromClientLayer(clientLayer)
                    .getOutboundPacketCodec(packetClass).encode(byteBuf, packet);
        }
    }
}
