package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockPlaceEvent;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class PlayerBlockPlacementPacketListener {

    private final PlayerManager playerManager;
    private final MaterialRegistry materialRegistry;

    @Autowired
    public PlayerBlockPlacementPacketListener(PlayerManager playerManager, MaterialRegistry materialRegistry) {
        this.playerManager = playerManager;
        this.materialRegistry = materialRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<PlayerBlockPlacementPacket> playerInboundPacketEvent) {

        PlayerBlockPlacementPacket packet = playerInboundPacketEvent.getPacket();

        Position newBlockPosition = new Position(packet.getPosition().toVector()
                .addVector(packet.getFace().toUnitVector()));

        Player player = playerInboundPacketEvent.getPlayer();

        ItemStack itemStack = player.getEntity().getItemByHand(packet.getHand());

        if (itemStack == null) {
            return;
        }

        BlockType blockType = materialRegistry.getBlockTypeByItemType(itemStack.getType());
        if (blockType != null) {
            Block placedBlock = ImmutableBlock.of(blockType);

            player.getEntity().getWorld().setBlockAt(newBlockPosition, placedBlock);
            CleanstoneServer.publishEvent(new BlockPlaceEvent(placedBlock, newBlockPosition, player, packet.getFace()));

            //TODO: Move this to a BlockPlaceEvent Listener?
            BlockChangePacket blockChangePacket = new BlockChangePacket(newBlockPosition, placedBlock.getState());
            playerManager.broadcastPacket(blockChangePacket);
        }
    }
}
