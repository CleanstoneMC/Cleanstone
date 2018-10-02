package rocks.cleanstone.net.event.packet.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.block.event.BlockDamageEvent;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.enums.DiggingStatus;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.player.Player;

@Component
public class PlayerDiggingPacketListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<PlayerDiggingPacket> playerInboundPacketEvent) {
        PlayerDiggingPacket packet = playerInboundPacketEvent.getPacket();
        Player player = playerInboundPacketEvent.getPlayer();
        Position position = packet.getPosition();

        if (packet.getDiggingStatus() == DiggingStatus.STARTED_DIGGING || packet.getDiggingStatus() == DiggingStatus.FINISHED_DIGGING) {
            player.getEntity().getWorld().getBlockAt(position).addCallback(block -> {
                boolean blockBroken = false;

                if (packet.getDiggingStatus() == DiggingStatus.STARTED_DIGGING) {
                    BlockDamageEvent blockDamageEvent = CleanstoneServer.publishEvent(new BlockDamageEvent(block, position, player));

                    if (!blockDamageEvent.isCancelled()) {
                        blockBroken = playerInboundPacketEvent.getPlayer().getGameMode().getRuleSet().canInstantBreakBlocks();
                    }
                }

                if (packet.getDiggingStatus() == DiggingStatus.FINISHED_DIGGING || blockBroken) {
                    BlockBreakEvent blockBreakEvent = new BlockBreakEvent(block, packet.getPosition(), playerInboundPacketEvent.getPlayer(), Collections.emptySet());

                    CleanstoneServer.publishEvent(blockBreakEvent);
                }
            }, e -> logger.error("Could not get Block", e));
        }
    }

}
