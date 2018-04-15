package rocks.cleanstone.net.packet.protocol;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

public abstract class LayeredProtocol implements Protocol {

    private final List<ServerProtocolLayer> protocolLayers = Lists.newLinkedList();

    public void registerProtocolLayer(ServerProtocolLayer protocolLayer) {
        protocolLayers.add(protocolLayer);
        protocolLayers.sort(Comparator.reverseOrder());
    }

    public Collection<ServerProtocolLayer> getProtocolLayers() {
        return protocolLayers;
    }

    @Override
    public <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packetClass,
                                                            ClientProtocolLayer clientLayer) {
        return new PacketCodec<T>() {
            @Override
            public T decode(ByteBuf byteBuf) throws IOException { // receive from client
                // downgrade ByteBuf from client version to supported server version

                for (ServerProtocolLayer serverLayer : protocolLayers) { // lower to higher
                    PacketCodec codec = serverLayer.getPacketCodec(packetClass);
                    if (serverLayer.getCorrespondingClientLayer().getOrderedID() < clientLayer.getOrderedID()) {
                        byteBuf = codec.downgradeByteBuf(byteBuf);
                    } else {
                        //noinspection unchecked
                        return (T) serverLayer.getPacketCodec(packetClass).decode(byteBuf);
                    }
                }
                throw new RuntimeException("");
            }

            @Override
            public ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException { // send to client
                // upgrade POJO from supported server version to client version

                protocolLayers.sort(Comparator.naturalOrder());
                try {
                    for (ServerProtocolLayer serverLayer : protocolLayers) { // higher to lower
                        PacketCodec codec = serverLayer.getPacketCodec(packetClass);
                        if (serverLayer.getCorrespondingClientLayer().getOrderedID() > clientLayer.getOrderedID()) {
                            byteBuf = codec.upgrade(byteBuf);
                        } else {
                            //noinspection unchecked
                            return serverLayer.getPacketCodec(packetClass).encode(byteBuf, packet);
                        }
                    }
                    throw new RuntimeException("");
                } finally {
                    protocolLayers.sort(Comparator.reverseOrder());
                }
            }

            @Override
            public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
                throw new UnsupportedOperationException("downgradebyteBuf not supported with " +
                        "VersionedProtocol");
            }

            @Override
            public Packet downgradePOJO(Packet nextLayerPacket) {
                throw new UnsupportedOperationException("downgradePOJO not supported with VersionedProtocol");
            }
        };
    }

    @Override
    public int translateIngoingPacketId(int clientPacketId) {
        return 0;
    }

    @Override
    public int translateOutgoingPacketId(int serverPacketId) {
        return 0;
    }
}
