package rocks.cleanstone.endpoint.minecraft.java.net.listener.inbound.place;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.vanilla.HalfBlockPosition;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockProperties;
import rocks.cleanstone.player.Player;

@Component
public class BlockPlaceHalfBlockProvider implements BlockPlacePropertyProvider<HalfBlockPosition> {
    @Override
    public Property<HalfBlockPosition> getSupported() {
        return VanillaBlockProperties.HALF_BLOCK_POSITION;
    }

    @Override
    public HalfBlockPosition computeProperty(BlockType blockType, Player player, PlayerBlockPlacementPacket packet) {
        if (packet.getFace() == Face.BOTTOM) {
            return HalfBlockPosition.TOP;
        } else if (packet.getFace() == Face.TOP) {
            return HalfBlockPosition.BOTTOM;
        } else if (packet.getCursorPositionY() > 0.5) {
            return HalfBlockPosition.TOP;
        } else {
            return HalfBlockPosition.BOTTOM;
        }
    }

}
