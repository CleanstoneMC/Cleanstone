package rocks.cleanstone.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetAddress;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.player.PlayerManager;

@Slf4j
public class NettyNetworking extends AbstractNetworking implements SmartLifecycle {
    private final boolean epoll = false;
    private final int socketBacklog = 128;
    private final boolean socketKeepAlive = true;
    private final PlayerManager playerManager;
    private EventLoopGroup bossGroup, workerGroup;
    private boolean running = false;

    public NettyNetworking(int port, InetAddress address, Protocol protocol, PlayerManager playerManager) {
        super(port, address, protocol);
        this.playerManager = playerManager;
    }

    @Override
    public void start() {
        bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        final ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer(this))
                .option(ChannelOption.SO_BACKLOG, socketBacklog)
                .childOption(ChannelOption.SO_KEEPALIVE, socketKeepAlive);
        bootstrap.localAddress(this.getAddress(), this.getPort());
        bootstrap.bind().addListener(future -> {
            if (future.isSuccess()) {
                log.info(CleanstoneServer.getMessage("net.netty.bind-successful",
                        protocol.getClass().getSimpleName(), getAddress(), getPort() + ""));
            } else {
                log.error(CleanstoneServer.getMessage("net.netty.bind-failure",
                        getAddress().getHostAddress(), getPort() + ""), future.cause());
            }
        });
        running = true;
    }

    @Override
    public void stop() {
        playerManager.getOnlinePlayers().forEach(player -> player.kick(
                Text.ofLocalized("game.command.cleanstone.default-stop-reason", player.getLocale())));
        log.info("Closing " + protocol.getClass().getSimpleName());
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        running = false;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(@Nonnull Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 10;
    }
}
