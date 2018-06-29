package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockBreakEvent;
import rocks.cleanstone.game.block.event.BlockPlaceEvent;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

import java.util.ArrayList;

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
        playerBlockPlacementPacket.getPosition().setWorld(event.getPlayer().getEntity().getLocation().getPosition().getWorld()); //TODO: Remove this please O_O

        Position newBlockPosition = new Position(playerBlockPlacementPacket.getPosition());
        switch (playerBlockPlacementPacket.getFace()) {
            case TOP:
                newBlockPosition.addY(1);
                break;
            case BOTTOM:
                newBlockPosition.addY(-1);
                break;
            case NORTH:
                newBlockPosition.addZ(-1);
                break;
            case SOUTH:
                newBlockPosition.addZ(1);
                break;
            case EAST:
                newBlockPosition.addX(1);
                break;
            case WEST:
                newBlockPosition.addX(-1);
                break;
        }

        Player player = event.getPlayer();

        ItemStack itemStack = player.getEntity().getItemByHand(playerBlockPlacementPacket.getHand());

        if (itemStack == null) {
            return;
        }

        Block placedBlock = ImmutableBlock.of(itemStack.getItemType().getMaterial(), (byte) itemStack.getMetadata());

        newBlockPosition.getWorld().setBlockAt(newBlockPosition, placedBlock);
        CleanstoneServer.publishEvent(new BlockPlaceEvent(placedBlock, newBlockPosition, player, playerBlockPlacementPacket.getFace()));

        //TODO: Move this to a BlockPlaceEvent Listener?
        BlockChangePacket blockChangePacket = new BlockChangePacket(newBlockPosition, placedBlock);
        playerManager.broadcastPacket(blockChangePacket);
    }

    @Async(value = "playerExec")
    @EventListener
    public void onBlockBreak(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerDiggingPacket)) {
            return;
        }

        PlayerDiggingPacket playerDiggingPacket = (PlayerDiggingPacket) event.getPacket();
        playerDiggingPacket.getPosition().setWorld(event.getPlayer().getEntity().getLocation().getPosition().getWorld()); //TODO: Remove this please O_O

        Block placedBlock = ImmutableBlock.of(VanillaMaterial.AIR);

        playerDiggingPacket.getPosition().getWorld().setBlockAt(playerDiggingPacket.getPosition(), placedBlock);
        CleanstoneServer.publishEvent(new BlockBreakEvent(placedBlock, playerDiggingPacket.getPosition(), event.getPlayer(), new ArrayList<>()));//TODO: Add Drops

        BlockChangePacket blockChangePacket = new BlockChangePacket(
                playerDiggingPacket.getPosition(), placedBlock);

        playerManager.broadcastPacket(blockChangePacket);
    }
}
