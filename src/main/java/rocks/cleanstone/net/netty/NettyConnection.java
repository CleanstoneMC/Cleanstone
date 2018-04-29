package rocks.cleanstone.net.netty;

import java.net.InetAddress;

import io.netty.channel.Channel;
import rocks.cleanstone.net.AbstractConnection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.ProtocolState;

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
    public void sendPacket(Packet packet) {
        channel.writeAndFlush(packet);
    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public void close(Packet packet) {
        sendPacket(packet);
        close();
    }
}
