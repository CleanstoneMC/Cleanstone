package rocks.cleanstone.net.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.net.InetAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.player.PlayerManager;

public class NettyNetworking extends AbstractNetworking {

    private final boolean epoll = false;
    private final int socketBacklog = 128;
    private final boolean socketKeepAlive = true;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PlayerManager playerManager;
    private EventLoopGroup bossGroup, workerGroup;

    public NettyNetworking(int port, InetAddress address, Protocol protocol, PlayerManager playerManager) {
        super(port, address, protocol);
        this.playerManager = playerManager;
    }

    public void init() {
        bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer(this))
                .option(ChannelOption.SO_BACKLOG, socketBacklog)
                .childOption(ChannelOption.SO_KEEPALIVE, socketKeepAlive);
        bootstrap.localAddress(this.getAddress(), this.getPort());
        bootstrap.bind().addListener(future -> {
            if (future.isSuccess()) {
                logger.info(CleanstoneServer.getMessage("net.netty.bind-successful",
                        protocol.getClass().getSimpleName(), getAddress(), getPort() + ""));
            } else {
                logger.error(CleanstoneServer.getMessage("net.netty.bind-failure",
                        getAddress().getHostAddress(), getPort() + ""), future.cause());
            }
        });
    }

    @EventListener
    public void onPreDestroy(ContextClosedEvent e) {
        playerManager.getOnlinePlayers().forEach(player -> player.kick(
                Text.ofLocalized("game.command.cleanstone.default-stop-reason", player.getLocale())));
        logger.info("Closing " + protocol.getClass().getSimpleName());
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
