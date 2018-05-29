package rocks.cleanstone.player;

import rocks.cleanstone.io.data.InGamePlayerDataRepository;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

public interface PlayerManager {

    Collection<Player> getOnlinePlayers();

    @Nullable
    Player getOnlinePlayer(PlayerID id);

    @Nullable
    Player getOnlinePlayer(Connection connection);

    @Nullable
    Player getOnlinePlayer(String name);

    Collection<PlayerID> getAllPlayerIDs();

    InGamePlayerDataRepository getPlayerDataContainer(PlayerID id);

    PlayerID getPlayerID(UUID uuid, String accountName);

    void initializePlayer(Player player);

    void terminatePlayer(Player player);

    boolean isPlayerOperator(PlayerID playerID);

    void broadcastPacket(Packet packet, Player... broadcastExemptions);
}
