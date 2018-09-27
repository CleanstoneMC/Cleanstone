package rocks.cleanstone.game.command.cleanstone;

import java.util.List;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.SimpleWorldManager;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.generation.WorldGenerationParameter;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.PlayerManager;

@Component
public class WorldCommand extends SimpleCommand {
    private final SimpleWorldManager worldManager;
    private final PlayerChunkLoadService playerChunkLoadService;
    private final PlayerManager playerManager;
    private final List<WorldGenerator> worldGenerators;

    public WorldCommand(
            SimpleWorldManager worldManager,
            PlayerChunkLoadService playerChunkLoadService,
            PlayerManager playerManager,
            List<WorldGenerator> worldGenerators
    ) {
        super("world");
        this.worldManager = worldManager;
        this.playerChunkLoadService = playerChunkLoadService;
        this.playerManager = playerManager;
        this.worldGenerators = worldGenerators;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        String command = commandMessage.requireParameter(String.class);
        switch (command) {
            case "reload":
                reloadWorld(commandMessage);
                break;
            case "delete":
                deleteWorld(commandMessage);
                break;
            case "generation":
                changeGeneration(commandMessage);
                break;
            default:
                commandMessage.getCommandSender().sendRawMessage("unknown world command: " + command);
        }
    }

    private void reloadWorld(CommandMessage commandMessage) {
        worldManager.getLoadedWorlds()
                .forEach(World::unloadRegions);

        invalidateLoadedPlayerChunks();
    }

    private void deleteWorld(CommandMessage commandMessage) {
        worldManager.getLoadedWorlds()
                .forEach(w -> {
                    w.unloadRegions();
                    w.delete();
                });

        invalidateLoadedPlayerChunks();
    }

    private void changeGeneration(CommandMessage commandMessage) {
        String command = commandMessage.requireParameter(String.class);
        switch (command) {
            case "list":
                listGenerationParameters(commandMessage);
                break;
            case "set":
                setGenerationParameter(commandMessage);
                break;
            default:
                commandMessage.getCommandSender().sendRawMessage("unknown world generation command: " + command);
        }
    }

    private void listGenerationParameters(CommandMessage commandMessage) {
        worldGenerators.forEach(wg -> {
            commandMessage.getCommandSender().sendRawMessage(wg.getName());
            wg.getGenerationParameters().forEach((key, value) -> {
                commandMessage.getCommandSender().sendRawMessage(
                        String.format("    %s: %s", key, value)
                );
            });
        });
    }

    private void setGenerationParameter(CommandMessage commandMessage) {
        WorldGenerationParameter parameter = commandMessage.requireParameter(WorldGenerationParameter.class);
        double value = commandMessage.requireParameter(Double.class);

        worldGenerators.forEach(wg -> wg.setGenerationParameter(parameter, value));
    }

    private void invalidateLoadedPlayerChunks() {
        playerManager.getOnlinePlayers().forEach(player -> {
            playerChunkLoadService.unloadAllChunks(player);

            int x = player.getEntity().getPosition().getXAsInt() << 4;
            int z = player.getEntity().getPosition().getYAsInt() << 4;
            playerChunkLoadService.loadChunk(player, x, z);
        });
    }
}
