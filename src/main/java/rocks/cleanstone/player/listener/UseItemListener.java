package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockPlaceEvent;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
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

        Block placedBlock = ImmutableBlock.of(itemStack.getItemType().getMaterial());
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

        BlockChangePacket blockChangePacket = new BlockChangePacket(
                playerDiggingPacket.getLocation(), ImmutableBlock.of(VanillaMaterial.AIR));

        playerManager.broadcastPacket(blockChangePacket);
    }
}
