package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

import java.util.ArrayList;

public class UseItemListener {

    private final PlayerManager playerManager;
    private final MaterialRegistry materialRegistry;

    @Autowired
    public UseItemListener(PlayerManager playerManager, MaterialRegistry materialRegistry) {
        this.playerManager = playerManager;
        this.materialRegistry = materialRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockPlace(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerBlockPlacementPacket)) {
            return;
        }

        PlayerBlockPlacementPacket packet = (PlayerBlockPlacementPacket) event.getPacket();

        Position newBlockPosition = new Position(packet.getPosition().toVector()
                .addVector(packet.getFace().toUnitVector()));

        Player player = event.getPlayer();

        ItemStack itemStack = player.getEntity().getItemByHand(packet.getHand());

        if (itemStack == null) {
            return;
        }

//        BlockType blockType = materialRegistry.getBlockType(itemStack.getType().getID());
//        if (blockType != null) {
//            Block placedBlock = ImmutableBlock.of(blockType);
//
//            player.getEntity().getWorld().setBlockAt(newBlockPosition, placedBlock);
//            CleanstoneServer.publishEvent(new BlockPlaceEvent(placedBlock, newBlockPosition, player, packet.getFace()));
//
            //TODO: Move this to a BlockPlaceEvent Listener?
//            BlockChangePacket blockChangePacket = new BlockChangePacket(newBlockPosition, placedBlock);
//            playerManager.broadcastPacket(blockChangePacket);
//        }
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockBreak(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerDiggingPacket)) {
            return;
        }
        PlayerDiggingPacket packet = (PlayerDiggingPacket) event.getPacket();

        Block placedBlock = ImmutableBlock.of(VanillaBlockType.AIR);

        event.getPlayer().getEntity().getWorld().setBlockAt(packet.getPosition(), placedBlock);
        CleanstoneServer.publishEvent(new BlockBreakEvent(placedBlock, packet.getPosition(),
                event.getPlayer(), new ArrayList<>()));//TODO: Add Drops

        BlockChangePacket blockChangePacket = new BlockChangePacket(
                packet.getPosition(), placedBlock);

        playerManager.broadcastPacket(blockChangePacket);
    }
}
