package rocks.cleanstone.net.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import rocks.cleanstone.net.netty.pipeline.inbound.ByteStreamDecoder;
import rocks.cleanstone.net.netty.pipeline.inbound.IdentificationHandler;
import rocks.cleanstone.net.netty.pipeline.inbound.InboundPacketHandler;
import rocks.cleanstone.net.netty.pipeline.inbound.PacketDataDecoder;
import rocks.cleanstone.net.netty.pipeline.outbound.ByteStreamEncoder;
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
        channel.pipeline().addLast("identificationHandler", new IdentificationHandler(nettyNetworking));
        channel.pipeline().addLast("encryptionDecoder", new ChannelInboundHandlerAdapter());
        channel.pipeline().addLast("byteStreamDecoder", new ByteStreamDecoder());
        channel.pipeline().addLast("compressionDecoder", new ChannelInboundHandlerAdapter());
        channel.pipeline().addLast("packetDataDecoder", new PacketDataDecoder(nettyNetworking.getProtocol()));
        channel.pipeline().addLast("inboundPacketHandler", new InboundPacketHandler(nettyNetworking));
        // outbound
        channel.pipeline().addFirst("outboundPacketHandler", new OutboundPacketHandler(nettyNetworking));
        channel.pipeline().addFirst("packetEncoder", new PacketEncoder(nettyNetworking.getProtocol()));
        channel.pipeline().addFirst("compressionEncoder", new ChannelOutboundHandlerAdapter());
        channel.pipeline().addFirst("byteStreamEncoder", new ByteStreamEncoder());
        channel.pipeline().addFirst("encryptionEncoder", new ChannelOutboundHandlerAdapter());
    }
}
