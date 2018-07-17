package rocks.cleanstone.player;

import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;

import rocks.cleanstone.game.Identity;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.data.PlayerDataSource;

public interface PlayerManager {

    Collection<Player> getOnlinePlayers();

    @Nullable
    Player getOnlinePlayer(Identity id);

    @Nullable
    Player getOnlinePlayer(Connection connection);

    @Nullable
    Player getOnlinePlayer(String name);

    Collection<Identity> getAllPlayerIDs();

    Identity getPlayerID(UUID uuid, String accountName);

    PlayerDataSource getPlayerDataSource();

    void initializePlayer(Player player);

    void terminatePlayer(Player player);

    boolean isPlayerOperator(Identity playerID);

    void broadcastPacket(Packet packet, Player... broadcastExemptions);

    boolean isTerminating(Player player);
}
