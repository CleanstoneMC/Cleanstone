package rocks.cleanstone.core.player;

import java.util.Collection;

public interface PlayerManager {

    Collection<OnlinePlayer> getOnlinePlayers();

    OnlinePlayer getOnlinePlayer(PlayerId id);

    Collection<PlayerId> getAllPlayerIds();

    PlayerDataContainer getPlayerDataContainer(PlayerId id);
}
