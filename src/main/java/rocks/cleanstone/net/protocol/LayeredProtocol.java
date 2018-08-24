package rocks.cleanstone.net.protocol;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    public PacketCodec getPacketCodec(Class<? extends Packet> packetClass, ClientProtocolLayer clientLayer) {
        return new LayeredPacketCodec(packetClass, clientLayer);
    }

    private class LayeredPacketCodec implements PacketCodec {

        private final Class<? extends Packet> packetClass;
        private final ClientProtocolLayer clientLayer;

        LayeredPacketCodec(Class<? extends Packet> packetClass, ClientProtocolLayer clientLayer) {
            this.packetClass = packetClass;
            this.clientLayer = clientLayer;
        }

        @Override
        public Packet decode(ByteBuf byteBuf) throws IOException {
            return Objects.requireNonNull(getServerLayerFromClientLayer(clientLayer))
                    .getPacketCodec(packetClass).decode(byteBuf);
        }

        @Override
        public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
            return Objects.requireNonNull(getServerLayerFromClientLayer(clientLayer))
                    .getPacketCodec(packetClass).encode(byteBuf, packet);
        }
    }
}
