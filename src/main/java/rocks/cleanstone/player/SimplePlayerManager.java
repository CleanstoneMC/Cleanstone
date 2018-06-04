package rocks.cleanstone.player;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;
import rocks.cleanstone.player.event.PlayerJoinEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

public class SimplePlayerManager implements PlayerManager {

    private final Collection<Player> onlinePlayers = Sets.newConcurrentHashSet();
    private final Collection<Player> terminatingPlayers = Sets.newConcurrentHashSet();
    private final Collection<PlayerID> playerIDs = Sets.newConcurrentHashSet();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<Player> getOnlinePlayers() {
        return ImmutableSet.copyOf(onlinePlayers);
    }

    @Override
    @Nullable
    public Player getOnlinePlayer(PlayerID id) {
        return onlinePlayers.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    @Nullable
    public Player getOnlinePlayer(Connection connection) {
        return onlinePlayers.stream()
                .filter(player -> player instanceof OnlinePlayer)
                .filter(player -> ((OnlinePlayer) player).getConnection() == connection)
                .findAny().orElse(null);
    }

    @Override
    @Nullable
    public Player getOnlinePlayer(String name) {
        return onlinePlayers.stream()
                .filter(player -> player.getId().getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }

    @Override
    public Collection<PlayerID> getAllPlayerIDs() {
        return ImmutableSet.copyOf(playerIDs);
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
    public boolean isPlayerOperator(PlayerID playerID) {
        List<String> ops = CleanstoneServer.getInstance().getMinecraftConfig().getOps();
        return ops.contains(playerID.getName()) || ops.contains(playerID.getUUID().toString()); //TODO: Make this beauty <3
    }

    @Override
    public void broadcastPacket(Packet packet, Player... broadcastExemptions) {
        Collection<Player> exemptions = Arrays.asList(broadcastExemptions);
        getOnlinePlayers().stream().filter(p -> !exemptions.contains(p))
                .forEach(onlinePlayer -> onlinePlayer.sendPacket(packet));
    }

    @Override
    public void initializePlayer(Player player) {
        logger.info("Initializing player");
        Preconditions.checkState(onlinePlayers.add(player),
                "Cannot initialize already initialized player " + player);
        try {
            CleanstoneServer.publishEvent(new AsyncPlayerInitializationEvent(player), true);
        } catch (Exception e) {
            logger.error("Error occurred during player initialization for " + player.getId().getName(), e);
            player.kick(Text.of("Error occurred during player initialization"));
            return;
        }
        CleanstoneServer.publishEvent(new PlayerJoinEvent(player));
    }

    @Override
    public synchronized void terminatePlayer(Player player) {
        logger.info("Terminating player");
        Preconditions.checkState(onlinePlayers.contains(player),
                "Cannot terminate already terminated / non-initialized player " + player);
        terminatingPlayers.add(player);
        CleanstoneServer.publishEvent(new PlayerQuitEvent(player));
        CleanstoneServer.publishEvent(new AsyncPlayerTerminationEvent(player));
        onlinePlayers.remove(player);
        terminatingPlayers.remove(player);
    }

    @Override
    public boolean isTerminating(Player player) {
        return terminatingPlayers.contains(player);
    }
}
