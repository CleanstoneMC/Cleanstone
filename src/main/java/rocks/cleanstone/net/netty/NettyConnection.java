package rocks.cleanstone.net.netty;

import com.google.common.util.concurrent.Futures;

import java.net.InetAddress;
import java.util.concurrent.Future;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import rocks.cleanstone.net.AbstractConnection;
import rocks.cleanstone.net.netty.pipeline.inbound.EncryptionDecoder;
import rocks.cleanstone.net.netty.pipeline.outbound.EncryptionEncoder;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.ProtocolState;

public class NettyConnection extends AbstractConnection {

    private Channel channel;

    public NettyConnection(Channel channel, InetAddress address, ClientProtocolLayer clientProtocolLayer,
                           ProtocolState protocolState) {
        super(address, clientProtocolLayer, protocolState);
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void setEncryptionEnabled(boolean encryptionEnabled) {
        if (isClosed()) return;
        super.setEncryptionEnabled(encryptionEnabled);
        channel.pipeline().replace("encryptionEncoder", "encryptionEncoder",
                encryptionEnabled ? new EncryptionEncoder() : new ChannelOutboundHandlerAdapter());
        channel.pipeline().replace("encryptionDecoder", "encryptionDecoder",
                encryptionEnabled ? new EncryptionDecoder() : new ChannelInboundHandlerAdapter());
    }

    @Override
    public void setCompressionEnabled(boolean compressionEnabled) {
        if (isClosed()) return;
        super.setCompressionEnabled(compressionEnabled);
        channel.pipeline().replace("compressionEncoder", "compressionEncoder",
                compressionEnabled ? new JdkZlibEncoder() : new ChannelOutboundHandlerAdapter());
        channel.pipeline().replace("compressionDecoder", "compressionDecoder",
                compressionEnabled ? new JdkZlibDecoder() : new ChannelInboundHandlerAdapter());
    }

    @Override
    public Future<Void> sendPacket(Packet packet) {
        if (!isClosed()) {
            return channel.writeAndFlush(packet);
        }
        return Futures.immediateFuture(null);
    }

    @Override
    public Future<Void> close() {
        return close(null);
    }

    @Override
    public Future<Void> close(Packet packet) {
        if (!isClosed()) {
            if (packet != null && channel.isActive()) {
                return channel.writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
            } else {
                channel.flush();
                return channel.close();
            }
        }
        return Futures.immediateFuture(null);
    }

    @Override
    public boolean isClosed() {
        return !channel.isOpen();
    }
}
