package rocks.cleanstone.net.minecraft.listener.inbound;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockPlaceEvent;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.vanilla.BlockHalf;
import rocks.cleanstone.game.block.state.property.vanilla.Facing;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;


import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockProperties.FACING;
import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockProperties.HALF;

@Component
public class PlayerBlockPlacementPacketListener {
    private final Logger logger = LoggerFactory.getLogger(PlayerBlockPlacementPacketListener.class);
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
            PropertiesBuilder properties = new PropertiesBuilder(blockType);
            computeFacing(properties, blockType, player);
            computeHalf(properties, blockType, packet.getFace());

            Block placedBlock = ImmutableBlock.of(blockType, properties.create());
            logger.debug("{} places {}", player.getName(), placedBlock);
            //noinspection unchecked

            player.getEntity().getWorld().setBlockAt(newBlockPosition, placedBlock);
            CleanstoneServer.publishEvent(new BlockPlaceEvent(placedBlock, newBlockPosition, player, packet.getFace()));

            //TODO: Move this to a BlockPlaceEvent Listener?
            BlockChangePacket blockChangePacket = new BlockChangePacket(newBlockPosition, placedBlock.getState());
            playerManager.broadcastPacket(blockChangePacket);
        }
    }

    private void computeFacing(PropertiesBuilder builder, BlockType type, Player player) {
        if (!Arrays.stream(type.getProperties()).anyMatch(def -> def.getProperty() == FACING)) {
            return;
        }

        Rotation headRotation = player.getEntity().getPosition().getHeadRotation();
        builder.withProperty(FACING, getFacing(headRotation));
    }

    private void computeHalf(PropertiesBuilder builder, BlockType type, Face face) {
        if (!Arrays.stream(type.getProperties()).anyMatch(def -> def.getProperty() == HALF)) {
            return;
        }

        builder.withProperty(HALF, face == Face.BOTTOM ? BlockHalf.UPPER : BlockHalf.LOWER);
    }

    private Facing getFacing(Rotation headRotation) {
        float yaw = headRotation.getYaw();

        logger.debug("yaw at block place is {}", yaw);
        if (yaw < 45 || yaw >= 315) {
            return Facing.NORTH;
        } else if (yaw >= 45 && yaw < 135) {
            return Facing.EAST;
        } else if (yaw >= 135 && yaw < 225) {
            return Facing.SOUTH;
        } else if (yaw >= 225 && yaw < 315) {
            return Facing.WEST;
        } else {
            throw new IllegalStateException("a circle only has 360 degrees");
        }
    }
}
