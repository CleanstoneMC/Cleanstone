package rocks.cleanstone.player;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.io.data.InGamePlayerDataRepository;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;
import rocks.cleanstone.player.event.PlayerJoinEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

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
    public Player getPlayerByConnection(Connection connection) {
        Optional<Player> optionalPlayer = onlinePlayers.stream().filter(player -> player instanceof OnlinePlayer && ((OnlinePlayer) player).getConnection() == connection).findAny();

        return optionalPlayer.get();
    }

    @Override
    public void initializePlayer(Player player) {
        logger.info("Initializing player");
        Preconditions.checkState(onlinePlayers.add(player),
                "Cannot initialize already initialized player " + player);
        try {
            CleanstoneServer.publishEvent(new AsyncPlayerInitializationEvent(player));
        } catch (Exception e) {
            logger.error("Error occurred during player initialization for " + player.getId().getName(), e);
            player.kick(Text.of("Error occurred during player initialization"));
            return;
        }
        CleanstoneServer.publishEvent(new PlayerJoinEvent(player));
    }

    @Override
    public void terminatePlayer(Player player) {
        logger.info("Terminating player");
        Preconditions.checkState(onlinePlayers.contains(player),
                "Cannot terminate already terminated / non-initialized player " + player);

        CleanstoneServer.publishEvent(new PlayerQuitEvent(player));
        try {
            CleanstoneServer.publishEvent(new AsyncPlayerTerminationEvent(player));
        } catch (Exception e) {
            logger.error("Error occurred during player termination for " + player.getId().getName(), e);
            return;
        }
        onlinePlayers.remove(player);
    }
}
