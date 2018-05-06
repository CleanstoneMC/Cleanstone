package rocks.cleanstone.core.player;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.Nullable;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.player.event.PlayerInitializationEvent;
import rocks.cleanstone.core.player.event.PlayerJoinEvent;
import rocks.cleanstone.core.player.event.PlayerQuitEvent;
import rocks.cleanstone.core.player.event.PlayerTerminationEvent;
import rocks.cleanstone.io.data.InGamePlayerDataRepository;

public class SimplePlayerManager implements PlayerManager {

    private final Collection<Player> onlinePlayers = Sets.newConcurrentHashSet();
    private final Collection<PlayerID> playerIDs = Sets.newConcurrentHashSet();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<Player> getOnlinePlayers() {
        return ImmutableSet.copyOf(onlinePlayers);
    }

    @Override
    @Nullable
    public Player getOnlinePlayer(PlayerID id) {
        return onlinePlayers.stream().filter(player -> player.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Collection<PlayerID> getAllPlayerIDs() {
        return ImmutableSet.copyOf(playerIDs);
    }

    @Override
    public InGamePlayerDataRepository getPlayerDataContainer(PlayerID id) {
        // TODO get player data repo
        return null;
    }

    @Override
    public PlayerID getPlayerID(UUID uuid, String accountName) {
        return playerIDs.stream().filter(id -> id.getUUID().equals(uuid)).findFirst()
                .orElse(registerNewPlayerID(uuid, accountName));
    }

    private PlayerID registerNewPlayerID(UUID uuid, String accountName) {
        PlayerID id = new SimplePlayerID(uuid, accountName);
        playerIDs.add(id);
        return id;
    }

    @Override
    public void initializePlayer(Player player) {
        logger.info("Initializing player");
        Preconditions.checkState(onlinePlayers.add(player),
                "Cannot initialize already initialized player " + player);

        CleanstoneServer.publishEvent(new PlayerInitializationEvent(player));
        CleanstoneServer.publishEvent(new PlayerJoinEvent(player));
        //player.sendPacket(new DisconnectPacket(Text.of("Kicked")));
    }

    @Override
    public void terminatePlayer(Player player) {
        logger.info("Terminating player");
        Preconditions.checkState(onlinePlayers.contains(player),
                "Cannot terminate already terminated / non-initialized player " + player);

        CleanstoneServer.publishEvent(new PlayerQuitEvent(player));
        CleanstoneServer.publishEvent(new PlayerTerminationEvent(player));
        onlinePlayers.remove(player);
    }
}
