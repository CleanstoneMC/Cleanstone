package rocks.cleanstone.game.command.cleanstone;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.SimpleWorldManager;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.PlayerManager;

@Component
public class ReloadWorldCommand extends SimpleCommand {
    private final SimpleWorldManager worldManager;
    private final PlayerChunkLoadService playerChunkLoadService;
    private final PlayerManager playerManager;

    public ReloadWorldCommand(
            SimpleWorldManager worldManager,
            PlayerChunkLoadService playerChunkLoadService,
            PlayerManager playerManager
    ) {
        super("reloadworld");
        this.worldManager = worldManager;
        this.playerChunkLoadService = playerChunkLoadService;
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        worldManager.getLoadedWorlds()
                .forEach(World::unloadRegions);

        playerManager.getOnlinePlayers().forEach(player -> {
            playerChunkLoadService.unloadAllChunks(player);

            int x = player.getEntity().getPosition().getXAsInt() << 4;
            int z = player.getEntity().getPosition().getYAsInt() << 4;
            playerChunkLoadService.loadChunk(player, x, z);
        });
    }
}
