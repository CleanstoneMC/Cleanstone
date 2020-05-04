package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound.place;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockProperties;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockType;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.vanilla.Facing;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.player.Player;

import java.util.Arrays;
import java.util.List;

@Component
public class BlockPlaceFacingProvider implements BlockPlacePropertyProvider<Facing> {
    private final List<BlockType> detectByBlockFace = Arrays.asList(
            VanillaBlockType.WALL_TORCH,
            VanillaBlockType.REDSTONE_WALL_TORCH,
            VanillaBlockType.LADDER
    );

    @Override
    public Property<Facing> getSupported() {
        return VanillaBlockProperties.FACING;
    }

    @Override
    public Facing computeProperty(BlockType blockType, Player player, PlayerBlockPlacementPacket packet) {
        if (detectByBlockFace.contains(blockType)) {
            // todo: get correct state when placing below block
            // todo: deny if not possible to place block
            return getBlockSiteFacing(packet);
        } else {
            float yaw = player.getEntity().getPosition().getHeadRotation().getYaw();
            return getPlayerDirectionFacing(yaw);
        }
    }

    private Facing getPlayerDirectionFacing(float yaw) {
        if (yaw < 45 || yaw >= 315) {
            return Facing.NORTH;
        } else if (yaw >= 45 && yaw < 135) {
            return Facing.EAST;
        } else if (yaw >= 135 && yaw < 225) {
            return Facing.SOUTH;
        } else if (yaw >= 225 && yaw < 315) {
            return Facing.WEST;
        } else {
            throw new IllegalStateException("a circle only has 360 degrees" );
        }
    }

    private Facing getBlockSiteFacing(PlayerBlockPlacementPacket packet) {
        switch (packet.getFace()) {
            case SOUTH:
                return Facing.NORTH;
            case EAST:
                return Facing.WEST;
            case NORTH:
                return Facing.SOUTH;
            case WEST:
                return Facing.EAST;
            default:
                throw new IllegalStateException("cant compute facing for " + packet.getFace());
        }
    }
}
