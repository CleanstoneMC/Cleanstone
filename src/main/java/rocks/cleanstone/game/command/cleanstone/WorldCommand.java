package rocks.cleanstone.game.command.cleanstone;

import org.springframework.stereotype.Component;

import java.util.List;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.SimpleWorldManager;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.generation.FractalWorldGenerationParameter;
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

        addSubCommand(new ReloadWorldCommand());
        addSubCommand(new DeleteWorldCommand());
        addSubCommand(new WorldGenerationCommand());
    }

    private void invalidateLoadedPlayerChunks() {
        playerManager.getOnlinePlayers().forEach(player -> {
            playerChunkLoadService.unloadAllChunks(player);

            int x = player.getEntity().getPosition().getXAsInt() << 4;
            int z = player.getEntity().getPosition().getYAsInt() << 4;
            playerChunkLoadService.loadChunk(player, ChunkCoords.of(x, z));
        });
    }

    class ReloadWorldCommand extends SimpleCommand {

        ReloadWorldCommand() {
            super("reload");
        }

        @Override
        public void execute(CommandMessage commandMessage) {
            worldManager.getLoadedWorlds()
                    .forEach(World::unloadRegions);

            invalidateLoadedPlayerChunks();
        }
    }

    class DeleteWorldCommand extends SimpleCommand {

        DeleteWorldCommand() {
            super("delete");
        }

        @Override
        public void execute(CommandMessage commandMessage) {
            worldManager.getLoadedWorlds()
                    .forEach(w -> {
                        w.unloadRegions();
                        w.delete();
                    });

            invalidateLoadedPlayerChunks();
        }
    }

    class WorldGenerationCommand extends SimpleCommand {

        WorldGenerationCommand() {
            super("generation");

            addSubCommand(new ListWorldGenerationParamsCommand());
            addSubCommand(new SetWorldGenerationParamCommand());
            addSubCommand(new SetFractalWorldGenerationParamCommand());
        }


        class ListWorldGenerationParamsCommand extends SimpleCommand {

            ListWorldGenerationParamsCommand() {
                super("list");
            }

            @Override
            public void execute(CommandMessage commandMessage) {
                worldGenerators.forEach(wg -> {
                    commandMessage.getCommandSender().sendRawMessage(wg.getName());
                    wg.getGenerationParameters().forEach((key, value) -> {
                        commandMessage.getCommandSender().sendRawMessage(
                                String.format("    %s: %s", key, value)
                        );
                    });
                    wg.getFractalGenerationParameters().cellSet().forEach((cell) -> {
                        commandMessage.getCommandSender().sendRawMessage(
                                String.format("    %s-%s %s", cell.getRowKey(),
                                        cell.getColumnKey(), cell.getValue())
                        );
                    });
                });
            }
        }

        class SetWorldGenerationParamCommand extends SimpleCommand {

            SetWorldGenerationParamCommand() {
                super("set", WorldGenerationParameter.class, Double.class);
            }

            @Override
            public void execute(CommandMessage commandMessage) {
                WorldGenerationParameter parameter = commandMessage.requireParameter(WorldGenerationParameter.class);
                double value = commandMessage.requireParameter(Double.class);

                worldGenerators.forEach(wg -> wg.setGenerationParameter(parameter, value));
            }
        }

        class SetFractalWorldGenerationParamCommand extends SimpleCommand {

            SetFractalWorldGenerationParamCommand() {
                super("setfractal", FractalWorldGenerationParameter.class, Integer.class, Double.class);
            }

            @Override
            public void execute(CommandMessage commandMessage) {
                FractalWorldGenerationParameter parameter = commandMessage
                        .requireParameter(FractalWorldGenerationParameter.class);
                int octave = commandMessage.requireParameter(Integer.class);
                double value = commandMessage.requireParameter(Double.class);

                worldGenerators.forEach(wg -> wg.setFractalGenerationParameter(parameter, octave, value));
            }
        }
    }
}
