package rocks.cleanstone.logic.world.listener;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.PlayerUnloadedChunkEvent;
import rocks.cleanstone.storage.world.WorldDataSource;

@Component
public class PlayerUnloadsChunkListener {

    private PlayerChunkLoadService playerChunkLoadService;

    public PlayerUnloadsChunkListener(PlayerChunkLoadService playerChunkLoadService) {
        this.playerChunkLoadService = playerChunkLoadService;
    }

    @EventListener
    public void onPlayerUnloadsChunk(PlayerUnloadedChunkEvent playerUnloadedChunkEvent) {
//        playerUnloadedChunkEvent.getWorld().getChunk(playerUnloadedChunkEvent.getCoords())
        playerChunkLoadService.getAllLoadedChunks();
    }
}
