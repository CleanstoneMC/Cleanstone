package rocks.cleanstone.player;

import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.storage.player.PlayerDataSource;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

public interface PlayerManager {

    Collection<Player> getOnlinePlayers();

    @Nullable
    Player getOnlinePlayer(Identity id);

    @Nullable
    Player getOnlinePlayer(Connection connection);

    @Nullable
    Player getOnlinePlayer(String name);

    @Nullable
    Player getOnlinePlayer(Entity entity);

    Collection<Identity> getAllPlayerIDs();

    Identity getPlayerID(UUID uuid, String accountName);

    PlayerDataSource getPlayerDataSource();

    void initializePlayer(Player player);

    void terminatePlayer(Player player);

    boolean isPlayerOperator(Identity playerID);

    void broadcastPacket(Packet packet, Player... broadcastExemptions);

    boolean isTerminating(Player player);
}
