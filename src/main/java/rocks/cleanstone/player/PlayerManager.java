package rocks.cleanstone.player;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.Nullable;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;

public interface PlayerManager {

    Collection<Player> getOnlinePlayers();

    @Nullable
    Player getOnlinePlayer(PlayerID id);

    @Nullable
    Player getOnlinePlayer(Connection connection);

    @Nullable
    Player getOnlinePlayer(String name);

    Collection<PlayerID> getAllPlayerIDs();

    PlayerID getPlayerID(UUID uuid, String accountName);

    void initializePlayer(Player player);

    void terminatePlayer(Player player);

    boolean isPlayerOperator(PlayerID playerID);

    void broadcastPacket(Packet packet, Player... broadcastExemptions);

    boolean isTerminating(Player player);
}
