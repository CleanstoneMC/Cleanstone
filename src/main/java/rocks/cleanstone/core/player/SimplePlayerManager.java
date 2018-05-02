package rocks.cleanstone.core.player;

import com.google.common.collect.Sets;

import org.springframework.context.event.EventListener;

import java.util.Collection;

import rocks.cleanstone.net.event.ConnectionClosedEvent;
import rocks.cleanstone.io.data.InGamePlayerDataRepository;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;

public class SimplePlayerManager implements PlayerManager {

    private final Collection<OnlinePlayer> onlinePlayers = Sets.newConcurrentHashSet();

    @Override
    public Collection<OnlinePlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    @Override
    public OnlinePlayer getOnlinePlayer(PlayerId id) {
        return null;
    }

    @Override
    public Collection<PlayerId> getAllPlayerIds() {
        return null;
    }

    @Override
    public InGamePlayerDataRepository getPlayerDataContainer(PlayerId id) {
        return null;
    }

    @Override
    public void addOnlinePlayer(OnlinePlayer player) {
        onlinePlayers.add(player);
    }

    @Override
    public void removeOnlinePlayer(OnlinePlayer player) {
        onlinePlayers.remove(player);
    }

    @Override
    public void kickPlayer(OnlinePlayer player, Text reason) {
        removeOnlinePlayer(player);
        player.getConnection().close(new DisconnectPacket(reason));
    }

    @EventListener
    public void onPlayerConnectionClosed(ConnectionClosedEvent event) {
    }
}
