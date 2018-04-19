package rocks.cleanstone.net.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import rocks.cleanstone.net.netty.pipeline.inbound.*;
import rocks.cleanstone.net.netty.pipeline.outbound.*;

import java.util.Collections;

public class ServerChannelInitializer extends ChannelInitializer {

    private final NettyNetworking nettyNetworking;

    public ServerChannelInitializer(NettyNetworking nettyNetworking) {
        this.nettyNetworking = nettyNetworking;
    }

    @Override
    protected void initChannel(Channel channel) {
        // inbound
        channel.pipeline().addLast(
                new IdentificationHandler(Collections.emptySet()),
                new EncryptionDecoder(),
                new ByteStreamDecoder(),
                new CompressionDecoder(),
                new PacketDataDecoder(nettyNetworking.getProtocol()),
                new InboundPacketHandler(nettyNetworking));
        // outbound
        channel.pipeline().addFirst(
                new OutboundPacketHandler(nettyNetworking),
                new PacketEncoder(nettyNetworking.getProtocol()),
                new CompressionEncoder(),
                new ByteStreamEncoder(),
                new EncryptionEncoder());
    }
}
