package rocks.cleanstone.net.mcpe;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.player.PlayerManager;

public class StatusProvider extends MinecraftIdentifier {

    private final PlayerManager playerManager;
    private final String motd;
    private final int maxPlayers;

    @Autowired
    public StatusProvider(MinecraftConfig minecraftConfig, PlayerManager playerManager) {
        super(minecraftConfig.getMotd(), 137, "1.2", playerManager.getOnlinePlayers().size(),
                minecraftConfig.getMaxPlayers(), ThreadLocalRandom.current().nextLong(),
                "Cleanstone World", "Creative");
        this.motd = minecraftConfig.getMotd();
        this.maxPlayers = minecraftConfig.getMaxPlayers();
        this.playerManager = playerManager;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void updateStatus() {
        setOnlinePlayerCount(playerManager.getOnlinePlayers().size());
    }
}
