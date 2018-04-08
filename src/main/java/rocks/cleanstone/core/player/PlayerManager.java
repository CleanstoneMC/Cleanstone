package rocks.cleanstone.core.player;

import rocks.cleanstone.io.data.InGamePlayerDataRepository;

import java.util.Collection;

public interface PlayerManager {

    Collection<OnlinePlayer> getOnlinePlayers();

    OnlinePlayer getOnlinePlayer(PlayerId id);

    Collection<PlayerId> getAllPlayerIds();

    InGamePlayerDataRepository getPlayerDataContainer(PlayerId id);
}
