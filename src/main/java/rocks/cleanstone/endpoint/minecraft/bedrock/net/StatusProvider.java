package rocks.cleanstone.endpoint.minecraft.bedrock.net;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.config.GameConfig;
import rocks.cleanstone.player.PlayerManager;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class StatusProvider extends MinecraftIdentifier {

    private final PlayerManager playerManager;
    private final String motd;
    private final int maxPlayers;

    @Autowired
    public StatusProvider(GameConfig gameConfig, PlayerManager playerManager) {
        super(gameConfig.getMotd(), 137, "1.2", playerManager.getOnlinePlayers().size(),
                gameConfig.getMaxPlayers(), ThreadLocalRandom.current().nextLong(),
                "Cleanstone World", "Creative");
        this.motd = gameConfig.getMotd();
        this.maxPlayers = gameConfig.getMaxPlayers();
        this.playerManager = playerManager;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void updateStatus() {
        setOnlinePlayerCount(playerManager.getOnlinePlayers().size());
    }
}
