package rocks.cleanstone.net.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import rocks.cleanstone.net.netty.pipeline.inbound.ByteStreamDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.CompressionDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.EncryptionDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.IdentificationHandler;
import rocks.cleanstone.net.netty.pipeline.inbound.InboundPacketHandler;
import rocks.cleanstone.net.netty.pipeline.inbound.PacketDataDecoder;
import rocks.cleanstone.net.netty.pipeline.outbound.ByteStreamEncoder;
import rocks.cleanstone.net.netty.pipeline.outbound.CompressionEncoder;
import rocks.cleanstone.net.netty.pipeline.outbound.EncryptionEncoder;
import rocks.cleanstone.net.netty.pipeline.outbound.OutboundPacketHandler;
import rocks.cleanstone.net.netty.pipeline.outbound.PacketEncoder;

public class ServerChannelInitializer extends ChannelInitializer {

    private final NettyNetworking nettyNetworking;

    public ServerChannelInitializer(NettyNetworking nettyNetworking) {
        this.nettyNetworking = nettyNetworking;
    }

    @Override
    protected void initChannel(Channel channel) {
        // inbound
        channel.pipeline().addLast(
                new IdentificationHandler(nettyNetworking.getProtocol(), nettyNetworking.getClientAddressBlacklist()),
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
