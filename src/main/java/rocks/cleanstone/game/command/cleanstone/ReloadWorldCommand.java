package rocks.cleanstone.game.command.cleanstone;

import org.springframework.stereotype.Component;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.SimpleWorldManager;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.packet.outbound.UnloadChunkPacket;
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
            playerChunkLoadService.loadedChunks(player.getID().getUUID())
                    .forEach(chunk -> {
                        int x = chunk.getLeft();
                        int y = chunk.getRight();
                        UnloadChunkPacket unloadChunkPacket = new UnloadChunkPacket(x, y);
                        player.sendPacket(unloadChunkPacket);
                        playerChunkLoadService.playerUnload(player.getID().getUUID(), x, y);
                    });

            int x = player.getEntity().getPosition().getXAsInt() << 4;
            int y = player.getEntity().getPosition().getYAsInt() << 4;
            player.getEntity().getWorld().getChunk(x, y).addCallback(chunk -> {
                if (chunk == null) {
                    return;
                }

                ChunkDataPacket chunkDataPacket = new ChunkDataPacket(x, y, true, chunk.getBlockDataTable(), new NamedBinaryTag[]{});
                player.sendPacket(chunkDataPacket);
            }, throwable -> {
            });
        });
    }
}
