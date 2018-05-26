package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.UseItemPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class UseItemListener {

    private final PlayerManager playerManager;

    public UseItemListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockPlace(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof PlayerBlockPlacementPacket)) {
            return;
        }

        PlayerBlockPlacementPacket playerBlockPlacementPacket = (PlayerBlockPlacementPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());

        BlockChangePacket blockChangePacket = new BlockChangePacket(playerBlockPlacementPacket.getLocation(), ImmutableBlock.of(VanillaMaterial.DIRT));

        playerManager.broadcastPacket(blockChangePacket);
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockBreak(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof PlayerDiggingPacket)) {
            return;
        }

        PlayerDiggingPacket playerDiggingPacket = (PlayerDiggingPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());

        BlockChangePacket blockChangePacket = new BlockChangePacket(playerDiggingPacket.getLocation(), ImmutableBlock.of(VanillaMaterial.AIR));

        playerManager.broadcastPacket(blockChangePacket);
    }
}
