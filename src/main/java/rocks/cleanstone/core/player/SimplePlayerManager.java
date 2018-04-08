package rocks.cleanstone.core.player;

import rocks.cleanstone.io.data.InGamePlayerDataRepository;

import java.util.Collection;

public class SimplePlayerManager implements PlayerManager {
    @Override
    public Collection<OnlinePlayer> getOnlinePlayers() {
        return null;
    }

    @Override
    public OnlinePlayer getOnlinePlayer(PlayerId id) {
        return null;
    }

    @Override
    public Collection<PlayerId> getAllPlayerIds() {
        return null;
    }

    @Override
    public InGamePlayerDataRepository getPlayerDataContainer(PlayerId id) {
        return null;
    }
}
