package rocks.cleanstone.net.netty;

import java.net.InetAddress;

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
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.ProtocolState;

public class NettyConnection extends AbstractConnection {

    private Channel channel;
    private boolean closed = false;

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
        super.setEncryptionEnabled(encryptionEnabled);
        channel.pipeline().replace("encryptionEncoder", "encryptionEncoder",
                encryptionEnabled ? new EncryptionEncoder() : new ChannelOutboundHandlerAdapter());
        channel.pipeline().replace("encryptionDecoder", "encryptionDecoder",
                encryptionEnabled ? new EncryptionDecoder() : new ChannelInboundHandlerAdapter());
    }

    @Override
    public void setCompressionEnabled(boolean compressionEnabled) {
        super.setCompressionEnabled(compressionEnabled);
        channel.pipeline().replace("compressionEncoder", "compressionEncoder",
                compressionEnabled ? new JdkZlibEncoder() : new ChannelOutboundHandlerAdapter());
        channel.pipeline().replace("compressionDecoder", "compressionDecoder",
                compressionEnabled ? new JdkZlibDecoder() : new ChannelInboundHandlerAdapter());
    }

    @Override
    public void sendPacket(Packet packet) {
        if (closed) throw new IllegalStateException("Connection has been closed");
        channel.writeAndFlush(packet);
    }

    @Override
    public void close() {
        close(null);
    }

    @Override
    public void close(Packet packet) {
        if (!closed) {
            if (packet != null && channel.isActive()) {
                channel.writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
            } else {
                channel.flush();
                channel.close();
            }
            closed = true;
        }
    }
}
