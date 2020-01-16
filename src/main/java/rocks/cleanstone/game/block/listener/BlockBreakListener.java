package rocks.cleanstone.game.block.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.gamemode.GameModeRuleSet;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.player.PlayerManager;

@Component
public class BlockBreakListener {

    private final PlayerManager playerManager;

    @Autowired
    public BlockBreakListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async
    @EventListener(condition = "#blockBreakEvent.cancelled == false")
    public void onBlockBreak(BlockBreakEvent blockBreakEvent) {
        GameModeRuleSet ruleSet = blockBreakEvent.getPlayer().getGameMode().getRuleSet();

        if (!ruleSet.canModifyWorld()) {
            BlockChangePacket blockChangePacket = new BlockChangePacket(blockBreakEvent.getPosition(), blockBreakEvent.getBlock().getState());

            playerManager.broadcastPacket(blockChangePacket);
            return;
        }

        BlockState blockState = BlockState.of(VanillaBlockType.AIR);

        blockBreakEvent.getPlayer().getEntity().getWorld().setBlockAt(blockBreakEvent.getPosition(), ImmutableBlock.of(blockState));
        BlockChangePacket blockChangePacket = new BlockChangePacket(blockBreakEvent.getPosition(), blockState);

        playerManager.broadcastPacket(blockChangePacket);
    }
}