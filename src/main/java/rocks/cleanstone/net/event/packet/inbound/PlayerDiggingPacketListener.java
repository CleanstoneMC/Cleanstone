package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.PlayerManager;

import java.util.ArrayList;

@Component
public class PlayerDiggingPacketListener extends PlayerInboundPacketEventListener<PlayerDiggingPacket> {
    private PlayerManager playerManager;

    @Autowired
    public PlayerDiggingPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    @Override
    public void onPacket(PlayerInboundPacketEvent<PlayerDiggingPacket> playerInboundPacketEvent) {
        PlayerDiggingPacket packet = playerInboundPacketEvent.getPacket();

        Block placedBlock = ImmutableBlock.of(VanillaBlockType.AIR);

        playerInboundPacketEvent.getPlayer().getEntity().getWorld().setBlockAt(packet.getPosition(), placedBlock);
        CleanstoneServer.publishEvent(new BlockBreakEvent(placedBlock, packet.getPosition(),
                playerInboundPacketEvent.getPlayer(), new ArrayList<>()));//TODO: Add Drops

        BlockChangePacket blockChangePacket = new BlockChangePacket(packet.getPosition(), placedBlock.getState());

        playerManager.broadcastPacket(blockChangePacket);
    }
}
