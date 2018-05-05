package rocks.cleanstone.core.player;

import java.util.Collection;
import java.util.UUID;

import rocks.cleanstone.io.data.InGamePlayerDataRepository;

public interface PlayerManager {

    Collection<Player> getOnlinePlayers();

    Player getOnlinePlayer(PlayerID id);

    Collection<PlayerID> getAllPlayerIDs();

    InGamePlayerDataRepository getPlayerDataContainer(PlayerID id);

    PlayerID getPlayerID(UUID uuid);

    void initializePlayer(Player player);

    void terminatePlayer(Player player);
}
