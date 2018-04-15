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
        protocolLayers.sort(Comparator.naturalOrder());
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

                for (ServerProtocolLayer serverLayer : protocolLayers) { // higher to lower
                    if (serverLayer.getCorrespondingClientLayer().getOrderedID() >= clientLayer.getOrderedID())
                        continue;
                    PacketCodec serverCodec = serverLayer.getPacketCodec(packetClass);
                    byteBuf = serverCodec.downgradeByteBuf(byteBuf);
                }
                // lowest=current serverLayer decodes byteBuf
                //noinspection unchecked
                return (T) protocolLayers.get(protocolLayers.size()).getPacketCodec(packetClass).decode(byteBuf);
            }

            @Override
            public ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException { // send to client
                // upgrade POJO from supported server version to client version

                protocolLayers.sort(Comparator.reverseOrder());
                try {
                    boolean skippedFirst = false;
                    for (ServerProtocolLayer serverLayer : protocolLayers) { // lower to higher
                        if (!skippedFirst) {
                            skippedFirst = true;
                            continue;
                        }
                        if (serverLayer.getCorrespondingClientLayer().getOrderedID() > clientLayer.getOrderedID()) {
                            break;
                        }
                        //noinspection unchecked
                        packet = (T) serverLayer.getPacketCodec(packetClass).upgradePOJO(packet);
                    }
                    //noinspection unchecked
                    return protocolLayers.get(protocolLayers.size()).getPacketCodec(packetClass).encode(byteBuf, packet);
                } finally {
                    protocolLayers.sort(Comparator.naturalOrder());
                }
            }

            @Override
            public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
                throw new UnsupportedOperationException("downgradeByteBuf is not supported with " +
                        "VersionedProtocol");
            }

            @Override
            public Packet upgradePOJO(Packet previousLayerPacket) {
                throw new UnsupportedOperationException("upgradePOJO is not supported with " +
                        "VersionedProtocol");
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
