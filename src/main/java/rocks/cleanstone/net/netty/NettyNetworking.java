package rocks.cleanstone.net.netty;

import java.net.InetAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class NettyNetworking extends AbstractNetworking {

    private final boolean epoll = true;
    private final int socketBacklog = 128;
    private final boolean socketKeepAlive = true;

    public NettyNetworking(int port, InetAddress address, PacketTypeRegistry packetTypeRegistry, Protocol protocol) {
        super(port, address, packetTypeRegistry, protocol);
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer(this))
                    .option(ChannelOption.SO_BACKLOG, socketBacklog)
                    .childOption(ChannelOption.SO_KEEPALIVE, socketKeepAlive);
            bootstrap.bind();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void sendPacket(Connection connection, SendPacket packet) {
    }
}
