package rocks.cleanstone.net.netty;

import java.util.Collections;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import rocks.cleanstone.net.netty.pipeline.inbound.ByteStreamDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.CompressionDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.IdentificationHandler;
import rocks.cleanstone.net.netty.pipeline.inbound.InsulatedPacketDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.PacketDataDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.PacketHandler;
import rocks.cleanstone.net.netty.pipeline.outbound.CompressionEncoder;

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
                new ByteStreamDecoder(),
                new CompressionDecoder(),
                new PacketDataDecoder(),
                new InsulatedPacketDecoder(nettyNetworking.getProtocol()),
                new PacketHandler(nettyNetworking));
        // outbound
        channel.pipeline().addLast(
                new CompressionEncoder());
    }
}
