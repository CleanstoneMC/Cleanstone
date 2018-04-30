package rocks.cleanstone.core.player;

import java.util.Collection;

import rocks.cleanstone.io.data.InGamePlayerDataRepository;
import rocks.cleanstone.net.minecraft.packet.data.Chat;

public interface PlayerManager {

    Collection<OnlinePlayer> getOnlinePlayers();

    OnlinePlayer getOnlinePlayer(PlayerId id);

    Collection<PlayerId> getAllPlayerIds();

    InGamePlayerDataRepository getPlayerDataContainer(PlayerId id);

    void addOnlinePlayer(OnlinePlayer player);

    void removeOnlinePlayer(OnlinePlayer player);

    void kickPlayer(OnlinePlayer player, Chat reason);
}
