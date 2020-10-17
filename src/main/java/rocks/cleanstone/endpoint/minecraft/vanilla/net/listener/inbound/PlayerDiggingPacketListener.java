package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.DiggingStatus;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.block.event.BlockDamageEvent;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.game.inventory.event.ItemDropEvent;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.Player;

import java.util.Collections;

@Slf4j
@Component
public class PlayerDiggingPacketListener {

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
            }, e -> log.error("Could not get Block", e));
        }

        if (packet.getDiggingStatus() == DiggingStatus.DROP_ITEM) {
            ItemDropEvent itemDropEvent = new ItemDropEvent(new Position(player.getEntity().getPosition()), player, player.getEntity().getItemByHand(Hand.MAIN_HAND));

            CleanstoneServer.publishEvent(itemDropEvent);
        }
    }
}
