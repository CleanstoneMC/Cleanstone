package rocks.cleanstone.net.mcpe;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.player.PlayerManager;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class StatusProvider extends MinecraftIdentifier {

    private final PlayerManager playerManager;

    @Autowired
    public StatusProvider(MinecraftConfig minecraftConfig, PlayerManager playerManager) {
        super(minecraftConfig.getMotd(), 137, "1.2", playerManager.getOnlinePlayers().size(),
                minecraftConfig.getMaxPlayers(), ThreadLocalRandom.current().nextLong(),
                "Cleanstone World", "Creative");
        String motd = minecraftConfig.getMotd();
        int maxPlayers = minecraftConfig.getMaxPlayers();
        this.playerManager = playerManager;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void updateStatus() {
        setOnlinePlayerCount(playerManager.getOnlinePlayers().size());
    }
}
