package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class UseItemListener {

    private final PlayerManager playerManager;

    public UseItemListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockPlace(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerBlockPlacementPacket)) {
            return;
        }

        PlayerBlockPlacementPacket playerBlockPlacementPacket = (PlayerBlockPlacementPacket) event.getPacket();

        BlockChangePacket blockChangePacket = new BlockChangePacket(
                playerBlockPlacementPacket.getLocation(), ImmutableBlock.of(VanillaMaterial.DIRT));

        playerManager.broadcastPacket(blockChangePacket);
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockBreak(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerDiggingPacket)) {
            return;
        }

        PlayerDiggingPacket playerDiggingPacket = (PlayerDiggingPacket) event.getPacket();

        BlockChangePacket blockChangePacket = new BlockChangePacket(
                playerDiggingPacket.getLocation(), ImmutableBlock.of(VanillaMaterial.AIR));

        playerManager.broadcastPacket(blockChangePacket);
    }
}
