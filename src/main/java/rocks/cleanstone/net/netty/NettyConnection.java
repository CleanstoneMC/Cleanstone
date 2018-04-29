package rocks.cleanstone.net.netty;

import java.net.InetAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import rocks.cleanstone.net.AbstractConnection;
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
